package es.unileon.happycow.gui.evaluation.cow;

import es.unileon.happycow.controller.evaluation.IEvaluationCowController;
import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdCategory;
import es.unileon.happycow.handler.IdCriterion;
import es.unileon.happycow.handler.IdHandler;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author dorian
 */
public class PanelPonderations extends JPanel {
    private IEvaluationCowController controller;
    
    private JComboBox<String> comboCategory;
    private JComboBox<String> comboCriterion;
    private JTextField ponderationCategory;
    private JTextField ponderationCriterion;
    private JLabel labelPonderationCriterion;
    private JLabel labelPonderationCategory;

    public PanelPonderations() {
        initComponents();
    }
    
    public void setController(IEvaluationCowController controller){
        this.controller=controller;
    }
    
    public void setModelCriterion(LinkedList<String> model){
        DefaultComboBoxModel modelCombo=new DefaultComboBoxModel(model.toArray());
        comboCriterion.setModel(modelCombo);
        comboCriterion.setSelectedIndex(0);
    }
    
    public void setColorPonderationCriterion(Color color){
        ponderationCriterion.setForeground(color);
    }
    public void setColorPonderationCategory(Color color){
        ponderationCategory.setForeground(color);
    }
    public void setPonderationCategory(float ponderation){
        ponderationCategory.setText(Float.toString(ponderation));
    }
    public void setPonderationCriterion(float ponderation){
        ponderationCriterion.setText(Float.toString(ponderation));
    }
    
    public IdHandler getCategorySelected(){
        IdHandler category=new IdCategory((String)comboCategory.getSelectedItem());
        return category;
    }
    
    public IdHandler getCriterionSelected(){
        if(comboCriterion.getSelectedIndex()<0){
            return null;
        }
        Object selected=comboCriterion.getSelectedItem();
        IdHandler criterion=new IdCriterion((String)selected);
        return criterion;
    }
    
    private void initComponents(){
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        comboCategory=new JComboBox<>();
        comboCriterion=new JComboBox<>();
        ponderationCategory=new JTextField("1.0");
        ponderationCriterion= new JTextField("1.0");
        labelPonderationCategory=new JLabel();
        labelPonderationCriterion=new JLabel();
    }
    
    private void configureComponents(){
        Insets in=ponderationCategory.getInsets();
        in.left=15;
        in.right=15;
        in.top=0;
        in.bottom=0;
        ponderationCategory.setMargin(in);
        
        in=ponderationCriterion.getInsets();
        in.left=15;
        in.right=15;
        in.top=0;
        in.bottom=0;
        ponderationCriterion.setMargin(in);
        
        ComboBoxModel modelCategory=new DefaultComboBoxModel(Category.getArrayString());
        comboCategory.setModel(modelCategory);
        
        
        labelPonderationCategory.setText("Ponderación categoría: ");
        labelPonderationCriterion.setText("Ponderación criterio: ");
        
        ponderationCriterion.setToolTipText("Introduzca ponderación para el criterio");
        ponderationCategory.setToolTipText("Introduzca ponderación para la categoría");
        
        
    }
    
    private void addEvents(){
        ponderationCriterion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                controller.setPonderationCriterion(getCriterionSelected(), ponderationCriterion.getText());
            }
        });
        
        ponderationCategory.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                controller.setCategoryPonderation(getCategorySelected(),ponderationCategory.getText());
            }
        });
        
        comboCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.selectedCategoryPonderation();
            }
        });
        
        comboCriterion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controller.selectedCriterionPonderation();
            }
        });
    }
    
    private void addLayout(){
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        add(labelPonderationCategory);
        add(comboCategory);
        add(ponderationCategory);
        
        add(labelPonderationCriterion);
        add(comboCriterion);
        add(ponderationCriterion);
    }
}
