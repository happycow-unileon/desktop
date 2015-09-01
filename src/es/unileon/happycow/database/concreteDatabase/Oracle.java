package es.unileon.happycow.database.concreteDatabase;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dorian
 */
public class Oracle extends DatabaseObject {


    public Oracle() {
        try {
            conection = DriverManager.getConnection("jdbc:oracle:thin:@-PC:1521:XE", "vaca", "vaca");
            user = null;
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean openDB() {
        boolean correct = false;
        try {
            if (conection.isClosed()) {
                conection = DriverManager.getConnection("jdbc:oracle:thin:@-PC:1521:XE", "vaca", "vaca");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            correct = !conection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correct;

    }
}
