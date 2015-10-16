package es.unileon.happycow.database.concreteDatabase;

import es.unileon.happycow.database.DataBaseOperations;
import es.unileon.happycow.database.EntityDB;
import es.unileon.happycow.database.backup.FilesDB;
import es.unileon.happycow.database.backup.PonderationDB;
import es.unileon.happycow.database.backup.ValorationDB;
import es.unileon.happycow.database.prototype.InsertCriterion;
import es.unileon.happycow.database.mapper.FarmMapper;
import es.unileon.happycow.database.mapper.EvaluationMapper;
import es.unileon.happycow.database.mapper.UserMapper;
import es.unileon.happycow.database.mapper.CriterionMapper;
import es.unileon.happycow.database.mapper.InformationEvaluationMapper;
import es.unileon.happycow.database.prototype.CriterionPrototype;
import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.*;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Main class for a database
 *
 * @author dorian
 */
public abstract class DatabaseObject implements DataBaseOperations {

    /**
     * Conection
     */
    protected static Connection conection = null;
    /**
     * Resultset
     */
    protected static ResultSet resultSet = null;
    /**
     * User logued
     */
    protected User user;
    /**
     * Count of id valorations to give
     */
    protected int nextIdValoration = -1;
    /**
     * Prototype of criterions
     */
    protected CriterionPrototype criterions;
    /**
     * Are the prototypes of criterion initialized?
     */
    protected boolean criterionInitialized;

    protected enum TYPESQL {

        CONSULTA, MODIFICACION
    };

    /**
     * Constructor
     */
    public DatabaseObject() {
        criterions = new CriterionPrototype();
        criterionInitialized = false;
    }

    @Override
    public void startTransaccion() throws SQLException {
        if (conection != null) {
            if (conection.getAutoCommit()) {
                conection.setAutoCommit(false);
            }
        }
    }

    @Override
    public void rollback() {
        try {
            if (conection != null) {
                conection.rollback();
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    @Override
    public void commit() {
        try {
            if (conection != null) {
                conection.commit();
                conection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Execute a sql
     *
     * @param sqlConsulta the sql statement
     * @param modo consulta o modificación
     * @throws Exception
     */
    protected void executeSQL(PreparedStatement sqlConsulta,
            TYPESQL modo) throws Exception {
        try {
            switch (modo) {
                case CONSULTA:
                    resultSet = sqlConsulta.executeQuery();
                    break;
                case MODIFICACION:
                    sqlConsulta.executeUpdate();
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public boolean closeDB() {
        try {
            if (conection != null) {
                conection.close();
            }
            System.out.println("Base de datos cerrada");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * TODO:Mejorar cuando se tenga las sentencias de delete on cascade
     *
     * @return
     */
    @Override
    public boolean clearDB() {
        boolean correct = true;
        try {
            startTransaccion();

            StringBuilder borrar = new StringBuilder();
            borrar.append("delete from valoration");
            PreparedStatement sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from ponderacioncriterio");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from ponderacionCategoria");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from evaluation");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from farm");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from usuario");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            borrar = new StringBuilder();
            borrar.append("delete from criterion");
            sql = conection.prepareStatement(borrar.toString());
            sql.execute();

            commit();

        } catch (SQLException ex) {
            rollback();
            correct = false;
        }
        return correct;
    }

    /**
     * Crea las tablas si no existen
     *
     * @throws Exception excepción con el mensaje de error
     */
    protected static void crearBaseDatos() throws Exception {
        System.out.println("* Creating table...");

        //tabla con los usuarios y las contraseñas
        PreparedStatement sql = conection.prepareStatement(""
                + "CREATE TABLE IF NOT EXISTS USUARIO ("
                + "  NOMBREUSUARIO NVARCHAR2(50) UNIQUE,"
                + "  CONTRASENIA NVARCHAR2(50),"
                + "  ROL NVARCHAR2(50),"
                + "  CONSTRAINT PK_USUARIO PRIMARY KEY (NOMBREUSUARIO));");
        try {
            sql.executeUpdate();

            //tablas con los datos de los códigos y su usuario
            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS FARM ("
                    + "  IDGRANJA NUMBER(38, 0),"
                    + "  NOMBREGRANJA NVARCHAR2(50),"
                    + "  NOMBREUSUARIO NVARCHAR2(50),"
                    + "  DIRECCION NVARCHAR2(100),"
                    + "  NOMBREGANADERO NVARCHAR2(100),"
                    + "  NUMEROVACAS NUMBER(38, 0),"
                    + "  IDENTIFICADORGRANJA NVARCHAR2(50),"
                    + "  OTROSDATOS NVARCHAR2(350),"
                    + "  DNIGANADERO NVARCHAR2(50),"
                    + "  ENABLED BOOL NOT NULL  DEFAULT TRUE,"
                    + "  CONSTRAINT PK_FARM PRIMARY KEY (IDGRANJA),"
                    + "  CONSTRAINT FK_FARM_USUARIO_NOMBREUSUARIO FOREIGN KEY (NOMBREUSUARIO)"
                    + "  REFERENCES USUARIO(NOMBREUSUARIO) ON DELETE CASCADE,"
                    + "  CONSTRAINT KEY_FARM_IDGRANJA UNIQUE (IDGRANJA));");

            sql.executeUpdate();

            //tablas con registro de los tipos de codigos usados y su usuario
            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS EVALUATION ("
                    + "  IDEVALUATION NUMBER(38, 0),"
                    + "  IDGRANJA NUMBER(38, 0),"
                    + "  USUARIO NVARCHAR2(50),"
                    + "  NOTA FLOAT(32),"
                    + "  ALIMENTACION FLOAT(32),"
                    + "  SALUD FLOAT(32),"
                    + "  COMFORT FLOAT(32),"
                    + "  COMPORTAMIENTO FLOAT(32),"
                    + "  FECHA DATE,"
                    + "  NUMEROVACAS NUMBER(38,0),"
                    + "  CONSTRAINT PK_EVALUATION PRIMARY KEY (IDEVALUATION),"
                    + "CONSTRAINT FK_EVALUATION_NOMBREUSUARIO FOREIGN KEY (USUARIO) "
                    + "REFERENCES USUARIO(NOMBREUSUARIO) ON DELETE CASCADE,"
                    + "  CONSTRAINT KEY_EVALUATION_IDEVALUACION UNIQUE (IDEVALUATION));");
            sql.executeUpdate();

            //tablas con registro de los tipos de codigos usados y su usuario
            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS CRITERION ("
                    + "  NOMBRECRITERIO NVARCHAR2(100) UNIQUE,"
                    + "  DESCRIPCION NVARCHAR2(300),"
                    + "  HELP CLOB,"
                    + "  CATEGORIA NVARCHAR2(50),"
                    + "  CONSTRAINT PK_CRITERION PRIMARY KEY (NOMBRECRITERIO));");
            sql.executeUpdate();

            //tablas con registro de los tipos de codigos usados y su usuario
            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS VALORATION ("
                    + "  IDVALORATION NUMBER(38, 0),"
                    + "  NOMBRECRITERIO NVARCHAR2(100),"
                    + "  IDEVALUATION NUMBER(38, 0),"
                    + "  NOTA FLOAT(32),"
                    + "  PONDERACION FLOAT(32),"
                    + "  CONSTRAINT PK_VALORATION PRIMARY KEY (IDVALORATION, IDEVALUATION),"
                    + "  CONSTRAINT FK_VALORATION_CRITERION FOREIGN KEY (NOMBRECRITERIO)"
                    + "    REFERENCES CRITERION(NOMBRECRITERIO) ON DELETE CASCADE,"
                    + "  CONSTRAINT FK_VALORATION_EVALUATION FOREIGN KEY (IDEVALUATION)"
                    + "    REFERENCES EVALUATION(IDEVALUATION) ON DELETE CASCADE);");
            sql.executeUpdate();

            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS PONDERACIONCATEGORIA "
                    + "(IDEVALUATION NUMBER(38, 0), "
                    + "CATEGORIA NVARCHAR2(50),  "
                    + "PONDERACION FLOAT,  "
                    + "CONSTRAINT PK_PONDERACIONCATEGORIA PRIMARY KEY (IDEVALUATION, CATEGORIA))");
            sql.executeUpdate();

            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS PONDERACIONCRITERIO (  "
                    + "IDEVALUATION NUMBER(38, 0), "
                    + "NOMBRECRITERIO NVARCHAR2(50),  "
                    + "PONDERACION FLOAT(126),  "
                    + "CONSTRAINT PK_PONDERACIONCRITERIO PRIMARY KEY (IDEVALUATION, NOMBRECRITERIO))");
            sql.executeUpdate();

            sql = conection.prepareStatement("CREATE TABLE IF NOT EXISTS FILES ( "
                    + "IDEVALUATION NUMBER(38, 0), "
                    + "FILE BLOB, "
                    + "FILENAME NVARCHAR2(100), "
                    + "CONSTRAINT PK_FILES PRIMARY KEY (IDEVALUATION, FILENAME))");
            /**
             * TODO !!! Se ha quitado la constrain porque cuando la evaluación
             * todavía no se ha guardado al guardar el fichero no tiene el id e
             * incumple la foreign key + "CONSTRAINT FK_FILES_EVALUATION FOREIGN
             * KEY (IDEVALUATION) " + "REFERENCES EVALUATION(IDEVALUATION) ON
             * DELETE CASCADE);
             */

            sql.executeUpdate();

            System.out.println("* Tables created");
            try {
                sql = conection.prepareStatement("SELECT COUNT(*) FROM CRITERION");
                resultSet = sql.executeQuery();

                if (resultSet.next()) {
                    int number = resultSet.getInt(1);

                    if (number <= 0) {
                        InsertCriterion fill = new InsertCriterion();
                        LinkedList<String> sentences = fill.execute();
                        System.out.println("Sentencias de criterios recogida: " + sentences.size());
                        if (sentences != null) {
                            for (String sentence : sentences) {
                                sql = conection.prepareStatement(sentence);
                                sql.executeUpdate();
                            }
                        } else {
                            System.out.println("No tenemos los criterios");
                        }
                    } else {
                        System.out.println("Error, metiendo los criterios ya están metidos");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creando tablas: ".concat(e.toString()));
            throw new Exception("Error creando las tablas de la base de datos");
        }
    }

    /**
     * Encript a string
     *
     * @param toEncript
     * @return the string encrypted
     */
    public static String encript(String toEncript) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return new String(md.digest(toEncript.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Store an object to the database
     *
     * @param object
     * @return true if stored
     */
    public boolean storeObject(EntityDB object) {
        boolean result = false;
        try {
            //start a transaction
            startTransaccion();
            //get the list of statements needed
            List<PreparedStatement> list = object.insertObject(conection);
            //execute all of them
            for (PreparedStatement sql : list) {
                executeSQL(sql, TYPESQL.MODIFICACION);
            }
            //commit changes
            commit();
            result = true;
        } catch (SQLException ex) {
            rollback();
        } catch (Exception ex) {
            rollback();
        }
        return result;
    }

    /**
     * Update a stored object in database
     *
     * @param object
     * @return
     */
    public boolean updateObject(EntityDB object) {
        boolean result = false;
        try {
            //start the transaction
            startTransaccion();
            //get the statements needed
            List<PreparedStatement> list = object.updateObject(conection);
            for (PreparedStatement sql : list) {
                executeSQL(sql, TYPESQL.MODIFICACION);
            }
            commit();
            result = true;
        } catch (SQLException ex) {
            rollback();
        } catch (Exception ex) {
            rollback();
        }
        return result;
    }

    /**
     * Delete an object in database
     *
     * @param object
     * @return
     */
    public boolean removeObject(EntityDB object) {
        boolean result = false;
        try {
            //get the list of statement needed
            List<PreparedStatement> list = object.deleteObject(conection);
            startTransaccion();
            for (PreparedStatement sql : list) {
                executeSQL(sql, TYPESQL.MODIFICACION);
            }
            commit();
            result = true;
        } catch (SQLException ex) {
            rollback();
        } catch (Exception ex) {
            rollback();
        }
        return result;
    }

    @Override
    public boolean login(String user, String passwd) {
        User userobject = getUser(new IdUser(user));
        boolean result = false;
        if (userobject == null) {
            JOptionPane.showMessageDialog(null,
                    "El usuario no existe.", "Mal usuario",
                    JOptionPane.ERROR_MESSAGE);
        } else if (userobject.getPassword().compareTo(passwd) != 0) {
            JOptionPane.showMessageDialog(null,
                    "Contraseña incorrecta.", "Contraseña incorrecta",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            this.user = userobject;
            result = true;
        }

        return result;

    }

    @Override
    public void logout() {
        user = null;
    }

    //usuarios
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public User getUser(IdHandler id) {
        User theUser = null;
        try {
            PreparedStatement sql = UserMapper.getObject(conection, id);
            executeSQL(sql, TYPESQL.CONSULTA);
            if (resultSet.next()) {
                theUser = UserMapper.restoreObject(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theUser;
    }

    @Override
    public LinkedList<User> getListUsers() {
        LinkedList<User> listUsers = new LinkedList<>();
        try {
            PreparedStatement sql = UserMapper.getAllObject(conection);
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                listUsers.add(UserMapper.restoreObject(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }

    @Override
    public boolean newUser(User user) {
        UserMapper map = new UserMapper(user);
        return storeObject(map);
    }

    @Override
    public boolean removeUser(User user) {
        UserMapper map = new UserMapper(user);
        return removeObject(map);

    }

    /**
     * Compruebo si existe el usuario en la base de datos
     *
     * @param usuario
     * @return true si existe el usuario, false si no hay results
     * @throws Exception cualquier error que pueda surgir en la conexión
     */
    protected boolean existUser(String usuario) throws Exception {
        User userobject = getUser(new IdUser(usuario));
        return userobject != null;
    }

    @Override
    public boolean updateUser(User user) {
        UserMapper map = new UserMapper(user);
        return updateObject(map);
    }

    //granjas
    @Override
    public LinkedList<Farm> getListFarms() {
        return getListFarms(user.getId());
    }

    @Override
    public Farm getFarm(IdHandler id) {
        Farm farm = null;
        try {
            PreparedStatement sql = FarmMapper.getObject(id, conection);
            executeSQL(sql, TYPESQL.CONSULTA);
            if (resultSet.next()) {
                farm = FarmMapper.restoreObject(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return farm;
    }

    @Override
    public boolean newFarm(Farm farm) {
        FarmMapper map = new FarmMapper(farm);
        return storeObject(map);
    }

    @Override
    public boolean enableFarm(IdHandler id) {
        return switchFarm(id, true);
    }

    @Override
    public boolean disableFarm(IdHandler id) {
        return switchFarm(id, false);
    }

    /**
     * Change the enable/disable state of a farm
     *
     * @param id
     * @param enabled
     * @return
     */
    protected boolean switchFarm(IdHandler id, boolean enabled) {
        boolean resultSwitch = false;

        try {
            PreparedStatement sql = conection.prepareStatement("UPDATE FARM SET "
                    + "ENABLED=? WHERE IDGRANJA=?");
            sql.setBoolean(1, enabled);
            sql.setInt(2, Integer.parseInt(id.getValue()));
            executeSQL(sql, TYPESQL.MODIFICACION);
            resultSwitch = true;
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return resultSwitch;
    }

    @Override
    public boolean removeFarm(Farm farm) {
        FarmMapper map = new FarmMapper(farm);
        return removeObject(map);
    }

    @Override
    public boolean updateFarm(Farm farm) {
        FarmMapper map = new FarmMapper(farm);
        return updateObject(map);
    }

    /**
     * Comprobar si no hace falta un inner join
     *
     * @param idUser
     * @return
     */
    @Override
    public LinkedList<Farm> getListFarms(IdHandler idUser) {
        LinkedList<Farm> listFarms = new LinkedList<>();
        try {
            PreparedStatement sql = FarmMapper.getAllObject(idUser, conection);
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                listFarms.add(FarmMapper.restoreObject(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFarms;
    }

    @Override
    public LinkedList<Farm> getDisabledFarms() {
        LinkedList<Farm> listFarms = new LinkedList<>();
        try {
            PreparedStatement sql = FarmMapper.getDisabledFarms(user.getId(), conection);
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                listFarms.add(FarmMapper.restoreObject(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFarms;
    }

    @Override
    public int getNumberCowEvaluation(IdHandler evaluation) {
        int number = 0;
        try {
            PreparedStatement sql = EvaluationMapper.getNumberCows(conection, evaluation);
            executeSQL(sql, TYPESQL.CONSULTA);

            if (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    @Override
    public int getNumberCow(IdHandler idFarm) {
        int number = 0;
        try {
            PreparedStatement sql = FarmMapper.getNumberCows(idFarm, conection);
            executeSQL(sql, TYPESQL.CONSULTA);

            if (resultSet.next()) {
                number = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number;
    }

    //evaluaciones
    @Override
    public LinkedList<InformationEvaluation> getListEvaluations(IdHandler idFarm) {
        //TODO: (Necesario?!?!) CREAR UNA CLASE LIST EVALUATIONS el cual controla también si es cargada o no
        LinkedList<InformationEvaluation> lista = null;
        try {
            PreparedStatement sql = InformationEvaluationMapper.getListEvaluations(conection, idFarm);

            executeSQL(sql, TYPESQL.CONSULTA);
            lista = new LinkedList<>();
            while (resultSet.next()) {
                lista.add(InformationEvaluationMapper.restoreObject(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    @Override
    public Evaluation getEvaluation(IdHandler id) {
        Evaluation evaluation = null;
        try {
            PreparedStatement sql = EvaluationMapper.getObject(conection, id);
            executeSQL(sql, TYPESQL.CONSULTA);
            evaluation = EvaluationMapper.restoreObject(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evaluation;
    }

    @Override
    public boolean saveEvaluation(IEvaluationModel evaluation) {
        EvaluationMapper map = new EvaluationMapper(evaluation);
        return storeObject(map);
    }

    @Override
    public boolean updateEvaluation(IEvaluationModel evaluation) {
        EvaluationMapper map=new EvaluationMapper(evaluation);
        return updateObject(map);
    }

    /**
     * TODO: MEJORAR CON LA ELIMINACIÓN EN CASCADA DE LA BASE DE DATOS
     *
     * @param id
     * @param id
     * @return
     */
    @Override
    public boolean removeEvaluation(IdHandler id) {
        //TODO mejorar con la eliminación en cascada de la base de datos
        EvaluationMapper map=new EvaluationMapper(id);
        return removeObject(map);
    }

    //identificadores
    @Override
    public int nextIdFarm() {
        //cada granja tiene un identificador único
        int id = 1;
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT MAX(IDGRANJA) AS IDGRANJA FROM FARM");
            executeSQL(sql, TYPESQL.CONSULTA);
            if (resultSet.next()) {
                id = resultSet.getInt("IDGRANJA") + 1;
            }

        } catch (SQLException ex) {
            System.out.println("error!!");
        } catch (Exception ex) {
            System.out.println("error!");
        }
        return id;
    }

    @Override
    public int nextIdEvaluation() {
        //cada evaluación tiene un identificador único
        int id = 1;
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT MAX(IDEVALUATION)AS IDEVALUATION FROM EVALUATION");
            executeSQL(sql, TYPESQL.CONSULTA);
            if (resultSet.next()) {
                id = resultSet.getInt("idevaluation") + 1;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return id;
    }

    //criterios
    @Override
    public boolean newCriterion(Criterion criterion) {
        CriterionMapper map = new CriterionMapper(criterion);
        boolean result = storeObject(map);
        if (result) {
            criterions.add(criterion);
        }
        return result;
    }

    /**
     * Check if criterions are initialized
     *
     * @return true if are initialized, false otherwise
     */
    protected boolean isCriterionInitialized() {
        return criterionInitialized;
    }

    @Override
    public LinkedList<Criterion> getListCriterion() {
        if (!isCriterionInitialized()) {
            try {
                PreparedStatement sql = CriterionMapper.getAllObject(conection);
                executeSQL(sql, TYPESQL.CONSULTA);

                while (resultSet.next()) {
                    criterions.add(CriterionMapper.restoreObject(resultSet));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return criterions.getList();
    }

    @Override
    public boolean removeCriterion(IdHandler idCriterion) {
        Criterion cri = new Criterion(idCriterion, null);
        CriterionMapper map = new CriterionMapper(cri);
        boolean result = removeObject(map);
        if (result) {
            criterions.remove(cri);
        }
        return result;
    }

    @Override
    public Criterion getCriterion(IdHandler id) {
        if (criterions == null) {
            getListCriterion();
        }
        return criterions.clone(id.getValue());
    }

    //funciones de los adjuntos para evaluaciones
    @Override
    public boolean saveFile(IdHandler handler, File file) {
        byte[] arr = getByteArray(file);
        return saveFile(handler, arr, file.getName());
    }

    /**
     * Save the file in the database
     *
     * @param handler id of evaluation
     * @param arr data file
     * @param name of the file
     * @return true if stored correctly, false otherwise
     */
    private boolean saveFile(IdHandler handler, byte[] arr, String name) {
        boolean result = true;

        try {
            PreparedStatement sql = conection.prepareStatement("INSERT INTO FILES (IDEVALUATION, FILE,  FILENAME) VALUES(?,?, ?)");

            sql.setString(1, handler.getValue());
            sql.setBytes(2, arr);
            sql.setString(3, name);
            executeSQL(sql, TYPESQL.MODIFICACION);

        } catch (Exception ex) {
            result = false;
            ex.printStackTrace();
        }

        return result;

    }

    @Override
    public List<String> getFileNames(IdHandler idHandler) {
        List<String> fileNamesList = new ArrayList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM FILES WHERE IDEVALUATION=?");
            sql.setString(1, idHandler.getValue());

            executeSQL(sql, TYPESQL.CONSULTA);
            while (resultSet.next()) {
                fileNamesList.add(resultSet.getString("FILENAME"));
            }

            System.out.println(fileNamesList.size());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return fileNamesList;
    }

    //Metodo que devuelve null si no se ha encontrado el fichero para una evaluacion
    @Override
    public byte[] getFile(IdHandler idHandler, String name) {
        byte[] fileBytes = null;
        boolean found = false;
        try {

            PreparedStatement sql = conection.prepareStatement("SELECT * FROM FILES WHERE IDEVALUATION=? AND FILENAME=?");
            sql.setString(1, idHandler.getValue());
            sql.setString(2, name);

            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next() && !found) {
                fileBytes = resultSet.getBytes("FILE");
                found = true;
            }

        } catch (Exception ex) {

        }

        return fileBytes;
    }

    @Override
    public void saveFileToTheSystem(byte[] arr, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(arr);
            fos.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Metodos auxiliares para los blobs
    private byte[] getByteArray(File file) {

        FileInputStream fileInputStream = null;

        byte[] bFile = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bFile;
    }

    @Override
    public boolean removeFile(IdHandler idEvaluation, String name) {
        boolean resultRemove = false;
        try {
            StringBuilder borrar = new StringBuilder();
            borrar.append("delete from files where idevaluation = " + idEvaluation.getValue() + " and filename='" + name + "'");
            PreparedStatement preparedStatement = conection.prepareStatement(borrar.toString());
            preparedStatement.execute();

            resultRemove = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return resultRemove;
    }

    //Backup
    @Override
    public LinkedList<Farm> getAllFarms() {
        LinkedList<Farm> farms = new LinkedList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM FARM");
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                farms.add(FarmMapper.restoreObject(resultSet));
            }
        } catch (Exception e) {
        }
        return farms;
    }

    @Override
    public LinkedList<FilesDB> getAllFiles() {
        LinkedList<FilesDB> list = new LinkedList<>();
        byte[] fileBytes = null;
        try {

            PreparedStatement sql = conection.prepareStatement("SELECT * FROM FILES");
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                fileBytes = resultSet.getBytes("FILE");
                int idEvaluation = resultSet.getInt("IDEVALUATION");
                String name = resultSet.getString("FILENAME");
                list.add(new FilesDB(idEvaluation, name, fileBytes));
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return list;
    }

    @Override
    public LinkedList<InformationEvaluation> getAllInformationEvaluations() {
        LinkedList<InformationEvaluation> model = new LinkedList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM EVALUATION");
            executeSQL(sql, TYPESQL.CONSULTA);

            while (resultSet.next()) {
                InformationEvaluation info = new InformationEvaluation(
                        new IdEvaluation(resultSet.getInt("IDEVALUATION")),
                        new IdFarm(resultSet.getInt("IDGRANJA")),
                        new IdUser(resultSet.getString("USUARIO")),
                        resultSet.getInt("NOTA"),
                        resultSet.getInt("ALIMENTACION"),
                        resultSet.getInt("SALUD"),
                        resultSet.getInt("COMFORT"),
                        resultSet.getInt("COMPORTAMIENTO"),
                        resultSet.getDate("FECHA"),
                        resultSet.getInt("NUMEROVACAS"));
                model.add(info);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return model;
    }

    @Override
    public LinkedList<PonderationDB> getCategoryPonderation() {
        LinkedList<PonderationDB> lista = new LinkedList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM PONDERACIONCATEGORIA");
            executeSQL(sql, TYPESQL.CONSULTA);
            while (resultSet.next()) {
                int idEval = resultSet.getInt("idevaluation");
                String categoria = resultSet.getString("categoria");
                float b = resultSet.getFloat("ponderacion");
                PonderationDB pon = new PonderationDB(idEval, categoria, b);

                lista.add(pon);
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return lista;
    }

    @Override
    public LinkedList<PonderationDB> getCriterionPonderation() {
        LinkedList<PonderationDB> lista = new LinkedList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM PONDERACIONCRITERIO");
            executeSQL(sql, TYPESQL.CONSULTA);
            while (resultSet.next()) {
                int idEval = resultSet.getInt("idevaluation");
                String categoria = resultSet.getString("nombrecriterio");
                float b = resultSet.getFloat("ponderacion");
                PonderationDB pon = new PonderationDB(idEval, categoria, b);
                lista.add(pon);
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        }

        return lista;
    }

    @Override
    public LinkedList<ValorationDB> getAllValorations() {
        LinkedList<ValorationDB> valores = new LinkedList<>();
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT * FROM VALORATION");
            executeSQL(sql, TYPESQL.CONSULTA);
            while (resultSet.next()) {
                int numValoration = resultSet.getInt("idvaloration");
                IdHandler idValoration = new IdEvaluation(numValoration);
                float nota = resultSet.getFloat("nota");
                ValorationDB val = new ValorationDB(idValoration,
                        new IdEvaluation(resultSet.getInt("IDEVALUATION")),
                        new IdCriterion(resultSet.getString("NOMBRECRITERIO")),
                        nota);
                valores.add(val);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return valores;
    }

    @Override
    public boolean saveInformationEvaluation(InformationEvaluation info) {
        try {
            InformationEvaluationMapper map = new InformationEvaluationMapper(info);
            List<PreparedStatement> list = map.insertObject(conection);
            for (PreparedStatement sql : list) {
                executeSQL(sql, TYPESQL.MODIFICACION);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean saveFiles(LinkedList<FilesDB> list) {
        for (FilesDB file : list) {
            saveFile(new IdEvaluation(file.getId()), file.getFile(), file.getFilename());
        }
        return true;
    }

    @Override
    public boolean saveCategoryPonderation(LinkedList<PonderationDB> list) {
        boolean correct = true;
        for (PonderationDB ponderationDB : list) {
            correct = correct && newPonderacionCategoria(
                    ponderationDB.getId(), ponderationDB.getName(), ponderationDB.getPonderation());
        }
        return correct;
    }

    /**
     * Store a category's ponderation
     *
     * @param idEvaluation
     * @param idCategoria
     * @param ponderacion
     * @return
     */
    private boolean newPonderacionCategoria(int idEvaluation, String idCategoria, float ponderacion) {
        boolean nuevo = false;
        try {
            /**
             * SetFloat del sql tiene errores de forma que si es 0.5, aparece
             * 0.50000000000000342 y similares por ello se setea directamente en
             * el sql
             */
            PreparedStatement sql = conection.prepareStatement("INSERT INTO PONDERACIONCATEGORIA"
                    + "(IDEVALUATION,CATEGORIA,PONDERACION) VALUES(?,?,'" + ponderacion + "')");
            sql.setInt(1, idEvaluation);
            sql.setString(2, idCategoria);
            executeSQL(sql, TYPESQL.MODIFICACION);
            nuevo = true;
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return nuevo;
    }

    @Override
    public boolean saveCriterionPonderation(LinkedList<PonderationDB> list) {
        boolean correct = true;
        for (PonderationDB ponderationDB : list) {
            correct = correct && newPonderacionCriterio(
                    ponderationDB.getId(), ponderationDB.getName(), ponderationDB.getPonderation());
        }
        return correct;
    }

    /**
     * Store a criterion's ponderation
     *
     * @param idEvaluation
     * @param idCriterio
     * @param ponderacion
     * @return
     */
    private boolean newPonderacionCriterio(int idEvaluation, String idCriterio, float ponderacion) {
        boolean nuevo = false;
        try {
            /**
             * SetFloat del sql tiene errores de forma que si es 0.5, aparece
             * 0.50000000000000342 y similares por ello se setea directamente en
             * el sql
             */
            PreparedStatement sql = conection.prepareStatement("INSERT INTO PONDERACIONCRITERIO"
                    + "(IDEVALUATION, NOMBRECRITERIO, PONDERACION) VALUES(?,?,'" + ponderacion + "')");
            sql.setInt(1, idEvaluation);
            sql.setString(2, idCriterio.toString());
            //sql.setFloat(3, ponderacion);
            executeSQL(sql, TYPESQL.MODIFICACION);
            nuevo = true;
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return nuevo;

    }

    @Override
    public boolean saveValoration(ValorationDB valoration) {
        boolean resultSave = false;
        try {
            PreparedStatement sql = conection.prepareStatement("INSERT INTO VALORATION"
                    + "(IDVALORATION, NOMBRECRITERIO,IDEVALUATION,NOTA,PONDERACION)"
                    + " VALUES(?,?,?,?,?)");
            sql.setInt(1, Integer.parseInt(valoration.getIdValoration().getValue()));
            sql.setString(2, valoration.getIdCriterion().getValue());
            sql.setInt(3, Integer.parseInt(valoration.getIdEvaluation().getValue()));
            sql.setFloat(4, valoration.getNota());
            sql.setFloat(5, valoration.getWeighing());

            executeSQL(sql, TYPESQL.MODIFICACION);
            resultSave = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultSave;
    }

    @Override
    public boolean newCriterionBackup(Criterion criterion) {
        try {
            CriterionMapper map = new CriterionMapper(criterion);
            List<PreparedStatement> list = map.insertObject(conection);
            for (PreparedStatement sql : list) {
                executeSQL(sql, TYPESQL.MODIFICACION);
            }
            criterions.add(criterion);

            return true;

        } catch (Exception ex) {
            return false;
        }
    }
}
