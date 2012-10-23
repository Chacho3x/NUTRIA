package NutriaTableModel;

import NutriaModel.NutrientConstraintSheet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientConstraintSheetTableModel extends AbstractTableModel {
    
    private String[] columnNames = new String[] {"id", "Hoja Nutricional"};
    private List<NutrientConstraintSheet> nutritionalSheetList;
    
    public NutrientConstraintSheetTableModel() {
        nutritionalSheetList = new ArrayList<>();
    }
    
    public NutrientConstraintSheetTableModel(List<NutrientConstraintSheet> nutritionalSheetList) {
        this.nutritionalSheetList = nutritionalSheetList;
    }
    
    public void setNutritionalSheetList(List<NutrientConstraintSheet> nutritionalSheetList) {
        this.nutritionalSheetList = nutritionalSheetList;
    }
    
    public List<NutrientConstraintSheet> getNutritionalSheetList() {
        return this.nutritionalSheetList;
    }
    
    public int getRowCount() {
        return this.nutritionalSheetList.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        NutrientConstraintSheet nutritionalSheet = nutritionalSheetList.get(rowIndex);
        Object[] values = new Object[] {
            nutritionalSheet.getId(),
            nutritionalSheet.getName()
        };
        return values[columnIndex];
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return nutritionalSheetList.get(rowIndex);
    }
}
