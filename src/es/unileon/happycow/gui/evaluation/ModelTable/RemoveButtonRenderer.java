/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion.valorations;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author dorian
 */
public class RemoveButtonRenderer extends JButton
        implements TableCellRenderer {

    /**
     * Create the ButtonColumn to be used as a renderer and editor. The renderer
     * and editor will automatically be installed on the TableColumn of the
     * specified column.
     *
     * @param table the table containing the button renderer/editor
     * @param column the column to which the button renderer/editor is added
     */
    public RemoveButtonRenderer() {
        this.setIcon(new javax.swing.ImageIcon
                        (getClass().getResource("/images/unchecked.png")));
       
        this.setToolTipText("Eliminar");
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
    }


    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
