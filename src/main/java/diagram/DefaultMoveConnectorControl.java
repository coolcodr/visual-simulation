package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

class DefaultMoveConnectorControl extends Control implements KeyListener {
	
	int x, y;
	protected static boolean _addedToPane;
	
	//Testing!!!!!
	KeyControl _keyControl = KeyControl._defaultKeyControl;
	
	static {
		_addedToPane = false;
	}

	public void mouseDragged(MouseEvent e)
	{
		DiagramConnector dc = (DiagramConnector) e.getSource();
		
		int x = e.getX() + dc.getX();
		int y = e.getY() + dc.getY();
		
		dc.movePoint(x, y, dc.getFocusedPoint());
		
		DiagramElement source = dc.getAssociations().getConnectorSource(dc);
		DiagramElement target = dc.getAssociations().getConnectorTarget(dc);
		Point p;
		
		if (dc.getFocusedPoint() == 1) {
			p = dc.findPoint(x, y, source);
			dc.movePoint(p.x, p.y, 0);
		}
		
		if (dc.getFocusedPoint() == dc.getPoints().size()-2) {
			p = dc.findPoint(x, y, target);
			dc.movePoint(p.x, p.y, -1);
		}
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		//System.out.println(e);
		x = e.getX();
		y = e.getY();
		
		DiagramConnector dc = (DiagramConnector) e.getSource();
		//Testing!!!!!!!
		DiagramConnector.getDiagram().getDiagramControl().setSelectedElement(dc);
		DiagramConnector.getDiagram().getDiagramControl().restoreSelectedElementsControl();
		int index = dc.contain2(x, y);
		
		if (index != -1) {
			dc.setFocusedPoint(index+1);
			dc.addPoint(e.getX()+dc.getX(), e.getY()+dc.getY(), index+1);
			dc.addReshapePoint(e.getX()+dc.getX(), e.getY()+dc.getY(), index+1);
		}
		
		dc.setReshapePointsVisible(true);
		
		dc.requestFocus();
	}
	
	public void mouseReleased(MouseEvent e) {
		DiagramConnector dc = (DiagramConnector) e.getSource();
		DiagramConnector.getDiagram().getDiagramControl().hideDiagramElementsResizePoints();
		dc.setReshapePointsVisible(true);
		int index = dc.getFocusedPoint();
		
		if ((index > 0) && (index < dc.getPoints().size()-1)) {
			
			double x = dc.getPoint(index).x;
			double y = dc.getPoint(index).y;
			double p1x = dc.getPoint(index-1).x;
			double p1y = dc.getPoint(index-1).y;
			double p2x = dc.getPoint(index+1).x;
			double p2y = dc.getPoint(index+1).y;
			double slope = 0;
			double eSlope1 = 0;
			double eSlope2 = 0;
			
			boolean result  = false;
			
			if ((x >= Math.min(p1x, p2x)-5) && (x <= Math.max(p1x, p2x)+5) && (y >= Math.min(p1y, p2y)-5) && (y <= Math.max(p1y, p2y)+5)) {
				
				slope = (p2y-p1y)/(p2x-p1x);
				
				if (p1x == p2x) {  // for slope = infinity
					if (Math.abs(x-p1x) < 3)
						result = true;
				}
				else if (p1y == p2y) {  // for slope = 0
					if (Math.abs(y-p1y) < 3)
						result = true;
				}
				else {
					if ((slope >= -1) && (slope <= 1)) {
						eSlope1 = (y-(p1y-5))/(x-p1x);
						eSlope2 = (y-(p1y+5))/(x-p1x);
						
						if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope) )
							result = true;
					}
					else {
						eSlope1 = (y-p1y)/(x-(p1x-5));
						eSlope2 = (y-p1y)/(x-(p1x+5));
						
						if ((eSlope1/eSlope2) > 0 ) {
							if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope) )
								result = true;
						}
						else if (eSlope1 != eSlope2) {
							if (slope > 0) {
								if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) <= slope) )
									result = true;
							}
							else {
								if ( (Math.min(eSlope1, eSlope2) >= slope) && (Math.max(eSlope1, eSlope2) >= slope) )
									result = true;
							}
						}
					}
				}
				
				if (result) {
					dc.removePoint(index);
					dc.removeReshapePoint(index);
//					dc.setReshapePointsVisible(false);
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int kc = e.getKeyCode();
		
		if (kc == 127)
			_keyControl = KeyControl._deleteKeyControl;
		else
			_keyControl = KeyControl._defaultKeyControl;
		
		_keyControl.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		_keyControl.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		_keyControl.keyTyped(e);
	}
}