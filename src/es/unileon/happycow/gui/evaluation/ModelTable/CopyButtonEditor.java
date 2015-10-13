/*
 * 
 */
package es.unileon.happycow.gui.evaluation.ModelTable;

import es.unileon.happycow.controller.evaluation.IEvaluationCriterionController;
import es.unileon.happycow.model.composite.Valoration;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author dorian
 */
public class CopyButtonEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener, MouseListener {

    private JButton buttonCopy;
    private ModelTable model;
    private JTable table;
    private boolean isButtonColumnEditor;
    private IEvaluationCriterionController controller;

    public CopyButtonEditor(ModelTable model, JTable table, IEvaluationCriterionController controller) {
        buttonCopy = new JButton(new javax.swing.ImageIcon(getClass().getResource("/images/copy.png")));
        buttonCopy.setToolTipText("Copiar");
        buttonCopy.setBorderPainted(false);
        buttonCopy.setContentAreaFilled(false);
        buttonCopy.setFocusPainted(false);
        buttonCopy.addActionListener(this);

        this.table = table;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public Object getCellEditorValue() {
        return buttonCopy;
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject eo) {
        return true;
    }

    /*
     *	The button has been pressed. Stop editing and invoke the custom Action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        Valoration val = model.getValoration(row);
        fireEditingStopped();

        if (isButtonColumnEditor) {
            controller.copyValoration(val.getId());
        }

    }

    /*
     *  When the mouse is pressed the editor is invoked. If you then then drag
     *  the mouse to another cell before releasing it, the editor is still
     *  active. Make sure editing is stopped when the mouse is released.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (table.isEditing()
                && table.getCellEditor() == this) {
            isButtonColumnEditor = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isButtonColumnEditor
                && table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }

        isButtonColumnEditor = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {

        return buttonCopy;
    }

}
