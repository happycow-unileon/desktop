package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ManageFarmController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

/**
 *
 * @author dorian
 */
public class PanelManageFarm extends javax.swing.JPanel {

    private ManageFarmController controller;
    private DefaultListModel<InformationEvaluation> model;
//    private Farm farmSelected;

    /**
     * Creates new form PanelManagementFarm
     *
     * @param farm
     */
    public PanelManageFarm() {
        this.controller = null;
        model = new DefaultListModel<>();
        initComponents();
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
//        farmSelected = farm;
        fillDataFarm(farm);
    }

    public void setList(List<InformationEvaluation> list) {
        DefaultListModel model = new DefaultListModel();
        for (InformationEvaluation element : list) {
            model.addElement(element);
        }
        jListFarms.setModel(model);

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
        InformationEvaluation info = (InformationEvaluation) jListFarms.getSelectedValue();
        if (info != null) {
            controller.evaluationSelected(info);
        }
    }

    private void removeEvaluationEvent() {
        InformationEvaluation info = (InformationEvaluation) jListFarms.getSelectedValue();
        if (info != null) {
            controller.removeEvaluation(info.getIdEvaluation());
        }
    }

    public void removeEvaluation(IdHandler id) {
        for (int i = 0; i < model.getSize(); i++) {
            InformationEvaluation get = model.getElementAt(i);
            if (id.compareTo(get.getIdEvaluation()) == 0) {
                model.removeElementAt(i);
            }
        }
    }

    private void report() {
        InformationEvaluation info = (InformationEvaluation) jListFarms.getSelectedValue();
        if (info != null) {
            controller.report(info.getIdEvaluation());
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
    private void createComponents() {
        panelInformation = new PanelFarmDetails(null, null);
        panelButtons = new javax.swing.JPanel();
        buttonExcel = new javax.swing.JButton("Exportar a excel");

        buttonDisableFarm = new javax.swing.JButton("Deshabilitar granja");

        Image img = new javax.swing.ImageIcon(
                getClass().getResource("/images/add.png")).getImage();
        Image resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonNewEvaluation = new javax.swing.JButton(new ImageIcon(resized));

        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/edit.png")).getImage();
        resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonModifyEvaluation = new javax.swing.JButton(new ImageIcon(resized));

        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/see.png")).getImage();
        resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonSeeEvaluation = new javax.swing.JButton(new ImageIcon(resized));

        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/delete.png")).getImage();
        resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonRemove = new javax.swing.JButton(new ImageIcon(resized));

        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/report.png")).getImage();
        resized = img.getScaledInstance(22, 22, java.awt.Image.SCALE_SMOOTH);
        buttonReport = new javax.swing.JButton(new ImageIcon(resized));

        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
        scrollList = new javax.swing.JScrollPane();
        jListFarms = new javax.swing.JList();
    }

    /**
     * Configurar los componentes
     */
    private void configureComponents() {
        setLayout(new java.awt.BorderLayout());

        jListFarms.setCellRenderer(new EvaluationListRenderer());
        jListFarms.setModel(model);

        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);

        buttonNewEvaluation.setBorderPainted(false);
        buttonNewEvaluation.setContentAreaFilled(false);
        buttonNewEvaluation.setFocusPainted(false);
        buttonNewEvaluation.setToolTipText("Añadir nueva evaluación");

        buttonRemove.setBorderPainted(false);
        buttonRemove.setContentAreaFilled(false);
        buttonRemove.setFocusPainted(false);
        buttonRemove.setToolTipText("Borrar evaluación");

        buttonModifyEvaluation.setBorderPainted(false);
        buttonModifyEvaluation.setContentAreaFilled(false);
        buttonModifyEvaluation.setFocusPainted(false);
        buttonModifyEvaluation.setToolTipText("Modificar/ver evaluación");

        buttonSeeEvaluation.setBorderPainted(false);
        buttonSeeEvaluation.setContentAreaFilled(false);
        buttonSeeEvaluation.setFocusPainted(false);
        buttonSeeEvaluation.setToolTipText("Modificar/ver evaluación");

        buttonReport.setBorderPainted(false);
        buttonReport.setContentAreaFilled(false);
        buttonReport.setFocusPainted(false);
        buttonReport.setToolTipText("Ver reporte");

        jListFarms.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollList.setViewportView(jListFarms);

        decorator = new Decorator(scrollList,
                Anchor.NORTH_EAST, Decorator.Direction.HORIZONTAL);

        decorator.addDecoration(buttonNewEvaluation);
        decorator.addDecoration(buttonModifyEvaluation);
        decorator.addDecoration(buttonSeeEvaluation);
        decorator.addDecoration(buttonReport);
        decorator.addDecoration(buttonRemove);

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
                removeEvaluationEvent();
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

        buttonModifyEvaluation.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeModify();
            }
        });
        
        buttonSeeEvaluation.addActionListener(new java.awt.event.ActionListener() {
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
    private javax.swing.JButton buttonModifyEvaluation;
    private javax.swing.JButton buttonSeeEvaluation;
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
    private javax.swing.JList jListFarms;
    /**
     * panel de botones
     */
    private javax.swing.JPanel panelButtons;
    /**
     * panel de información de la granja
     */
    private PanelFarmDetails panelInformation;
    /**
     * scroll del listado de evaluaciones
     */
    private javax.swing.JScrollPane scrollList;
}
