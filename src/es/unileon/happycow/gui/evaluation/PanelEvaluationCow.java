package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.EvaluationControllerCow;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.model.composite.Criterion;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;

import javax.swing.DefaultListModel;

/**
 *
 * @author dorian
 */
public class PanelEvaluationCow extends javax.swing.JPanel {
    
    private DefaultListModel model;
    private EvaluationControllerCow controller;

    /**
     * Creates new form PanelEvaluationCow
     */
    public PanelEvaluationCow() {
        initComponents();
    }
    
    /**
     * establece un controlador al panel
     * @param controller un LoginController como controlador
     */
    public void setController(EvaluationControllerCow controller){
	this.controller=controller;
    }
    
    private void createComponents(){
        jScrollInformation = new javax.swing.JScrollPane();
        textareaInformation = new javax.swing.JTextArea();
        jScrollListValorations = new javax.swing.JScrollPane();
        jListValoration = new javax.swing.JList();
        comboCategory = new javax.swing.JComboBox();
        comboCriterion = new javax.swing.JComboBox();
        comboValoration = new javax.swing.JComboBox();
        comboSeeCow = new javax.swing.JComboBox();
        textPonderationCategory = new javax.swing.JTextField();
        textPonderationCriterion = new javax.swing.JTextField();
        buttonCopyCow = new javax.swing.JButton("Copiar vaca");
        buttonHelp = new javax.swing.JButton();
        comboValorateCow = new javax.swing.JComboBox();
        lblSeeCow = new javax.swing.JLabel("Ver vaca");
        lblCow = new javax.swing.JLabel("Vaca");
        lblCategory = new javax.swing.JLabel("Categoria");
        lblCriterion = new javax.swing.JLabel("Criterio");
        lblPonderationCat = new javax.swing.JLabel("Ponderacion");
        lblPonderationCri = new javax.swing.JLabel("Ponderacion");
        lblValoration = new javax.swing.JLabel("Valoración");
        buttonValorate = new javax.swing.JButton("Valorar");
        buttonEvaluated = new javax.swing.JButton("Evaluado");
        buttonCancel=new javax.swing.JButton("Cancelar");
        lblNumberCows=new javax.swing.JLabel();
        model=new DefaultListModel();
    }
    
    public void add(){
        
    }
    
    public void remove(){
        
    }
    
    public void get(){
        
    }
    
    public void setNumberCow(int number){
        lblNumberCows.setText("Se necesitan " + String.valueOf(number) + " vacas.");
    }
    
    public void setComboCriterion(ComboBoxModel list){
        comboCriterion.setModel(list);
    }
    
    private void setComboCategory(){
        for (String string : Category.getArrayString()) {
            comboCategory.addItem(string);
        }
        comboCategory.setSelectedIndex(0);
    }
    
    private void setComboValoration(){
        comboValoration.setModel(new javax.swing.DefaultComboBoxModel(
                new String[] { "1","2","3","4","5" }));
    }
    
    private void setComboCow(){
        comboSeeCow.setModel(new javax.swing.DefaultComboBoxModel(
                new String[] { }));
        
        comboValorateCow.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nueva vaca" }));
    }
    
    private void configureComponents(){
        setLayout(new java.awt.GridBagLayout());
        
        setComboCategory();
        setComboValoration();
        setComboCow();
        
        textareaInformation.setColumns(20);
        textareaInformation.setRows(5);
        textareaInformation.setLineWrap(true);
        textareaInformation.setWrapStyleWord(true);
        jScrollInformation.setViewportView(textareaInformation);
        jListValoration.setModel(model);
        jScrollListValorations.setViewportView(jListValoration);
        
        textPonderationCategory.setColumns(5);
        textPonderationCriterion.setColumns(5);
        
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        
        lblCow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCriterion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblValoration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        setTooltip();
        
    }
    
    private void setTooltip(){
        comboCategory.setToolTipText("Seleccione categoria que va a evaluar");
        comboCriterion.setToolTipText("Seleccione criterio que va a evaluar");
        comboValoration.setToolTipText("Seleccione la valoración");
        comboSeeCow.setToolTipText("Seleccione la vaca que quiere ver");
        buttonCopyCow.setToolTipText("Copie la vaca que esta viendo");
        comboValorateCow.setToolTipText("Seleccione vaca a evaluar");
        buttonValorate.setToolTipText("Valore este criterio a esta vaca");
        buttonEvaluated.setToolTipText("Guardar evaluación");
        buttonHelp.setToolTipText("Ayuda");
    }
    
    private void buttonCancel(){
        controller.cancel();
    }
    
    private void addEvents(){
        comboSeeCow.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSeeCowActionPerformed();
            }
        });
        
        textPonderationCategory.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPonderationCategoryActionPerformed();
            }
        });
        
        textPonderationCriterion.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPonderationCriterionFocusLost();
            }
        });
        
        textPonderationCriterion.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPonderationCriterionActionPerformed();
            }
        });
        
        textPonderationCategory.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPonderationCategoryFocusLost();
            }
        });
        
        
        buttonCopyCow.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCopyCowActionPerformed();
            }
        });
        
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed();
            }
        });
        
        buttonValorate.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValorateActionPerformed();
            }
        });
        
        buttonEvaluated.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEvaluatedActionPerformed();
            }
        });
        
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancel();
            }
        });
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        add(jScrollInformation, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        add(jScrollListValorations, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCategory, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCriterion, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboValoration, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboSeeCow, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        add(textPonderationCategory, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        add(textPonderationCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonCopyCow, gridBagConstraints);

        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboValorateCow, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblSeeCow, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCow, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCategory, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCriterion, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPonderationCat, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPonderationCri, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblValoration, gridBagConstraints);

        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonValorate, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor= java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblNumberCows, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonEvaluated, gridBagConstraints);
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();  
    }
    
    public int getCowViewed(){
        return comboSeeCow.getSelectedIndex();
    }
    
    public String getSelectedCriterion(){
        return (String)comboCriterion.getSelectedItem();
    }
    
    public Category getSelectedCategory(){
        return Category.getEnum((String)comboCriterion.getSelectedItem());
    }
    
    public void setPonderationCategory(Float ponderation){
        textPonderationCategory.setText(Float.toString(ponderation));
    }
    
    public void setPonderationCriterion(Float ponderation){
        textPonderationCriterion.setText(Float.toString(ponderation));
    }
    
    public int getSelectedCow(){
        return comboValorateCow.getSelectedIndex();
    }

    private void buttonCopyCowActionPerformed() {
        controller.duplicateCow();
    }

    private void buttonEvaluatedActionPerformed() {
        controller.saveEvaluation();
    }

    private void buttonHelpActionPerformed() {
    }

    private void buttonValorateActionPerformed() {
        controller.addValoration((Integer)comboValoration.getSelectedItem());
    }

    private void comboSeeCowActionPerformed() {
        controller.changeViewCow();
    }

    private void textPonderationCategoryActionPerformed() {
    }

    private void textPonderationCriterionActionPerformed() {
    }

    private void textPonderationCategoryFocusLost() {
   
    }

    private void textPonderationCriterionFocusLost() {
        
    }


    private javax.swing.JButton buttonCopyCow;
    private javax.swing.JButton buttonEvaluated;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonValorate;
    private javax.swing.JComboBox comboCategory;
    private javax.swing.JComboBox comboCriterion;
    private javax.swing.JComboBox comboSeeCow;
    private javax.swing.JComboBox comboValorateCow;
    private javax.swing.JComboBox comboValoration;
    private javax.swing.JList jListValoration;
    private javax.swing.JScrollPane jScrollInformation;
    private javax.swing.JScrollPane jScrollListValorations;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCow;
    private javax.swing.JLabel lblCriterion;
    private javax.swing.JLabel lblPonderationCat;
    private javax.swing.JLabel lblPonderationCri;
    private javax.swing.JLabel lblSeeCow;
    private javax.swing.JLabel lblValoration;
    private javax.swing.JTextField textPonderationCategory;
    private javax.swing.JTextField textPonderationCriterion;
    private javax.swing.JTextArea textareaInformation;
    private javax.swing.JLabel lblNumberCows;
    private javax.swing.JButton buttonCancel;
}
