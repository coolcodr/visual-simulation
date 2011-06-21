package designer;

import javax.swing.*;
import java.awt.dnd.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;
import java.util.Hashtable;

import java.awt.datatransfer.*;
import diagram.*;

public class DiagramSourceAnalysisList extends JTree implements DNDComponentInterface, DragSourceListener, DragGestureListener, MouseListener
{
    private DragSource dragSource = null;
    private DefaultMutableTreeNode root;

    public Vector analysisComponent;

    public Hashtable componentTable;
    private boolean isSelected = false;

    public static Object selectedObject;

    public DiagramSourceAnalysisList()
    {
        this(new DefaultMutableTreeNode("Report Element"));
        this.setCellRenderer(new SourceTreeRenderer());
    }

    public DiagramSourceAnalysisList(DefaultMutableTreeNode s)
    {
        super(s);
        root = s;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);

        this.addMouseListener(this);

    }

    public AnalysisSet getComponent(String key)
    {
        return (AnalysisSet) componentTable.get(key);
    }
    public void addNode(DefaultMutableTreeNode node)
    {
        root.add(node);
    }
    public void addElement(Object s)
    {
    }

    public void removeElement()
    {
    }

    public void dragDropEnd(DragSourceDropEvent event)
    {
    }

    public void dragEnter(DragSourceDragEvent event)
    {
    }

    public void dragExit(DragSourceEvent event)
    {
    }

    public void dragOver(DragSourceDragEvent event)
    {
    }

    public void dropActionChanged(DragSourceDragEvent event)
    {
    }

    public void dragGestureRecognized(DragGestureEvent event)
    {
        TreePath selected = this.getSelectionPath();

        if (selected != null && selected.getParentPath() != this.getPathForRow(0))
        {
            StringSelection text = new StringSelection(selected.toString());

            selectedObject = ( (DefaultMutableTreeNode) (selected.getLastPathComponent())).getUserObject();

            DiagramSourceList.selectedObject = null;
            //selectedObject = null;

            if ( selectedObject instanceof AnalysisSet )
            {
                isSelected = true;
                AnalysisSet aSet = (AnalysisSet) selectedObject;
                String s = aSet.getTag();//setsetPane.getLabel().getText() + "\t\t" + "[%$" + setPane.getName() + "|" + setPane.getSetMode() + "$%]";
                text = new StringSelection(s); //setPane.getLabel().getText() + "\t\t" + "[%$ " + setPane.getName() + "|" + setPane.getSetMode() + "$%]");
                DiagramSourceList.stringSelection = s;
                dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, this);
            }
            else if (selectedObject instanceof ChartSet)
            {
                isSelected = true;
                ChartSet aSet = (ChartSet) selectedObject;
                String s = aSet.getTag(); //setsetPane.getLabel().getText() + "\t\t" + "[%$" + setPane.getName() + "|" + setPane.getSetMode() + "$%]";
                text = new StringSelection(s); //setPane.getLabel().getText() + "\t\t" + "[%$ " + setPane.getName() + "|" + setPane.getSetMode() + "$%]");
                DiagramSourceList.stringSelection = s;
                dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, this);
            }
            else
                selectedObject= null;
        }
        else
        {
            System.out.println("nothing was selected");
        }
    }

    public void mousePressed(MouseEvent event)
    {
        TreePath selected = this.getSelectionPath();
        if (selected != null && selected.getParentPath() != this.getPathForRow(0))
        {
            StringSelection text = new StringSelection(selected.toString());
            //selectedNode = (DefaultMutableTreeNode) (selected.getLastPathComponent());
            //selectedObject = selectedNode.getUserObject();
        }
    }

    public void mouseReleased(MouseEvent event)
    {
    }
    public void mouseExited(MouseEvent event){}
    public void mouseEntered(MouseEvent event){}
    public void mouseClicked(MouseEvent event)
    {
    }

}