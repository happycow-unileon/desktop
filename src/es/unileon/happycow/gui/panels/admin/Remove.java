package es.unileon.happycow.gui.panels.admin;

import es.unileon.happycow.controller.admin.AdminController;
import es.unileon.happycow.controller.admin.RemoveController;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.JButton;

/**
 *
 * @author dorian
 */
public class Remove extends javax.swing.JPanel implements InterfaceTabs{
    private RemoveController controller;
    
    

    /**
     * Creates new form RemoveUser
     * @param title
     * @param warning
     * @param combo
     * @param controller
     */
    public Remove(String title, String warning, LinkedList<String> combo, 
            RemoveController controller) {
        initComponents();
        lblTitle.setText(title);
        lblWarning.setText(warning);
        this.controller=controller;
        for (String item : combo) {
            comboData.addItem(item);
        }
    }
    
    public Remove(String title, String warning, LinkedList<String> combo) {
        this(title, warning, combo, null);
    }

    public Remove(LinkedList<String> combo, RemoveController controller) {
        this("", "", combo,controller);
    }
    
    public Remove(LinkedList<String> combo) {
        this("", "", combo,null);
    }

    public Remove(String title, LinkedList<String> combo) {
        this(title, "", combo);
    }
    
    public Remove(String title, LinkedList<String> combo, AdminController controller) {
        this(title, "", combo);
    }
    
    public void setController(RemoveController controller){
        this.controller=controller;
    }
    
    public void setHeader(String header){
        lblTitle.setText(header);
    }
    
    public void setWarning(String warning){
        lblWarning.setText(warning);
    }
    
    public void removeElement(String element){
        comboData.removeItem(element);
    }
    
    public void addElement(String element){
        comboData.addItem(element);
    }
    
    public String getElement(){
        return comboData.getSelectedItem().toString();
    }
    
    private void buttonHelp(){
        
    }
    
    private void buttonRemove(){
        controller.remove();
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        lblTitle = new javax.swing.JLabel();
        comboData = new javax.swing.JComboBox();
        lblWarning = new javax.swing.JLabel();
        buttonRemove = new javax.swing.JButton();
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
    }
    
    private void configureComponents(){
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonRemove.setText("Eliminar");
        lblWarning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setLayout(new java.awt.GridBagLayout());
        
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
    }
    
    private void addEvents(){
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonHelp();
            }
        });
        
       buttonRemove.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonRemove();
            }
        });
        
        
    }
    
    public JButton getButtonHelp(){
        return buttonHelp;
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblTitle, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        add(comboData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblWarning, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonRemove, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }



    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JComboBox comboData;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWarning;

    //TODO
    public void updateInformation() {
        controller.update();
    }
    
    public void changeCombo(LinkedList<String> combo){
        comboData.removeAllItems();
        for (String item : combo) {
            comboData.addItem(item);
        }
    }
}
