package NutriaTableModel;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ariel
 */
public class BaseTableModel extends AbstractTableModel {
    protected String[] columnNames;
    protected Class[] columnClass;
    protected boolean[] columnEditable;
    
    public BaseTableModel(String[] columnNames, Class[] columnClass, boolean[] columnEditable) {
        this.columnNames = columnNames;
        this.columnClass = columnClass;
        this.columnEditable = columnEditable;
    }
    
    public void setColumnEditable(int columnIndex, boolean isEditable) {
        columnEditable[columnIndex] = isEditable;
    }
    
    public boolean getColumnEditable(int columnIndex){
        return columnEditable[columnIndex];
    }
    
    @Override
    public int getRowCount() {
        //this method must be overriden
        return 0;
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnEditable[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //this method must be overriden
        return new Object();
    }
    
    @Override
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    
}
