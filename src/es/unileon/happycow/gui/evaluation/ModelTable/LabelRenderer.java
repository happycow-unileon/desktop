/*
 * 
 */
package es.unileon.happycow.gui.evaluation.ModelTable;

import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author dorian
 */
public class LabelRenderer extends JLabel
        implements TableCellRenderer {

    /**
     * Create the ButtonColumn to be used as a renderer and editor. The renderer
     * and editor will automatically be installed on the TableColumn of the
     * specified column.
     *
     * @param table the table containing the button renderer/editor
     * @param column the column to which the button renderer/editor is added
     */
    public LabelRenderer() {
        setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    }


    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setText((String)value);
        return this;
    }
}
