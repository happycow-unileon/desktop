package es.unileon.happycow.gui.admin;

import es.unileon.happycow.controller.admin.NewUserController;
import es.unileon.happycow.model.Rol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dorian
 */
public class NewUser extends javax.swing.JPanel implements InterfaceTabs{
    private NewUserController controller;

    /**
     * Creates new form NewUser
     */
    public NewUser() {
        initComponents();
    }

    public void setController(NewUserController controller) {
        this.controller=controller;
    }

    private void buttonHelp(){
        
    }
    
    private void saveUser(){
        controller.saveUser();
    }
    
    public String getNameUser(){
        return textName.getText();
    }
    
    public String getPasswd(){
        return new String(textPasswd.getPassword());
    }
    
    public void setWarning(String warning){
        lblInformation.setText(warning);
    }
    
    public Rol getRol(){
        String selection=(String)comboRol.getSelectedItem();
        if(selection.compareToIgnoreCase("Veterinario")==0){
            return Rol.VETERINARIO;
        }else{
            return Rol.ADMINISTRADOR;
        }
    }
    
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addlayout();
    }

    private void createComponents() {
        lblName = new javax.swing.JLabel("Nombre usuario");
        lblPasswd = new javax.swing.JLabel("Contraseña");
        lblRol = new javax.swing.JLabel("ROL");
        lblInformation = new javax.swing.JLabel();
        buttonNewUser = new javax.swing.JButton("Añadir");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
        textName = new javax.swing.JTextField();
        textPasswd = new javax.swing.JPasswordField();
        comboRol = new javax.swing.JComboBox();
    }

    private void configureComponents() {
        setLayout(new java.awt.GridBagLayout());
        lblInformation.setForeground(new java.awt.Color(204, 0, 0));
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        textName.setToolTipText("Introduzca el nombre de usuario");
        textPasswd.setToolTipText("Introduzca la contraseña");
        comboRol.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Veterinario", "Administrador"}));
    }

    private void addEvents() {
//        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaAdminNuevoUsuario");
//        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaAdminNuevoUsuario");
        
        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonHelp();
            }
        });
        
        buttonNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
    }

    private void addlayout() {
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblPasswd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblRol, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblInformation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonNewUser, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textName, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textPasswd, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboRol, gridBagConstraints);
    }

    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonNewUser;
    private javax.swing.JComboBox comboRol;
    private javax.swing.JLabel lblInformation;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPasswd;
    private javax.swing.JLabel lblRol;
    private javax.swing.JTextField textName;
    private javax.swing.JPasswordField textPasswd;

    @Override
    public void updateInformation() {
        
    }

}
