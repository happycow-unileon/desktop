package es.unileon.happycow.gui.evaluation;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author antonio
 */
public class IconoListCellRenderer extends JLabel implements ListCellRenderer {

    public IconoListCellRenderer() {
        this.setOpaque(true);
        this.setIconTextGap(10);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        IconList emo = (IconList) value;
        this.setText(emo.getNombreCriterio());
        this.setIcon(emo.getImagenCheck());
        
        if (isSelected) {
            this.setBackground(Color.GRAY);
            this.setForeground(Color.WHITE);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        return this;
    }
}
