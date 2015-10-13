/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class PanelEvaluationCriterion extends JPanel {
    //controladores y modelos
    private IEvaluationCriterionController controller;
    

    //paneles que forman la ventana
    private PanelFileList panelFileList;
    private es.unileon.happycow.gui.evaluation.ModelTable.PanelValorationList panelValorations;
    private PanelCriterion panelCriterionInformation;
    private PanelListCriterion panelListCriterion;
    private PanelCategory panelCategory;
    private PanelListCategory panelListCategory;
    //algunos botones y combos extras
    private JButton addValorationButton;
    private JButton btnHelp;
    private JComboBox comboValoration;
    private JButton addFile;
    

    public PanelEvaluationCriterion() {
        super();
        initComponent();
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
        
        panelFileList.setController(controller);
        panelValorations.setController(controller);
        panelCriterionInformation.setController(controller);
        panelListCriterion.setController(controller);
        panelCategory.setController(controller);
        panelListCategory.setController(controller);
    }
    
    
    public String getSelectedCriterion() {
        return panelListCriterion.getCriterion();
    }
    public void addCriterion(IdHandler id){
        panelListCriterion.addCriterion(id.getValue());
    }
    public void removeCriterion(IdHandler id){
        panelListCriterion.removeCriterion(id.getValue());
    }
    public void setCriterionList(LinkedList<String> criterions){
        panelListCriterion.setListCriterion(criterions);
    }
    public void setCriterionEvaluated(IdHandler id, boolean evaluated){
        panelListCriterion.setCriterionEvaluated(id.getValue(), evaluated);
    }
    public boolean getCriterionEvaluated(IdHandler id){
        return panelListCriterion.getCriterionEvaluated(id.getValue());
    }
    public void setComboCriterion(LinkedList<String> criterions){
        panelCategory.setListCriterion(criterions);
    }
    
    /**
     * Seteo la lista de ficheros que tiene el modelo
     * @param files 
     */
    public void setFileList(LinkedList<String> files){
        panelFileList.addFileList(files);
    }
    public void addFile(String file){
        panelFileList.addFile(file);
    }
    public void removeFile(IdHandler file){
        panelFileList.removeFile(file.getValue());
        //panelFileList.revalidate();
    }
    
    
    public void removeValoration(IdHandler id){
        panelValorations.removeValoration(id);
    }
    public void addValoration(Valoration valoration){
        panelValorations.addValoration(valoration);
    }
    public float getSelectedValoration(){
        return comboValoration.getSelectedIndex()+1;
    }
    public void setValorationList(LinkedList<Valoration> list){
        panelValorations.setListValoration(list);
    }
    
    public void setCriterionInformation(IdHandler criterion, float ponderation, boolean evaluated){
        panelCriterionInformation.setNameCriterion(criterion.getValue());
        panelCriterionInformation.setCriterion(criterion);
        panelCriterionInformation.setEvaluated(evaluated);
        panelCriterionInformation.setPonderation(ponderation);
    }
    public List<Object> getCriterionsAddingSelected(){
        return panelCategory.getSelectedCriterion();
    }
    public void setModelCriterion(DefaultListModel<IconList> list){
        panelListCriterion.setModel(list);
    }
    
    public void setColorPonderationCriterion(Color color){
        panelCriterionInformation.setColorPonderation(color);
    }
    public void setColorPonderationCategory(Color color){
        panelCategory.setColorPonderation(color);
    }
    public void setPonderationCategory(float ponderation){
        panelCategory.setPonderation(ponderation);
    }
    public void setPonderationCriterion(float ponderation){
        panelCriterionInformation.setPonderation(ponderation);
    }
    
    public void criterionInformationVisibility(boolean visible){
        panelCriterionInformation.setVisible(visible);
    }
    public void setTitleValorations(int valorations){
        panelValorations.setTitle(valorations);
    }
    public void setMinimunCows(int cows){
        panelValorations.setMinimunCows(cows);
    }
    public void setCategory(Category category){
        panelListCategory.setCategory(category);
    }
 
    
    /**
     * Inicializo los componentes gráficos de la ventana
     */
    private void initComponent() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        panelListCategory = new PanelListCategory();
        panelCategory = new PanelCategory();

        addFile = new JButton("Añadir");
        JComponent[] files = {addFile};
        panelFileList = new PanelFileList(files);

        panelCriterionInformation = new PanelCriterion();
        panelListCriterion = new PanelListCriterion(null);

        addValorationButton = new JButton("Add");
        comboValoration = new javax.swing.JComboBox();
        JComponent[] components = {comboValoration, addValorationButton};
        panelValorations = new es.unileon.happycow.gui.evaluation.ModelTable.PanelValorationList(components);
        
        btnHelp=new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
    }

    private void configureComponents() {
        setLayout(new java.awt.GridBagLayout());
        
        panelCategory.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panelValorations.setTitleMargin(new Insets(5, 5, 20, 5));
        panelFileList.setTitleMargin(new Insets(5, 5, 20, 5));
        panelFileList.setTitle("Ficheros");
        panelValorations.setTitle("");
        panelListCriterion.setTitle("Criterios");
        comboValoration.setModel(
                new javax.swing.DefaultComboBoxModel(
                        new String[]{"1", "2", "3", "4", "5"}));
        
        btnHelp.setBorderPainted(false);
        btnHelp.setContentAreaFilled(false);
        btnHelp.setFocusPainted(false);

    }

    private void addEvents() {
        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addFile();
            }
        });
        
        addValorationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addNewValoration();
            }
        });
        
        btnHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpSystem.getInstance().seeHelp(HelpTheme.EvaluationCriterion);
            }
        });
        
    }

    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor=java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnHelp, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelListCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelListCriterion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelCriterionInformation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelValorations, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelFileList, gridBagConstraints);

    }
}
