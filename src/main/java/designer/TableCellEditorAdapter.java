package designer;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import designer.deployment.FrameIDandName;

public class TableCellEditorAdapter extends DefaultCellEditor implements CellEditorListener {
    /**
     * 
     */
    private static final long serialVersionUID = 767096177666167839L;
    Object object;

    public TableCellEditorAdapter(JComboBox jComponent) {
        super(jComponent);
        addCellEditorListener(this);
        setClickCountToStart(1);
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
        if (value == null) {
            return null;
        }
        return super.getTableCellEditorComponent(table, value, isSelected, r, c);
    }

    public void editingCanceled(ChangeEvent e) {
    }

    public void editingStopped(ChangeEvent e) {
        // boolean newValue =
        // ((CEditor)e.getSource()).getCellEditorValue().toString().equalsIgnoreCase("true");
        // System.out.println(((TableCellEditorAdapter)e.getSource()).getCellEditorValue());
        FrameIDandName test = (FrameIDandName) ((TableCellEditorAdapter) e.getSource()).getCellEditorValue();
        System.out.println(test);
        int row = UIDesigner.propertiseTable.getSelectedRow();
        int column = UIDesigner.propertiseTable.getSelectedColumn();
        FrameIDandName frame = (FrameIDandName) UIDesigner.propertiseTable.getValueAt(row, 1);
        frame.replaceValue(test);
        System.out.println(frame);
        // ((SimPropertyChoice)test).setDisplay(newValue);
        // ((TempTestBoolean)object).setValue(newValue);
        // ((Boolean)object).s
        // System.out.println(object);
        // root.getUserObjectPath()
    }

}
