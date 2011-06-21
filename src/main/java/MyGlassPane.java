import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class MyGlassPane extends JComponent implements MouseListener, MouseMotionListener {
	
	MouseInputAdapter _controller;
	CBListener _defaultListener;
	
	public MyGlassPane(Container contentPane, JToolBar toolbar) {
	        _defaultListener = new CBListener(this, contentPane, toolbar);
	        restoreDefaultController();
	        addMouseListener(_controller);
	        addMouseMotionListener(_controller);	        
	}
	
	public void mouseClicked(MouseEvent e) {
		if (_controller != null)
			_controller.mouseClicked(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		if (_controller != null)
			_controller.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e) {
		if (_controller != null)
			_controller.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		//System.out.println(_controller);
		if (_controller != null)
			_controller.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e)  {
		if (_controller != null)
			_controller.mouseReleased(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (_controller != null)
			_controller.mouseDragged(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		if (_controller != null)
			_controller.mouseMoved(e);
	}
	
	public void restoreDefaultController() {
		_controller = _defaultListener;
	}
	
	public void setController(MouseInputAdapter controller) {
		_controller = controller;
	}
		
}