/*
 * 
 */
package es.unileon.happycow.gui;

import es.unileon.happycow.model.InformationEvaluation;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author dorian
 */
public class EvaluationListRenderer extends DefaultListCellRenderer{
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         if (value instanceof InformationEvaluation) {
            InformationEvaluation info = (InformationEvaluation)value;
            String date=info.getFecha().toString();
            date=String.valueOf(index)+ ": " + date+
                    " -> Valoración: "+ String.valueOf((int)info.getNota());
             setText(date);
        }
        return this;
    }
}
