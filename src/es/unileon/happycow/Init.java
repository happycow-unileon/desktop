package es.unileon.happycow;

import es.unileon.happycow.application.JFrame;
import es.unileon.happycow.database.DataBaseOperations;
import es.unileon.happycow.database.Database;
import javax.swing.UIManager;

/**
 * Clase usada para iniciar el programa en pruebas, sin el splashscreen
 *
 * @author dorian
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Ocean".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            System.out.println("Error con el Look and feel");
//        }

        //com.jtattoo.plaf.aluminium.AluminiumLookAndFeel
        //com.jtattoo.plaf.fast.FastLookAndFeel mñegh
        //com.jtattoo.plaf.graphite.GraphiteLookAndFeel
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //invoco el run principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //abro la base de datos
                DataBaseOperations db = Database.getInstance();
                db.openDB();
                new JFrame();

            }
        });
    }

}
