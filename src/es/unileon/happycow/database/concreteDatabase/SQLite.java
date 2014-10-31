package es.unileon.happycow.database.concreteDatabase;

import es.unileon.happycow.model.Rol;
import es.unileon.happycow.model.User;
import java.io.File;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class SQLite extends DefaultDatabase {
    private static final String stringConexion = "jdbc:sqlite::resource:database/database.sqlite";
    private static final String pathDatabase= "database/database.sqlite";

    public SQLite() {
        super();
    }

    @Override
    public boolean openDB() {
        //si la conexión es nula, se crea la conexión/base de datos por primera vez
        if (conection == null) {
            System.out.println("* Starting...");
            

            //cargamos el driver
            try {
                Class.forName("org.sqlite.JDBC"); //Load driver
                System.out.println("* Loading driver...");
            } catch (ClassNotFoundException e) {
                System.out.println("ERROR: failed to load JDBC driver.");
                JOptionPane.showMessageDialog(null,
                        "Error con el driver de la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(5);
            }

            //conectamos/creamos la base de datos            
            try {
                // Connect to the database or create if it don't exist
                conection = DriverManager.getConnection(stringConexion);
                File fil=new File(pathDatabase);
                System.out.println(fil.getAbsolutePath());
                System.out.println("* Creating connection...");
                //creamos la base de datos si no está creada
                crearBaseDatos();
                System.out.println("Start with criterions");
                //cargamos los criterios
                getListCriterion();
                newUser(new User("Admin", "Admin", Rol.ADMINISTRADOR));
            } catch (Exception e) {
                System.out.println("Error creando la conexion: ".concat(e.toString()));
                JOptionPane.showMessageDialog(null,
                        "Error creando la base de datos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(5);
            }
            System.out.println("* Connection created...");
        }
        
        //configuramos parámetros
        try {
            sql=conection.prepareStatement("PRAGMA foreign_keys = ON");
            executeSQL(sql, TIPOSQL.MODIFICACION);
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //devolvemos la instancia única de la clase
        return true;
    }
}
