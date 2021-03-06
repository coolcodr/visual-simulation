package diagram;

import java.awt.event.KeyEvent;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DeleteKeyControl implements KeyControl {
    DiagramElement d;

    public void keyPressed(KeyEvent e) {
        d = (DiagramElement) e.getSource();
    }

    public void keyReleased(KeyEvent e) {
        DiagramShape.getDiagram().getDiagramControl().removeDiagramElement(d);
    }

    public void keyTyped(KeyEvent e) {
    }
}
