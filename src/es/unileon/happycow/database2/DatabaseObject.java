package es.unileon.happycow.database2;

import es.unileon.happycow.database2.mapper.FarmMapper;
import es.unileon.happycow.database2.mapper.EvaluationMapper;
import es.unileon.happycow.database2.mapper.UserMapper;
import es.unileon.happycow.database2.mapper.CriterionMapper;
import es.unileon.happycow.database.*;
import es.unileon.happycow.database.concreteDatabase.InsertCriterion;
import es.unileon.happycow.database.prototype.CriterionPrototype;
import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.*;
import es.unileon.happycow.model.composite2.Criterion;
import es.unileon.happycow.model.composite2.Evaluation;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public abstract class DatabaseObject implements DataBaseOperations {

    protected static Connection conection = null;
    protected static ResultSet resultSet = null;

    protected User user;
    protected int nextIdValoration = -1;
    protected CriterionPrototype criterions;
    protected boolean criterionInitialized;

    protected enum TIPOSQL {

        CONSULTA, MODIFICACION
    };

    public DatabaseObject() {
        criterions = new CriterionPrototype();
        criterionInitialized = false;
    }

    @Override
    public void startTransaccion() throws SQLException {
        if (conection != null) {
            conection.setAutoCommit(false);
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
     *
     * @param sqlConsulta
     * @param modo
     * @throws Exception
     */
    protected void executeSQL(PreparedStatement sqlConsulta,
            TIPOSQL modo) throws Exception {
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
     * Mejorar cuando se tenga las sentencias de delete on cascade
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
                    + "  IDEVALUATION NUMBER(38, 0),"
                    + "  NOTA FLOAT(32),"
                    + "  PONDERACION FLOAT(32),"
                    + "  CONSTRAINT PK_VALORATION PRIMARY KEY (IDVALORATION, IDEVALUATION),"
                    + "  CONSTRAINT FK_VALORATION_EVALUATION FOREIGN KEY (IDEVALUATION)"
                    + "    REFERENCES EVALUATION(IDEVALUATION) ON DELETE CASCADE,"
                    + "  CONSTRAINT KEY_VALORATION_IDVALORATION UNIQUE (IDVALORATION));");
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
     *
     * @param toEncript
     * @return
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

    public boolean storeObject(EntityDB object) {
        boolean result = false;
        try {
            List<PreparedStatement> list = object.insertObject(conection);
            startTransaccion();
            for (PreparedStatement sql : list) {
                executeSQL(sql, TIPOSQL.MODIFICACION);
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

    public boolean updateObject(EntityDB object) {
        boolean result = false;
        try {
            List<PreparedStatement> list = object.updateObject(conection);
            startTransaccion();
            for (PreparedStatement sql : list) {
                executeSQL(sql, TIPOSQL.MODIFICACION);
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

    public boolean removeObject(EntityDB object) {
        boolean result = false;
        try {
            List<PreparedStatement> list = object.deleteObject(conection);
            startTransaccion();
            for (PreparedStatement sql : list) {
                executeSQL(sql, TIPOSQL.MODIFICACION);
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
        User userobject=getUser(new IdUser(user));
        boolean result=false;
        if(userobject==null){
            JOptionPane.showMessageDialog(null,
                        "El usuario no existe.", "Mal usuario",
                        JOptionPane.ERROR_MESSAGE);
        }else if(userobject.getPassword().compareTo(passwd)!=0){
            JOptionPane.showMessageDialog(null,
                            "Contraseña incorrecta.", "Contraseña incorrecta",
                            JOptionPane.ERROR_MESSAGE);
        }else{
            this.user=userobject;
            result=true;
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
            executeSQL(sql, TIPOSQL.CONSULTA);
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
            executeSQL(sql, TIPOSQL.CONSULTA);

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
        User userobject=getUser(new IdUser(usuario));
        return userobject!=null;
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
            executeSQL(sql, TIPOSQL.CONSULTA);
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

    protected boolean switchFarm(IdHandler id, boolean enabled) {
        boolean resultSwitch = false;

        try {
            PreparedStatement sql = conection.prepareStatement("UPDATE FARM SET "
                    + "ENABLED=? WHERE IDGRANJA=?");
            sql.setBoolean(1, enabled);
            sql.setInt(2, Integer.parseInt(id.toString()));
            executeSQL(sql, TIPOSQL.MODIFICACION);
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
            executeSQL(sql, TIPOSQL.CONSULTA);

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
            executeSQL(sql, TIPOSQL.CONSULTA);

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
            executeSQL(sql, TIPOSQL.CONSULTA);

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
    public int getNumberCow(IdHandler id) {
        int number = 0;
        try {
            PreparedStatement sql = FarmMapper.getNumberCows(id, conection);
            executeSQL(sql, TIPOSQL.CONSULTA);

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
    public LinkedList<IEvaluationModel> getListEvaluations(IdHandler idFarm) {
        //CREAR UNA CLASE LIST EVALUATIONS el cual controla también si es cargada o no
    }


    @Override
    public IEvaluationModel getEvaluation(IdHandler id) {

    }

    @Override
    public boolean saveEvaluation(IEvaluationModel evaluation) {
        EvaluationMapper map = new EvaluationMapper(evaluation);
        return storeObject(map);
    }

    @Override
    public boolean saveModifiedEvaluation(IEvaluationModel evaluation) {

    }

    /**
     * MEJORAR CON LA ELIMINACIÓN EN CASCADA DE LA BASE DE DATOS
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeEvaluation(Evaluation evaluation) {

    }

    //identificadores
    @Override
    public int nextIdFarm() {
        //cada granja tiene un identificador único
        int id = 1;
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT MAX(IDGRANJA) AS IDGRANJA FROM FARM");
            executeSQL(sql, TIPOSQL.CONSULTA);
            if (resultSet.next()) {
                id = resultSet.getInt("IDGRANJA") + 1;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
        return id;
    }

//    @Override
//    public int nextIdValoration() {
//        //cada id es único dentro de la evaluación
//    }

    @Override
    public int nextIdEvaluation() {
        //cada evaluación tiene un identificador único
        int id = 1;
        try {
            PreparedStatement sql = conection.prepareStatement("SELECT MAX(IDEVALUATION)AS IDEVALUATION FROM EVALUATION");
            executeSQL(sql, TIPOSQL.CONSULTA);
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
        boolean result= storeObject(map);
        if(result){
            criterions.add(criterion);
        }
        return result;
    }

    protected boolean isCriterionInitialized() {
        return criterionInitialized;
    }
    
    @Override
    public LinkedList<Criterion> getListCriterion() {
        if (!isCriterionInitialized()) {
        try {
            PreparedStatement sql = UserMapper.getAllObject(conection);
            executeSQL(sql, TIPOSQL.CONSULTA);

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
        Criterion cri=new Criterion(idCriterion, null);
        CriterionMapper map=new CriterionMapper(cri);
        boolean result=removeObject(map);
        if(result){
            criterions.remove(cri);
        }
        return result;
    }

    @Override
    public Criterion getCriterion(IdHandler id) {
        Criterion cri = null;
        try {
            PreparedStatement sql = UserMapper.getObject(conection, id);
            executeSQL(sql, TIPOSQL.CONSULTA);
            if (resultSet.next()) {
                cri = CriterionMapper.restoreObject(resultSet);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cri;
    }

    //funciones de los adjuntos para evaluaciones
    @Override
    public boolean saveFile(IdHandler handler, File file) {

    }

    @Override
    public List<String> getFileNames(IdHandler idHandler) {

    }

    //Metodo que devuelve null si no se ha encontrado el fichero para una evaluacion
    @Override
    public byte[] getFile(IdHandler idHandler, String name) {

    }

    @Override
    public void saveFileToTheSystem(byte[] arr, File file) {

    }

    @Override
    public LinkedList<FilesDB> getAllFiles() {

    }

    public boolean saveFiles(LinkedList<FilesDB> list) {

    }
    public float getPonderacionCategoria(IdHandler idEvaluation, IdHandler idCategoria) {

    }
    
}
