package diagram;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class MoveSelectionControl extends Control {

    Point _startP, _stopP;
    boolean _pressed;
    Vector _deVector;
    int previousX;
    int previousY;
    Diagram _diagram;

    public MoveSelectionControl(Vector deVector, Diagram diagram) {
        _deVector = deVector;
        _startP = new Point();
        _stopP = new Point();
        _diagram = diagram;
    }

    public void mousePressed(MouseEvent e) {

        _startP = SwingUtilities.convertPoint((JComponent) e.getSource(), new Point(e.getX(), e.getY()), _diagram);
        _pressed = false;
        previousX = 0;
        previousY = 0;

    }

    public void mouseDragged(MouseEvent e) {

        _stopP = SwingUtilities.convertPoint((JComponent) e.getSource(), new Point(e.getX(), e.getY()), _diagram);
        int deltaX = _stopP.x - _startP.x;
        int deltaY = _stopP.y - _startP.y;
        for (int i = 0; i < _deVector.size(); i++) {
            DiagramElement tempDe = (DiagramElement) _deVector.elementAt(i);
            if (tempDe.getType().getCategory() == DiagramElementType.SHAPE) {
                tempDe.setLocation(tempDe.getLocation().x - previousX + deltaX, tempDe.getLocation().y - previousY + deltaY);
                ((DiagramShape) tempDe).repaintAssociations();
                ((DiagramShape) tempDe).hideResizePoints();
                ((DiagramShape) tempDe).showResizePoints();
            } else if (tempDe.getType().isConnector()) {

                for (int k = 1; k < ((DiagramConnector) tempDe).getReshapePoints().size() - 1; k++) {
                    ReshapePoint rsp = ((DiagramConnector) tempDe).getReshapePoint(k);
//					Point tempPoint = SwingUtilities.convertPoint(DiagramConnector.getDiagram().getContentPane(), new Point(rsp.getX(),rsp.getY()),(JComponent)tempDe);
//					rsp.setLocation(rsp.getLocation().x-previousX+deltaX,rsp.getLocation().y-previousY+deltaY);
                    ((DiagramConnector) tempDe).movePoint(rsp.getLocation().x - previousX + deltaX + rsp.getWidth() / 2, rsp.getLocation().y - previousY + deltaY + rsp.getHeight() / 2, k);
                }

            } else {
            }
        }

        previousX = deltaX;
        previousY = deltaY;
    }
}
