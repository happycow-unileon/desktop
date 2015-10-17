package es.unileon.happycow.gui.evaluation.cow;

import es.unileon.happycow.controller.evaluation.IEvaluationCowController;
import es.unileon.happycow.gui.evaluation.criterion.PanelFileList;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.japura.gui.TitlePanel;

/**
 *
 * @author dorian
 */
public class PanelEvaluationCow extends javax.swing.JPanel {

    private IEvaluationCowController controller;
    private DefaultListModel<String> modelCows;
    private DefaultListModel<String> modelValorations;

    /**
     * Creates new form PanelEvaluationCow
     */
    public PanelEvaluationCow() {
        initComponents();
    }

    public void setController(IEvaluationCowController controller) {
        this.controller = controller;
        panelPonderation.setController(controller);
        panelFileList.setController(controller);
    }

    public void setModelCows(DefaultListModel<String> modelCows) {
        this.modelCows = modelCows;
    }

    public void setModelValorations(DefaultListModel<String> modelValorations) {
        this.modelValorations = modelValorations;
    }

    public void removeCow(int position) {
        modelCows.remove(position);
    }

    public void setComboCriterion(LinkedList<String> list) {
        comboCriterion.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    public void setComboCriterionPonderation(LinkedList<String> list) {
        panelPonderation.setModelCriterion(list);
    }

    public Category getSelectedCategory() {
        Category result;
        int index = comboCategory.getSelectedIndex();
        result = Category.getEnum(index);
        return result;
    }
    public void addCow(){
        int size=modelCows.getSize();
        modelCows.addElement("Vaca ".concat(Integer.toString(size+1)));
    }

    public IdHandler getCriterion() {
        return new IdCriterion((String) comboCriterion.getSelectedItem());
    }

    public IdHandler getCategory() {
        return new IdCategory((String) comboCategory.getSelectedItem());
    }

    public float getValoration() {
        return (float) comboValoration.getSelectedIndex() + 1;
    }

    public int getNumberCows() {
        return listCows.getModel().getSize();
    }

    public int getSelectedCow() {
        return listCows.getSelectedIndex();
    }
    
    public void setSelectedCow(int index){
        listCows.setSelectedIndex(index);
    }

    public void setNumberCows(int cows) {
        listCowsPanel.setTitle("Mínimo ".concat(Integer.toString(cows)).concat(" vacas"));
    }

    public void setColorPonderationCriterion(Color color) {
        panelPonderation.setColorPonderationCriterion(color);
    }

    public void setColorPonderationCategory(Color color) {
        panelPonderation.setColorPonderationCategory(color);
    }

    public void setPonderationCategory(float ponderation) {
        panelPonderation.setPonderationCategory(ponderation);
    }

    public void setPonderationCriterion(float ponderation) {
        panelPonderation.setPonderationCriterion(ponderation);
    }

    public IdHandler getCategoryPonderationSelected() {
        return panelPonderation.getCategorySelected();
    }

    public IdHandler getCriterionPonderationSelected() {
        return panelPonderation.getCriterionSelected();
    }

    /**
     * Seteo la lista de ficheros que tiene el modelo
     *
     * @param files
     */
    public void setFileList(List<String> files) {
        panelFileList.addFileList(files);
    }

    public void addFile(String file) {
        panelFileList.addFile(file);
    }

    public void removeFile(IdHandler file) {
        panelFileList.removeFile(file.getValue());
        //panelFileList.revalidate();
    }

    public void removeValoration(String id) {
        int index = -1;
        //if is not the selected criterion, lets find it
        for (int i = 0; i < modelValorations.getSize() & index < 0; i++) {
            String o = modelValorations.getElementAt(i);
            if (o.compareTo(id) == 0) {
                index = i;
            }
        }
        if (index >= 0) {
            modelValorations.remove(index);
        }
    }
    
    public String getSelectedValoration(){
        if(listValorations.isSelectionEmpty()){
            return "";
        }
        String valoration=(String)listValorations.getSelectedValue();
        return valoration;
    }
    
    
    public void setValorations(List<String> valorations){
        modelValorations.clear();
        for (String valoration : valorations) {
            modelValorations.addElement(valoration);
        }
    }
    
    public void clearValorations(){
        modelValorations.clear();
    }

    public void addValoration(String valoration){
        modelValorations.addElement(valoration);
    }
    
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        modelCows=new DefaultListModel<>();
        modelValorations=new DefaultListModel<>();
        
        btnHelp=new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
        btnCriterionHelp=new javax.swing.JButton(new javax.swing.ImageIcon(
                getClass().getResource("/images/help.png")));
        
        listCows = new JList<>(modelCows);
        listValorations = new JList<>(modelValorations);
        panelPonderation = new PanelPonderations();

        btnAddValoration = new javax.swing.JButton();
        comboCategory = new javax.swing.JComboBox();
        comboCriterion = new javax.swing.JComboBox();
        comboValoration = new javax.swing.JComboBox();

        addFile = new JButton();
        JComponent[] files = {addFile};
        panelFileList = new PanelFileList(files);
        

        Image img = new javax.swing.ImageIcon(
                getClass().getResource("/images/add.png")).getImage();
        Image resized = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        btnAddCow = new javax.swing.JLabel(new javax.swing.ImageIcon(resized));
        
        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/copy.png")).getImage();
        resized = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        btnCloneCow = new javax.swing.JLabel(new javax.swing.ImageIcon(resized));
        
        JComponent[] components = {btnCloneCow, btnAddCow};
        listCowsPanel = new TitlePanel("", components);

        img = new javax.swing.ImageIcon(
                getClass().getResource("/images/delete.png")).getImage();
        resized = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        btnRemoveValoration = new javax.swing.JLabel(new javax.swing.ImageIcon(resized));
        JComponent[] components2 = {btnRemoveValoration};
        panelListValorations = new TitlePanel("", components2);
        
        btnFinish=new JButton("Evaluación terminada");
    }

    private void configureComponents() {
        addFile.setText("Añadir");
        btnAddValoration.setText("Añadir");
        btnAddCow.setToolTipText("Añadir nueva vaca");
        
        panelListValorations.setTitleMargin(new Insets(5,10,10,10));
        listValorations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCowsPanel.setTitleMargin(new Insets(5,10,10,10));

        btnCloneCow.setToolTipText("Duplicar vaca");
        btnAddValoration.setText("Valorar");

        panelFileList.setTitleMargin(new Insets(5, 5, 20, 5));
        panelFileList.setTitle("Ficheros");
        panelListValorations.setTitle("Valoraciones");

        comboValoration.setModel(new javax.swing.DefaultComboBoxModel(
                new String[]{"1", "2", "3", "4", "5"}));

        comboCategory.setModel(new DefaultComboBoxModel(Category.getArrayString()));
        
        btnHelp.setBorderPainted(false);
        btnHelp.setContentAreaFilled(false);
        btnHelp.setFocusPainted(false);
        
        btnCriterionHelp.setBorderPainted(false);
        btnCriterionHelp.setContentAreaFilled(false);
        btnCriterionHelp.setFocusPainted(false);

    }

    private void addEvents() {
        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addFile();
            }
        });

        comboCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.changeCategory();
            }
        });

        btnAddValoration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addNewValoration();
            }
        });

        btnAddCow.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.addCow();
            }
        });
        
        btnRemoveValoration.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                controller.removeValoration(null);
            }
        });

        btnCloneCow.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.duplicateCow();
            }
        });
        
        listCows.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                controller.selectedCow();
            }
        });
        
        btnFinish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.finishEvaluation();
            }
        });
        
        btnCriterionHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.help(getCriterion());
            }
        });
        
        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpSystem.getInstance().seeHelp(HelpTheme.EvaluationCow);
            }
        });
    }

    private void addLayout() {
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnHelp, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(btnFinish, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelPonderation, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
//        gridBagConstraints.fill=java.awt.GridBagConstraints.HORIZONTAL;
        add(btnCriterionHelp, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        add(btnAddValoration, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCriterion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboValoration, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelListValorations, gridBagConstraints);
        panelListValorations.add(listValorations);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(listCowsPanel, gridBagConstraints);
        listCowsPanel.add(listCows);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelFileList, gridBagConstraints);

    }

    private javax.swing.JButton btnAddValoration;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnCriterionHelp;
    private javax.swing.JButton btnHelp;
    private javax.swing.JLabel btnAddCow;
    private javax.swing.JLabel btnRemoveValoration;
    private javax.swing.JLabel btnCloneCow;
    private javax.swing.JComboBox comboCategory;
    private javax.swing.JComboBox comboCriterion;
    private javax.swing.JComboBox comboValoration;
    private TitlePanel listCowsPanel;
    private JList listCows;
    private TitlePanel panelListValorations;
    private JList listValorations;
    private PanelPonderations panelPonderation;
    private PanelFileList panelFileList;
    private JButton addFile;
}
