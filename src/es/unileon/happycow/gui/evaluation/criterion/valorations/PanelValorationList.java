/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion.valorations;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.japura.gui.TitlePanel;

/**
 *
 * @author dorian
 */
public class PanelValorationList extends TitlePanel {

    private IEvaluationCriterionController controller;
    private String minCows;
    private ModelTable modelList;

    public PanelValorationList(JComponent[] components) {
        super("", components);
        initComponents();
        minCows = "";
    }

    public void setTitle(int valorations) {
        super.setTitle(Integer.toString(valorations).concat(" valoraciones ").concat(minCows));
    }

    public void setMinimunCows(int cows) {
        minCows = "(mínimo ".concat(Integer.toString(cows)).concat(")");
    }

    public void setController(IEvaluationCriterionController controller) {
        this.controller = controller;
        
        CopyButtonEditor editorCopy=new CopyButtonEditor(modelList, table, controller);
        table.setDefaultEditor(CopyButtonRenderer.class, editorCopy);
        table.addMouseListener(editorCopy);
        
        RemoveButtonEditor editorRemove=new RemoveButtonEditor(modelList, table, controller);
        table.setDefaultEditor(RemoveButtonRenderer.class, editorRemove);
        table.addMouseListener(editorRemove);
    }

    public void setListValoration(List<Valoration> valorations) {
        modelList.clearValorations();
        for (Valoration valoration : valorations) {
            addValoration(valoration);
        }
    }

    public void addValoration(Valoration val) {
        modelList.addValoration(val);
    }

    public void removeValoration(IdHandler val) {
        modelList.removeValoration(val);
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        scrollList = new JScrollPane();
        table = new JTable();
        modelList = new ModelTable();
    }

    private void configureComponents() {
        table.setModel(modelList);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setMaxWidth(40);
        table.getColumnModel().getColumn(2).setMaxWidth(40);
        table.setDefaultRenderer(CopyButtonRenderer.class, new CopyButtonRenderer());
        table.setDefaultRenderer(RemoveButtonRenderer.class, new RemoveButtonRenderer());
        table.setDefaultRenderer(String.class, new LabelRenderer());
        table.setTableHeader(null);
        
        scrollList.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollList.setViewportView(table);
    }

    private void addEvents() {
    }

    private void addLayout() {
        setLayout(new BorderLayout());
        add(scrollList, BorderLayout.CENTER);
    }

    private JScrollPane scrollList;
    private JTable table;

}
