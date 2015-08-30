/*
 * 
 */
package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ExcelController;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.util.LinkedList;
import org.japura.gui.model.DefaultListCheckModel;

/**
 *
 * @author dorian
 */
public class PanelUserExcel extends javax.swing.JPanel {

    private final DefaultListCheckModel modelListFarm;
    private final DefaultListCheckModel modelListEvaluations;
    private ExcelController controller;

    /**
     * Creates new form PanelExcel
     *
     * @param controller
     */
    public PanelUserExcel(ExcelController controller) {
        initComponents();
        modelListFarm = new DefaultListCheckModel();
        modelListEvaluations = new DefaultListCheckModel();
        this.controller = controller;
        this.setEnabled(false);
    }

    public PanelUserExcel() {
        this(null);
    }

    public void setController(ExcelController controller) {
        this.controller = controller;
    }

    public void setListFarm(LinkedList<Farm> list) {
        modelListFarm.clear();
        for (Farm farm : list) {
            modelListFarm.addElement(farm.getIdFarm().toString());
        }
    }

    public void setListEvaluations(LinkedList<IEvaluationModel> list) {
        //bueno, la lista es de evaluaciones y hago lo mismo que en setListFarm
    }

    public DefaultListCheckModel getModelListEvaluations() {
        return modelListEvaluations;
    }

    public DefaultListCheckModel getModelListFarm() {
        return modelListFarm;
    }

    public void buttonHelp() {

    }


    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        scrollEvaluations = new javax.swing.JScrollPane();
        scrollFarms = new javax.swing.JScrollPane();
        buttonAllEvaluation = new javax.swing.JButton("Todos");
        buttonNoEvaluations = new javax.swing.JButton("Ninguno");
        buttonAllFarms = new javax.swing.JButton("Todos");
        buttonNoFarm = new javax.swing.JButton("Ninguno");
        listCheckFarms = new org.japura.gui.CheckList();
        listCheckEvaluation = new org.japura.gui.CheckList();
    }

    private void configureComponents() {
        setLayout(new java.awt.GridBagLayout());
        scrollEvaluations.setViewportView(listCheckEvaluation);
        scrollFarms.setViewportView(listCheckFarms);
    }

    private void addEvents() {

        buttonAllEvaluation.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.selectedAllEvaluations();
                }
            }
        });

        buttonAllFarms.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.selectedAllFarm();
                }
            }
        });

        buttonNoEvaluations.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.selectedNoneEvaluations();
                }
            }
        });

        buttonNoFarm.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (controller != null) {
                    controller.selectedNoneFarm();
                }
            }
        });

        listCheckFarms.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                controller.changeListEvaluation();
            }
        });
    }

    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollEvaluations, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollFarms, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonAllEvaluation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonNoEvaluations, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonAllFarms, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonNoFarm, gridBagConstraints);
    }

    private javax.swing.JButton buttonAllEvaluation;
    private javax.swing.JButton buttonAllFarms;
    private javax.swing.JButton buttonNoEvaluations;
    private javax.swing.JButton buttonNoFarm;
    private javax.swing.JScrollPane scrollEvaluations;
    private javax.swing.JScrollPane scrollFarms;
    private org.japura.gui.CheckList listCheckFarms;
    private org.japura.gui.CheckList listCheckEvaluation;

    public String getFarm() {
        return (String) listCheckFarms.getSelectedValue();
    }

    public void changeFarms(DefaultListCheckModel farms) {
        if (farms != null) {
            listCheckFarms.setModel(farms);
        }
    }

    public void changeEvaluations(DefaultListCheckModel evaluations) {
        if (evaluations != null) {
            listCheckEvaluation.setModel(evaluations);
        }
    }

}
