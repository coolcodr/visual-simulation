package diagram;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JComponent;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DefaultShapeControl extends Control {

    int x, y;

    static public UndoCommand undoCommand;// new undoredo[
    static private LinkedList undoList;
    Vector undovalue, undomethod;
    Point flag;

    static public void setLinkedList(LinkedList vSimUndoList) {
        undoList = vSimUndoList;
    }// new undoredo

    public void mouseDragged(MouseEvent e) {
        int dx, dy;
        JComponent b;

        ((DiagramShape) e.getSource()).hideResizePoints();
        // move the shape
        b = (JComponent) e.getSource();
        b.setLocation(b.getX() + e.getX() - x, b.getY() + e.getY() - y);

        ((DiagramShape) e.getSource()).repaintAssociations();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        undovalue = new Vector();// new
        undomethod = new Vector();// new

        DiagramShape d = (DiagramShape) e.getSource();
        DiagramShape.getDiagram().getDiagramControl().setSelectedElement(d);
        DiagramShape.getDiagram().getDiagramControl().restoreSelectedElementsControl();
        DiagramShape.getDiagram().getDiagramControl().hideDiagramElementsResizePoints();
        d.showResizePoints();
        x = e.getX();
        y = e.getY();

        flag = d.getLocation();// new
        undovalue.add("getLocation");// new
        undomethod.add("setLocation");
        undoCommand = new UndoCommand(d, undovalue, undomethod);// new
                                                                // UndoCommand
        undoList.add(undoCommand);// new]

        // Testing!!!!
        d.requestFocus();
    }

    public void mouseReleased(MouseEvent e) {
        DiagramShape d = (DiagramShape) e.getSource();
        d.showResizePoints();
        if (flag.equals(d.getLocation())) {
            undoList.removeLast();
        }
    }

    public void mouseEntered(MouseEvent e) {
//		DiagramShape d = (DiagramShape) e.getSource();
//		d.getDiagram().getDiagramControl().hideDiagramElementsResizePoints();
//		
//		d.showResizePoints();	
    }

    public void mouseExited(MouseEvent e) {
//		DiagramShape d = (DiagramShape) e.getSource();
//		d.hideResizePoints();	
    }
}
