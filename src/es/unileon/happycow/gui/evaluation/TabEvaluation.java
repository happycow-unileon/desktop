package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.EvaluationControllerCriterion;
import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.handler.Category;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 *
 * @author dorian
 */
public class TabEvaluation extends javax.swing.JPanel implements InterfaceEvaluationCriterionPanel{
    private DefaultListModel<String> modelValoration;
    private DefaultListModel<IconList> modelCriterion;
    private EvaluationControllerCriterion controller;
    private final Category category;

    /**
     * Creates new form TabEvaluation
     * @param cat
     */
    public TabEvaluation(Category cat) {
        initComponents();
        this.category=cat;
    }
    
    /**
     *
     * @param number
     */
    @Override
    public void setNumberCow(int number){
        lblNumberCows.setText("Se necesitan evaluar " + String.valueOf(number) + " vacas.");
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getCriterion(){
        int index=jListCriterion.getSelectedIndex();
        if(index<0){
            return null;
        }else{
            return modelCriterion.getElementAt(index).getNombreCriterio();
        }
    }
    
    @Override
    public String getValoration(){
        int index=jListValorations.getSelectedIndex();
        return modelValoration.getElementAt(index);
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        scrollCriterion = new javax.swing.JScrollPane();
        jListCriterion = new javax.swing.JList();
        scrollValoration = new javax.swing.JScrollPane();
        jListValorations = new javax.swing.JList();
        scrollHelp = new javax.swing.JScrollPane();
        textPaneHelp = new javax.swing.JTextPane();
        lblNumberCows = new javax.swing.JLabel("Number of cows");
        upperPanel = new javax.swing.JPanel();
        buttonEvaluatedAll=new javax.swing.JButton("Todo evaluado");
        lblValoration = new javax.swing.JLabel("Valoración");
        lblPonderationCriterion = new javax.swing.JLabel("Ponderación Criterio");
        buttonAddValoration = new javax.swing.JButton("Añadir");
        comboValoration = new javax.swing.JComboBox();
        textPonderationCriterion = new javax.swing.JTextField();
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
        bottomPanel = new javax.swing.JPanel();
        lblCriterion = new javax.swing.JLabel("Criterio a añadir");
        lblPonderationCategory = new javax.swing.JLabel("Ponderación Categoria");
        textPonderationCategory = new javax.swing.JTextField();
        comboCriterion = new javax.swing.JComboBox();
        buttonAddCriterion = new javax.swing.JButton("Añadir");
        buttonEvaluated = new javax.swing.JButton("Criterio evaluado");
        
        modelCriterion=new DefaultListModel<>();
        modelValoration=new DefaultListModel<>();
    }
    
    private void configureComponents(){
        setLayout(new java.awt.GridBagLayout());
        upperPanel.setLayout(new java.awt.GridBagLayout());
        bottomPanel.setLayout(new java.awt.GridBagLayout());
        
        textPonderationCategory.setColumns(5);
        textPonderationCategory.setHorizontalAlignment(JTextField.CENTER);
        textPonderationCriterion.setColumns(5);
        lblPonderationCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCriterion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textPonderationCriterion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textPonderationCriterion.setToolTipText("Ponderación del criterio");
        textPonderationCategory.setToolTipText("Ponderación de la categoría");
        
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
        
        buttonEvaluated.setToolTipText("Marque el criterio como evaluado");
        buttonEvaluatedAll.setToolTipText("Evaluación de la granja finalizada");
        
        textPaneHelp.setContentType("text/html");
        textPaneHelp.setEnabled(false);
        textPaneHelp.setDisabledTextColor(Color.BLACK);
        scrollHelp.setViewportView(textPaneHelp);
        
        jListValorations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListValorations.setToolTipText("Valoraciones realizadas");
        scrollValoration.setViewportView(jListValorations);
        jListValorations.setModel(modelValoration);
        
        jListCriterion.setCellRenderer(new IconoListCellRenderer());
        jListCriterion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCriterion.setToolTipText("Lista de criterios seleccionados para evaluar");
        scrollCriterion.setViewportView(jListCriterion);
        jListCriterion.setModel(modelCriterion);
        
        comboValoration.setModel(
                new javax.swing.DefaultComboBoxModel(
                        new String[] { "1", "2", "3", "4", "5" }));
    }
    
    private void addEvents(){
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaEvaluacionCriterio");
        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaEvaluacionCriterio");
        
        textPonderationCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.setPonderationCategory(Float.parseFloat(textPonderationCategory.getText()));
            }
        });
        textPonderationCriterion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.setPonderationCriterion(Float.parseFloat(textPonderationCriterion.getText()));
            }
        });
        
        textPonderationCategory.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                textPonderationCategory.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                controller.setPonderationCategory(Float.parseFloat(textPonderationCategory.getText()));
            }
            
        });
        
        textPonderationCriterion.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                textPonderationCriterion.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                controller.setPonderationCriterion(Float.parseFloat(textPonderationCriterion.getText()));
            }
        });
        
        
        jListCriterion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListCriterionMousePressed();
                if (evt.getClickCount() == 3) {   // Triple-click
                    controller.removeCriterion(((IconList)jListCriterion.getSelectedValue()).getNombreCriterio());
                    modelCriterion.remove(jListCriterion.getSelectedIndex());
                }
            }
        });
        buttonEvaluatedAll.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluacionTerminada();
            }
        });
        buttonEvaluated.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEvaluatedActionPerformed();
            }
        });
        buttonAddCriterion.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCriterionActionPerformed();
            }
        });
        buttonHelp.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHelpActionPerformed();
            }
        });
        buttonAddValoration.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddValorationActionPerformed();
            }
        });
        jListValorations.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 3) {   // Triple-click
                    controller.removeValoration((String)jListValorations.getSelectedValue());
                }
            }
        });
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollValoration, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(scrollHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblNumberCows, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonEvaluated, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperPanel.add(lblValoration, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperPanel.add(lblPonderationCriterion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        upperPanel.add(buttonAddValoration, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperPanel.add(comboValoration, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperPanel.add(textPonderationCriterion, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperPanel.add(buttonHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(upperPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        bottomPanel.add(lblCriterion, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        bottomPanel.add(lblPonderationCategory, gridBagConstraints);

        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        bottomPanel.add(textPonderationCategory, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        bottomPanel.add(comboCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        bottomPanel.add(buttonAddCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 5);
        bottomPanel.add(buttonEvaluatedAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(bottomPanel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
//        add(scrollInformation, gridBagConstraints);
    }

    private void evaluacionTerminada(){
        controller.saveValoration();
    }
    
    private void jListCriterionMousePressed() {
        int selection=jListCriterion.getSelectedIndex();
        if(selection!=-1){
            controller.CriterionSelected(modelCriterion.getElementAt(selection).getNombreCriterio());
        }
    }

    private void buttonAddValorationActionPerformed() {
        String value=(String)comboValoration.getSelectedItem();
        controller.addValoration(Integer.parseInt(value));
        String item="Vaca "+String.valueOf(modelValoration.size()+1) 
                + " - Valoración: " + String.valueOf(Integer.parseInt(value));
        modelValoration.addElement(item);
    }           

    private void buttonHelpActionPerformed() {
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaEvaluacionCriterio");
        
    }

    private void buttonEvaluatedActionPerformed() {
        IconList criterion = this.modelCriterion.elementAt(jListCriterion.getSelectedIndex());
        criterion.setChecked();
        this.modelCriterion.set(jListCriterion.getSelectedIndex(),criterion);
        controller.criterionSaved();
    }

    private void buttonAddCriterionActionPerformed() {
        controller.addCriterion((String)comboCriterion.getSelectedItem());
    }
    
    
    @Override
    public void deshabilitarValoraciones() {
        buttonAddValoration.setEnabled(false);
        textPonderationCriterion.setEnabled(false);
        buttonEvaluated.setEnabled(false);
    }

    @Override
    public void habilitarValoraciones() {
        buttonAddValoration.setEnabled(true);
        textPonderationCriterion.setEnabled(true);
        buttonEvaluated.setEnabled(true);
    }

    @Override
    public void setHelp(String help) {
        StringBuilder text=new StringBuilder();
        text.append("<html><body>");
        text.append(text);
        text.append("</body></html>");
        textPaneHelp.setText(help);
    }
    
    @Override
    public void setController(EvaluationControllerCriterion controller) {
        this.controller=controller;
    }

    @Override
    public Category getSelectedCategory() {
        return category;
    }

    @Override
    public void addValoration(String valoration) {
        modelValoration.addElement(valoration);
    }

    @Override
    public void addCriterion(String criterion) {
        IconList element=new IconList(criterion);
        boolean contains=false;
        for (int i = 0; i < modelCriterion.size() && !contains; i++) {
            IconList iconList = modelCriterion.get(i);
            if(iconList.getNombreCriterio().compareToIgnoreCase(criterion)==0){
                contains=true;
            }
        }
        if(!contains){
            modelCriterion.addElement(element);
        }
    }

    @Override
    public void removeCriterion(String criterion) {
//        modelCriterion.removeElement(criterion);
//        jListCriterion.setModel(modelCriterion);
    }

    @Override
    public void removeValorations() {
        modelValoration.clear();
    }

    @Override
    public void setModelValoration(DefaultListModel list) {
        modelValoration=list;
        jListValorations.setModel(list);
    }

    @Override
    public void setModelCriterion(DefaultListModel list) {
        modelCriterion=list;
        jListCriterion.setModel(list);
    }

    @Override
    public void setPonderationCriterion(float ponderation) {
        textPonderationCriterion.setText(String.valueOf(ponderation));
    }

    @Override
    public void setPonderationCategory(float ponderation) {
        textPonderationCategory.setText(String.valueOf(ponderation));
    }
    
    @Override
    public void setComboCriterion(Category cat, LinkedList<String> list) {
        if(cat==this.category){
            comboCriterion.removeAllItems();
            for (String string : list) {
                comboCriterion.addItem(string);
            }
        }
    }
    
    private javax.swing.JButton buttonAddCriterion;
    private javax.swing.JButton buttonAddValoration;
    private javax.swing.JButton buttonEvaluated;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JComboBox comboCriterion;
    private javax.swing.JComboBox comboValoration;
    private javax.swing.JList jListCriterion;
    private javax.swing.JList jListValorations;
    private javax.swing.JPanel upperPanel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JLabel lblCriterion;
    private javax.swing.JLabel lblNumberCows;
    private javax.swing.JLabel lblPonderationCategory;
    private javax.swing.JLabel lblPonderationCriterion;
    private javax.swing.JLabel lblValoration;
    private javax.swing.JScrollPane scrollCriterion;
    private javax.swing.JScrollPane scrollHelp;
//    private javax.swing.JScrollPane scrollInformation;
    private javax.swing.JScrollPane scrollValoration;
//    private javax.swing.JPanel panelInformation;
    private javax.swing.JTextPane textPaneHelp;
    private javax.swing.JTextField textPonderationCategory;
    private javax.swing.JTextField textPonderationCriterion;
    private javax.swing.JButton buttonEvaluatedAll;

    @Override
    public void setInformation(String... information) {
//        panelInformation.removeAll();
//        for (String string : information) {
//            panelInformation.add(new JLabel(string));
//        }
    }

    /**
     *
     * @param cat
     * @param list
     */
    @Override
    public void setCriterion(Category cat, LinkedList<String> list) {
        if(category==cat){
            DefaultListModel<IconList> listIcon=new DefaultListModel<>();
            for (String string : list) {
                listIcon.addElement(new IconList(string));
            }
            setModelCriterion(listIcon);
        }
    }

    @Override
    public void addFilePanel(String file) {
        
    }

    @Override
    public void setListFiles(List<String> list) {
        
    }

}
