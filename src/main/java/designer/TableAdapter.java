package designer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import designer.deployment.FrameIDandName;

public class TableAdapter extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 7111570092053885363L;
    Vector propertise = new Vector();
    JTable table;
    int columnCount = 2;
    String[] columnNames = { "Name", "Value" };

    public TableAdapter(Vector propertise) {
        this.propertise = propertise;
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public int getRowCount() {
        return propertise.size();
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return ((Object[]) (propertise.elementAt(row)))[0];
        } else if (col == 1) {
            return ((Object[]) propertise.elementAt(row))[1] != null ? ((

            (((Object[]) (propertise.elementAt(row)))[1]))) : "";
            /*
             * Property p =(Property)_pm.elementAt(row);
             * 
             * if(col == 0) return p.getName(); else if(col == 1) {
             * if(p.getValue() != null) { if(isPrimitive(p.getType())) { return
             * p.getValue(); } else { String className =
             * ((String)p.getValue().getClass().getName()); String classNameOnly
             * = className.substring((className.lastIndexOf(".")+1)); return
             * classNameOnly; } } else return ""; } else{ return "Hello"; }
             */
        } else {
            return "0";
        }
    }

    public Class getColumnClass(int c) {
        table = UIDesigner.propertiseTable;
        int rowIndex = table.getSelectedRow();
        // if(rowIndex != -1){
        // table.getColumnModel().getColumn(c).setCellEditor(new CEditor(new
        // JTextField()));
        // }

        // return new JComboBox().getClass();
        Vector designPanes = DesignerControl.designPanes;
        Object[] object = new Object[designPanes.size()];
        for (int i = 0; i < designPanes.size(); i++) {
            DesignPane current = ((DesignPane) designPanes.elementAt(i));
            // System.out.println(">>"+current.getTitle());
            object[i] = new FrameIDandName(current.getID(), current.getTitle());
            // System.out.println(object[i]);
        }
        table.getColumnModel().getColumn(c).setCellEditor(new TableCellEditorAdapter(new JComboBox(object)));
        return new JTextField().getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        } else {
            return true;
        }
    }

}
