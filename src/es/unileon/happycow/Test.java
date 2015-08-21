/*
 * 
 */
package es.unileon.happycow;

import es.unileon.happycow.gui.evaluation2.criterion.PanelCriterion;
import es.unileon.happycow.gui.evaluation2.file.PanelFileList;
import es.unileon.happycow.gui.evaluation2.valoration.PanelValoration;
import es.unileon.happycow.gui.evaluation2.valoration.PanelValorationList;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdGeneric;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();   
    }

    @SuppressWarnings("unchecked")
    
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        
        this.setMinimumSize(new Dimension(300, 300));
        pack();
        
        PanelCriterion a=new PanelCriterion();
//        List<String> list=new LinkedList<>();
//        list.add("prueba");
//        list.add("asdf");
//        list.add("lkjñlk");
//        list.add("zxv");
//        list.add("prueba");
//        list.add("asdf");
//        list.add("lkjñlk");
//        list.add("zxv");
//        list.add("prueba");
//        list.add("asdf");
//        list.add("lkjñlk");
//        list.add("zxv");
        a.setCriterion(new IdCriterion("criterio nombre"));
        a.setNameCriterion("criterio nombre");
        a.setEvaluated(false);
        getContentPane().add(a);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }
}
