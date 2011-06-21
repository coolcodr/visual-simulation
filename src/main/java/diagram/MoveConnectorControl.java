package diagram;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

class MoveConnectorControl extends Control implements KeyListener {

    int x, y;
    protected static boolean _addedToPane;

    // Testing!!!!!
    KeyControl _keyControl = KeyControl._defaultKeyControl;

    Control _controller, _defaultController;

    static {
        _addedToPane = false;
    }

    public MoveConnectorControl() {
        _defaultController = new DefaultMoveConnectorControl();
        restoreControl();
    }

    public void mouseDragged(MouseEvent e) {
//		System.err.println("Dragged");
        if (_controller != null) {
            _controller.mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
//		System.err.println("Moved");
        if (_controller != null) {
            _controller.mouseMoved(e);
        }
    }

    public void mousePressed(MouseEvent e) {
        // System.out.println(e);
//		System.err.println("Pressed");
        if (_controller != null) {
            _controller.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
//		System.err.println("Released");
        if (_controller != null) {
            _controller.mouseReleased(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        if (kc == 127) {
            _keyControl = KeyControl._deleteKeyControl;
        } else {
            _keyControl = KeyControl._defaultKeyControl;
        }

        _keyControl.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        _keyControl.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        _keyControl.keyTyped(e);
    }

    public void restoreControl() {
        _controller = _defaultController;
    }

    public void setControl(Control controller) {
        _controller = controller;
    }
}
