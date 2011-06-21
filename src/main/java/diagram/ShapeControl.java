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

public class ShapeControl extends Control implements KeyListener {

    KeyControl _keyControl;
    Control _controller, _defaultController;

    int x, y;

    public ShapeControl() {
        _defaultController = new DefaultShapeControl();
        restoreControl();
    }

    public void setControl(Control controller) {
        _controller = controller;
    }

    public void restoreControl() {
        _controller = _defaultController;
    }

    public void mouseDragged(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (_controller != null) {
            _controller.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseReleased(e);
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseEntered(e);
        }
    }

//	public void mouseExited(MouseEvent e) {
//		if (_controller != null)
//			_controller.mouseExited(e);
//	}			

    public void keyPressed(KeyEvent e) {
        int kc = e.getKeyCode();

        if (kc > 36 && kc < 41) {
            _keyControl = KeyControl._moveShapeKeyControl;
        } else if (kc == 127) {
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
}
