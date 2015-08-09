package es.unileon.happycow.gui;

import es.unileon.happycow.controller.BarOptionsController;
import javax.swing.Box;
import javax.swing.JButton;

/**
 *
 * @author dorian
 */
public class PanelOptions extends javax.swing.JPanel {
    private BarOptionsController controller;
    

    /**
     * Creates new form Opciones
     */
    public PanelOptions() {
        initComponents();
        
    }

    public void setController(BarOptionsController controller) {
        this.controller = controller;
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        buttonView = new javax.swing.JButton("Modo criterios", new javax.swing.ImageIcon
                        (getClass().getResource("/images/view.png")));
        buttonComeBack = new javax.swing.JButton("Volver", new javax.swing.ImageIcon
                        (getClass().getResource("/images/back.png")));
        buttonUnlogin = new javax.swing.JButton("Cerrar sesión",new javax.swing.ImageIcon
                        (getClass().getResource("/images/out.png")));
        buttonClose = new javax.swing.JButton("Salir",new javax.swing.ImageIcon
                        (getClass().getResource("/images/unchecked.png")));
        buttonContrasena= new javax.swing.JButton("Cambiar Contraseña",new javax.swing.ImageIcon
                        (getClass().getResource("/images/key.png")));
    }
    
    private void configureComponents(){
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        
        configureButton(buttonClose);
        configureButton(buttonUnlogin);
        configureButton(buttonComeBack);
        configureButton(buttonView);
        configureButton(buttonContrasena);
    }
    
    private void configureButton(JButton button){
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
    }
    
    private void addEvents(){
        
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed();
            }
        });

        buttonComeBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonComeBackActionPerformed();
            }
        });

        buttonUnlogin.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUnloginActionPerformed();
            }
        });
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed();
            }
        });
        
        buttonContrasena.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeContrasena();
            }
        });
        
    }
    
    private void addLayout(){
        add(buttonView);
        add(buttonContrasena);
        add(Box.createHorizontalGlue());
        add(buttonComeBack);
        add(buttonUnlogin);
        add(buttonClose);
    }

    private void buttonCloseActionPerformed() {
        controller.close();
    }

    private void buttonUnloginActionPerformed() {
        controller.unlogin();
    }

    private void buttonComeBackActionPerformed() {
        controller.comeBack();
    }

    private void buttonViewActionPerformed() {
        controller.changeView();
    }
    
    private void changeContrasena(){
        controller.changePassword();
    }

    public void setButtonView(String text){
        buttonView.setText(text);
    }
    
    private javax.swing.JButton buttonContrasena;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonComeBack;
    private javax.swing.JButton buttonUnlogin;
    private javax.swing.JButton buttonView;
}
