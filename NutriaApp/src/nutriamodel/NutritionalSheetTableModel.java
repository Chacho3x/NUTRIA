package NutriaModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutritionalSheetTableModel extends AbstractTableModel {
    
    private String[] columnNames = new String[] {"id", "Hoja Nutricional"};
    private List<NutritionalSheet> nutritionalSheetList;
    
    public NutritionalSheetTableModel() {
        nutritionalSheetList = new ArrayList<>();
    }
    
    public NutritionalSheetTableModel(List<NutritionalSheet> nutritionalSheetList) {
        this.nutritionalSheetList = nutritionalSheetList;
    }
    
    public void setNutritionalSheetList(List<NutritionalSheet> nutritionalSheetList) {
        this.nutritionalSheetList = nutritionalSheetList;
    }
    
    public List<NutritionalSheet> getNutritionalSheetList() {
        return this.nutritionalSheetList;
    }
    
    public int getRowCount() {
        return this.nutritionalSheetList.size();
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        NutritionalSheet nutritionalSheet = nutritionalSheetList.get(rowIndex);
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
