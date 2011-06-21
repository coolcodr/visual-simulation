import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.event.MouseInputAdapter;

class MyGlassPane extends JComponent implements MouseListener, MouseMotionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 8928840647986253448L;
    MouseInputAdapter _controller;
    CBListener _defaultListener;

    public MyGlassPane(Container contentPane, JToolBar toolbar) {
        _defaultListener = new CBListener(this, contentPane, toolbar);
        restoreDefaultController();
        addMouseListener(_controller);
        addMouseMotionListener(_controller);
    }

    public void mouseClicked(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseClicked(e);
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseEntered(e);
        }
    }

    public void mouseExited(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseClicked(e);
        }
    }

    public void mousePressed(MouseEvent e) {
        // System.out.println(_controller);
        if (_controller != null) {
            _controller.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseReleased(e);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseDragged(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        if (_controller != null) {
            _controller.mouseMoved(e);
        }
    }

    public void restoreDefaultController() {
        _controller = _defaultListener;
    }

    public void setController(MouseInputAdapter controller) {
        _controller = controller;
    }

}
