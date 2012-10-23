package NutriaTableModel;

import NutriaModel.NutrientConstraint;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintReadOnlyTableModel extends AbstractTableModel {
    
    private String[] columnNames = new String[] {"id", "Nutriente", "Restriccion"};
    private List<NutrientConstraint> nutrientConstraintList;
    
    public NutrientConstraintReadOnlyTableModel() {
        nutrientConstraintList = new ArrayList<>();
    }
    
    public NutrientConstraintReadOnlyTableModel(List<NutrientConstraint> nutrientConstraintList) {
        this.nutrientConstraintList = nutrientConstraintList;
    }
    
    public void setNutrientConstraintList(List<NutrientConstraint> nutrientConstraintList) {
        this.nutrientConstraintList = nutrientConstraintList;
    }
    
    public List<NutrientConstraint> getNutrientConstraintList() {
        return this.nutrientConstraintList;
    }
    
    public int getRowCount() {
        return nutrientConstraintList.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        NutrientConstraint nutConstraint = nutrientConstraintList.get(rowIndex);
        String constraint = "";
        if(nutConstraint.getMinBound() != null) {
            constraint += nutConstraint.getMinRelationship() + " " + nutConstraint.getMinBound() + "; ";
        }
        if(nutConstraint.getMaxBound() != null) {
            constraint += nutConstraint.getMaxRelationship() + " " + nutConstraint.getMaxBound();
        }
        Object[] values = new Object[] {
            nutConstraint.getId(),
            nutConstraint.getNutrient().getName(),
            constraint
        };
        return values[columnIndex];
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return nutrientConstraintList.get(rowIndex);
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return this.columnNames[columnIndex];
    }
    
    public Class getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
