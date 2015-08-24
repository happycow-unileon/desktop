/*
 * 
 */
package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.japura.gui.TitlePanel;

/**
 *
 * @author dorian
 */
public class PanelValorationList extends TitlePanel{
    private IEvaluationCriterionController controller;
    private LinkedList<PanelValoration> valorationPanels;

    public PanelValorationList(JComponent[] components) {
        super("", components);
        initComponents();
        
        valorationPanels=new LinkedList<>();
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }
    
    public void setListValoration(List<Valoration> valorations){
        valorationPanels.clear();
        addListValoration(valorations);
    }

    public void addListValoration(List<Valoration> valorations) {
        for (Valoration val : valorations) {
            addValoration(val);
        }
    }

    public void addValoration(Valoration val) {
        PanelValoration panel = new PanelValoration(val.getId());
        panel.setTextValoration("Valoración: ".concat(Float.toString(val.getNota())));
        panel.setController(controller);

        valorationPanels.add(panel);
        list.add(panel);
    }

    public void removeValoration(IdHandler val) {
        boolean removed=false;
        for (Iterator<PanelValoration> iterator = valorationPanels.iterator(); iterator.hasNext() && !removed;) {
            PanelValoration next = iterator.next();
            if(next.getValoration().compareTo(val)==0){
                //remove the list from panel
                list.remove(next);
                //remove from list
                iterator.remove();
                removed=true;
            }
        }
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        scrollList = new JScrollPane();
        list=new JPanel();
    }

    private void configureComponents() {
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        scrollList.setViewportView(list);
        scrollList.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        
    }

    private void addEvents() {

    }

    private void addLayout() {
        setLayout(new BorderLayout());
        add(scrollList, BorderLayout.CENTER);
    }
    

    private JScrollPane scrollList;
    private JPanel list;

    
}
