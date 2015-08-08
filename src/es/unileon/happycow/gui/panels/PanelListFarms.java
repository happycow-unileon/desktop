package es.unileon.happycow.gui.panels;

import es.unileon.happycow.controller.ButtonFarmInterfaceController;
import es.unileon.happycow.controller.ListFarmsController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.BoxLayout;

/**
 * Panel from the list of farms to select
 * @author dorian
 */
public class PanelListFarms extends javax.swing.JPanel {
    /**
     * Controller of the panel
     */
    private ListFarmsController controller;

    /**
     * Creates new form PanelListFarmsManagement
     * @param list listado de granjas
     */
    public PanelListFarms(LinkedList<Farm> list) {
        this(list, null);
    }

    /**
     * Constructor
     */
    public PanelListFarms() {
        this(new LinkedList<Farm>(), null);
    }
    
    /**
     * Constructor
     * @param list listado de granjas
     * @param controller el controlador
     */
    public PanelListFarms(LinkedList<Farm> list, ListFarmsController controller){
        initComponents();
        this.controller=controller;
        //a the list
        addList(list);
    }
    
    /**
     * Set the welcome's message or similar
     * @param welcome 
     */
    public void setWelcome(String welcome){
        labelWelcome.setText(welcome);
    }
    
    /**
     * set the controller
     * @param controller the controller
     */
    public void setController(ListFarmsController controller){
        this.controller=controller;
        //for every component of the panel
        for (int i = 0; i < panelList.getComponentCount(); i++) {
            //get the panel of the farm
            PanelFarm panelFarm = (PanelFarm)panelList.getComponent(i);
            //set the controller to the farm's panel
            panelFarm.setController((ButtonFarmInterfaceController)controller);
        }
    }
    
    /**
     * Change the farm's list
     * @param list new farm's list
     */
    public void changeList(LinkedList<Farm> list){
        //remove all components
        panelList.removeAll();
        //add the new list
        addList(list);
    }
    
    /**
     * add the list of farms to the panel
     * @param list farm's list
     */
    private void addList(LinkedList<Farm> list){
        //if not null...
        if(list!=null){
            //for every farm
            for (Farm farm : list) {
                //create a new panel with its controller and add it to the list of the panel
                panelList.add(new PanelFarm(farm, controller, "Pulse para gestionar la granja"));
            }
        }
    }

    /**
     * Remove a farm from the list
     * @param id farm's identifier
     */
    public void removeFarm(IdHandler id){
        //farm's index
        int target=-1;
        //looking in every component
        for (int i = 0; i < panelList.getComponentCount() && target<0; i++) {
            //get the panel
            PanelFarm panelFarm = (PanelFarm)panelList.getComponent(i);
            //check the identifier
            if (panelFarm.getId().compareTo(id)==0){
                //if is the farm, store the index
                target=i;
            }
        }
        
        //if farm was found
        if(target!=-1){
            //remove it from the list
            panelList.remove(target);
            //repaint the list panel
            this.revalidate();
        }
    }
    
    /**
     * Add a new farm to the list
     * @param farm farm to add
     */
    public void addFarm(Farm farm){
        //create the panel
        PanelFarm panel=new PanelFarm(farm, null);
        //add it
        panelList.add(panel);
        //repaint the list panel
        panelList.revalidate();
    }
    
    /**
     * Init the components
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    /**
     * Create the components
     */
    private void createComponents(){
        labelWelcome = new javax.swing.JLabel("Bienvenido");
        scrollFarms = new javax.swing.JScrollPane();
        panelList = new javax.swing.JPanel();
        buttonNewFarm = new javax.swing.JButton("Nueva granja");
        buttonExit = new javax.swing.JButton("Cerrar sesión");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
        buttonEnable = new javax.swing.JButton("Habilitar granja deshabilitada");
        buttonExcel=new javax.swing.JButton("Exportar a excel");
    }
    
    /**
     * Configure the components
     */
    private void configureComponents(){
        this.setPreferredSize(new Dimension(537, 419));
        this.setMinimumSize(new Dimension(537, 419));
        this.setMaximumSize(new Dimension(650, 500));
        //set a box layout
        BoxLayout layout=new BoxLayout(panelList, BoxLayout.Y_AXIS);
        panelList.setLayout(layout);
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        scrollFarms.setViewportView(panelList);
    }
    
    /**
     * Add the events to the components
     */
    private void addEvents(){
        //set the help of the window
//        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaListaGranjas");
//        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaListaGranjas");
        
        //button add new farm
        buttonNewFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNewFarmActionPerformed();
            }
        });

        //button exit
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed();
            }
        });
        
        //button enable button
        buttonEnable.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnableActionPerformed();
            }
        });
        
        //button excel
        buttonExcel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonExcelActionPerformed();
            }
        });
    }
    
    /**
     * Add the components to the panel with the layout
     */
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        //label welcome
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelWelcome, gridBagConstraints);
        
        // scroll farm
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollFarms, gridBagConstraints);
        
        //button new farm
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonNewFarm, gridBagConstraints);
        
        //button exit
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonExit, gridBagConstraints);
        
        
        //button help
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
        
        //button excel
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonExcel, gridBagConstraints);
        
        //button enable farm
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonEnable, gridBagConstraints);
    }
    
    /**
     * Event of new farm
     */
    private void buttonNewFarmActionPerformed() {
        controller.newFarm();
    }

    /**
     * Event of exit
     */
    private void buttonExitActionPerformed() {
        controller.exit();
    }

    /**
     * Event of enable a disabled farm
     */
    private void buttonEnableActionPerformed() {
        controller.enableFarm();
    }
    
    /**
     * Event export excel
     */
    private void buttonExcelActionPerformed(){
        controller.exportExcel();
    }

    /**
     * button exit
     */
    private javax.swing.JButton buttonExit;
    /**
     * button help
     */
    private javax.swing.JButton buttonHelp;
    /**
     * button new farm
     */
    private javax.swing.JButton buttonNewFarm;
    /**
     * button enable farm
     */
    private javax.swing.JButton buttonEnable;
    /**
     * label welcome
     */
    private javax.swing.JLabel labelWelcome;
    /**
     * panel of farm's list
     */
    private javax.swing.JPanel panelList;
    /**
     * scroll of the panelof list farm
     */
    private javax.swing.JScrollPane scrollFarms;
    /**
     * button to export excel
     */
    private javax.swing.JButton buttonExcel;
}
