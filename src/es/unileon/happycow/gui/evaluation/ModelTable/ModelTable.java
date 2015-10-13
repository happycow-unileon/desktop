/*
 * 
 */
package es.unileon.happycow.gui.evaluation.ModelTable;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Valoration;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dorian
 */
public class ModelTable extends AbstractTableModel {

    ArrayList<Valoration> data;

    private javax.swing.JButton buttonCopy;
    private javax.swing.JButton buttonRemove;

    public ModelTable() {
        buttonCopy = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();
        data = new ArrayList<>();
    }
    
    public Valoration getValoration(int index){
        return data.get(index);
    }

    public void addValoration(Valoration val) {
        data.add(val);

        this.fireTableRowsInserted(this.getRowCount() - 1, this.getRowCount() - 1);
    }
    
    public void clearValorations(){
        data.clear();
    }

    public void removeValoration(IdHandler val) {
        int index = -1;
        for (int i = 0; i < data.size() && index == -1; i++) {
            Valoration get = data.get(i);
            if (get.getId().compareTo(val) == 0) {
                index = i;
            }
        }
        data.remove(index);
        this.fireTableRowsDeleted(index, index);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column > 0;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return CopyButtonRenderer.class;
            case 2:
                return RemoveButtonRenderer.class;
            default:
                return Object.class;
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        String result = "";
        switch (columnIndex) {
            case 0:
                result = "Valoration";
                break;
            case 1:
                result = "Copy";
                break;
            case 2:
                result = "Remove";
                break;
        }
        return result;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Valoration val = data.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = "Valoración: ".concat(Float.toString(val.getNota()));
                break;
            case 1:
                value = buttonCopy;
                break;

            case 2:
                value = buttonRemove;
                break;
        }

        return value;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //nothing!!
    }

}
