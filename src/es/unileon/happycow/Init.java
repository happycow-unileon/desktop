package es.unileon.happycow;

import es.unileon.happycow.controller.JFrameController;
import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.JFrameApplication;

/**
 * Clase usada para iniciar el programa en pruebas, sin el splashscreen
 * @author dorian
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Error con el Look and feel");
        }
        
        //invoco el run principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //abro la base de datos
                DataBaseOperations db=Database.getInstance();
                db.openDB();
                //le digo al jframe que comienza la fiesta
                JFrameApplication.start();
                //le digo al controller más de lo mismo
                JFrameController.getInstance().startApplication();
                //pongo el jframe visible y el pack para que se situe y tenga tamaño
                JFrameApplication.getInstance().setVisible(true);
                JFrameApplication.getInstance().setLocationRelativeTo(null);
                JFrameApplication.getInstance().pack();
            }
        });
    }
    
}
