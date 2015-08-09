package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ManageFarmController;
import es.unileon.happycow.model.Farm;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

/**
 *
 * @author dorian
 */
public class PanelManageFarm extends javax.swing.JPanel {

    private ManageFarmController controller;
    private Farm farmSelected;

    /**
     * Creates new form PanelManagementFarm
     *
     * @param farm
     */
    public PanelManageFarm() {
        this.controller = null;
        initComponents();
        setList();
    }

    /**
     * Relleno los datos de la granja
     *
     * @param farm
     */
    private void fillDataFarm(Farm farm) {
        panelInformation.setFarm(farm);
        panelInformation.setTooltip("Pulse para cambiar datos de la granja");
    }

    /**
     * Seteo el controlador
     *
     * @param controller
     */
    public void setController(ManageFarmController controller) {
        this.controller = controller;
        panelInformation.setController(controller);
    }

    /**
     * Establezco los datos de la granja que muestro
     *
     * @param farm
     */
    public void setFarm(Farm farm) {
        farmSelected = farm;
        fillDataFarm(farm);
        setList();
    }

    private void setList() {
        DefaultListModel model=new DefaultListModel();
//        model.addElement("hola1");
//        model.addElement("hola2");
        jListEvaluations.setModel(model);
        
        
//        int conteo=1;
//        for (InterfaceEvaluationModel eval : farmSelected.getListEvaluation()) {
//            String date=eval.getInformation().getFecha().toString();
//            date=String.valueOf(conteo)+ ": " + date+ " -> Valoración: "+ String.valueOf((int)eval.getInformation().getNota());
//            model.addElement(date);
//            conteo++;
//        }
//        jListEvaluations.setModel(model);
    }

    private void help() {
        //boton de ayuda
    }

    private void disableFarm() {
        controller.disableFarm();
    }

    private void excel() {
        controller.excel();
    }

    private void newEvaluation() {
        controller.newEvaluation();
    }

    private void seeModify() {
//        int index=jListEvaluations.getSelectedIndex();
//        if(index>=0){
//            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
//            controller.evaluationSelected(id);
//        }
    }

    private void removeEvaluation() {
//        int index=jListEvaluations.getSelectedIndex();
//        if(index>=0){
//            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
//            controller.removeEvaluation(id);
//            farmSelected.removeEvaluation(id);
//            setList();
//        }
    }

    private void report() {
//        int index=jListEvaluations.getSelectedIndex();
//        if(index>=0){
//            IdHandler id=farmSelected.getListEvaluation().get(index).getIdHandler();
//            controller.report(id);
//
//        }
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
    private void createComponents() {
        panelInformation = new PanelFarm(null, null);
        panelButtons = new javax.swing.JPanel();
        buttonExcel = new javax.swing.JButton("Exportar a excel");

        buttonNewEvaluation = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/add1.png")));

        buttonDisableFarm = new javax.swing.JButton("Inhabilitar Granja");
        
        
        buttonSeeModifyEvaluation = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/edit.png")));
        
        buttonRemove = new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/unchecked.png")));
        
        
        buttonReport = new javax.swing.JButton("Ver reporte");
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        scrollList = new javax.swing.JScrollPane();
        jListEvaluations = new javax.swing.JList();
    }

    /**
     * Configurar los componentes
     */
    private void configureComponents() {
        setLayout(new java.awt.BorderLayout());
        
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);

        jListEvaluations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollList.setViewportView(jListEvaluations);

        decorator = new Decorator(scrollList,
                Anchor.NORTH_EAST, Decorator.Direction.HORIZONTAL);

        buttonNewEvaluation.setBorderPainted(false);
        buttonNewEvaluation.setContentAreaFilled(false);
        buttonNewEvaluation.setFocusPainted(false);
        
        buttonRemove.setBorderPainted(false);
        buttonRemove.setContentAreaFilled(false);
        buttonRemove.setFocusPainted(false);
        
        buttonSeeModifyEvaluation.setBorderPainted(false);
        buttonSeeModifyEvaluation.setContentAreaFilled(false);
        buttonSeeModifyEvaluation.setFocusPainted(false);
        
        decorator.addDecoration(buttonRemove);
        decorator.addDecoration(buttonSeeModifyEvaluation);
        decorator.addDecoration(buttonNewEvaluation);
        
    }

    /**
     * Añado los eventos
     */
    private void addEvents() {
//        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaGestionGranja");
//        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaGestionGranja");

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
    private void addLayout() {
        //añado los botones al panel de botones
        panelButtons.add(buttonExcel);
        panelButtons.add(buttonDisableFarm);
        panelButtons.add(buttonReport);
        panelButtons.add(buttonHelp);

        add(panelButtons, java.awt.BorderLayout.SOUTH);
        add(panelInformation, java.awt.BorderLayout.NORTH);
        add(decorator, java.awt.BorderLayout.CENTER);
    }
    
    private Decorator decorator;

    private javax.swing.JButton buttonReport;
    private javax.swing.JButton buttonRemove;
    /**
     * Botón de ayuda
     */
    private javax.swing.JButton buttonHelp;
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
