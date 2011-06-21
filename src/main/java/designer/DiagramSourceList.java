
package designer;

import designer.deployment.*;

import javax.swing.*;
import java.awt.dnd.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import java.util.Hashtable;
import java.lang.Cloneable;

import java.awt.datatransfer.*;
import diagram.*;

public class DiagramSourceList extends JTree
        implements DNDComponentInterface, DragSourceListener, DragGestureListener, MouseListener, Cloneable
{
    public static DropTarget dropTarget1 = null;
    public static DropTarget dropTarget2 = null;

    public static Object selectedObject = null;
    public static String stringSelection = null;
	public static boolean isSelected = false;

    private DefaultMutableTreeNode selectedNode = null;

    private DragSource dragSource = null;
    private DefaultMutableTreeNode root;

    public Vector inputComponent;
    public Vector inputComponentInternal;

    public Hashtable componentTable;

    private JPopupMenu jPopupMenu;

    public DiagramSourceList()
    {
        this(new DefaultMutableTreeNode("Diagram Element"));
        this.setCellRenderer(new SourceTreeRenderer());
    }
    /*
    public Object clone ()
    {
        DiagramSourceList clonedList ;//= null;
        /*
        try {
            clonedList = (DiagramSourceList)super.clone();
        } catch ( CloneNotSupportedException ex )
        {
            return null;
        }

        DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode) root.clone();

        for ( int i = 0 ; i < root.getChildCount() ; i ++ )
        {
            newRoot.add( cloneNode ( (DefaultMutableTreeNode) root.getChildAt(i) ) );
        }

        clonedList = new DiagramSourceList( newRoot );

        clonedList.inputComponent = (Vector) inputComponent.clone();
        clonedList.inputComponentInternal = (Vector) inputComponentInternal.clone();
        clonedList.componentTable = (Hashtable) componentTable.clone();

        return clonedList;
    }
    private DefaultMutableTreeNode cloneNode ( DefaultMutableTreeNode node )
    {
        DefaultMutableTreeNode clonedNode = (DefaultMutableTreeNode) node.clone();
        for ( int i = 0 ; i < node.getChildCount() ; i ++ )
        {
            clonedNode.add( cloneNode ( (DefaultMutableTreeNode) node.getChildAt(i) ) );
        }
        return clonedNode;
    }*/
    public DiagramSourceList( DefaultMutableTreeNode s )
    {
        super(s);
        root = s;
        //dropTarget1 = new DropTarget (this, DesignerControl.currentUpperPane);
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer( this, DnDConstants.ACTION_MOVE, this);
        this.addMouseListener(this);

        this.inputComponent = new Vector();
        this.inputComponentInternal = new Vector();
        this.componentTable = new Hashtable();

        jPopupMenu = new JPopupMenu("Dialog");

        JMenuItem mi;

        MenuAdapter menuAdapter = new MenuAdapter();

        mi = new JMenuItem("Create new Associated Pane");
        mi.addActionListener(menuAdapter);
        jPopupMenu.add(mi);

        mi = new JMenuItem("Edit Associated Pane");
        mi.addActionListener(menuAdapter);
        jPopupMenu.add(mi);

        jPopupMenu.setInvoker(this);

    }
    public DiagramComponentSetPanel getComponent ( String key )
    {
        return (DiagramComponentSetPanel) componentTable.get(key);
    }
    public void addNode ( DefaultMutableTreeNode node )
    {
        root.add(node);
    }
    public void addElement( Object s )
    {
    }
    public void removeElement()
    {
    }
    public void dragDropEnd (DragSourceDropEvent event)
    {
    }
    public void dragEnter (DragSourceDragEvent event)
    {
    }
    public void dragExit (DragSourceEvent event)
    {
    }
    public void dragOver (DragSourceDragEvent event)
    {
    }
    public void dropActionChanged ( DragSourceDragEvent event)
    {
    }
    public void dragGestureRecognized( DragGestureEvent event)
    {

        //Object selected = getSelectedValue();
        TreePath selected = this.getSelectionPath();

        if ( selected != null && selected.getParentPath() != this.getPathForRow(0) )
        {
            StringSelection text = new StringSelection( selected.toString());
            selectedObject = ((DefaultMutableTreeNode)(selected.getLastPathComponent())).getUserObject();

            if ( selectedObject instanceof DiagramComponentSetPanel )
            {
                isSelected = true;
                DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) selectedObject;
                String s = setPane.getLabel().getText() + "\t\t" + "[%$" + setPane.getName() + "|" + setPane.getSetMode() + "$%]";
                text = new StringSelection( s );//setPane.getLabel().getText() + "\t\t" + "[%$ " + setPane.getName() + "|" + setPane.getSetMode() + "$%]");
                stringSelection = s;
                dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, this);
            }
        }
        else
        {
            System.out.println( "nothing was selected");
        }
    }

    public void mousePressed(MouseEvent event)
    {
        //if ( selectedNode != null )
            //selectedNode.removeAllChildren();
        //for ( int i = 0 ; i < selectedNode.getChildCount() ; i ++ )
            //selectedNode.remove((DefaultMutableTreeNode)selectedNode.getChildAt(i));

        TreePath selected = this.getSelectionPath();
        if ( selected != null && selected.getParentPath() != this.getPathForRow(0) )
        {
            StringSelection text = new StringSelection(selected.toString());
            selectedNode = (DefaultMutableTreeNode)(selected.getLastPathComponent());
            selectedObject = selectedNode.getUserObject();
            //System.out.println(((DiagramComponentSetPanel)selectedObject).getInternalValueNode());
            //DiagramSourceTable sourceTable = null;
            /*
            try{
                //sourceTable = new DiagramSourceTable(((DiagramComponentSetPanel)selectedObject).getInternalValueNode());
                DefaultMutableTreeNode internalNode = (DefaultMutableTreeNode)((DiagramComponentSetPanel)selectedObject).getInternalValueNode();

                for ( int i = 0 ; i < internalNode.getChildCount() ; i ++ )
                    selectedNode.add((DefaultMutableTreeNode)internalNode.getChildAt(i));

                selectedNode.setAllowsChildren(true);
                this.repaint();
            }catch(Exception e){}

            //System.out.println(((DefaultMutableTreeNode)((DiagramComponentSetPanel)selectedObject).getInternalValueNode()).getChildCount());
            ((UIDesigner)UIDesigner.mainWindow).setDiagramSourceTable(sourceTable);
            //UIDesigner.sourceTable.repaint();
            UIDesigner.mainWindow.repaint();\
            */
        }

        /*
        DesignerUI.currentSetPanel = (designer.DiagramComponentSetPanel)(((DefaultMutableTreeNode)(this.getSelectionPath().getLastPathComponent())).getUserObject());
        DesignerUI.createFrameButton.setEnabled(false);
        System.out.println(((DefaultMutableTreeNode)this.getSelectionPath().getLastPathComponent()).getChildCount());
        if (((DefaultMutableTreeNode)this.getSelectionPath().getLastPathComponent()).getChildCount() >0 )
            DesignerUI.createFrameButton.setEnabled(true);
        Vector vPropertise = ((designer.DiagramComponentSetPanel)(((DefaultMutableTreeNode)(this.getSelectionPath().getLastPathComponent())).getUserObject())).getPropertise();
        //if ( vPropertise.size() > 0 )
        //{
            DesignerUI.jTable.setModel(new TableAdapter(vPropertise));
            DesignerUI.jTable.repaint();
        //}*/
    }
    public void mouseReleased(MouseEvent event)
    {
        if (event.isPopupTrigger())
        {
            int x = event.getX();
            int y = event.getY();
           // int row = this.getselec
           //if (row != -1)
           //{
           //tree.setSelectionRow(row);
           int row = this.getRowForLocation(x, y);
           if (row != -1)
           {
               this.setSelectionRow(row);
               TreePath selected = this.getSelectionPath();

               selectedNode = (DefaultMutableTreeNode) (selected.getLastPathComponent());
               selectedObject = selectedNode.getUserObject();

               //TreePath selected = this.getSelectionPath();
               //selectedObject = ( (DefaultMutableTreeNode) (selected.getLastPathComponent())).getUserObject();
               if (selectedObject instanceof SimPropertyChoice)
                   popupMenu(selectedObject, x, y);
           }
                //jPopupMenu.show(this, x, y);
                //System.out.println("POPUPED");
        }

    }
    public void popupMenu ( Object selectedObject, int x, int y )
    {
        //TreeTableCellRenderer tree = this.getTree();
       // TreePath selected = tree.getSelectionPath();

        //if (selected != null && selected.getParentPath() == tree.getPathForRow(0))
        //{
            //System.out.println("ID :  "+((SimPropertyChoice)DiagramSourceTable.selectedObject).getFrameID());
            if ( ((SimPropertyChoice)selectedObject).getFrameID().equalsIgnoreCase("-1") )
                jPopupMenu.getComponent(1).setEnabled(false);
            else
                jPopupMenu.getComponent(1).setEnabled(true);

            jPopupMenu.show(this, x, y);
        //}

    }

    public void mouseExited(MouseEvent event){}
    public void mouseEntered(MouseEvent event){}
    public void mouseClicked(MouseEvent event)
    {
    }
}

class MenuAdapter implements ActionListener
{
    public MenuAdapter ()
    {
    }
    public void actionPerformed ( ActionEvent event )
    {
        String command = event.getActionCommand();

        if (command.equals("Create new Associated Pane"))
        {
            CreateFrameControl.ForceCreateFrame( (SimPropertyChoice)DiagramSourceList.selectedObject);
        }
        else if (command.equals("Edit Associated Pane"))
        {
            CreateFrameControl.showFrame( (SimPropertyChoice)DiagramSourceList.selectedObject);
        }
    }
}
