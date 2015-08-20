/*
 * 
 */
package es.unileon.happycow.gui.evaluation2.criterion;

import es.unileon.happycow.handler.IdHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author dorian
 */
public class PanelCriterion extends JPanel {
    private IdHandler criterion;
    private ICriterionPanel controller;

    public PanelCriterion() {
        initComponents();
    }

    public void setController(ICriterionPanel controller) {
        this.controller = controller;
    }
    
    private void initComponents(){
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        buttonEvaluated=new JToggleButton();
        buttonHelpCriterion= new JButton();
        
        title=new JLabel("Criterio");
        ponderationLabel=new JLabel();
        
        ponderationText=new JTextField();
    }
    
    private void configureComponents(){
        setLayout(new java.awt.GridBagLayout());
        
        ponderationText.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ponderationText.setToolTipText("Introduzca ponderación para el criterio");
    }
    
    public void setCriterion(IdHandler id){
        this.criterion=id;
    }

    public IdHandler getCriterion() {
        return criterion;
    }
    
    public void setNameCriterion(String name){
        title.setText(name);
    }
    
    public void setPonderation(Float note){
        ponderationText.setText(Float.toString(note));
    }
    
    public void setEvaluated(boolean evaluated){
        buttonEvaluated.setSelected(evaluated);
        String text=(evaluated)? "No evaluado":"Evaluado";
        buttonEvaluated.setText(text);
    }
    
    public boolean getEvaluated(){
        return buttonEvaluated.isSelected();
    }
    
    private void addEvents(){
        buttonHelpCriterion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.help(criterion);
            }
        });
        
        buttonEvaluated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.evaluated(criterion);
                setEvaluated(buttonEvaluated.isSelected());
            }
        });
        
        ponderationText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //comprobar cada vez que escribe y setear la ponderacion
            }
        });
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(title, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(ponderationLabel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        add(ponderationText, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(ponderationText, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        add(buttonHelpCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        add(buttonEvaluated, gridBagConstraints);
        
    }
    
    private JButton buttonHelpCriterion;
    private JToggleButton buttonEvaluated;
    private JLabel title;
    private JLabel ponderationLabel;
    private JTextField ponderationText;
}
