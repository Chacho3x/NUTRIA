package NutriaTableModel;

import NutriaModel.QuantifiedIngredient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class QuantifiedIngredientTableModel extends BaseTableModel {
    
    private List<QuantifiedIngredient> quantifiedIngredientList;
    
    public QuantifiedIngredientTableModel() {
        super(
                new String[] {"id", "Ingrediente", "% Humedad", "Precio", "Cantidad"},
                new Class[] {Long.class, String.class, Double.class, Double.class, Double.class},
                new boolean[] {false, false, false, false, false}
        );
        quantifiedIngredientList = new ArrayList<>();
    }
    
    public void setQuantifiedIngredientList(List<QuantifiedIngredient> nutrientConstraintList) {
        this.quantifiedIngredientList = nutrientConstraintList;
    }
    
    public List<QuantifiedIngredient> getQuantifiedIngredientList() {
        return this.quantifiedIngredientList;
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return quantifiedIngredientList.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return quantifiedIngredientList.size();
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        QuantifiedIngredient qIngredient = quantifiedIngredientList.get(rowIndex);
        switch(columnIndex) {
            case 4:
                qIngredient.setQuantity(Double.parseDouble(value.toString()));
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QuantifiedIngredient quantifiedIngredient = quantifiedIngredientList.get(rowIndex);
        Object[] values = new Object[] {
            quantifiedIngredient.getId(),
            quantifiedIngredient.getIngredient().getName(),
            quantifiedIngredient.getIngredient().getWetnessPercentage(),
            quantifiedIngredient.getIngredient().getPrice(),
            quantifiedIngredient.getQuantity()
        };
        return values[columnIndex];
    }
}
