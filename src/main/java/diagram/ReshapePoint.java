package diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReshapePoint extends JComponent implements MouseListener, MouseMotionListener {
	
	private int _x, _y;
	
	private DiagramConnector _diagramConnector;
	
	public ReshapePoint() {
		super();
		this.setSize(8,8);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void setDiagramElement(DiagramConnector d) {
		_diagramConnector = d;
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.fillRect(0,0, getWidth()-1, getHeight()-1);
	}
	
	public  void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {
		_x = e.getX();
		_y = e.getY();
		
		DiagramConnector dc = _diagramConnector;
		for (int i = 0; i < dc.getReshapePoints().size(); i++) {
			if (this == dc.getReshapePoint(i)) {
				dc.setFocusedPoint(i);
			}
		}
		
		dc.setReshapePointsVisible(true);
	}
	
	public void mouseReleased(MouseEvent e) {
		DiagramConnector dc = _diagramConnector;
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
						
						if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope) ) {
							result = true;
						}
					}
					else {
						eSlope1 = (y-p1y)/(x-(p1x-5));
						eSlope2 = (y-p1y)/(x-(p1x+5));
						
						if ((eSlope1/eSlope2) > 0 ) {
							if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope) ) {
								result = true;
							}
						}
						else if (eSlope1 != eSlope2) {
							if (slope > 0) {
								if ( (Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) <= slope) ) {
									result = true;
								}
							}
							else {
								if ( (Math.min(eSlope1, eSlope2) >= slope) && (Math.max(eSlope1, eSlope2) >= slope) ) {
									result = true;
								}
							}
						}
					}
				}
				
				if (result) {
					dc.removePoint(index);
					dc.removeReshapePoint(index);
					dc.setReshapePointsVisible(false);
				}
			}
		}
	}
	
	public void mouseDragged(MouseEvent e) {
		DiagramConnector dc = _diagramConnector;
		
		int index = dc.getFocusedPoint();
		int x = this.getX()+this.getWidth()/2-(_x-e.getX());
		int y = this.getY()+this.getHeight()/2-(_y-e.getY());
		
		DiagramElement source = dc.getAssociations().getConnectorSource(dc);
		DiagramElement target = dc.getAssociations().getConnectorTarget(dc);
		
		if (index == 0) {
			
			if (x < source.getX())
				x = source.getX();
			else if (x > source.getX() + source.getWidth())
				x = source.getX() + source.getWidth();
			
			if (y < source.getY())
				y = source.getY();
			else if (y > source.getY() + source.getHeight())
				y = source.getY() + source.getHeight();
		}
		else if (index == dc.getPoints().size()-1) {
			
			if (x < target.getX())
				x = target.getX();
			else if (x > target.getX() + target.getWidth())
				x = target.getX() + target.getWidth();
			
			if (y < target.getY())
				y = target.getY();
			else if (y > target.getY() + target.getHeight())
				y = target.getY() + target.getHeight();
		}
		
		dc.movePoint(x, y, index);
		
		Point p;
		
		if (index == 1) {
			p = dc.findPoint(x, y, source);
			dc.movePoint(p.x, p.y, 0);
		}
		
		if (index == dc.getPoints().size()-2) {
			p = dc.findPoint(x, y, target);
			dc.movePoint(p.x, p.y, -1);
		}
	}
	
	public void mouseMoved(MouseEvent e) {
	}
}