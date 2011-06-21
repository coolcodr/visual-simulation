package diagram;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class PortControl extends MouseAdapter implements MouseMotionListener {

    int x, y;

    public void mouseDragged(MouseEvent e) {
        int _dx, _dy;
        DiagramPort _d = (DiagramPort) e.getSource();

        _d.setLocation(_d.findParentPoint(e.getX(), e.getY()));

        DiagramElement _p = _d.getAssociations().getParent(_d);
        _d.setParentPointX(_d.getX() - _p.getX());
        _d.setParentPointY(_d.getY() - _p.getY());

        ((DiagramPort) e.getSource()).repaintAssociations();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

}
