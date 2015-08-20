/*
 * 
 */
package es.unileon.happycow.gui.evaluation2.valoration;

import es.unileon.happycow.gui.evaluation2.EvaluationController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author dorian
 */
public class PanelValorationList extends JPanel implements IValorationButton {
    private EvaluationController controller;
    private LinkedList<PanelValoration> valorationPanels;

    public PanelValorationList() {
        super(null);
        initComponents();
    }

    public void addListValoration(List<Valoration> valorations) {
        for (Valoration val : valorations) {
            addValoration(val);
        }
    }

    public void addValoration(Valoration val) {
        PanelValoration panel = new PanelValoration(val.getId());
        panel.setName("Valoración: ".concat(Float.toString(val.getNota())));
        panel.setController(this);

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

    @Override
    public void remove(IdHandler id) {
        //remove in model
        controller.removeValoration(id);
        //remove in gui
        removeValoration(id);
    }
    
    @Override
    public void copy(IdHandler valoration) {
        //copy in model, the model will come back to add the new valoration
        controller.copyValoration(valoration);
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
        list.setLayout(new GridLayout(0, 1));
    }

    private void addEvents() {

    }

    private void addLayout() {
        scrollList.add(list);
        add(scrollList);
    }
    

    private JScrollPane scrollList;
    private JPanel list;

    
}
