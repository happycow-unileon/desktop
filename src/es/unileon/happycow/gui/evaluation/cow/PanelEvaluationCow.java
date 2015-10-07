package es.unileon.happycow.gui.evaluation.cow;

import es.unileon.happycow.controller.evaluation.IEvaluationCowController;
import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.gui.evaluation.criterion.PanelFileList;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdHandler;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;

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
    
    public void setComboCriterion(DefaultComboBoxModel list){
        comboCriterion.setModel(list);
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
    
    public void removeValoration(String id) {
        int index = -1;
        //if is not the selected criterion, lets find it
        for (int i = 0; i < modelValorations.getSize() & index < 0; i++) {
            String o = modelValorations.getElementAt(i);
            if (o.compareTo(id) == 0) {
                index = i;
            }
        }
        if (index < 0) {
            modelValorations.remove(index);
        }
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        listCows=new JList<String>();
        listValorations=new JList<String>();
        panelPonderation=new PanelPonderations();
        
        btnAddValoration = new javax.swing.JButton();
        comboCategory = new javax.swing.JComboBox();
        comboCriterion = new javax.swing.JComboBox();
        comboValoration = new javax.swing.JComboBox();
        
        addFile = new JButton();
        JComponent[] files = {addFile};
        panelFileList = new PanelFileList(files);
    }

    private void configureComponents() {
        addFile.setText("Añadir");
        btnAddValoration.setText("Valorar");
        panelFileList.setTitleMargin(new Insets(5, 5, 20, 5));
        panelFileList.setTitle("Ficheros");
        
        comboValoration.setModel(new javax.swing.DefaultComboBoxModel(
                        new String[]{"1", "2", "3", "4", "5"}));
        
        comboCategory.setModel(new DefaultComboBoxModel(Category.getArrayString()));
        
    }

    private void addEvents() {
        addFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addFile();
            }
        });
        
        comboCategory.
    }

    private void addLayout() {
        setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints;

        btnAddValoration.setText("Añadir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(btnAddValoration, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCategory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboValoration, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelPonderation, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(listValorations, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(listCows, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelFileList, gridBagConstraints);

    }

    private javax.swing.JButton btnAddValoration;
    private javax.swing.JComboBox comboCategory;
    private javax.swing.JComboBox comboCriterion;
    private javax.swing.JComboBox comboValoration;
    private JList listCows;
    private JList listValorations;
    private PanelPonderations panelPonderation;
    private PanelFileList panelFileList;
    private JButton addFile;
}
