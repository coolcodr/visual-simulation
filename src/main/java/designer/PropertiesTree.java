package designer;

import table.*;
import java.io.File;
import java.util.Date;
import javax.swing.tree.*;
import javax.swing.*;
import java.util.Vector;

public class PropertiesTree extends AbstractTreeTableModel implements TreeTableModel
{
    private Object componentObject;
    private DiagramSourceTable table;

    public PropertiesTree( DiagramSourceTable table )
    {
        this (null, table) ;//TEMP
    }
	/*
	public PropertiesTree ( ElementProperties properties )
	{
		this((Object)properties);
		this.properties = properties;
	}
	public PropertiesTree ( Object root )
	{
		super(root);
	}
	*/

    public PropertiesTree( Object object, DiagramSourceTable table)
    {
        super(object);
        this.table = table;
        //componentObject = ((DefaultMutableTreeNode)object).getUserObject();
    }

    public int getChildCount(Object node)
    {
        return ((DefaultMutableTreeNode)node).getChildCount();
    }

    public Object getChild(Object node, int i)
    {
        return ((DefaultMutableTreeNode)node).getChildAt(i);
    }

    public boolean isLeaf(Object node)
    {
        return ((DefaultMutableTreeNode)node).getChildCount() == 0;
    }

    public int getColumnCount()
    {
        return 2;
    }

    public String getColumnName(int column)
    {
        switch (column) {
            case 0:
            return "Property";
            case 1:
            return "Value";
        }
        return "";
    }

    public Class getColumnClass(int column)
    {
        switch (column) {
            case 0:
                return TreeTableModel.class;
            case 1:
            {
                if ( table.getSelectedRow() != -1 )
                {
                    /*
                    PropertyCommand property = (PropertyCommand)table.getValueAt(table.getSelectedRow(), 0);
                    if ( property.getEditor() instanceof JTextField )
                        table.getColumnModel().getColumn(1).setCellEditor(new CEditor((JTextField)property.getEditor()));
                    else if ( property.getEditor() instanceof JComboBox )
                        table.getColumnModel().getColumn(1).setCellEditor(new CEditor((JComboBox)property.getEditor()));
                    else if ( property.getEditor() instanceof JCheckBox )
                        table.getColumnModel().getColumn(1).setCellEditor(new CEditor((JCheckBox)property.getEditor()));
                     */
                     return JTextField.class;
                 }
            }
        }
        return JTextField.class;
    }

    public boolean isCellEditable(Object node, int column)
    {
        if (column == 0)
            return true;
        else if (column == 1)
            if (((DefaultMutableTreeNode)node).isLeaf())
                return true;
            else
                return false;
        return true;
    }

    public Object getValueAt(Object node, int column)
    {
        switch (column) {
            case 0:
            {
                /*DefaultMutableTreeNode currentNode = ((DefaultMutableTreeNode)node);
                if ( currentNode.getLevel() != 1)
                    return null;
                else
                    return new Boolean(((SimPropertyChoice)currentNode.getUserObject()).getDisplay());*/
                    return ((DefaultMutableTreeNode) node).getUserObject();
            }
            case 1:
            {
                DefaultMutableTreeNode currentNode = ((DefaultMutableTreeNode)node);
                Object object = "";
                if ( currentNode.isLeaf() )
                    object = ((PropertyCommand)currentNode.getUserObject()).getValue();
                return object;
            }
        }
        return "";
    }

}
