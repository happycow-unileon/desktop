package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ManagementFarmController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.facade.InterfaceEvaluationModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author dorian
 */
public class PanelManagementFarm extends javax.swing.JPanel {
    private ManagementFarmController controller;
    private Farm farmSelected;
    
    /**
     * Creates new form PanelManagementFarm
     * @param farm
     */
    public PanelManagementFarm(Farm farm){
        this(farm, null);
    }
    
    public PanelManagementFarm(Farm farm, ManagementFarmController controller){
        initComponents();
        this.controller=controller;
        if(farm!=null){
            setFarm(farm);
        }
    }
    
    /**
     * Relleno los datos de la granja
     * @param farm 
     */
    private void fillDataFarm(Farm farm){
        panelInformation.setFarm(farm);
        panelInformation.setTooltip("Pulse para cambiar datos de la granja");
    }
    
    /**
     * Constructor
     */
    public PanelManagementFarm(){
        this(null,null);
    }
    
    /**
     * Seteo el controlador
     * @param controller 
     */
    public void setController(ManagementFarmController controller){
        this.controller=controller;
        panelInformation.setController(controller);
    }
    
    /**
     * Establezco los datos de la granja que muestro
     * @param farm 
     */
    public void setFarm(Farm farm){
        farmSelected=farm;
        fillDataFarm(farm);
        setList();
    }
    
    
    
    private void setList(){
        DefaultListModel model=new DefaultListModel();
        int conteo=1;
        for (InterfaceEvaluationModel eval : farmSelected.getListEvaluation()) {
            String date=eval.getInformation().getFecha().toString();
            date=String.valueOf(conteo)+ ": " + date+ " -> Valoración: "+ String.valueOf((int)eval.getInformation().getNota());
            model.addElement(date);
            conteo++;
        }
        jListEvaluations.setModel(model);
    }
    
    private void help(){
        //boton de ayuda
    }
    
    private void comeBack(){
        controller.returnWindow();
    }
    
    private void disableFarm(){
        controller.disableFarm();
    }
    
    private void excel(){
        controller.excel();
    }
    
    private void newEvaluation(){
        controller.newEvaluation();
    }
    
    
    
    private void seeModify(){   
        int index=jListEvaluations.getSelectedIndex();
        if(index>=0){
            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
            controller.evaluationSelected(id);
        }
    }
    
    private void removeEvaluation(){
        int index=jListEvaluations.getSelectedIndex();
        if(index>=0){
            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
            controller.removeEvaluation(id);
            farmSelected.removeEvaluation(id);
            setList();
        }
    }
    
    private void report(){
        int index=jListEvaluations.getSelectedIndex();
        if(index>=0){
            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
            controller.report(id);

        }
    }
      
    /**
     * Inicio los componentes
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
        
    }
    
    /**
     * Creo los componentes
     */
    private void createComponents(){
        panelInformation = new PanelFarm(null, null);
        panelButtons = new javax.swing.JPanel();
        buttonExcel = new javax.swing.JButton("Exportar a excel");
        buttonNewEvaluation = new javax.swing.JButton("Nueva evaluación");
        buttonDisableFarm = new javax.swing.JButton("Inhabilitar Granja");
        buttonSeeModifyEvaluation = new javax.swing.JButton("Ver/Modificar Evaluacion");
        buttonComeBack = new javax.swing.JButton("Volver");
        buttonRemove = new javax.swing.JButton("Eliminar evaluación");
        buttonReport = new javax.swing.JButton("Ver reporte");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
        scrollList = new javax.swing.JScrollPane();
        jListEvaluations = new javax.swing.JList();
    }
    
    /**
     * Configurar los componentes
     */
    private void configureComponents(){
        setLayout(new java.awt.BorderLayout());
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        
        jListEvaluations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollList.setViewportView(jListEvaluations);
    }
    
    /**
     * Añado los eventos
     */
    private void addEvents(){
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaGestionGranja");
        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaGestionGranja");
        
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeEvaluation();
            }
        });
        
        buttonReport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report();
            }
        });
        
        buttonComeBack.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comeBack();
            }
        });
        
        buttonDisableFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableFarm();
            }
        });
        
        buttonExcel.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excel();
            }
        });
        
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                help();
            }
        });
        
        buttonNewEvaluation.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newEvaluation();
            }
        });
        
        buttonSeeModifyEvaluation.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeModify();
            }
        });
    }
    
    /**
     * Añado los componentes configurando el layout
     */
    private void addLayout(){
        //añado los botones al panel de botones
        panelButtons.add(buttonExcel);
        panelButtons.add(buttonNewEvaluation);
        panelButtons.add(buttonDisableFarm);
        panelButtons.add(buttonSeeModifyEvaluation);
        panelButtons.add(buttonReport);
        panelButtons.add(buttonRemove);
        panelButtons.add(buttonComeBack);
        panelButtons.add(buttonHelp);
        
        add(panelButtons, java.awt.BorderLayout.SOUTH);
        add(panelInformation, java.awt.BorderLayout.NORTH);
        add(scrollList, java.awt.BorderLayout.CENTER);
    }


    private javax.swing.JButton buttonReport;
    private javax.swing.JButton buttonRemove;
    /**
     * Botón de ayuda
     */
    private javax.swing.JButton buttonHelp;
    /**
     * Botón de regreso a la pantalla anterior
     */
    private javax.swing.JButton buttonComeBack;
    /**
     * Botón para ver/modificar la granja seleccionada
     */
    private javax.swing.JButton buttonSeeModifyEvaluation;
    /**
     * botón para deshabilitar la granja
     */
    private javax.swing.JButton buttonDisableFarm;
    /**
     * botón para nueva evaluación
     */
    private javax.swing.JButton buttonNewEvaluation;
    /**
     * botón para exportar a excel la granja
     */
    private javax.swing.JButton buttonExcel;
    /**
     * lista de evaluaciones
     */
    private javax.swing.JList jListEvaluations;
    /**
     * panel de botones
     */
    private javax.swing.JPanel panelButtons;
    /**
     * panel de información de la granja
     */
    private PanelFarm panelInformation;
    /**
     * scroll del listado de evaluaciones
     */
    private javax.swing.JScrollPane scrollList;
}
