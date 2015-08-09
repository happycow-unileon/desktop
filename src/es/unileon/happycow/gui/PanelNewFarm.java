package es.unileon.happycow.gui;

import es.unileon.happycow.controller.NewFarmController;
import es.unileon.happycow.model.Farm;
import java.awt.event.ActionEvent;

/**
 *
 * @author dorian
 */
public class PanelNewFarm extends javax.swing.JPanel {
    private NewFarmController controller;

    /**
     * Creates new form PanelNewFarm
     * @param controller
     */
    public PanelNewFarm(NewFarmController controller) {
        this(controller, null);
    }
    
    public PanelNewFarm(NewFarmController controller, Farm farm){
        this.controller=controller;
        initComponents();
        if(farm!=null){
            //pasar todos los datos de la granja al formulario
            textAreaOtherData.setText(farm.getOtherData());
            textIdFarm.setText(farm.getFarmIdentifier());
            textIdFarmer.setText(farm.getDniFarmer());
            textNameFarm.setText(farm.getFarmName());
            textNameFarmer.setText(farm.getFarmerName());
            textNumberCows.setText(String.valueOf(farm.getCowNumber()));
            textaddressFarm.setText(farm.getAddress());
            
//            JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaModificarDatosGranja");
//            JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaModificarDatosGranja");
        }else{
//            JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaNuevaGranjaAnhadir");
//            JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaNuevaGranjaAnhadir");
        }
    }

    public PanelNewFarm(Farm farm) {
        this(null, farm);
    }

    public PanelNewFarm() {
        this(null, null);
    }
    
    
    
    public void setController(NewFarmController controller){
        this.controller=controller;
    }
    
    public String getOtherData(){
        return textAreaOtherData.getText();
    }
    
    public String getIdFarm(){
        return textIdFarm.getText();
    }
    
    public String getNameFarm(){
        return textNameFarm.getText();
    }
    
    public String getIdFarmer(){
        return textIdFarmer.getText();
    }
    
    public String getNameFarmer(){
        return textNameFarmer.getText();
    }
    
    public String getNumberCows(){
        return textNumberCows.getText();
    }
    
    public String getAddressFarm(){
        return textaddressFarm.getText();
    }

    private void initComponents() {
        createComponents();
        configureComponentes();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
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
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
        
    }
    
    private void configureComponentes(){
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
    
    private void addEvents(){
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
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelNameFarm, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textNameFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelIdFarm, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textIdFarm, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelNameFarmer, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textNameFarmer, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelIdFarmer, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textIdFarmer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelFarmPlace, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textaddressFarm, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelNumCows, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(textNumberCows, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelOtherData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jScrollPane2, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonCancel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonSave, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        if(controller!=null){
            controller.returnWindow();
        }
    }

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        if(controller!=null){
            controller.saveFarm();
        }
    }
    
    private void buttonHelp(){
        
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
