/*
 * 
 */
package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.IdGeneric;
import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 *
 * @author dorian
 */
public class PanelFile extends javax.swing.JPanel {

    private IEvaluationCriterionController controller;
    private String fileName;
    
    /**
     * Creates new form PanelFile
     */
    public PanelFile() {
        initComponents();
    }
    
    public void setFileName(String name){
        this.fileName=name;
        this.name.setText(name);
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }

    public String getFileName() {
        return fileName;
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        name = new javax.swing.JLabel();
        
        buttonDownload = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/download.png")));
        
        buttonRemove = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/unchecked.png")));
    }
    
    private void configureComponents(){
        buttonDownload.setToolTipText("Descargar fichero");
        buttonRemove.setToolTipText("Borrar fichero");
        
        buttonDownload.setBorderPainted(false);
        buttonDownload.setContentAreaFilled(false);
        buttonDownload.setFocusPainted(false);
        
        buttonRemove.setBorderPainted(false);
        buttonRemove.setContentAreaFilled(false);
        buttonRemove.setFocusPainted(false);
    }
    
    private void addEvents(){
        buttonDownload.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.downloadFile(new IdGeneric(fileName));
            }
        });
        
        buttonRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.removeFile(new IdGeneric(fileName));
            }
        });
    }
    
    private void addLayout(){
        BoxLayout layout=new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        
        add(name);
        add(Box.createHorizontalGlue());
        add(buttonDownload);
        add(buttonRemove);
    }


    private javax.swing.JButton buttonDownload;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JLabel name;
}
