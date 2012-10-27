package NutriaTableModel;

import NutriaModel.NutrientConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintTableModel extends AbstractTableModel {
    
    private String[] columnNames = new String[] {"id", "Nutriente", "R1", "Min", "R2", "Max"};
    private List<NutrientConstraint> nutrientConstraintList;
    
    public NutrientConstraintTableModel() {
        nutrientConstraintList = new ArrayList<>();
    }
    
    public NutrientConstraintTableModel(List<NutrientConstraint> nutrientConstraintList) {
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
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex >= 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if(columnIndex >= 2) {
            NutrientConstraint nutrientConstraint = nutrientConstraintList.get(rowIndex);
            if(columnIndex == 2) {
                if(value != null)
                    nutrientConstraint.setMinRelationship(value.toString());
            } else if(columnIndex == 3) {
                nutrientConstraint.setMinBound((Double)value);
            } else if(columnIndex == 4) {
                if(value != null)
                    nutrientConstraint.setMaxRelationship(value.toString());
            } else if(columnIndex == 5) {
                nutrientConstraint.setMaxBound((Double)value);
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        NutrientConstraint nutrientConstraint = nutrientConstraintList.get(rowIndex);
        Object[] values = new Object[] {
            nutrientConstraint.getId(),
            nutrientConstraint.getNutrient().getName(),
            nutrientConstraint.getMinRelationship(),
            nutrientConstraint.getMinBound(),
            nutrientConstraint.getMaxRelationship(),
            nutrientConstraint.getMaxBound()
        };
        
        return values[columnIndex];
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return nutrientConstraintList.get(rowIndex);
    }
    
    public Class getColumnClass(int columnIndex) {
        Object aux = getValueAt(0, columnIndex);
        if (aux != null) {
            return aux.getClass();
        } else if(columnIndex == 3 || columnIndex == 5) {
            return Double.class;
        } else {
            return String.class;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
