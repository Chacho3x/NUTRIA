package NutriaModel;

import NutriaLib.LinkLabel;
import java.sql.SQLException;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class NutrientTableModel extends AbstractTableModel {
    private String[] columnNames = new String[] {"id", "Nombre", "Unidad", "Acciones"};
    private List<Nutrient> nutrientList;
    private Object[] actions;
    private NutrientDaoImpl nutrientDao;
    
    public NutrientTableModel() {
        initNutrientTableModel();
    }
    
    private void initNutrientTableModel() {
        try {
            nutrientDao = new NutrientDaoImpl();
            this.nutrientList = nutrientDao.queryForAll();
            this.actions = new Object[nutrientList.size()];
        } catch(SQLException ex) {
            //TODO: handle init NutrientTableModel
        }
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
    public void fireTableDataChanged() {
        initNutrientTableModel();
        fireTableChanged(new TableModelEvent(this));
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
}