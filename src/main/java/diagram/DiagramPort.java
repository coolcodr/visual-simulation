package diagram;
import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramPort extends DiagramElement {
	
	private static PortControl _portControl = new PortControl();
	
	static String INPORT = "inPort";
	static String OUTPORT = "outPort";
	static String EXITPORT = "exitPort";
	static String STARTPORT = "startPort";
	static String ENDPORT = "endPort";
	//Added by Kenny
	static String DATASOURCEPORT = "dataSourcePort";
	static String CHARTPORT = "chartPort";	
	
	static final int DEFAULT_WIDTH = 15;
	static final int DEFAULT_HEIGHT = 15;
	
	private int _portIndex;
	
	//Testing!!!!
	private int _parentPointX, _parentPointY;
	
	public DiagramPort() {
		//Testing!!!
		
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		this.addMouseListener(_portControl);
		this.addMouseMotionListener(_portControl);
		
		setConnectValid(true);
	}
	
	public void setPortIndex(int portIndex) {
		_portIndex = portIndex;
	}
	
	public int getPortIndex() {
		return _portIndex;
	}
	
	public void repaintAssociations() {
		// move the connectors
		
		DiagramElement d = this;
		Enumeration en = d.getAssociations().getConnections(d);
		DiagramConnector dc;
		Association a;
		for (; en.hasMoreElements();) {
			a = (Association) en.nextElement();
			DiagramElement source = a.getParent();
			DiagramElement target = a.getChild();
			dc = a.getConnector();
			Point p;
			
			if (source == d) {
				p = dc.findPoint(dc.getPoint(1).x, dc.getPoint(1).y, d);
				dc.movePoint(p.x, p.y, 0);

				p = dc.findPoint(dc.getPoint(dc.getPoints().size()-2).x, dc.getPoint(dc.getPoints().size()-2).y, target);
				dc.movePoint(p.x, p.y, -1);
			}
			else {
				p = dc.findPoint(dc.getPoint(dc.getPoints().size()-2).x, dc.getPoint(dc.getPoints().size()-2).y, target);
				dc.movePoint(p.x, p.y, -1);

				p = dc.findPoint(dc.getPoint(1).x, dc.getPoint(1).y, source);
				dc.movePoint(p.x, p.y, 0);
			}
		}
	}
	
	public Point findParentPoint(int ex, int ey) { // event x, y of the DiagramPort  &&  return absolute x, y
		int _dx, _dy;
		int _ex, _ey;
		
		_dx = _ex = this.getX() + ex;
		_dy = _ey = this.getY() + ey;
		
		DiagramElement _p = this.getAssociations().getParent(this);
		
		if (_ex < _p.getX())
			_dx = _p.getX()-this.getWidth();
		else if (_ex > (_p.getX() + _p.getWidth()))
			_dx = _p.getX() + _p.getWidth();
		
		if (_ey < _p.getY())
			_dy = _p.getY()-this.getHeight();
		else if (_ey > (_p.getY() + _p.getHeight()))
			_dy = _p.getY() + _p.getHeight();
		
		if ((_ex <= _p.getX()+_p.getWidth()/2)&&(_ey <= _p.getY()+_p.getHeight()/2)&&(_ex >= _p.getX())&&(_ey >= _p.getY())) {
			if (((_ex-_p.getX())*_p.getHeight()) > ((_ey-_p.getY())*_p.getWidth()))
				_dy = _p.getY()-this.getHeight();
			else
				_dx = _p.getX()-this.getWidth();
		}
		else if ((_ex > _p.getX()+_p.getWidth()/2)&&(_ey < _p.getY()+_p.getHeight()/2)&&(_ex <= _p.getX()+_p.getWidth())&&(_ey >= _p.getY())) {
			if (((_p.getWidth()-(_ex-_p.getX()))*_p.getHeight()) > ((_ey-_p.getY())*_p.getWidth()))
				_dy = _p.getY()-this.getHeight();
			else
				_dx = _p.getX() + _p.getWidth();
		}
		else if ((_ex < _p.getX()+_p.getWidth()/2)&&(_ey > _p.getY()+_p.getHeight()/2)&&(_ex >= _p.getX())&&(_ey <= _p.getY()+_p.getHeight())) {
			if (((_ex-_p.getX())*_p.getHeight()) > ((_p.getHeight()-(_ey-_p.getY()))*_p.getWidth()))
				_dy = _p.getY()+_p.getHeight();
			else
				_dx = _p.getX()-this.getWidth();
		}
		else if ((_ex >= _p.getX()+_p.getWidth()/2)&&(_ey >= _p.getY()+_p.getHeight()/2)&&(_ex <= _p.getX()+_p.getWidth())&&(_ey <= _p.getY()+_p.getHeight())) {
			if (((_p.getWidth()-(_ex-_p.getX()))*_p.getHeight()) > ((_p.getHeight()-(_ey-_p.getY()))*_p.getWidth()))
				_dy = _p.getY() + _p.getHeight();
			else
				_dx = _p.getX() + _p.getWidth();
		}
		
		return new Point(_dx, _dy);  // return absolute x, y
	}
	
	public void setParentPointX(int x) {
		_parentPointX = x;
	}
	
	public void setParentPointY(int y) {
		_parentPointY = y;
	}
	
	public int getParentPointX() {
		return _parentPointX;
	}
	
	public int getParentPointY() {
		return _parentPointY;
	}
	
	public boolean isMessageQueue() {
		DiagramShape shape = (DiagramShape) this.getAssociations().getParent(this);
		
		return shape.isMessageQueue();
	}
}