package es.unileon.happycow.gui;

import es.unileon.happycow.controller.EnableFarmController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import javax.swing.BoxLayout;

/**
 *
 * @author dorian
 */
public class PanelEnableFarm extends javax.swing.JPanel {
    
    private EnableFarmController controller;

    /**
     * Creates new form PanelEnableFarm
     * @param controller
     */
    public PanelEnableFarm(EnableFarmController controller) {
        this(controller, new LinkedList<Farm>());
    }
    
    public PanelEnableFarm(){
        this(null, new LinkedList<Farm>());
    }
    
    public PanelEnableFarm(LinkedList<Farm> list) {
        this(null, list);
    }
    
    public PanelEnableFarm(EnableFarmController controller, LinkedList<Farm> list){
        initComponents();
        this.controller=controller;
        //añade la lista
        addList(list);
    }
    
    public void setController(EnableFarmController controller){
        this.controller=controller;
        for (Component component : panelList.getComponents()) {
            ((PanelFarm)component).setController(controller);
        }
    }
    
    /**
     * Establece un mensaje
     * @param text 
     */
    public void setMessage(String text){
        labelMessage.setText(text);
    }
    
    /**
     * Cambia la lista
     * @param list nueva lista
     */
    public void changeList(LinkedList<Farm> list){
        //quita todos los componentes
        panelList.removeAll();
        //añade la nueva lista
        addList(list);
        
//        panelList.invalidate();
//        panelList.repaint();
        panelList.updateUI();
    }
    
    /**
     * Añade la lista al panel
     * @param list lista de granjas
     */
    private void addList(LinkedList<Farm> list){
        //si no es null...
        if(list!=null){
            //por cada granja...
            for (Farm farm : list) {
                //crea un nuevo panel con su controlador y lo añade a la lista
                panelList.add(new PanelFarm(farm, controller, "Pulse para habilitar esta granja"));
            }
        }
    }

    /**
     * Elimina una granja del listado
     * @param id identificador de la granja
     */
    public void removeFarm(IdHandler id){
        //indice de la granja
        int target=-1;
        //mira en todos los componentes...
        for (int i = 0; i < panelList.getComponentCount() && target<0; i++) {
            //saca el panel
            PanelFarm panelFarm = (PanelFarm)panelList.getComponent(i);
            //comprueba los id
            if (panelFarm.getId().compareTo(id)==0){
                //si es cierto, guarda el índice, con lo que sale del for
                target=i;
            }
        }
        
        //si encontró la granja
        if(target!=-1){
            //lo quita de la lista
            panelList.remove(target);
            //repinta el panel
            this.revalidate();
        }
    }
    
    /**
     * Añade una nueva granja a la lista
     * @param farm granja a añadir
     */
    public void addFarm(Farm farm){
        //crea el panel
        PanelFarm panel=new PanelFarm(farm, null);
        //lo añade
        panelList.add(panel);
        //repinta el panel
        panelList.revalidate();
    }
    
    /**
     * evento de salida
     */
    private void buttonExitActionPerformed() {
        controller.comeBack();
    }
    
    /**
     * Botón de ayuda
     */
    private void buttonHelpActionPerformed(){
    
    }

   /**
     * Inicia los componentes
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    /**
     * Crea los componentes
     */
    private void createComponents(){
        labelMessage = new javax.swing.JLabel("Lista de granjas deshabilitadas");
        scrollFarms = new javax.swing.JScrollPane();
        panelList = new javax.swing.JPanel();
        buttonExit = new javax.swing.JButton("Volver");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
    }
    
    /**
     * Configura los componentes
     */
    private void configureComponents(){
        this.setPreferredSize(new Dimension(537, 419));
        this.setMinimumSize(new Dimension(537, 419));
        this.setMaximumSize(new Dimension(650, 500));
        BoxLayout layout=new BoxLayout(panelList, BoxLayout.Y_AXIS);
        panelList.setLayout(layout);
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        scrollFarms.setViewportView(panelList);
    }
    
    /**
     * Añade los eventos a los componentes
     */
    private void addEvents(){
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaHabilitarDeshabilitar");
        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaHabilitarDeshabilitar");

        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed();
            }
        });
        
        
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonHelpActionPerformed();
            }
        });
        
        
    }
    
    /**
     * Agrega todos los componentes al panel configurando el layout
     */
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        setLayout(new java.awt.GridBagLayout());
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(labelMessage, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollFarms, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonExit, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

    }


     /**
     * botón de salida
     */
    private javax.swing.JButton buttonExit;
    /**
     * botón de ayuda
     */
    private javax.swing.JButton buttonHelp;
    /**
     * label de bienvenida
     */
    private javax.swing.JLabel labelMessage;
    /**
     * Panel del listado de granjas
     */
    private javax.swing.JPanel panelList;
    /**
     * panel que ofrece el scroll para el panel de listado de granjas
     */
    private javax.swing.JScrollPane scrollFarms;
}
