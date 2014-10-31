package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.EvaluationControllerCriterion;
import es.unileon.happycow.handler.Category;
import java.util.LinkedList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author dorian
 */
public class PanelEvaluation extends javax.swing.JPanel implements InterfaceEvaluationCriterionPanel{
    private TabEvaluation[] panels;
    private Category categorySelected;
    private EvaluationControllerCriterion controller;
    private javax.swing.JTabbedPane panelPestanas;
    private javax.swing.JPanel panelInformation;
    private javax.swing.JScrollPane scrollInformation;

    /**
     * Creates new form PanelEvaluation
     */
    public PanelEvaluation() {
        initComponents();
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        panelPestanas = new javax.swing.JTabbedPane();
        panels=new TabEvaluation[4];
        panels[Category.FOOD.ordinal()]=new TabEvaluation(Category.FOOD);
        panels[Category.HEALTH.ordinal()]=new TabEvaluation(Category.HEALTH);
        panels[Category.HOUSE.ordinal()]=new TabEvaluation(Category.HOUSE);
        panels[Category.BEHAVIOUR.ordinal()]=new TabEvaluation(Category.BEHAVIOUR);
        
        scrollInformation = new javax.swing.JScrollPane();
        panelInformation = new javax.swing.JPanel();
    }
    
    private void configureComponents(){
        categorySelected=Category.FOOD;
        panelInformation.setPreferredSize(new java.awt.Dimension(60, 85));
        scrollInformation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInformation.setViewportView(panelInformation);
        scrollInformation.setMinimumSize(new java.awt.Dimension(60, 30));
        scrollInformation.setMaximumSize(new java.awt.Dimension(150, Integer.MAX_VALUE));
        panelInformation.setLayout(new BoxLayout(panelInformation, BoxLayout.X_AXIS));
    }
    
    private void addEvents(){
        panelPestanas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelPestanasMouseClicked(evt);
            }
        });
    }
    
    private void addLayout(){
        setLayout(new java.awt.BorderLayout());
        
        panelPestanas.addTab(Category.getName(Category.FOOD), panels[Category.FOOD.ordinal()]);
        panelPestanas.addTab(Category.getName(Category.HEALTH), panels[Category.HEALTH.ordinal()]);
        panelPestanas.addTab(Category.getName(Category.HOUSE), panels[Category.HOUSE.ordinal()]);
        panelPestanas.addTab(Category.getName(Category.BEHAVIOUR), panels[Category.BEHAVIOUR.ordinal()]);
        add(panelPestanas, java.awt.BorderLayout.CENTER);
        
        add(scrollInformation, java.awt.BorderLayout.SOUTH);
        
        
    }
    
    

    private void panelPestanasMouseClicked(java.awt.event.MouseEvent evt) {
        categorySelected=Category.getEnum(panelPestanas.getSelectedIndex());
        controller.changeCategory();
    }

    private TabEvaluation getActualPanel(){
        return panels[categorySelected.ordinal()];
    }
    
    @Override
    public void setNumberCow(int number) {
        for (TabEvaluation tabEvaluation : panels) {
            tabEvaluation.setNumberCow(number);
        }
    }

    @Override
    public String getCriterion() {
        return getActualPanel().getCriterion();
    }

    @Override
    public String getValoration() {
        return getActualPanel().getValoration();
    }

    @Override
    public void setController(EvaluationControllerCriterion controller) {
        this.controller=controller;
        for (TabEvaluation panel : panels) {
            panel.setController(controller);
        }
    }

    @Override
    public Category getSelectedCategory() {
        return categorySelected;
    }

    @Override
    public void addValoration(String valoration) {
        getActualPanel().addValoration(valoration);
    }

    @Override
    public void addCriterion(String criterion) {
        getActualPanel().addCriterion(criterion);
    }

    @Override
    public void removeCriterion(String criterion) {
        getActualPanel().removeCriterion(criterion);
    }

    @Override
    public void removeValorations() {
        getActualPanel().removeValorations();
    }

    @Override
    public void setModelValoration(DefaultListModel list) {
        getActualPanel().setModelValoration(list);
    }

    @Override
    public void setModelCriterion(DefaultListModel list) {
        getActualPanel().setModelCriterion(list);
    }

    @Override
    public void setPonderationCriterion(float ponderation) {
        getActualPanel().setPonderationCriterion(ponderation);
    }

    @Override
    public void setPonderationCategory(float ponderation) {
        getActualPanel().setPonderationCategory(ponderation);
    }

    @Override
    public void setComboCriterion(Category cat, LinkedList<String> list) {
        for (TabEvaluation tabEvaluation : panels) {
            tabEvaluation.setComboCriterion(cat, list);
        }
    }

    @Override
    public void deshabilitarValoraciones() {
        getActualPanel().deshabilitarValoraciones();
    }

    @Override
    public void habilitarValoraciones() {
        getActualPanel().habilitarValoraciones();
    }

    @Override
    public void setHelp(String help) {
        getActualPanel().setHelp(help);
    }

    @Override
    public void setInformation(String... information) {
//        getActualPanel().setInformation(information);
        panelInformation.removeAll();
        panelInformation.setLayout(new BoxLayout(panelInformation, BoxLayout.X_AXIS));
        for (String string : information) {
            JLabel info=new JLabel(string);
            panelInformation.add(info);
            panelInformation.add(Box.createHorizontalStrut(10));
        }
        panelInformation.add(Box.createHorizontalGlue());
        panelInformation.updateUI();
    }

    @Override
    public void setCriterion(Category cat, LinkedList list) {
        for (TabEvaluation tabEvaluation : panels) {
            tabEvaluation.setCriterion(cat, list);
        }
    }
}
