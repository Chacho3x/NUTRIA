/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NutriaModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientsByIngredientTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {"id", "Nutriente", "Cant."};
    private List<NutrientIngredient> nutrientList;
    
    public NutrientsByIngredientTableModel() {
        nutrientList = new ArrayList();
    }
    
    public NutrientsByIngredientTableModel(List<NutrientIngredient> nutrientList) {
        this.nutrientList = nutrientList;
    }
    
    public void setNutrientList(List<NutrientIngredient> nutrientList) {
        this.nutrientList = nutrientList;
    }
    
    public List<NutrientIngredient> getNutrientList() {
        return nutrientList;
    }
    
    public int getRowCount() {
        return nutrientList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            NutrientIngredient nutrient = nutrientList.get(rowIndex);
            nutrient.setNutrientQuantity((Double)value);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        NutrientIngredient nutrient = nutrientList.get(rowIndex);
        Object[] values = new Object[] {
            nutrient.getId(),
            nutrient.getNutrient().getName() + " (" + nutrient.getNutrient().getUnit() + ")",
            nutrient.getNutrientQuantity()
        };
        return values[columnIndex];
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return nutrientList.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}
