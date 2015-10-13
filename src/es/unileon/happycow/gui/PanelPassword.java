package es.unileon.happycow.gui;

import es.unileon.happycow.controller.PasswordController;
import javax.swing.JPasswordField;

/**
 *
 * @author dorian
 */
public class PanelPassword extends javax.swing.JPanel {

    private PasswordController controller;

    /**
     * Creates new form PanelPassword
     */
    public PanelPassword() {
        initComponents();
    }

    public void createComponents() {
        lblNewPassword = new javax.swing.JLabel();
        lblRenewPassword = new javax.swing.JLabel();
        lblOldPassword = new javax.swing.JLabel();
        textNewPassword = new javax.swing.JPasswordField();
        textRenewPassword = new javax.swing.JPasswordField();
        textOldPassword = new javax.swing.JPasswordField();
        buttonOk = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
    }

    public void configureComponents() {
        lblMessage.setForeground(new java.awt.Color(204, 0, 51));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setFocusable(false);
        buttonOk.setText("Cambiar");
        buttonCancel.setText("Cancelar");
    }

    public void addEvents() {
        textNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRenewPassword.requestFocus();
            }
        });
        textNewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPasswordFocusGained(evt);
            }
        });

        textRenewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textOldPassword.requestFocus();
            }
        });
        textRenewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPasswordFocusGained(evt);
            }
        });

        textOldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });
        textOldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPasswordFocusGained(evt);
            }
        });

        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                buttonOkActionPerformed(evt);
            }
        });

        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.cancel();
            }
        });
    }

    public void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());

        lblNewPassword.setText("Introduzca la nueva contraseña");
        lblNewPassword.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(lblOldPassword, gridBagConstraints);

        lblRenewPassword.setText("Reintroduzca la nueva contraseña");
        lblRenewPassword.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(lblRenewPassword, gridBagConstraints);

        lblOldPassword.setText("Introduzca su contraseña actual");
        lblOldPassword.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        add(lblNewPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        add(textOldPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        add(textRenewPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 20);
        add(textNewPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonOk, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        add(lblMessage, gridBagConstraints);
    }

    public void setController(PasswordController controller) {
        this.controller = controller;
    }

    public String getOldPassword() {
        return new String(textOldPassword.getPassword());
    }

    public String getNewPassword() {
        return new String(textNewPassword.getPassword());
    }

    public boolean isPasswordMatch() {
        String firstPass = new String(textNewPassword.getPassword());
        String secondPass = new String(textRenewPassword.getPassword());
        return firstPass.compareTo(secondPass) == 0 && firstPass.length() > 0;
    }

    public void setMessage(String message) {
        lblMessage.setText(message);
    }

    private void clearMessage() {
        lblMessage.setText("");
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {
        controller.changePassword();
    }

    private void textPasswordFocusGained(java.awt.event.FocusEvent evt) {
        JPasswordField text = (JPasswordField) evt.getSource();
        text.setText("");
        clearMessage();
    }

    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOk;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblOldPassword;
    private javax.swing.JLabel lblRenewPassword;
    private javax.swing.JPasswordField textNewPassword;
    private javax.swing.JPasswordField textOldPassword;
    private javax.swing.JPasswordField textRenewPassword;
}
