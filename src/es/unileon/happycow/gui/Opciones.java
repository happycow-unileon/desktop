package es.unileon.happycow.gui;

import es.unileon.happycow.controller.JFrameController;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class Opciones extends javax.swing.JPanel {
    private final JFrameController controller=JFrameController.getInstance();
    private boolean view;

    /**
     * Creates new form Opciones
     */
    public Opciones() {
        initComponents();
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        view=true;
        buttonView = new javax.swing.JButton("Modo criterios", new javax.swing.ImageIcon
                        (getClass().getResource("/images/view.png")));
        buttonComeBack = new javax.swing.JButton("Volver", new javax.swing.ImageIcon
                        (getClass().getResource("/images/back.png")));
        buttonUnlogin = new javax.swing.JButton("Desloguearse",new javax.swing.ImageIcon
                        (getClass().getResource("/images/out.png")));
        buttonClose = new javax.swing.JButton("Cerrar",new javax.swing.ImageIcon
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
        controller.exit();
    }

    private void buttonUnloginActionPerformed() {
        controller.login();
    }

    private void buttonComeBackActionPerformed() {
        controller.comeBack();
    }

    private void buttonViewActionPerformed() {
        JOptionPane.showMessageDialog(JFrameApplication.getInstance(), 
                "Paciencia, funcionalidad no implementada", 
                "No implementado", JOptionPane.INFORMATION_MESSAGE);
        
//        if(view){
//            buttonView.setText("Modo Vacas");
//        }else{
//            buttonView.setText("Modo Criterios");
//        }
//        view=!view;
//        controller.changeView();
    }
    
    private void changeContrasena(){
        controller.changePassword();
    }


    private javax.swing.JButton buttonContrasena;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonComeBack;
    private javax.swing.JButton buttonUnlogin;
    private javax.swing.JButton buttonView;
}
