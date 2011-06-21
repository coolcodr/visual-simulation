package diagram;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramElement extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = 2609858643092406180L;
    protected DiagramElementType _type;
    protected Renderer _renderer;
    protected Associations _associations;
    protected JLayeredPane _pane;
    protected ExporterControl _exporterControl;
    protected int _id;

    private boolean _connectValid;

    public void setPane(JLayeredPane pane) {
        _pane = pane;
    }

    public JLayeredPane getPane() {
        return (_pane);
    }

    public void setType(DiagramElementType t) {
        _type = t;
        _renderer = _type.getRenderer();
    }

    public DiagramElement() {
        super();

        this.setSize(100, 100);
    }

    public DiagramElement(DiagramElementType type) {
        super();
        _type = type;
        _renderer = type.getRenderer();
    }

    public DiagramElement(int w, int h) {
        super();

        // Testing!!!!
        this.setSize(w, h);
    }

    public void setAssociations(Associations asso) {
        _associations = asso;
    }

    public Associations getAssociations() {
        return _associations;
    }

    public void paint(Graphics g) {
        _renderer.paint(this, g);
        paintChildren(g);
    }

    public boolean contains(int x, int y) {
        return _renderer.contains(this, x, y);
    }

    public boolean contains2(int x, int y) {
        return super.contains(x, y);
    }

    // Testing!!!!!
    public void connected(boolean isCOnnected) { // abstract method
        // called when a connector connect this diagram element,
        // isConnected = true, a connector connect this
        // else, a connector disconnect this
    }

    public void setConnectValid(boolean valid) {
        _connectValid = valid;
    }

    public boolean getConnectValid() {
        return _connectValid;
    }

    public boolean isMessageQueue() {
        // Default -- DiagramElement is not a MessageQueue

        return false;
    }

    public void remove() { // abstract for remove this
    }

    public void setId(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public ExporterControl getExporterControl() {
        return _exporterControl;
    }

    public DiagramElementType getType() {
        return _type;
    }
}

class MoveControl extends MouseAdapter implements MouseMotionListener {
    int x, y;

    public void mouseMoved(MouseEvent e) {
        // System.out.println(e);
    }

    public void mouseDragged(MouseEvent e) {
        int dx, dy;
        JComponent b;
        b = (JComponent) e.getSource();
        b.setLocation(b.getX() + e.getX() - x, b.getY() + e.getY() - y);
        // System.out.println(e);
    }

    public void mousePressed(MouseEvent e) {
        // System.out.println(e);
        x = e.getX();
        y = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        // System.out.println(e);
    }

}
