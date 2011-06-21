package diagram;

import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class TableModelAdapter extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 8144202725064569903L;
    Vector _pm;
    JTable _table;
    int _columnCount = 2;
    String[] _columnNames = { "Name", "Value" };
    String[] primitiveAry = { "java.lang.String", "java.lang.Integer", "java.lang.Double" };

    public TableModelAdapter(Vector pm, JTable table) {
        _pm = pm;
        _table = table;
    }

    public int getColumnCount() {
        return _columnCount;
    }

    public int getRowCount() {
        return _pm.size();
    }

    public String getColumnName(int col) {
        return _columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Property p = (Property) _pm.elementAt(row);

        if (col == 0) {
            return p.getName();
        } else if (col == 1) {
            if (p.getValue() != null) {
                if (isPrimitive(p.getType())) {
                    return p.getValue();
                } else {
                    String className = (p.getValue().getClass().getName());
                    System.err.println("p.getValue(): -" + p.getValue() + "-");
                    String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                    if (classNameOnly.equals("Vector")) {
                        classNameOnly = new String("Defined");
                    }
                    return classNameOnly;
                }
            } else {
                return "";
            }
        } else {
            return "Hello";
        }
    }

    /*
     * public Class getColumnClass(int c) { return getValueAt(0, c).getClass();
     * }
     */

    public Class getColumnClass(int c) {
        int rowIndex = _table.getSelectedRow();
        Property p;
        if (rowIndex != -1) {
            _table.getColumnModel().getColumn(c).setCellEditor(new CEditor(new JTextField()));
        }

        return new JComboBox().getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if (columnIndex == 0) {
            ((Property) _pm.elementAt(rowIndex)).setName((String) aValue);
        } else if (columnIndex == 1) {
            if (aValue.equals("")) {
            } else {
                ((Property) _pm.elementAt(rowIndex)).setValue(aValue);
            }
        } else {
            System.out.println("Set Value Error At JTable");
        }
    }

    public boolean isPrimitive(String type) {
        boolean isPrimitiveType = false;
        int i = 0;
        while (!isPrimitiveType && i < primitiveAry.length) {
            if (type.equalsIgnoreCase(primitiveAry[i])) {
                isPrimitiveType = true;
            }
            i++;
        }
        return isPrimitiveType;
    }
}
