package NutriaTableModel;

import NutriaModel.Nutrient;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {"id", "Nombre", "Unidad"};
    private List<Nutrient> nutrientList;
    
    public NutrientTableModel() {
        nutrientList = new ArrayList<>();
    }
    
    public void setNutrientList(List<Nutrient> nutrientList) {
        this.nutrientList = nutrientList;
    }
    
    public List<Nutrient> getNutrientList() {
        return this.nutrientList;
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
            nutrient.getUnit()
        };
        return values[columnIndex];
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //TODO: implement setValueAt
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return nutrientList.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}