/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.IdHandler;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class PanelValoration extends JPanel {
    
    private IEvaluationCriterionController controller;
    private IdHandler valoration;
    
    /**
     * Creates new form PanelFile
     * @param valoration
     */
    public PanelValoration(IdHandler valoration) {
        initComponents();
        this.valoration=valoration;
    }
    
    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }

    public void setTextValoration(String text){
        this.name.setText(text);
    }
    
    public String getText(String text){
        return this.name.getText();
    }

    public IdHandler getValoration() {
        return valoration;
    }
    
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        name = new javax.swing.JLabel();
        
        buttonCopy = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/copy.png")));
        
        buttonRemove = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/unchecked.png")));
    }
    
    private void configureComponents(){
        buttonCopy.setToolTipText("Copiar");
        buttonRemove.setToolTipText("Eliminar");
        
        buttonRemove.setBorderPainted(false);
        buttonRemove.setContentAreaFilled(false);
        buttonRemove.setFocusPainted(false);
        
        buttonCopy.setBorderPainted(false);
        buttonCopy.setContentAreaFilled(false);
        buttonCopy.setFocusPainted(false);
    }
    
    private void addEvents(){
        buttonCopy.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.copyValoration(valoration);
            }
        });
        
        buttonRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controller.removeValoration(valoration);
            }
        });
    }
    
    private void addLayout(){
        BoxLayout layout=new BoxLayout(this, BoxLayout.X_AXIS);
        setLayout(layout);
        
        add(name);
        add(Box.createHorizontalGlue());
        add(buttonCopy);
        add(buttonRemove);
    }


    private javax.swing.JButton buttonCopy;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JLabel name;
    
}
