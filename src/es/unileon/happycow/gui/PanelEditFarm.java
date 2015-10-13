package es.unileon.happycow.gui;

import es.unileon.happycow.controller.EditFarmController;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import es.unileon.happycow.model.Farm;
import java.awt.event.ActionEvent;

/**
 *
 * @author dorian
 */
public class PanelEditFarm extends javax.swing.JPanel {

    private EditFarmController controller;

    public PanelEditFarm() {
        this(null, null);
    }

    public PanelEditFarm(EditFarmController controller) {
        this(controller, null);
    }

    public PanelEditFarm(EditFarmController controller, Farm farm) {
        this.controller = controller;
        initComponents();
        if (farm != null) {
            setFarm(farm);
        }
    }

    public PanelEditFarm(Farm farm) {
        this(null, farm);
    }

    public void setFarm(Farm farm) {
        //pasar todos los datos de la granja al formulario
        textAreaOtherData.setText(farm.getOtherData());
        textIdFarm.setText(farm.getFarmIdentifier());
        textIdFarmer.setText(farm.getDniFarmer());
        textNameFarm.setText(farm.getFarmName());
        textNameFarmer.setText(farm.getFarmerName());
        textNumberCows.setText(String.valueOf(farm.getCowNumber()));
        textaddressFarm.setText(farm.getAddress());
    }

    public void setController(EditFarmController controller) {
        this.controller = controller;
    }

    public String getOtherData() {
        return textAreaOtherData.getText();
    }

    public String getIdFarm() {
        return textIdFarm.getText();
    }

    public String getNameFarm() {
        return textNameFarm.getText();
    }

    public String getIdFarmer() {
        return textIdFarmer.getText();
    }

    public String getNameFarmer() {
        return textNameFarmer.getText();
    }

    public String getNumberCows() {
        return textNumberCows.getText();
    }

    public String getAddressFarm() {
        return textaddressFarm.getText();
    }

    private void initComponents() {
        createComponents();
        configureComponentes();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        labelNameFarm = new javax.swing.JLabel("Nombre de la granja");
        textNameFarm = new javax.swing.JTextField();
        labelIdFarm = new javax.swing.JLabel("Identificación granja");
        textIdFarm = new javax.swing.JTextField();
        labelNameFarmer = new javax.swing.JLabel("Nombre granjero");
        textNameFarmer = new javax.swing.JTextField();
        labelIdFarmer = new javax.swing.JLabel("Identificación granjero");
        textIdFarmer = new javax.swing.JTextField();
        labelFarmPlace = new javax.swing.JLabel("Lugar de la granja");
        textaddressFarm = new javax.swing.JTextField();
        labelNumCows = new javax.swing.JLabel("número de vacas");
        textNumberCows = new javax.swing.JTextField();
        labelOtherData = new javax.swing.JLabel("Otros datos");
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaOtherData = new javax.swing.JTextArea();
        buttonCancel = new javax.swing.JButton("Cancelar");
        buttonSave = new javax.swing.JButton("Guardar");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));

    }

    private void configureComponentes() {
        setLayout(new java.awt.GridBagLayout());

        textNameFarm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNameFarm.setToolTipText("Introduzca el nombre de la granja");

        textIdFarm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textIdFarm.setToolTipText("Introduzca la identificación de la granja");

        textNameFarmer.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNameFarmer.setToolTipText("Introduzca el nombre del granjero");

        textIdFarmer.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textIdFarmer.setToolTipText("Introduzca el DNI/identificador del granjero");

        textaddressFarm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textaddressFarm.setToolTipText("Introduzca la situación/lugar de la granja");

        textNumberCows.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textNumberCows.setToolTipText("Introduzca el número de vacas que tiene la granja");

        textAreaOtherData.setColumns(20);
        textAreaOtherData.setLineWrap(true);
        textAreaOtherData.setRows(5);
        textAreaOtherData.setLineWrap(true);
        textAreaOtherData.setWrapStyleWord(true);
        jScrollPane2.setViewportView(textAreaOtherData);

        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
    }

    private void addEvents() {
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonHelp.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonHelp();
            }
        });
    }

    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelNameFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textNameFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelIdFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textIdFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelNameFarmer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textNameFarmer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelIdFarmer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textIdFarmer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelFarmPlace, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textaddressFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelNumCows, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(textNumberCows, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        add(labelOtherData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        add(buttonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 15);
        add(buttonHelp, gridBagConstraints);
    }

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) {
            controller.returnWindow();
        }
    }

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        if (controller != null) {
            controller.saveFarm();
        }
    }

    private void buttonHelp() {
        HelpSystem.getInstance().seeHelp(HelpTheme.ModifyFarm);
    }

    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelFarmPlace;
    private javax.swing.JLabel labelIdFarm;
    private javax.swing.JLabel labelIdFarmer;
    private javax.swing.JLabel labelNameFarm;
    private javax.swing.JLabel labelNameFarmer;
    private javax.swing.JLabel labelNumCows;
    private javax.swing.JLabel labelOtherData;
    private javax.swing.JTextArea textAreaOtherData;
    private javax.swing.JTextField textIdFarm;
    private javax.swing.JTextField textIdFarmer;
    private javax.swing.JTextField textNameFarm;
    private javax.swing.JTextField textNameFarmer;
    private javax.swing.JTextField textNumberCows;
    private javax.swing.JTextField textaddressFarm;
}
