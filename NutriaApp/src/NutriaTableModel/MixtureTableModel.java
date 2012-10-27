package NutriaTableModel;

import NutriaModel.Mixture;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ariel
 */
public class MixtureTableModel extends BaseTableModel {
    
    private List<Mixture> mixtureList;
    
    public MixtureTableModel() {
        super(
                new String[] {"id", "Nombre de Mezcla", "Descripcion", "Costo"},
                new Class[] {Long.class, String.class, String.class, Double.class},
                new boolean[] {false, false, false, false}
                );
        mixtureList = new ArrayList<>();
    }
    
    public void setMixtureList(List<Mixture> mixtureList) {
        this.mixtureList = mixtureList;
    }
    
    public List<Mixture> getMixtureList() {
        return mixtureList;
    }
    
    public Object getObjectAtRow(int rowIndex) {
        return mixtureList.get(rowIndex);
    }
    
    @Override
    public int getRowCount() {
        return mixtureList.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mixture mixture = mixtureList.get(rowIndex);
        Object[] values = new Object[] {
            mixture.getId(),
            mixture.getName(),
            mixture.getDescription(),
            mixture.getMixtureCost()
        };
        
        return values[columnIndex];
    }
}
