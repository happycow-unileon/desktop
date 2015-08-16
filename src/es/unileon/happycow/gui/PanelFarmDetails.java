package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ButtonFarmDetailsIController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;

/**
 *
 * @author dorian
 */
public final class PanelFarmDetails extends javax.swing.JPanel {
    private IdHandler idFarm;
    private ButtonFarmDetailsIController controller;

    /**
     * Creates new form PanelFarm
     * @param farm
     * @param controller
     */
    public PanelFarmDetails(Farm farm, ButtonFarmDetailsIController controller) {
        this(farm, controller, "");
    }
    
    public PanelFarmDetails(Farm farm, ButtonFarmDetailsIController controller, String tooltip) {
        initComponents();
        this.controller=controller;
        if(farm!=null){
            idFarm=farm.getIdFarm();
            StringBuilder result=new StringBuilder();
            result.append("<html>");
            result.append(farm.getInformation());
            result.append("</html>");
            setText(result.toString());
            setFarmName(farm.getFarmName());
        }
        setTooltip(tooltip);
    }
   
    
    public void setFarm(Farm farm){
        if(farm!=null){
            idFarm=farm.getIdFarm();
            StringBuilder result=new StringBuilder();
            result.append("<html>");
            result.append(farm.getInformation());
            result.append("</html>");
            setText(result.toString());
            setFarmName(farm.getFarmName());
        }
    }
    
    public void setTooltip(String tooltip){
        buttonFarm.setToolTipText(tooltip);
    }
    
    public IdHandler getId(){
        return idFarm;
    }
    
    public void setText(String text){
        labelText.setText(text);
    }
    
    public void setFarmName(String name){
        buttonFarm.setText(name);
    }
    
    public void setController(ButtonFarmDetailsIController controller){
        this.controller=controller;
    }

   
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents(){
        buttonFarm = new javax.swing.JButton();
        labelText = new javax.swing.JLabel();
        
    }
    
    private void configureComponents(){
        labelText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelText.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    }
    
    private void addEvents(){
        buttonFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFarmActionPerformed();
            }
        });
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonFarm, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelText, gridBagConstraints);
        
        
    }
    
    private void buttonFarmActionPerformed() {
        if(controller!=null){
            controller.execute(idFarm);
        }
    }


    private javax.swing.JButton buttonFarm;
    private javax.swing.JLabel labelText;
}
