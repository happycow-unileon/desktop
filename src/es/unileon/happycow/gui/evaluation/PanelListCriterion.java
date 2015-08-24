/*
 * 
 */
package es.unileon.happycow.gui.evaluation;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.japura.gui.TitlePanel;

/**
 *
 * @author dorian
 */
public class PanelListCriterion extends TitlePanel {
    private IEvaluationCriterionController controller;
    private DefaultListModel<IconList> modelCriterion;
    private javax.swing.JList jListCriterion;
    private javax.swing.JScrollPane scrollCriterion;

    public PanelListCriterion(JComponent[] components) {
        super("", components);
        modelCriterion = new DefaultListModel<>();
        initComponents();
    }

    public void addComponent(JComponent component) {
        this.add(component);
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
    }

    public void removeCriterion(String id) {
        int index = -1;
        //if is not the selected criterion, lets find it
        for (int i = 0; i < modelCriterion.getSize() & index < 0; i++) {
            IconList o = modelCriterion.getElementAt(i);
            if (o.getNombreCriterio().compareTo(id.toString()) == 0) {
                index = i;
            }
        }
        if (index < 0) {
            modelCriterion.remove(index);
        }
    }

    public void setCriterionEvaluated(String id, boolean evaluated) {
        boolean found = false;

        int index = jListCriterion.getSelectedIndex();
        if (index >= 0) {
            IconList o = modelCriterion.getElementAt(index);
            if (o.getNombreCriterio().compareTo(id) == 0) {
                found = true;
                o.setChecked(evaluated);
            }
        }

        //if is not the selected criterion, lets find it
        for (int i = 0; i < modelCriterion.getSize() & !found; i++) {
            IconList o = modelCriterion.getElementAt(i);
            if (o.getNombreCriterio().compareTo(id.toString()) == 0) {
                o.setChecked(evaluated);
                found = true;
            }
        }

    }

    public boolean getCriterionEvaluated(String id) {
        boolean result = false;
        boolean found = false;

        int index = jListCriterion.getSelectedIndex();
        if (index >= 0) {
            IconList o = modelCriterion.getElementAt(index);
            if (o.getNombreCriterio().compareTo(id) == 0) {
                found = true;
                result = o.isChecked();
            }
        }

        //if is not the selected criterion, lets find it
        for (int i = 0; i < modelCriterion.getSize() & !found; i++) {
            IconList o = modelCriterion.getElementAt(i);
            if (o.getNombreCriterio().compareTo(id.toString()) == 0) {
                result = o.isChecked();
                found = true;
            }
        }

        return result;
    }

    public void setListCriterion(LinkedList<String> list) {
        modelCriterion.clear();
        for (String criterion : list) {
            addCriterion(criterion);
        }
    }

    public void addCriterion(String criterion) {
        IconList element = new IconList(criterion);
        boolean contains = false;
        for (int i = 0; i < modelCriterion.size() && !contains; i++) {
            IconList iconList = modelCriterion.get(i);
            if (iconList.getNombreCriterio().compareToIgnoreCase(criterion) == 0) {
                contains = true;
            }
        }
        if (!contains) {
            modelCriterion.addElement(element);
        }
    }

    public String getCriterion() {
        int index = jListCriterion.getSelectedIndex();
        if (index < 0) {
            return null;
        } else {
            return modelCriterion.getElementAt(index).getNombreCriterio();
        }
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvent();
        addLayout();
    }

    private void createComponents() {
        scrollCriterion = new javax.swing.JScrollPane();
        jListCriterion = new javax.swing.JList();
        modelCriterion = new DefaultListModel<>();
    }

    private void configureComponents() {
        setLayout(new java.awt.GridBagLayout());

        jListCriterion.setCellRenderer(new IconoListCellRenderer());
        jListCriterion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCriterion.setToolTipText("Lista de criterios seleccionados para evaluar");
        scrollCriterion.setViewportView(jListCriterion);
        scrollCriterion.setBorder(null);

        jListCriterion.setModel(modelCriterion);
    }

    private void addEvent() {
        jListCriterion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {   // right click
                    //conceptual menu
//                    controller.removeCriterion(((IconList)jListCriterion.getSelectedValue()).getNombreCriterio());
//                    modelCriterion.remove(jListCriterion.getSelectedIndex());
                }else{
                    jListCriterionMousePressed();
                }
            }
        });
    }

    private void addLayout() {
        add(scrollCriterion);
    }

    private void jListCriterionMousePressed() {
        int selection = jListCriterion.getSelectedIndex();
        if (selection != -1) {
            controller.criterionSelected();
        }
    }

}
