package NutriaModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class IngredientTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {"id", "Nombre", "Precio"};
    private List<Ingredient> ingredientList;
    
    public IngredientTableModel() { 
        ingredientList = new ArrayList<>();
    }
    
    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
    
    public int getRowCount() {
        return ingredientList.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ingredient ingredient = ingredientList.get(rowIndex);
        Object[] values=new Object[] {
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getPrice()
        };
        return values[columnIndex];
    }
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //TODO: implement this method.
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}