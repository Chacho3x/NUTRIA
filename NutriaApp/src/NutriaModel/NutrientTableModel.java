/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NutriaModel;

import NutriaLib.LinkLabel;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {"id", "Nombre", "Unidad", "Acciones"};
    private List<Nutrient> nutrientList;
    private Object[] actions;
    
    public NutrientTableModel(List<Nutrient> nutrientList) {
        this.nutrientList = nutrientList;
        this.actions = new Object[nutrientList.size()];
    }
    
    public int getRowCount() {
        return nutrientList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Nutrient nutrient = nutrientList.get(rowIndex);
        Object[] values=new Object[] {
            nutrient.getId(),
            nutrient.getName(),
            nutrient.getUnit(),
            actions[rowIndex]
        };
        return values[columnIndex];
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            actions[rowIndex] = (LinkLabel)aValue;
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}
