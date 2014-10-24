package es.unileon.happycow.gui.admin;

import es.unileon.happycow.controller.admin.NewCriterionController;
import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.handler.Category;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dorian
 */
public class NewCriterion extends javax.swing.JPanel implements InterfaceTabs{
    private NewCriterionController controller;
    
    
    /**
     * Creates new form NewCriterion
     */
    public NewCriterion() {
        initComponents();
    }
    
    public void setController(NewCriterionController controller){
        this.controller=controller;
    }
    
    private void buttonHelp(){
        
    }
    
    private void buttonSave(){
        controller.saveCriterion();
    }
    
    public Category getCategory(){
        String seleccion=(String)comboCategory.getSelectedItem();
        return Category.getEnum(seleccion);
    }
    
    public String getNameCriterion(){
        return textName.getText();
    }
    
    public void setWarning(String warning){
        lblWarning.setText(warning);
    }
    
    public String getHelp(){
        return textHelp.getText();
    }
    
    public String getDescription(){
        return textDescription.getText();
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents(){
        lblName = new javax.swing.JLabel("Nombre del criterio");
        lblCategory = new javax.swing.JLabel("Categoría");
        lblWarning=new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel("Ayuda");
        lblDescription = new javax.swing.JLabel("Descripción");
        comboCategory = new javax.swing.JComboBox();
        scrollDescription = new javax.swing.JScrollPane();
        textDescription = new javax.swing.JTextArea();
        scrollHelp = new javax.swing.JScrollPane();
        textHelp = new javax.swing.JTextArea();
        textName = new javax.swing.JTextField();
        buttonSave = new javax.swing.JButton("Guardar");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
    }
    
    private void configureComponents(){
        setLayout(new java.awt.GridBagLayout());
        comboCategory.setModel(
                new javax.swing.DefaultComboBoxModel(Category.getArrayString()));
        textHelp.setColumns(20);
        textHelp.setRows(5);
        textHelp.setLineWrap(true);
        textHelp.setWrapStyleWord(true);
        textHelp.setToolTipText("Ayuda/descripción más detallada acerca de lo que evalúa el criterio. Se puede usar html.");
        scrollHelp.setViewportView(textHelp);
        
        textDescription.setColumns(20);
        textDescription.setRows(5);
        textDescription.setLineWrap(true);
        textDescription.setWrapStyleWord(true);
        textDescription.setToolTipText("Descripción corta acerca de lo que evalúa el criterio");
        scrollDescription.setViewportView(textDescription);
        
        lblWarning.setForeground(new java.awt.Color(204, 0, 0));
        
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
    }
    
    private void addEvents(){
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaAdminNuevoCriterio");
        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaAdminNuevoCriterio");
        
        buttonHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonHelp();
            }
        });
        
        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSave();
            }
        });
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblName, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblCategory, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblHelp, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblDescription, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        add(comboCategory, gridBagConstraints);

        

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollDescription, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollHelp, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        add(textName, gridBagConstraints);
        
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblWarning, gridBagConstraints);
        
        

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }

   
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSave;
    private javax.swing.JComboBox comboCategory;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JScrollPane scrollDescription;
    private javax.swing.JScrollPane scrollHelp;
    private javax.swing.JTextArea textDescription;
    private javax.swing.JTextArea textHelp;
    private javax.swing.JTextField textName;

    @Override
    public void updateInformation() {
        
    }
}
