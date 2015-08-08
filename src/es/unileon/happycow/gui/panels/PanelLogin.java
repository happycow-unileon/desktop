package es.unileon.happycow.gui.panels;

import es.unileon.happycow.controller.LoginController;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 * Panel del login del programa
 * @author dorian
 */
public class PanelLogin extends javax.swing.JPanel {
    /**
     * Controlador del panel
     */
    private LoginController controller;
    /**
     * Creates new form PanelLogin
     */
    public PanelLogin() {
        //inicializar componentes
        initComponents();
        textUsuario.requestFocus();
    }
    
    /**
     * establece un controlador al panel
     * @param controller un LoginController como controlador
     */
    public void setController(LoginController controller){
	this.controller=controller;
    }

    /**
     * Establece un mensaje de error (como contraseña o usuario incorrectos)
     * @param message el mensaje a mostrar
     */
    public void setMessageError(String message){
        labelInformation.setText(message);
    }
    
    /**
     * Limpia la caja del usuario
     */
    public void clearUser(){
        textUsuario.setText("");
    }
    
    /**
     * Limpia la caja de la contraseña
     */
    public void clearPassword(){
        textContrasena.setText("");
    }
    
    /**
     * Limpia usuario, contraseña y mensaje de error
     */
    public void clearAll(){
        clearPassword();
        clearUser();
        setMessageError("");
    }
    
    /**
     * Inicializa los componentes
     */
    private void initComponents() {
	createComponents();
	configureComponents();
	addEvents();
	addLayout();
    }
    

    /**
     * Evento del logeo para pasar a la siguiente ventana
     */
    private void buttonLoginActionPerformed() {
        controller.checkLogin(textUsuario.getText(), new String(textContrasena.getPassword()));
    }

    /**
     * Evento de la caja de usuario, que pasa el foco al de contraseña
     */
    private void textUsuarioActionPerformed() {
        textContrasena.requestFocus();
    }

    /**
     * Evento de la caja de contraseña, que tiene el mismo efecto que pulsar el 
     * botón de logueo
     */
    private void textContrasenaActionPerformed() {
        buttonLoginActionPerformed();
    }

    /**
     * Crea los componentes
     */
    private void createComponents(){
	textContrasena = new javax.swing.JPasswordField();
        labelContrasena = new javax.swing.JLabel();
        labelUser = new javax.swing.JLabel();
        textUsuario = new javax.swing.JTextField();
        labelInformation = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
        buttonLogin = new javax.swing.JButton();
    }
    
    /**
     * Configura los componentes
     */
    private void configureComponents(){
	this.setMinimumSize(new java.awt.Dimension(500, 180));
	this.setPreferredSize(new java.awt.Dimension(500, 180));
    
        labelUser.setFocusable(false);
        labelContrasena.setFocusable(false);
        labelInformation.setFocusable(false);
        labelInformation.setForeground(Color.red);
	textContrasena.setToolTipText("Introduzca su contraseña");
	labelContrasena.setText("Contraseña: ");
	labelContrasena.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	labelUser.setText("Usuario: ");
	labelUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	textUsuario.setToolTipText("Introduzca su usuario");
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
	buttonLogin.setText("Entrar");
        buttonLogin.setToolTipText("Logearse");
        buttonLogin.setFocusPainted(false);
    }
    
    /**
     * Añade los eventos pertinentes
     */
    private void addEvents(){
	textContrasena.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textContrasenaActionPerformed();
            }
        });
        
        textContrasena.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
               ((JTextField)e.getComponent()).setText("");
            }
         });
	
	textUsuario.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUsuarioActionPerformed();
            }
        });
        
        textUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
               ((JTextField)e.getComponent()).setText("");
            }
         });
        
//        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaLogin");
//        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaLogin");
	
//	buttonHelp.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                buttonHelpActionPerformed();
//            }
//        });
	
	buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed();
            }
        });
    }
    
    /**
     * Agrega todo al panel configurando el layout
     */
    private void addLayout(){
	java.awt.GridBagConstraints gridBagConstraints;
	
	this.setLayout(new java.awt.GridBagLayout());
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        this.add(textContrasena, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        this.add(labelContrasena, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        this.add(labelUser, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        this.add(textUsuario, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        this.add(labelInformation, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        this.add(buttonHelp, gridBagConstraints);
	
	gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        this.add(buttonLogin, gridBagConstraints);
    }

    /**
     * Botón de ayuda
     */
    private javax.swing.JButton buttonHelp;
    /**
     * Botón de logeo
     */
    private javax.swing.JButton buttonLogin;
    /**
     * texto de contraseña
     */
    private javax.swing.JLabel labelContrasena;
    /**
     * texto de mensaje
     */
    private javax.swing.JLabel labelInformation;
    /**
     * Texto de usuario
     */
    private javax.swing.JLabel labelUser;
    /**
     * Caja de entrada de contraseña
     */
    private javax.swing.JPasswordField textContrasena;
    /**
     * Caja de entrada de usuario
     */
    private javax.swing.JTextField textUsuario;
}
