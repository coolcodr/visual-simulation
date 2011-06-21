package diagram;

import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;


public class SelectionControl2 extends Control {

	Point _startP, _stopP;
	boolean _pressed;
	Vector _deArray;
	int previousX;
	int previousY;	
	MyGlassPane _upperPane;
	Diagram _lowerPane;
	DiagramSelection _ds;

	public SelectionControl2(Diagram lowerPane) {
		_upperPane = null;
		_lowerPane = lowerPane;
	}
	
	public void mousePressed(MouseEvent e) {
					
		_deArray = new Vector();
		_startP = new Point();
		_stopP = new Point();
			
		_startP.x = e.getX();
		_startP.y = e.getY();
		_pressed = true;
		_ds = new DiagramSelection();
		_lowerPane.getMainPane().add(_ds);
		 _ds.setLocation(e.getX(), e.getY());
		previousX=0;
		previousY=0;		 	
	}
	
	public void mouseDragged(MouseEvent e) {
		
		_stopP.x=e.getX();
		_stopP.y = e.getY();
		if(_startP.distance(_stopP)>0) {
			int x,y;
			if(_startP.x > _stopP.x) {
				x = _stopP.x;
			}
			else {
				x = _startP.x;
			}
			if(_startP.y > _stopP.y) {
				y = _stopP.y;
			}
			else {
				y = _startP.y;
			}
			_ds.setLocation(x,y);
			_ds.setSize(Math.abs(_startP.x - _stopP.x),Math.abs(_startP.y - _stopP.y));
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
		for(int i=1;i<=_lowerPane.getDiagramControl().getMaxElementId();i++) {
			DiagramElement tempDe = _lowerPane.getDiagramControl().getDiagramElement(i);
			if(tempDe!=null) {
				if (tempDe.location().x > _ds.location().x && tempDe.location().y > _ds.location().y
				    && (tempDe.location().x+tempDe.size().getWidth()) < (_ds.location().x+_ds.size().getWidth())
				    && (tempDe.location().y+tempDe.size().getHeight()) < (_ds.location().y+_ds.size().getHeight())) {
					if(tempDe.getType().getCategory() == DiagramElementType.SHAPE) {
						((DiagramShape)tempDe).showResizePoints();
						_deArray.add(tempDe);
						System.err.println("\nselected element:"+tempDe);
						for(int k=0;k<_lowerPane.getDiagramControl().getAssociations().getAssociations().size();k++) {
							Association a = ((Association)_lowerPane.getDiagramControl().getAssociations().getAssociations().elementAt(k));
							if(a.getParent()==tempDe) {
								if(a.getChild().getType().isPort()) {
									_deArray.add(a.getChild());
									System.err.println("\nselected element(child):"+a.getChild());
								}
							}
							
						}
					}
					if(tempDe.getType().getCategory() == DiagramElementType.CONNECTOR){
						((DiagramConnector)tempDe).setReshapePointsVisible(true);
						_deArray.add(tempDe);
						System.err.println("\nselected element(connector):"+tempDe);
					}														
					
				}
			}
		}
		System.err.println("\nselected element size:"+_deArray.size());
//		System.err.println("\nDiagram Element in the diagram(SelectionControl2):");
//		for(int i =1;i<=_lowerPane.getDiagramControl().getMaxElementId();i++) {
//			System.err.println("\t" + _lowerPane.getDiagramControl().getDiagramElement(i));
//		}
		System.err.println("\nAssociation in the diagram(SelectionControl2):");			
		for(int i =0;i<_lowerPane.getDiagramControl().getAssociations().getAssociations().size();i++) {
			System.err.println("\t Child" + ((Association)_lowerPane.getDiagramControl().getAssociations().getAssociations().elementAt(i)).getChild());
			System.err.println("\t Parent" + ((Association)_lowerPane.getDiagramControl().getAssociations().getAssociations().elementAt(i)).getParent());
			System.err.println("\t Connector" + ((Association)_lowerPane.getDiagramControl().getAssociations().getAssociations().elementAt(i)).getConnector());
		}
		if(_deArray.size() != 0) {
			MoveSelectionControl MSC = new MoveSelectionControl(_deArray,_lowerPane);	
			for(int i =0;i<_deArray.size();i++) {
				if(((DiagramElement)_deArray.elementAt(i)).getType().getCategory() == DiagramElementType.SHAPE) {
	//				((DiagramShape)tempDe).showResizePoints(tempDe.getPane(), (DiagramShape)tempDe);
					((DiagramShape)_deArray.elementAt(i)).getShapeControl().setControl(MSC);
				}
				if(((DiagramElement)_deArray.elementAt(i)).getType().getCategory() == DiagramElementType.CONNECTOR){
					((DiagramConnector)_deArray.elementAt(i)).getController().setControl(MSC);
				}				
			}	
//			_lowerPane.getDiagramControl().setControl(new MoveSelectionControl(_deArray,_lowerPane));
			_lowerPane.getDiagramControl().setSelectedElements(_deArray);
			_lowerPane.getDiagramControl().setShift(0);
		}
		else {
			_lowerPane.getDiagramControl().setSelectedElements(null);
		}
		_lowerPane.getDiagramControl().restoreControl();
		_lowerPane.getMainPane().remove(_ds);
		_lowerPane.getMainPane().repaint();
	
	}
}