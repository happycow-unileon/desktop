package es.unileon.happycow.gui.evaluation.cow;

import es.unileon.happycow.handler.Category;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author dorian
 */
public class PanelPonderations {
    private JComboBox<String> comboCategory;
    private JComboBox<String> comboCriterion;
    private JTextField ponderationCategory;
    private JTextField ponderationCriterion;
    private JLabel labelPonderationCriterion;
    private JLabel labelPonderationCategory;
    
    public void setModelCriterion(ComboBoxModel<String> model){
        comboCriterion.setModel(model);
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
        comboCategory.
        
        labelPonderationCategory.setText("Ponderación categoría: ");
        labelPonderationCriterion.setText("Ponderación criterio: ");
        
        
    }
    
    private void addEvents(){
        
    }
    
    private void addLayout(){
        
    }
}
