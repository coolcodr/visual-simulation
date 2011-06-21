package diagram;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class Control extends MouseInputAdapter {

    protected DiagramElementType _type;
    protected DiagramControl _diagramControl;
    protected Diagram _diagram;

    public Control() {
    }

    public Control(DiagramElementType type, DiagramControl diagramControl) {
        _type = type;
        _diagramControl = diagramControl;

    }

    public void setType(DiagramElementType type) {
        _type = type;
    }

    public void setDiagramControl(DiagramControl diagramControl) {
        _diagramControl = diagramControl;
        _diagram = _diagramControl.getDiagram();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

}
