/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.japura.gui.CheckComboBox;

/**
 *
 * @author dorian
 */
public class PanelCategory extends JPanel {
    private JButton finishEvaluation;
    private JButton addCriterion;
    private CheckComboBox comboCriterion;
    
    private JLabel ponderationLabel;
    private JTextField ponderationText;
    
    private IEvaluationCriterionController controller;

    public PanelCategory() {
        initComponents();
    }
    
    public List<Object> getSelectedCriterion(){
        return comboCriterion.getModel().getCheckeds();
    }
    
    public void setListCriterion(List<String> list){
        comboCriterion.getModel().clear();
        for (String element : list) {
            comboCriterion.getModel().addElement(element);
        }
    }
    
    public void setColorPonderation(Color color){
        ponderationText.setForeground(color);
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }
    
    public void setPonderation(float pon){
        ponderationText.setText(Float.toString(pon));
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        finishEvaluation=new JButton("Evaluación terminada");
        addCriterion = new JButton("Añadir");
        comboCriterion = new CheckComboBox();
        
        ponderationLabel=new JLabel("Ponderación categoría");
        ponderationText=new JTextField("1.0");
        
        Insets in=ponderationText.getInsets();
        in.left=15;
        in.right=15;
        in.top=0;
        in.bottom=0;
        ponderationText.setMargin(in);
    }

    private void configureComponents() {
        comboCriterion.setTextFor(CheckComboBox.NONE, "* no criterio seleccionado *");
        comboCriterion.setTextFor(CheckComboBox.MULTIPLE, "* múltiples criterios *");
        comboCriterion.setTextFor(CheckComboBox.ALL, "* todos los criterios *");
    }

    private void addEvents() {
        finishEvaluation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.finishEvaluation();
            }
        });
        
        addCriterion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.addCriterions();
            }
        });
        
        ponderationText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setCategoryPonderation(ponderationText.getText());
            }
        });
    }
    

    private void addLayout() {
        setLayout(new GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(comboCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor=GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(addCriterion, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(finishEvaluation, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(ponderationLabel, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(ponderationText, gridBagConstraints);
    }
}
