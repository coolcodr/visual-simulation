package diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class MoveShapeKeyControl implements KeyControl{
	DiagramShape d;
	
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		d = (DiagramShape) e.getSource(); 
		
		d.hideResizePoints();
		
		switch(kc) {
			case 37:
				d.setLocation(d.getX()-1, d.getY());
				break;
			case 38:
				d.setLocation(d.getX(), d.getY()-1);
				break;
			case 39:
				d.setLocation(d.getX()+1, d.getY());
				break;
			case 40:
				d.setLocation(d.getX(), d.getY()+1);
				break;
		}
		
		d.repaintAssociations();
	}
	
	public void keyReleased(KeyEvent e) {
		d.showResizePoints();
	}
	
	public void keyTyped(KeyEvent e) {
	}
}