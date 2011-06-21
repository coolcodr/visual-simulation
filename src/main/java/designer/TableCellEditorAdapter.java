package designer;

import designer.deployment.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import mcomponent.distribution.*;
import javax.swing.event.*;
import java.lang.reflect.*;
import javax.swing.tree.*;

public class TableCellEditorAdapter extends DefaultCellEditor implements CellEditorListener
{
    Object object;

    public TableCellEditorAdapter( JComboBox jComponent )
    {
        super(jComponent);
        addCellEditorListener(this);
        setClickCountToStart(1);
    }

    public void setObject( Object object )
    {
        this.object = object;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c)
    {
        if ( value == null )
            return null;
        return super.getTableCellEditorComponent(table, value, isSelected, r , c);
    }


    public void editingCanceled(ChangeEvent e)
    {
    }

    public void editingStopped(ChangeEvent e)
    {
        //boolean newValue = ((CEditor)e.getSource()).getCellEditorValue().toString().equalsIgnoreCase("true");
        //System.out.println(((TableCellEditorAdapter)e.getSource()).getCellEditorValue());
        FrameIDandName test = (FrameIDandName)((TableCellEditorAdapter)e.getSource()).getCellEditorValue();
				System.out.println(test);
				int row = UIDesigner.propertiseTable.getSelectedRow();
				int column = UIDesigner.propertiseTable.getSelectedColumn();
				FrameIDandName frame = (FrameIDandName)UIDesigner.propertiseTable.getValueAt(row, 1);
				frame.replaceValue(test);
        System.out.println(frame);
				//((SimPropertyChoice)test).setDisplay(newValue);
        //((TempTestBoolean)object).setValue(newValue);
        //((Boolean)object).s
        //System.out.println(object);
        //root.getUserObjectPath()
    }

}