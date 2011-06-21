package designer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.tree.*;
import table.*;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;

public class DiagramSourceTable extends JTreeTable //implements DNDComponentInterface, DragSourceListener, DragGestureListener, MouseListener
{
    protected TreeTableCellRenderer tree;
    private PropertiesTree propertyTree ;//= new PropertiesTree();
    private DefaultMutableTreeNode root;
    private ElementProperties properties;
    private SourceTableRenderer renderer = new SourceTableRenderer();
	private SourceTableCellRenderer cellRenderer = new SourceTableCellRenderer();

    public DiagramSourceTable()
    {
    }

    public DiagramSourceTable(ElementProperties properties)
    {
        super();

        if (properties == null)
            root = new DefaultMutableTreeNode("NULL");
        else
            root = new DefaultMutableTreeNode("Properties");

		int counter = 0;
		String type = null;
		DefaultMutableTreeNode node = null;
		Vector inCount = new Vector();
        if (properties != null)
        {
            Vector props = properties.getProperties();

            for (int i = 0; i < props.size(); i++)
            {
                PropertyCommand property = (PropertyCommand) props.elementAt(i);
                String newType = property.getType();
                if ( ! newType.equalsIgnoreCase(type) )
                {
                	type = newType;
                	node = new DefaultMutableTreeNode(newType);
                	root.add(node);
                	inCount.add(new Integer(++counter));
                }
                node.add(new DefaultMutableTreeNode(property));
                ++counter;
            }
        }

        propertyTree = new PropertiesTree(root, this);

        setTreeTableModel(propertyTree);

		for ( int i = 0 ; i < inCount.size() ; i ++ )
		{
			Integer integer = (Integer)inCount.elementAt(i);
			int j = integer.intValue();
			//System.out.println("PATH: "+j);
			getTree().expandPath(getTree().getPathForRow(j));
		}
        getTree().setRootVisible(false);
		//new JTree().getPathForRow(
        getColumnModel().getColumn(1).setCellEditor(new CEditor(new JTextField()));
        //repaint();

        getTree().setCellRenderer(renderer);


		getColumnModel().getColumn(1).setCellRenderer(cellRenderer);



        //(DefaultMutableTreeNode)propertyTree.getRoot();

//propertyTree = new PropertiesTree( (Object) root);
//setTreeTableModel(propertyTree);

//propertyTree = new PropertiesTree(root);
//this.root = root;

//setTreeTableModel(propertyTree);

        //getTree().setCellRenderer(renderer);
        /*
                getTree().updateUI();
                getTree().repaint();
                renderer.updateUI();
                renderer.repaint();
                this.updateUI();
                this.repaint();
         */
        /*
           DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("NODE1");
           DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("NODE2");
           DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("NODE3");
           DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("NODE4");
           root.add(node1);
           root.add(node2);
           node2.add(node3);
           node3.add(node4);
         */

        //this.setTableHeader(null);
        //this.getColumn("CheckValue").setWidth(20);
        //this.getColumn("CheckValue").setMaxWidth(20);
        //this.getColumn("CheckValue").setMinWidth(20);

        //this.setTableHeader(null);
        // this.setShowHorizontalLines(false);
        //this.setShowVerticalLines(false);
        //this.setShowGrid(false);
        //this.setIntercellSpacing(new Dimension(0, 0));
        /*
                 CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
                getTree().updateUI();
                getTree().repaint();
                renderer.updateUI();
                renderer.repaint();
                this.updateUI();
                this.repaint();
         */
        //this.getColumnModel().getColumn(1).setCellEditor(new CEditor(new JTextField()));
        //this.getColumnModel().getColumn(1).setCellRenderer(checkBoxRenderer);

    }
/*
    public void setProperties(ElementProperties properties)
    {
        this.properties = properties;

        //DefaultMutableTreeNode root = new DefaultMutableTreeNode("Properties");
        root.removeAllChildren();
        //root = new DefaultMutableTreeNode("Properties");

        Vector props = properties.getProperties();

        for (int i = 0; i < props.size(); i++)
        {
            PropertyCommand property = (PropertyCommand) props.elementAt(i);
            root.add(new DefaultMutableTreeNode(property));
        }

        //propertyTree = new PropertiesTree( (Object) root);
        //setTreeTableModel(propertyTree);

        //propertyTree = new PropertiesTree(root);
        //this.root = root;

        //setTreeTableModel(propertyTree);

        repaint();
        UIDesigner.mainWindow.repaint();
    }
*/
}