package es.unileon.happycow.database.concreteDatabase;

import es.unileon.happycow.database.*;
import es.unileon.happycow.database.prototype.CriterionPrototype;
import es.unileon.happycow.handler.*;
import es.unileon.happycow.model.*;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.facade.EvaluationModel;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public abstract class DefaultDatabase implements DataBaseOperations {

    protected static Connection conection = null;
    protected static PreparedStatement sql = null;
    protected static ResultSet result = null;

    protected User user;
    protected int nextIdValoration = -1;
    protected CriterionPrototype criterions;
    protected boolean criterionInitialized;
    
    protected Farm actualFarm;

    protected enum TIPOSQL {

        CONSULTA, MODIFICACION
    };

    public DefaultDatabase() {
        criterions=new CriterionPrototype();
        criterionInitialized=false;
        actualFarm=null;
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
                    result = sqlConsulta.executeQuery();
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
            conection.close();
            System.out.println("Base de datos cerrada");
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Mejorar cuando se tenga las sentencias de delete on cascade
     * @return 
     */
    @Override
    public boolean clearDB() {
        boolean correct = true;
        try {
            startTransaccion();

            StringBuilder borrar = new StringBuilder();
            borrar.append("delete from valoration");
            sql = conection.prepareStatement(borrar.toString());
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
        sql = conection.prepareStatement(""
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
                    + "  CONSTRAINT PK_FARM PRIMARY KEY (IDGRANJA, NOMBREUSUARIO),"
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
                    + "  CONSTRAINT PK_EVALUATION PRIMARY KEY (IDEVALUATION, USUARIO),"
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
                    + "  CONSTRAINT PK_VALORATION PRIMARY KEY (IDVALORATION, NOMBRECRITERIO, IDEVALUATION),"
                    + "  CONSTRAINT FK_VALORATION_CRITERION FOREIGN KEY (NOMBRECRITERIO)"
                    + "    REFERENCES CRITERION(NOMBRECRITERIO) ON DELETE CASCADE,"
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

            System.out.println("* Tables created");
            
            InsertCriterion fill=new InsertCriterion();
            fill.execute(sql, conection);
            sql.executeUpdate();
        } catch (SQLException e) {
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

    @Override
    public boolean login(String user, String passwd) {
        boolean resultLogin = false;
        passwd = encript(passwd);
        try {
            //recojo el usuario con su contraseña
            sql = conection.prepareStatement("SELECT * FROM USUARIO WHERE NOMBREUSUARIO=?");
            sql.setString(1, user.toString());

            executeSQL(sql, TIPOSQL.CONSULTA);
            if (result.next()) {
                //compruebo la contraseña
                if (!result.getString("CONTRASENIA").equals(passwd)) {
                    JOptionPane.showMessageDialog(null,
                            "Contraseña incorrecta.", "Contraseña incorrecta",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    this.user = new User(user, passwd, result.getString("ROL"), true);
                    resultLogin = true;
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "El usuario no existe.", "Mal usuario",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error fatal comprobando el usuario.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return resultLogin;
    }

    @Override
    public void logout() {
        user = null;
    }

    public float getPonderacionCategoria(IdHandler idEvaluation, IdHandler idCategoria) {

        float weighting = 0;
        try {
            sql = conection.prepareStatement("SELECT PONDERACION FROM PONDERACIONCATEGORIA WHERE IDEVALUATION=? AND CATEGORIA=?");
            sql.setInt(1, Integer.parseInt(idEvaluation.toString()));
            sql.setString(2, idCategoria.toString());
            System.out.println("MIRAR AQUÍ!!!!!!!! " + idCategoria.toString());
            executeSQL(sql, TIPOSQL.CONSULTA);

            if (result.next()) {
                weighting = result.getFloat("PONDERACION");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return weighting;
    }

    
    
    //usuarios
    @Override
    public User getUser() {
        return user;
    }

    @Override
    public User getUser(IdHandler id) {
        try {
            //recojo el usuario con su contraseña
            sql = conection.prepareStatement("SELECT * FROM USUARIO WHERE NOMBREUSUARIO=?");
            sql.setString(1, id.toString());

            executeSQL(sql, TIPOSQL.CONSULTA);
            if (result.next()) {
                return new User(id.toString(), result.getString("CONTRASENIA"), result.getString("ROL"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error fatal comprobando el usuario.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public LinkedList<User> getListUsers() {
        LinkedList<User> listUsers = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM USUARIO");
            executeSQL(sql, TIPOSQL.CONSULTA);

            while (result.next()) {
                String a = result.getString("nombreusuario");
                String b = result.getString("contrasenia");
                String c = result.getString("rol");
                Rol rol;
                if (c.equalsIgnoreCase("veterinario")) {
                    rol = Rol.VETERINARIO;
                } else {
                    rol = Rol.ADMINISTRADOR;
                }

                IdHandler idUser = new IdUser(a);
                User actual = new User(idUser, b, rol, true);
                listUsers.add(actual);

            }
            result.next();

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsers;
    }

    @Override
    public boolean newUser(User user) {
        try {
            //preparo la sentencia
            sql = conection.prepareStatement("INSERT INTO USUARIO VALUES(?,?,?)");
            sql.setString(1, user.getName());
            sql.setString(2, user.getPassword());
            sql.setString(3, user.getStringRol());

            //guardo el usuario
            executeSQL(sql, TIPOSQL.MODIFICACION);
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println(e.getErrorCode());
            JOptionPane.showMessageDialog(null,
                    "Error con la base de datos, pruebe de nuevo", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            System.out.println("Error creando usuario: ".concat(e.toString()));
            try {
                if (existUser(user.getName())) {
                    JOptionPane.showMessageDialog(null,
                            "Ya existe ese usuario", "Usuario existente",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error desconocido de la base de datos", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Error desconocido de la base de datos:\n" + ex.toString(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    @Override
    public boolean removeUser(User user) {
        boolean correcto = false;
        try {
            //preparo la sentencia
            sql = conection.prepareStatement("DELETE FROM usuario "
                    + "WHERE nombreusuario=?;");
            sql.setString(1, user.getName());

            //lo ejecuto
            executeSQL(sql, TIPOSQL.MODIFICACION);
            //si no salta error, el usuario se eliminó correctamente
            //y con ello todo sus codigos y tipos de codigos
            correcto = true;
        } catch (SQLException e) {
            System.out.println("Error eliminando el usuario: ".concat(e.toString()));
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println("Error desconocido eliminando el usuario: ".concat(e.toString()));
        }
        return correcto;

    }

    /**
     * Compruebo si existe el usuario en la base de datos
     *
     * @param usuario
     * @return true si existe el usuario, false si no hay results
     * @throws Exception cualquier error que pueda surgir en la conexión
     */
    protected boolean existUser(String usuario) throws Exception {
        try {
            //recojo el usuario correspondiente
            sql = conection.prepareStatement("SELECT * FROM USUARIO WHERE NOMBREUSUARIO=?");
            sql.setString(1, usuario);

            executeSQL(sql, TIPOSQL.CONSULTA);
            //compruebo si existe el usuario o no
            return result.next();
        } catch (Exception e) {
            System.out.println("Error comprobando usuario: ".concat(e.toString()));
            throw new Exception("Error comprobando usuario");
        }
    }

    @Override
    public boolean changePassword(IdHandler user, String password){
        boolean resultChange=false;
        try {
            sql=conection.prepareStatement("UPDATE USUARIO SET CONTRASENIA=? WHERE NOMBREUSUARIO=?");
            sql.setString(1, password);
            sql.setString(2, user.toString());
            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultChange=true;
        } catch (SQLException ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultChange;
    }
    
    
    //granjas
    @Override
    public LinkedList<Farm> getListFarms() {
        return getListFarms(user.getId());
    }

    @Override
    public Farm getFarm(IdHandler id) {
        if(actualFarm!=null){
            if(actualFarm.getIdFarm().compareTo(id)==0){
                return actualFarm;
            }
        }
        
        return this.getFarm(this.user.getId(), id);
    }

    @Override
    public Farm getFarm(IdHandler idUser, IdHandler idFarm) {
        if(actualFarm!=null){
            if(actualFarm.getIdFarm().compareTo(idFarm)==0){
                return actualFarm;
            }
        }
        Farm farm=null;
        try {
            sql = conection.prepareStatement("SELECT * FROM FARM WHERE IDGRANJA=?");
            sql.setInt(1, Integer.parseInt(idFarm.toString()));

            executeSQL(sql, TIPOSQL.CONSULTA);

            if (result.next()) {
                farm = new Farm(idFarm, result.getString("NOMBREGRANJA"),
                        result.getString("IDENTIFICADORGRANJA"),
                        result.getString("DIRECCION"),
                        result.getString("NOMBREGANADERO"),
                        result.getString("DNIGANADERO"),
                        result.getInt("NUMEROVACAS"), idUser,
                        result.getString("OTROSDATOS"));
                actualFarm=farm;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return farm;
    }

    @Override
    public boolean newFarm(Farm farm) {
        actualFarm=farm;
        boolean resultFarm = false;
        try {
            sql = conection.prepareStatement("INSERT INTO FARM"
                    + "(IDGRANJA,NOMBREGRANJA,NOMBREUSUARIO,DIRECCION,"
                    + "NOMBREGANADERO,DNIGANADERO,NUMEROVACAS,"
                    + "IDENTIFICADORGRANJA,OTROSDATOS)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setInt(1, Integer.parseInt(farm.getIdFarm().toString()));
            sql.setString(2, farm.getFarmName());
            sql.setString(3, farm.getNameUser());
            sql.setString(4, farm.getAddress());
            sql.setString(5, farm.getFarmerName());
            sql.setString(6, farm.getDniFarmer());
            sql.setInt(7, farm.getCowNumber());
            sql.setString(8, farm.getFarmIdentifier());
            sql.setString(9, farm.getOtherData());

            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultFarm = true;
        } catch (SQLException e) {
            System.out.println("Error añadiendo la granja: ".concat(e.toString()));
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println("Error desconocido añadiendo la granja: "
                    .concat(e.toString()));
        }
        return resultFarm;
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
        String isEnabled;
        if(enabled){
            isEnabled="TRUE";
        }else{
            isEnabled="FALSE";
        }
        try {
            sql = conection.prepareStatement("UPDATE FARM SET "
                    + "ENABLED='" + isEnabled + "' WHERE IDGRANJA=?");
            sql.setInt(1, Integer.parseInt(id.toString()));
            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultSwitch = true;
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSwitch;
    }

    @Override
    public boolean removeFarm(Farm farm) {
        actualFarm=null;
        boolean resultRemove = false;
        try {
            sql = conection.prepareStatement("DELETE FROM FARM WHERE IDGRANJA=?");
            sql.setInt(1, Integer.parseInt(farm.getIdFarm().toString()));

            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultRemove = true;
        } catch (Exception e) {
        }
        return resultRemove;
    }

    @Override
    public boolean modifiedFarm(Farm farm) {
        actualFarm=farm;
        boolean modified = false;
        try {
            sql = conection.prepareStatement("UPDATE FARM SET "
                    + "NOMBREGRANJA=?,"
                    + "DIRECCION=?,"
                    + "NOMBREGANADERO=?,"
                    + "IDENTIFICADORGRANJA=?,"
                    + "OTROSDATOS=?,"
                    + "DNIGANADERO=?,"
                    + "NUMEROVACAS=? "
                    + "WHERE IDGRANJA=?");
            sql.setString(1, farm.getFarmName());
            sql.setString(2, farm.getAddress());
            sql.setString(3, farm.getFarmerName());
            sql.setString(4, farm.getFarmIdentifier());
            sql.setString(5, farm.getOtherData());
            sql.setString(6, farm.getDniFarmer());
            sql.setInt(7, farm.getCowNumber());
            sql.setInt(8, Integer.parseInt(farm.getIdFarm().toString()));

            executeSQL(sql, TIPOSQL.MODIFICACION);
            modified = true;
        } catch (SQLException e) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modified;
    }

    /**
     * Comprobar si no hace falta un inner join
     *
     * @param idUser
     * @return
     */
    @Override
    public LinkedList<Farm> getListFarms(IdHandler idUser) {
        LinkedList<Farm> lista = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM FARM WHERE NOMBREUSUARIO=? AND ENABLED='TRUE'");
            sql.setString(1, idUser.toString());
            executeSQL(sql, TIPOSQL.CONSULTA);

            while (result.next()) {
                int idGranja = result.getInt("IDGRANJA");
                String nombreGranja = result.getString("NOMBREGRANJA");
                String direccion = result.getString("DIRECCION");
                String nombreGanadero = result.getString("NOMBREGANADERO");
                String dniGanadero = result.getString("DNIGANADERO");
                int numeroVacas = result.getInt("NUMEROVACAS");
                String nombreUsuario = result.getString("NOMBREUSUARIO");
                String identificadorGranja = result.getString("IDENTIFICADORGRANJA");
                String otrosDatos = result.getString("OTROSDATOS");

                IdHandler usuario = new IdUser(nombreUsuario);
                IdHandler granja = new IdFarm(usuario, idGranja);

                Farm farm = new Farm(granja, nombreGranja, identificadorGranja,
                        direccion, nombreGanadero, dniGanadero, numeroVacas,
                        usuario, otrosDatos);

                lista.add(farm);
            }

            for (Farm farm : lista) {
                LinkedList<InterfaceEvaluationModel> listado = getListEvaluations(farm.getIdFarm());
                for (InterfaceEvaluationModel interfaceEvaluationModel : listado) {
                    farm.addEvaluation(interfaceEvaluationModel);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public LinkedList<Farm> getDisabledFarms() {
        LinkedList<Farm> list = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM FARM WHERE ENABLED='FALSE'");
            executeSQL(sql, TIPOSQL.CONSULTA);
            while (result.next()) {
                int idFarm = result.getInt("idgranja");
                String nameFarm = result.getString("nombregranja");
                String address = result.getString("direccion");
                String nameFarmer = result.getString("nombreganadero");
                String dniFarmer = result.getString("dniganadero");
                int cowNumber = result.getInt("numerovacas");
                //datos añadidos y necesarios
                String farmerIdentifier = result.getString("identificadorGranja");
                //datos no necesarios para instanciar la granja
                String otrosDatos = result.getString("otrosdatos");
                IdHandler id = new IdFarm(idFarm);

                LinkedList<InterfaceEvaluationModel> listEvaluations = new LinkedList<>();
                Farm farmDisabled = new Farm(id, nameFarm, farmerIdentifier, address,
                        nameFarmer, dniFarmer, cowNumber, user.getId(),
                        otrosDatos, listEvaluations);
                list.add(farmDisabled);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public int getNumberCow(IdHandler id, IdHandler evaluation){
        int number=0;
        if(actualFarm!=null){
            if(actualFarm.getIdFarm().compareTo(id)==0){
                number= actualFarm.getCowNumber();
            }
        }
        try {
            sql = conection.prepareStatement("SELECT NUMEROVACAS FROM FARM WHERE IDGRANJA=? AND IDEVALUATION=?");
            sql.setInt(1, Integer.parseInt(id.toString()));
            sql.setInt(2, Integer.parseInt(evaluation.toString()));

            executeSQL(sql, TIPOSQL.CONSULTA);

            if (result.next()) {
                number= result.getInt("NUMEROVACAS");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return number;
    }
    
    @Override
    public int getNumberCow(IdHandler id){
        int number=0;
        if(actualFarm!=null){
            if(actualFarm.getIdFarm().compareTo(id)==0){
                number= actualFarm.getCowNumber();
            }
        }
        try {
            sql = conection.prepareStatement("SELECT NUMEROVACAS FROM FARM WHERE IDGRANJA=?");
            sql.setInt(1, Integer.parseInt(id.toString()));

            executeSQL(sql, TIPOSQL.CONSULTA);

            if (result.next()) {
                number= result.getInt("NUMEROVACAS");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return number;
    }

    
    
    
    
    //evaluaciones
    @Override
    public LinkedList<InterfaceEvaluationModel> getListEvaluations(IdHandler idFarm) {
        return getListEvaluations(user.getId(), idFarm);
    }

    @Override
    public LinkedList<InterfaceEvaluationModel> getListEvaluations(IdHandler idUser, IdHandler idFarm) {
        LinkedList<InterfaceEvaluationModel> lista = null;
        try {
            sql = conection.prepareStatement("SELECT * FROM EVALUATION WHERE USUARIO=? AND IDGRANJA=?");
            sql.setString(1, idUser.toString());
            sql.setInt(2, Integer.parseInt(idFarm.toString()));

            executeSQL(sql, TIPOSQL.CONSULTA);
            lista = new LinkedList<>();
            while (result.next()) {
                lista.add(new EvaluationModel(false,
                        new InformationEvaluation(
                                (IdHandler) new IdEvaluation(result.getInt("IDEVALUATION")),
                                idFarm, result.getFloat("NOTA"),
                                result.getFloat("ALIMENTACION"),
                                result.getFloat("SALUD"),
                                result.getFloat("COMFORT"),
                                result.getFloat("COMPORTAMIENTO"),
                                result.getDate("FECHA"),
                                result.getInt("NUMEROVACAS"))));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }

    @Override
    public InterfaceEvaluationModel getEvaluation(IdHandler id) {
        return getEvaluation(id, user);
    }

    public InterfaceEvaluationModel getEvaluation(IdHandler id, User user) {
        InformationEvaluation information = null;
        InterfaceEvaluationModel evaluation = null;
        try {
            sql = conection.prepareStatement(
                    "SELECT " +
                    "E.IDEVALUATION, E.FECHA, E.NUMEROVACAS, E.IDGRANJA, E.NOTA AS NOTAEV, E.ALIMENTACION, E.SALUD, E.COMFORT, E.COMPORTAMIENTO," +
                    "C.CATEGORIA, PCA.PONDERACION AS PONCATEGORIA, C.NOMBRECRITERIO, PCR.PONDERACION AS PONCRITERIO, V.NOTA, " +
                    "V.IDVALORATION " +
                    "FROM " +
                    "EVALUATION AS E " +
                    "LEFT JOIN CRITERION AS C " +
                    "LEFT JOIN PONDERACIONCATEGORIA AS PCA ON PCA.CATEGORIA=C.CATEGORIA " +
                    "LEFT JOIN VALORATION AS V  ON C.NOMBRECRITERIO=V.NOMBRECRITERIO " +
                    "LEFT JOIN PONDERACIONCRITERIO AS PCR ON PCR.NOMBRECRITERIO=V.NOMBRECRITERIO AND PCR.IDEVALUATION=V.IDEVALUATION " +
                    "WHERE V.IDEVALUATION=? AND PCA.IDEVALUATION=? AND E.IDEVALUATION=?");
            sql.setInt(1, Integer.parseInt(id.toString()));
            sql.setInt(2, Integer.parseInt(id.toString()));
            sql.setInt(3, Integer.parseInt(id.toString()));
            executeSQL(sql, TIPOSQL.CONSULTA);

            boolean firstTime = true;
            while (result.next()) {
                if (firstTime) {
                    IdHandler idFarm = new IdFarm(result.getInt("IDGRANJA"));
                    float nota = result.getFloat("NOTAEV");
                    float alimentacion = result.getFloat("ALIMENTACION");
                    float salud = result.getFloat("SALUD");
                    float comfort = result.getFloat("COMFORT");
                    float comportamiento = result.getFloat("COMPORTAMIENTO");
                    Date fecha = result.getDate("FECHA");
                    int numberCow=result.getInt("NUMEROVACAS");
                    information = new InformationEvaluation(
                            id, idFarm, user.getId(), nota, alimentacion,
                            salud, comfort, comportamiento, fecha, numberCow);
                    evaluation = new EvaluationModel(true, information);
                    firstTime=false;
                }

                
                IdHandler category = new IdCategory(result.getString("CATEGORIA"));
                evaluation.setWeighing(category, result.getFloat("PONCATEGORIA"));
                if(result.getString("NOMBRECRITERIO")!=null){
                    Criterion criterion = criterions.clone(result.getString("NOMBRECRITERIO"));
                    criterion.setWeighing(result.getFloat("PONCRITERIO"));
                    evaluation.add(category, criterion);
                
                    Valoration valoration = new Valoration(new IdValoration(result.getInt("IDVALORATION")), result.getInt("NOTA"));

                    evaluation.add(category, criterion.getId(), valoration);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return evaluation;
    }

    private boolean saveEvaluation(InformationEvaluation evaluation) {
        boolean resultSave = false;
        try {
            sql = conection.prepareStatement("INSERT INTO EVALUATION"
                    + "('IDEVALUATION','IDGRANJA','USUARIO','NOTA',"
                    + "'ALIMENTACION','SALUD','COMFORT','COMPORTAMIENTO',"
                    + "'FECHA','NUMEROVACAS')"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)");
            sql.setInt(1, Integer.parseInt(evaluation.getIdEvaluation().toString()));
            sql.setInt(2, Integer.parseInt(evaluation.getIdFarm().toString()));
            sql.setString(3, evaluation.getIdUser().toString());
            sql.setFloat(4, evaluation.getNota());
            sql.setFloat(5, evaluation.getAlimentacion());
            sql.setFloat(6, evaluation.getSalud());
            sql.setFloat(7, evaluation.getComfort());
            sql.setFloat(8, evaluation.getComportamiento());
            sql.setDate(9, evaluation.getFecha());
            sql.setInt(10, evaluation.getNumberCows());

            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultSave = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return resultSave;
    }

    @Override
    public boolean saveEvaluation(InterfaceEvaluationModel evaluation) {
        boolean resultSave = false;
        try {
            startTransaccion();
            LinkedList<PonderationDB> ponderationCriterion = new LinkedList<>();
            LinkedList<PonderationDB> ponderationCategory = new LinkedList<>();

            if (saveEvaluation(evaluation.getInformation())) {
                for (Category cat : Category.values()) {
                    ponderationCategory.add(new PonderationDB(
                            Integer.parseInt(evaluation.getIdHandler().toString()), 
                            Category.getName(cat),
                            evaluation.getWeighing(new IdCategory(cat))));

                    for (Criterion criterio : evaluation.getListCriterion(cat)) {
                        ponderationCriterion.add(new PonderationDB(
                                Integer.parseInt(evaluation.getIdHandler().toString()), 
                                criterio.getName(), criterio.getWeighing()));
                        for (Valoration valoration : evaluation.listOfCriterion(criterio.getId())) {
                            if (!saveValoration(
                                    Integer.parseInt(evaluation.getIdHandler().toString()),
                                    criterio, valoration)) {
                                rollback();
                                break;
                            }
                        }
                    }
                }
                saveCriterionPonderation(ponderationCriterion);
                saveCategoryPonderation(ponderationCategory);
                resultSave = true;
                commit();
            }
        } catch (SQLException | NumberFormatException ex) {
            rollback();
            System.out.println(ex.toString());
        }
        return resultSave;
    }

    public LinkedList<Valoration> getAllValorationsFrom(IdHandler idEvaluation) {
        LinkedList<Valoration> valores = new LinkedList<>();
        try {
            sql = conection.prepareStatement(
                    "SELECT V.IDVALORATION, V.IDEVALUATION, V.NOTA,C.CATEGORIA, V.NOMBRECRITERIO AS NOMBRE "
                    + "FROM VALORATION AS V INNER JOIN CRITERION AS C ON V.NOMBRECRITERIO "
                    + " = C.NOMBRECRITERIO AND V.IDEVALUATION = ?");
            sql.setInt(1, Integer.parseInt(idEvaluation.toString()));
            executeSQL(sql, TIPOSQL.CONSULTA);
            while (result.next()) {
                String nombreCriterio = result.getString("nombre");
                IdHandler idCriterio = new IdCriterion(nombreCriterio);
                String categoria = result.getString("categoria");
                IdHandler idCategoria = new IdCategory(categoria);
                int numValoration = result.getInt("IDEVALUATION");
                IdHandler idValoration = new IdEvaluation(numValoration);
                float nota = result.getFloat("NOTA");
                Valoration val = new Valoration(idValoration, nota);
                valores.add(val);
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valores;
    }

    @Override
    public boolean saveModifiedEvaluation(InterfaceEvaluationModel evaluation) {
        boolean resultSave = true;
        try {
            startTransaccion();
        } catch (SQLException ex) {
            resultSave=false;
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultSave = resultSave & removeEvaluation(evaluation.getIdHandler());
        if (resultSave == false) {
            rollback();
        } else {
            resultSave = resultSave & saveEvaluation(evaluation);
        }
        return resultSave;
    }

    @Override
    public boolean saveValoration(int idEvaluation, Criterion criterion, Valoration valoration) {
        boolean resultSave = false;
        try {
            sql = conection.prepareStatement("INSERT INTO VALORATION"
                    + "(IDVALORATION, NOMBRECRITERIO,IDEVALUATION,NOTA,PONDERACION)"
                    + " VALUES(?,?,?,?,?)");
            sql.setInt(1, Integer.parseInt(valoration.getId().toString()));
            sql.setString(2, criterion.getIdCriterion().toString());
            sql.setInt(3, idEvaluation);
            sql.setFloat(4, valoration.getNota());
            sql.setFloat(5, valoration.getWeighing());

            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultSave = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultSave;
    }

    /**
     * MEJORAR CON LA ELIMINACIÓN EN CASCADA DE LA BASE DE DATOS
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeEvaluation(IdHandler id) {
        boolean resultRemove = false;
        try {
            StringBuilder borrar = new StringBuilder();
            borrar.append("delete from valoration where idevaluation = " + id.toString());
            PreparedStatement preparedStatement = conection.prepareStatement(borrar.toString());
            preparedStatement.execute();

            borrar = new StringBuilder();
            borrar.append("delete from ponderacioncriterio where idevaluation = " + id.toString());
            preparedStatement = conection.prepareStatement(borrar.toString());
            preparedStatement.execute();

            borrar = new StringBuilder();
            borrar.append("delete from ponderacioncategoria where idevaluation = " + id.toString());
            preparedStatement = conection.prepareStatement(borrar.toString());
            preparedStatement.execute();
            
            borrar = new StringBuilder();
            borrar.append("delete from evaluation where idevaluation = " + id.toString());
            preparedStatement = conection.prepareStatement(borrar.toString());
            preparedStatement.execute();
            resultRemove = true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return resultRemove;
    }

    
    
    
    //identificadores
    @Override
    public int nextIdFarm() {
        int id = 1;
        try {
            sql = conection.prepareStatement("SELECT MAX(IDGRANJA) AS IDGRANJA FROM FARM");
            executeSQL(sql, TIPOSQL.CONSULTA);
            if (result.next()) {
                id = result.getInt("IDGRANJA") + 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int nextIdValoration() {
        if (nextIdValoration == -1) {
            try {
                sql = conection.prepareStatement("SELECT MAX(IDVALORATION) AS IDVALORATION FROM VALORATION");
                executeSQL(sql, TIPOSQL.CONSULTA);
                if (result.next()) {
                    nextIdValoration = result.getInt("idvaloration") + 1;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            nextIdValoration++;
        }
        return nextIdValoration;
    }

    @Override
    public int nextIdEvaluation() {
        int id = 1;
        try {
            sql = conection.prepareStatement("SELECT MAX(IDEVALUATION)AS IDEVALUATION FROM EVALUATION");
            executeSQL(sql, TIPOSQL.CONSULTA);
            if (result.next()) {
                id = result.getInt("idevaluation") + 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    
    
    
    //criterios
    @Override
    public boolean newCriterion(Criterion criterion) {
        boolean resultMethod = false;
        try {

            sql = conection.prepareStatement("INSERT INTO "
                    + "CRITERION(NOMBRECRITERIO,DESCRIPCION,HELP,CATEGORIA) VALUES(?,?,?,?)");
            sql.setString(1, criterion.getName());
            sql.setString(2, criterion.getDescription());
            sql.setString(3, criterion.getHelp());
            sql.setString(4, ((IdCategory) criterion.getCategory()).toString());

            executeSQL(sql, TIPOSQL.MODIFICACION);
            criterions.add(criterion);
            resultMethod = true;

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return resultMethod;
    }

    protected boolean isCriterionInitialized() {
        return criterionInitialized;
    }
    
    @Override
    public LinkedList<Criterion> getListCriterion() {
        if(!isCriterionInitialized()){
            criterionInitialized=true;
            try {
                sql = conection.prepareStatement("SELECT * FROM CRITERION");
                executeSQL(sql, TIPOSQL.CONSULTA);

                while (result.next()) {
                    Criterion criterion = new Criterion(
                            result.getString("NOMBRECRITERIO"),
                            new IdCategory(result.getString("CATEGORIA")),
                            result.getString("DESCRIPCION"),
                            result.getString("HELP"));

                    criterions.add(criterion);
                }
            } catch (Exception e) {
                System.out.println("Pero que coño..." + e.toString());
            }
        }
        return criterions.getList();
    }

    @Override
    public boolean removeCriterion(IdHandler idCriterion) {
        boolean resultRemove = false;
        try {
            sql = conection.prepareStatement("DELETE FROM CRITERION WHERE NOMBRECRITERIO=?");
            sql.setString(1, idCriterion.toString());
            executeSQL(sql, TIPOSQL.MODIFICACION);
            criterions.remove(new Criterion(idCriterion, null));
            resultRemove = true;

        } catch (SQLException e) {
            System.out.println("Error eliminando el criterio: ".concat(e.toString()));
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println("Error desconocido eliminando el criterio: ".concat(e.toString()));
        }
        return resultRemove;
    }

    @Override
    public Criterion getCriterion(IdHandler id) {
        if (criterions == null) {
            getListCriterion();
        }
        return criterions.clone(id.toString());
    }

    
    
    
    
    
    //funciones del backup
    @Override
    public boolean saveValoration(ValorationDB valoration){
        boolean resultSave = false;
        try {
            sql = conection.prepareStatement("INSERT INTO VALORATION"
                    + "(IDVALORATION, NOMBRECRITERIO,IDEVALUATION,NOTA,PONDERACION)"
                    + " VALUES(?,?,?,?,?)");
            sql.setInt(1, Integer.parseInt(valoration.getIdValoration().toString()));
            sql.setString(2, valoration.getIdCriterion().toString());
            sql.setInt(3, Integer.parseInt(valoration.getIdEvaluation().toString()));
            sql.setFloat(4, valoration.getNota());
            sql.setFloat(5, valoration.getWeighing());

            executeSQL(sql, TIPOSQL.MODIFICACION);
            resultSave = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultSave;
    }
    
    
    @Override
    public LinkedList<Farm> getAllFarms() {
        LinkedList<Farm> farms = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM FARM");
            executeSQL(sql, TIPOSQL.CONSULTA);

            while (result.next()) {
                Farm farm = new Farm(new IdFarm(result.getInt("IDGRANJA")),
                        result.getString("NOMBREGRANJA"),
                        result.getString("IDENTIFICADORGRANJA"),
                        result.getString("DIRECCION"),
                        result.getString("NOMBREGANADERO"),
                        result.getString("DNIGANADERO"),
                        result.getInt("NUMEROVACAS"),
                        new IdUser(result.getString("NOMBREUSUARIO")),
                        result.getString("OTROSDATOS"),
                        result.getBoolean("ENABLED"));
                farms.add(farm);
            }
        } catch (Exception e) {
        }
        return farms;
    }

    @Override
    public LinkedList<PonderationDB> getCriterionPonderation() {
        LinkedList<PonderationDB> lista = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM PONDERACIONCRITERIO");
            executeSQL(sql, TIPOSQL.CONSULTA);
            while (result.next()) {
                int idEval = result.getInt("idevaluation");
                String categoria = result.getString("nombrecriterio");
                float b = result.getFloat("ponderacion");
                PonderationDB pon = new PonderationDB(idEval, categoria, b);
                lista.add(pon);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    @Override
    public LinkedList<EvaluationModel> getAllEvaluations() {
        LinkedList<EvaluationModel> model = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM EVALUATION");
            executeSQL(sql, TIPOSQL.CONSULTA);

            while (result.next()) {
                InformationEvaluation info = new InformationEvaluation(
                        new IdEvaluation(result.getInt("IDEVALUATION")),
                        new IdFarm(result.getInt("IDGRANJA")),
                        new IdUser(result.getString("USUARIO")),
                        result.getInt("NOTA"),
                        result.getInt("ALIMENTACION"),
                        result.getInt("SALUD"),
                        result.getInt("COMFORT"),
                        result.getInt("COMPORTAMIENTO"),
                        result.getDate("FECHA"),
                        result.getInt("NUMEROVACAS"));
                EvaluationModel mo = new EvaluationModel(false, info);
                model.add(mo);
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
            sql = conection.prepareStatement("SELECT * FROM PONDERACIONCATEGORIA");
            executeSQL(sql, TIPOSQL.CONSULTA);
            while (result.next()) {
                int idEval = result.getInt("idevaluation");
                String categoria = result.getString("categoria");
                float b = result.getFloat("ponderacion");
                PonderationDB pon = new PonderationDB(idEval, categoria, b);

                lista.add(pon);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public LinkedList<ValorationDB> getAllValorations() {
        LinkedList<ValorationDB> valores = new LinkedList<>();
        try {
            sql = conection.prepareStatement("SELECT * FROM VALORATION");
            executeSQL(sql, TIPOSQL.CONSULTA);
            while (result.next()) {
                int numValoration = result.getInt("idvaloration");
                IdHandler idValoration = new IdEvaluation(numValoration);
                float nota = result.getFloat("nota");
                ValorationDB val = new ValorationDB(idValoration,
                        new IdCriterion(result.getString("NOMBRECRITERIO")),
                        new IdEvaluation(result.getInt("IDEVALUATION")),
                        nota);
                valores.add(val);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return valores;
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

    private boolean newPonderacionCategoria(int idEvaluation, String idCategoria, float ponderacion) {
        boolean nuevo = false;
        try {
            /**
             * SetFloat del sql tiene errores de forma que si es 0.5, aparece 0.50000000000000342 y similares
             * por ello se setea directamente en el sql
             */
            sql = conection.prepareStatement("INSERT INTO PONDERACIONCATEGORIA"
                    + "(IDEVALUATION,CATEGORIA,PONDERACION) VALUES(?,?,'"+ponderacion +"')");
            sql.setInt(1, idEvaluation);
            sql.setString(2, idCategoria);
            //sql.setFloat(3, ponderacion);
            executeSQL(sql, TIPOSQL.MODIFICACION);
            nuevo = true;
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
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

    protected boolean newPonderacionCriterio(int idEvaluation, String idCriterio, float ponderacion) {
        boolean nuevo = false;
        try {
            /**
             * SetFloat del sql tiene errores de forma que si es 0.5, aparece 0.50000000000000342 y similares
             * por ello se setea directamente en el sql
             */
            sql = conection.prepareStatement("INSERT INTO PONDERACIONCRITERIO"
                    + "(IDEVALUATION, NOMBRECRITERIO, PONDERACION) VALUES(?,?,'"+ponderacion+"')");
            sql.setInt(1, idEvaluation);
            sql.setString(2, idCriterio.toString());
            //sql.setFloat(3, ponderacion);
            executeSQL(sql, TIPOSQL.MODIFICACION);
            nuevo = true;
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DefaultDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevo;

    }

    /**
     * La diferencia con el otro, es que este no hace commit
     * @param evaluation
     * @return 
     */
    @Override
    public boolean saveEvaluationBackup(InterfaceEvaluationModel evaluation) {
        boolean resultSave = false;
        try {
            LinkedList<PonderationDB> ponderationCriterion = new LinkedList<>();
            LinkedList<PonderationDB> ponderationCategory = new LinkedList<>();
            if (saveEvaluation(evaluation.getInformation())) {
                for (Category cat : Category.values()) {
                    ponderationCategory.add(new PonderationDB(
                            Integer.parseInt(evaluation.getIdHandler().toString()), 
                            Category.getName(cat),
                            evaluation.getWeighing(new IdCategory(cat))));
                    for (Criterion criterio : evaluation.getListCriterion(cat)) {
                        ponderationCriterion.add(new PonderationDB(
                                Integer.parseInt(evaluation.getIdHandler().toString()), 
                                criterio.getName(), criterio.getWeighing()));
                        for (Valoration valoration : evaluation.listOfCriterion(criterio.getId())) {
                            if (!saveValoration(
                                    Integer.parseInt(evaluation.getIdHandler().toString()),
                                    criterio, valoration)) {
                                resultSave = false;
                                break;
                            }
                        }
                    }
                }
                saveCriterionPonderation(ponderationCriterion);
                saveCategoryPonderation(ponderationCategory);
                resultSave = true;
            }
        } catch (NumberFormatException ex) {
            rollback();
            System.out.println(ex.toString());
        }
        return resultSave;
    }

}
