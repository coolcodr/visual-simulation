package diagram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JComponent;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class ResizePoint extends JComponent implements MouseListener, MouseMotionListener {

    /**
     * 
     */
    private static final long serialVersionUID = -6915141040439842662L;
    private int _position;
    private int _x, _y;
    private int _ddx, _ddy;
    private int mx, my;// new
    static public final int NW = 0;
    static public final int N = 1;
    static public final int NE = 2;
    static public final int E = 3;
    static public final int SE = 4;
    static public final int S = 5;
    static public final int SW = 6;
    static public final int W = 7;

    static public UndoCommand undoCommand;// new undoredo[
    static private LinkedList undoList;
    Vector undovalue, undomethod;// new

    private DiagramShape _diagramShape;

    public ResizePoint(int position) {
        super();
        this.setSize(8, 8);
        _position = position;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    static public void setLinkedList(LinkedList vSimUndoList) {
        undoList = vSimUndoList;
    }// new undoredo

    public void setDiagramElement(DiagramShape d) {
        _diagramShape = d;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        undovalue = new Vector();// new
        undomethod = new Vector();// new

        _x = e.getX();
        _y = e.getY();

        _ddx = _diagramShape.getX();
        _ddy = _diagramShape.getY();

        undovalue.add("getLocation");// new
        undovalue.add("getSize");
        undomethod.add("setLocation");
        undomethod.add("setSize");

        undoCommand = new UndoCommand(_diagramShape, undovalue, undomethod);// new
                                                                            // UndoCommand

        undoList.add(undoCommand);// new]

        System.out.println("mousePressed, x : " + _x);
        System.out.println("mousePressed, y : " + _y);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        int x1, y1, x2, y2, x3 = 0, y3 = 0;

        x1 = e.getX();
        y1 = e.getY();
        x2 = getX() + x1 - _x;
        y2 = getY() + y1 - _y;

        /*
         * System.out.println("mouseDragged, x1 : " + x1);
         * System.out.println("mouseDragged, y1 : " + y1);
         * System.out.println("mouseDragged, x2 : " + this.getX());
         * System.out.println("mouseDragged, y2 : " + this.getY());
         */

        /*
         * // move the resize point switch(_position) { case NW: case NE: case
         * SW: case SE: x3 = x2; y3 = y2; break; case E: case W: x3 = x2; y3 =
         * this.getY(); break; case N: case S: x3 = this.getX(); y3 = y2; break;
         * default:
         * System.out.println("Error: invalid position of Resize Point"); }
         * 
         * setLocation(x3, y3);
         */
        // resize the diagram element
        int dx, dy;
        dx = e.getX() - _x;
        dy = e.getY() - _y;
        x1 = _diagramShape.getX();
        y1 = _diagramShape.getY();
        x2 = x1 + _diagramShape.getWidth() - 1;
        y2 = y1 + _diagramShape.getHeight() - 1;
        int width, height;
        width = _diagramShape.getWidth();
        height = _diagramShape.getHeight();

        switch (_position) {
            case NW:
                _diagramShape.setLocation(x1 + dx, y1 + dy);
                // Testing!!!!!
                _diagramShape.setSize(width - dx, height - dy);
                break;
            case NE:
                _diagramShape.setLocation(x1, y1 + dy);
                _diagramShape.setSize(width + dx, height - dy);
                break;
            case SW:
                _diagramShape.setLocation(x1 + dx, y1);
                _diagramShape.setSize(width - dx, height + dy);
                break;
            case SE:
                _diagramShape.setLocation(x1, y1);
                _diagramShape.setSize(width + dx, height + dy);
                break;
            case E:
                _diagramShape.setLocation(x1, y1);
                _diagramShape.setSize(width + dx, height);
                break;
            case W:
                _diagramShape.setLocation(x1 + dx, y1);
                _diagramShape.setSize(width - dx, height);
                break;
            case N:
                _diagramShape.setLocation(x1, y1 + dy);
                _diagramShape.setSize(width, height - dy);
                break;
            case S:
                _diagramShape.setLocation(x1, y1);
                _diagramShape.setSize(width, height + dy);
                break;
            default:
                System.out.println("Error: invalid position of Resize Point");
        }

        if (_diagramShape.getWidth() < DiagramShape.DEFAULT_WIDTH) {
            _diagramShape.setLocation(x1, _diagramShape.getY());
            _diagramShape.setSize(DiagramShape.DEFAULT_WIDTH, _diagramShape.getHeight());
        }

        if (_diagramShape.getHeight() < DiagramShape.DEFAULT_HEIGHT) {
            _diagramShape.setLocation(_diagramShape.getX(), y1);
            _diagramShape.setSize(_diagramShape.getWidth(), DiagramShape.DEFAULT_HEIGHT);
        }

        (_diagramShape).repaintAssociations();
        (_diagramShape).showResizePoints();
    }

    public void mouseMoved(MouseEvent e) {
    }
}
