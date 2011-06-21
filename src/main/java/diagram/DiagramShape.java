package diagram;
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
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

public class DiagramShape extends DiagramElement{
	
	ShapeControl _shapeControl;
	ResizePoint _nw, _n, _ne, _e, _se, _s, _sw, _w;
	ResizePoint _resizePoints[];
	boolean _resizePointAdded;
	
	static final int DEFAULT_WIDTH = 75;
	static final int DEFAULT_HEIGHT = 75;
	
	//Testing!!!!  using the diagram and diagram control
	protected static Diagram _diagram = null;
	
	protected Vector _properties;
	//protected JLabel imageLabel; 
	
	public static void setDiagram(Diagram diagram) {
		_diagram = diagram;
	}
	
	public static Diagram getDiagram() {
		return _diagram;
	}
	
	public DiagramShape() {
		super(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		_resizePointAdded = false;
		_nw = new ResizePoint(ResizePoint.NW);
		_n = new ResizePoint(ResizePoint.N);
		_ne = new ResizePoint(ResizePoint.NE);
		_e = new ResizePoint(ResizePoint.E);
		_se = new ResizePoint(ResizePoint.SE);
		_s = new ResizePoint(ResizePoint.S);
		_sw = new ResizePoint(ResizePoint.SW);
		_w = new ResizePoint(ResizePoint.W);
		_resizePoints = new ResizePoint[8];
		_resizePoints[0] = _nw;
		_resizePoints[1] = _n;
		_resizePoints[2] = _ne;
		_resizePoints[3] = _e;
		_resizePoints[4] = _se;
		_resizePoints[5] = _s;
		_resizePoints[6] = _sw;
		_resizePoints[7] = _w;
		for (int i = 0; i < _resizePoints.length; i++) {
			_resizePoints[i].setDiagramElement(this);
			_resizePoints[i].setVisible(false);
			_diagram.getContentPane().add(_resizePoints[i], JLayeredPane.DEFAULT_LAYER, 0);
		}
		
		_shapeControl = new ShapeControl();		
	    addMouseListener(_shapeControl);
	    addMouseMotionListener(_shapeControl);
	  	addKeyListener(_shapeControl);
		_properties = new Vector();
		//imageLabel = new JLabel("Hello");
		//imageLabel.setVisible(true);
		setLayout(null);
		//imageLabel.setBounds(10, 20, 30, 40);
		//add(imageLabel);

	}
	
	public void showResizePoints() {
		int i;
		int w, h;
		int x1, y1, x2, y2;
		
		w = _nw.getWidth();
		h = _nw.getHeight();
		x1 = this.getX();
		y1 = this.getY();
		x2 = x1 + this.getWidth() - 1;
		y2 = y1 + this.getHeight() - 1;
		
		// set locations
		_nw.setLocation(x1-w, y1-h);
		_n.setLocation((x1+x2-w)/2, y1-h);
		_ne.setLocation(x2, y1-h);
		_e.setLocation(x2, (y1+y2-h)/2);
		_se.setLocation(x2, y2);
		_s.setLocation((x1+x2-w)/2, y2);
		_sw.setLocation(x1-w, y2);
		_w.setLocation(x1-w, (y1+y2-h)/2);
		
//		if (_resizePointAdded == false) {
//			for (i = 0; i < _resizePoints.length; i++) {
//				pane.add(_resizePoints[i], JLayeredPane.PALETTE_LAYER, 0);
//			}
//			_resizePointAdded = true;
//		}

		for (i = 0; i < _resizePoints.length; i++) {
//			_resizePoints[i].setDiagramElement(d);
			_resizePoints[i].setVisible(true);
		}

		
	}
	
	public void hideResizePoints() {
		for (int i = 0; i < _resizePoints.length; i++)
			_resizePoints[i].setVisible(false);
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
				p = dc.findPoint(dc.getPoint(dc.getPoints().size()-2).x, dc.getPoint(dc.getPoints().size()-2).y, d);
				dc.movePoint(p.x, p.y, -1);
				p = dc.findPoint(dc.getPoint(1).x, dc.getPoint(1).y, source);
				dc.movePoint(p.x, p.y, 0);
			}
			
		}
		
		//Testing!!!! for repaint ports association
		en = d.getAssociations().getGeneration(d);
		for (; en.hasMoreElements();) {
			a = (Association) en.nextElement();
			if (a.getParent() == d) {
				DiagramPort _port = ((DiagramPort)a.getChild());
				Point point = _port.findParentPoint(0, 0);
				
				if ((_port.getParentPointY() <= 0) || (_port.getParentPointY() >= d.getHeight())) {
					if (_port.getParentPointX() >d.getWidth()) {
						_port.setParentPointX(d.getWidth());
					}
				}
				else {
					if (_port.getParentPointX() > 0) {
						_port.setParentPointX(d.getWidth());
					}
				}
				
				if ((_port.getParentPointX() <= 0) || (_port.getParentPointX() >= d.getWidth())) {
					if (_port.getParentPointY() >d.getHeight()) {
						_port.setParentPointY(d.getHeight());
					}
				}
				else {
					if (_port.getParentPointY() > 0) {
						_port.setParentPointY(d.getHeight());
					}
				}
				
				_port.setLocation(d.getX()+_port.getParentPointX(), d.getY()+_port.getParentPointY());
				_port.repaintAssociations();
			}
		}
	}
	
	//Testing!!!!
	public void remove() {
		hideResizePoints();
	}
	
	//Testing!!!! for ADD Port
	public void setPort() { //abstract for set port....
	}
	
	//Testing!!!! for checking this shape is a Message Queue
	public boolean isMessageQueue() {
		return false; // Default is not
	}
	
	public DiagramPort addPort(DiagramElement parent, String type) throws java.lang.Exception {
		// add a Port
		DiagramElementType portType = null;
		DiagramPort pd = null;
		if (type == DiagramPort.INPORT) {
			portType = new DiagramElementType("Inport", "diagram.DiagramInPort", DiagramElementType.PORT, "diagram.InPortRenderer", "diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()-DiagramPort.DEFAULT_WIDTH, parent.getY()+parent.getHeight()/2-DiagramPort.DEFAULT_HEIGHT/2);
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()-pd.getWidth(), parent.getY()+parent.getHeight()/2-pd.getHeight()/2);
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
			
			//System.out.println("Port Width = " + pd.getWidth());
		}
		else if (type == DiagramPort.OUTPORT) {
			portType = new DiagramElementType("Outport", "diagram.DiagramOutPort", DiagramElementType.PORT, "diagram.OutPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()+parent.getWidth(), parent.getY()+parent.getHeight()/2-DiagramPort.DEFAULT_HEIGHT/2);
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()+parent.getWidth(), parent.getY()+parent.getHeight()/2-pd.getHeight()/2);
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		else if (type == DiagramPort.EXITPORT) {
			portType = new DiagramElementType("Exitport", "diagram.DiagramExitPort", DiagramElementType.PORT, "diagram.ExitPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()+parent.getWidth()/2-DiagramPort.DEFAULT_WIDTH/2, parent.getY()-DiagramPort.DEFAULT_HEIGHT);
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()+parent.getWidth()/2-pd.getWidth()/2, parent.getY()-pd.getHeight());
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		else if (type == DiagramPort.STARTPORT) {
			portType = new DiagramElementType("Startport", "diagram.DiagramStartPort", DiagramElementType.PORT, "diagram.StartPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()-DiagramPort.DEFAULT_WIDTH, parent.getY()+parent.getHeight()/2-DiagramPort.DEFAULT_HEIGHT/2);
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()-pd.getWidth(), parent.getY()+parent.getHeight()/2-pd.getHeight()/2);
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		else if (type == DiagramPort.ENDPORT) {
			portType = new DiagramElementType("Endport", "diagram.DiagramEndPort", DiagramElementType.PORT, "diagram.EndPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()+parent.getWidth(), parent.getY()+parent.getHeight()/2-DiagramPort.DEFAULT_HEIGHT/2);
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()+parent.getWidth(), parent.getY()+parent.getHeight()/2-pd.getHeight()/2);
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		// add by Kenny
		else if (type == DiagramPort.DATASOURCEPORT) {
			portType = new DiagramElementType("DataSourcePort", "diagram.DiagramDataSourcePort", DiagramElementType.PORT, "diagram.DataSourcePortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()+parent.getWidth()/2-DiagramPort.DEFAULT_WIDTH/2, parent.getY()+parent.getHeight());
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()+parent.getWidth()/2-pd.getWidth()/2, parent.getY()+parent.getHeight());
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		// add by Kenny
		else if (type == DiagramPort.CHARTPORT) {
			portType = new DiagramElementType("ChartPort", "diagram.DiagramChartPort", DiagramElementType.PORT, "diagram.ChartPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
			pd = (DiagramPort) _diagram.getDiagramControl().addDiagramElement(portType, parent.getX()+parent.getWidth()/2-DiagramPort.DEFAULT_WIDTH/2, parent.getY()+parent.getHeight());
			pd.getAssociations().add(parent, pd, null, Association.GENERATION, parent.getX()+parent.getWidth()/2-pd.getWidth()/2, parent.getY()+parent.getHeight());
			pd.setParentPointX(pd.getX()-parent.getX());
			pd.setParentPointY(pd.getY()-parent.getY());
		}
		
		
		pd.setPortIndex(-1);
		
		return pd;
	}
	
	public void requestFocus() {
		super.requestFocus();
		this.getDiagram().getJTable().setModel(new TableModelAdapter(_properties, this.getDiagram().getJTable()));
		this.getDiagram().getJTable().setProperties(_properties);
	}
	
	public void setProperties(Vector properties) {
		_properties = properties;
	}
	
	public Vector getProperties() {
		return _properties;
	}
	
	public void setResizePointAdded(boolean _added) {
		_resizePointAdded = _added;
	}
	
	public ShapeControl getShapeControl() {
		return _shapeControl;
	}
	
}