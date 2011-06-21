package diagram;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;


import javax.swing.JPanel;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramControl implements MouseListener, MouseMotionListener {
	private Diagram _diagram;
	private DiagramElement _element;
	private Associations _associations = new Associations(); // keeping the associations
	private Vector _elements = new Vector();
	private int _sx, _sy, _tx, _ty;
	private Control _controller, _defaultController;
	private Vector _selectedElements;
	private int _maxElementId = 0;
	private Vector _properties = new Vector();
	private int _shift = 0;

	public DiagramControl(Diagram d) {
		_defaultController = null;
		_diagram = d;
		_properties.add(new Property("java.lang.Integer", "Simulation time(s)", new Integer("0")));
	}
	
	public Diagram getDiagram() {
		return(_diagram);
	}
	
	public void setSelectedElement(DiagramElement d) {
		if(d!=null) {
			_selectedElements=new Vector();
			if(d.getType().isShape()) {
				for(int k=0;k<getAssociations().getAssociations().size();k++) {
					Association a = ((Association)getAssociations().getAssociations().elementAt(k));
					if(a.getParent()==d) {
						if(a.getChild().getType().isPort())
							_selectedElements.add(a.getChild());
					}
					
				}
			}
			_selectedElements.add(d);
		}
	}
//added by matthew

	public void setSelectedElements(Vector d) {
		_selectedElements = d;
	}
/*
	public void addAssociation(DiagramElement parent, DiagramElement child, String type, int x, int y) {
		_associations.add(new Association(parent, child, Association.CONNECTION, x, y));
	}
*/
	
	public void setControl(Control controller) {
		_controller = controller;
	}
	
	public void restoreControl() {
		_controller = _defaultController;
	}
	
	public DiagramElement addDiagramElement(DiagramElementType type, int x, int y) {
		try {
			_element = (DiagramElement) Class.forName(type.getElementClass()).newInstance();
			_element.setAssociations(this._associations);
			//DiagramElement element = new DiagramElement(_selectedDiagramElementType);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		_element.setType(type);
		_element.setAssociations(_associations);
		_element.setLocation(x,y);
		_maxElementId++;
		_element.setId(_maxElementId);
		_diagram.addDiagramElement(_element);
		_elements.add(_element); // add it to the list of elements
		return(_element);
	}
	
	
//added by matthew, testing!!!!
	public DiagramElement addDiagramSelection(DiagramElement _element, int x, int y) {
		System.err.println("\nAdd DiagramSelection(DiagramControl)");
		System.err.println("\t" + _element);
		_element.setLocation(x,y);
		_maxElementId++;
		_element.setId(_maxElementId);
		_diagram.addDiagramElement(_element);
		_elements.add(_element); // add it to the list of elements
		return(_element);
	}
	
	//Testing!!!
	public void removeDiagramElement(DiagramElement d) {
		System.err.println(d);
		d.remove();
		System.err.println("passed");
		Enumeration en = d.getAssociations().getAssociations(d);
		
		Association a;
		Association a2;
		
		for (; en.hasMoreElements();) {
			a = (Association) en.nextElement();
			
//			_associations.remove(a);
			
			if (a.isGeneration() && d == a.getParent()) {
				removeDiagramElement(a.getChild());
			}
			else if ((a.isConnection()) && (d != a.getConnector())) {
				removeDiagramElement(a.getConnector());
			}
			
			_associations.remove(a);
		}
		
		_diagram.removeDiagramElement(d);
		_elements.remove(d);
	}

	public Vector removeDiagramElements(Vector deVector) {
		Vector as=new Vector();
		boolean flag;
		for(int i=0;i<deVector.size();i++) {
			Enumeration en = ((DiagramElement)deVector.elementAt(i)).getAssociations().getAssociations(((DiagramElement)deVector.elementAt(i)));
			flag = false;
			for (; en.hasMoreElements();) {
				Association a = (Association) en.nextElement();
				for (int k=0; k<as.size();k++) {
					Association a2 = (Association) as.elementAt(k);
					if(a2==a) {
						flag = true;
					}
				}
				if(flag==false)
					as.add(a);
			}
		}
		for(int i=0;i<_selectedElements.size();i++) {
			if(((DiagramElement)deVector.elementAt(i)).getType().isShape())
				removeDiagramElement(((DiagramElement)deVector.elementAt(i)));
		}

		return as;
	}
	
	public Vector getElementAssociations(Vector deVector) {
		Vector as=new Vector();
		boolean flag;
		for(int i=0;i<deVector.size();i++) {
			Enumeration en = ((DiagramElement)deVector.elementAt(i)).getAssociations().getAssociations(((DiagramElement)deVector.elementAt(i)));
			flag = false;
			for (; en.hasMoreElements();) {
				Association a = (Association) en.nextElement();
				for (int k=0; k<as.size();k++) {
					Association a2 = (Association) as.elementAt(k);
					if(a2==a) {
						flag = true;
					}
				}
				if(flag==false)
					as.add(a);
			}
		}
		
		return as;		
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
		else {
			System.out.println("no things clicked");
			getDiagram().getJTable().setModel(new TableModelAdapter(_properties, getDiagram().getJTable()));
			getDiagram().getJTable().setProperties(_properties);
			hideDiagramElementsResizePoints();
			restoreSelectedElementsControl();
//			_selectedElement=null;
//			_selectedElements=null;
			this.setShift(0);
		}
			
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

	public DiagramElement getDiagramElement(int id) {

		DiagramElement e = null;
		boolean found = false;
		for (int k=0; k<_elements.size(); k++)
		{
			e=(DiagramElement)(_elements.elementAt(k));
			if(e.getId() == id) {
				k = _elements.size();
				found = true;
			}	
		}
		if(found == false){
			//System.out.println("No such diagramElement exists");
			return null;
	}
	else {
		//System.out.println("diagramElement exists");
		return e;
	}
}
	
  public void setMaxElementId(int id) {
    _maxElementId = id;
  }

  public int getMaxElementId() {
    return _maxElementId;
  }

public Associations getAssociations() {
	return _associations;
}

  public void clearDiagram() {
	  //clear the _contentPane of diagram;
	  _diagram.getContentPane().removeAll();
	  _diagram.getContentPane().repaint();
	  //clear the vector of _elements and _associations
	  this._elements = new Vector();
	  this._associations = new Associations();
  }
  
  public void exportDiagram() {
	
	/*for(int i=0;i<_elements.size();i++)
		System.out.println("********************"+_elements.elementAt(i));

	for(int i=0;i<_associations.getAssociations().size();i++)
		System.out.println("********************"+_associations.getAssociations().elementAt(i));
	System.out.println("Export diagram");*/

	//Create a file chooser
 	final JFileChooser fc = new JFileChooser();	
	fc.addChoosableFileFilter(new XFileFilter());
	fc.showSaveDialog(_diagram);
	File file = fc.getSelectedFile();
	String path;

	if(file.isFile())
		path = file.getAbsolutePath();
	else
		path = file.getAbsolutePath()+".xml";
	
	
	CreateDocumentControl _createDocumentControl = new CreateDocumentControl();
	Vector _associationss = _associations.getAssociations();
	_createDocumentControl.export(this, _elements, _associationss,  path);
  }


  public void importDiagram() {
	System.out.println("Import diagram");
	//Create a file chooser
 	final JFileChooser fc = new JFileChooser();	
	fc.addChoosableFileFilter(new XFileFilter());
	fc.showOpenDialog(_diagram);
	File file = fc.getSelectedFile();
	String path = null;
	if(file.isFile())
		path = file.getAbsolutePath();
	
	clearDiagram();
	//Document _document=null;
	ImportDocumentControl _importDocumentControl = new ImportDocumentControl();
	_importDocumentControl.inport(_diagram, path);
		
  } 

  public void generateCode() {
  	  designer.DesignerMain designerMain = new designer.DesignerMain();
      designerMain.setDiagramSource(this, _elements, _associations);
      designerMain.showDesigner();
//original code  	
//	  CodeGeneratorControl _codeGeneratorControl = new CodeGeneratorControl(this, _elements, _associations);
//		  _codeGeneratorControl.generateCode();
//		  _codeGeneratorControl.printAll();
  }
//modified by matthew
//to create a internal code to make the dynamic run model
  public void generateInternalCode() {
	  InternalCodeGeneratorControl _codeGeneratorControl = new InternalCodeGeneratorControl(this, _elements, _associations);
		  _codeGeneratorControl.generateCode();
		  _codeGeneratorControl.printAll();
  }

  public Vector getProperties() {
	  return _properties;
  }

  public void setProperties(Vector p) {
	  _properties = p;
  }
  
	public DiagramElement getSelectedElement(){
		return (DiagramElement)_selectedElements.elementAt(0);
	}
//added by matthew	
	public Vector getSelectedElements(){
		return _selectedElements;
	}
	
	public int getShift() {
		return _shift;
	}
	
	public void setShift(int i) {
		_shift = i;
	}
	
  	public void cloneElement(DiagramElement de, boolean isCut){
		DiagramElementType type = de.getType();
		Point pt = de.getLocation();
		DiagramElement _de=null;
		if (!isCut){
	 		if(de.getType().isShape()) {
				_de = addDiagramElement(de.getType(), pt.x+_shift, pt.y+_shift);
				_de.setPane((JLayeredPane)this.getDiagram().getContentPane());
				_de.setOpaque(true);
				((DiagramShape)_de).setPort();
				_de.requestFocus();
			}
		}else {
			if(de.getType().isShape()) {
				_de = addDiagramSelection(de, pt.x+_shift, pt.y+_shift);
				_de.setPane((JLayeredPane)this.getDiagram().getContentPane());
				_de.setOpaque(true);
				_de.requestFocus();
			}
			else {
				_de = addDiagramSelection(de, pt.x+_shift, pt.y+_shift);
				_de.setOpaque(false);
				_de.setPane((JLayeredPane)this.getDiagram().getContentPane());
			}
		}
		restoreControl();
	}
//added by matthew	
	public void cloneElements(boolean isCut, Vector as){
		
		if(!isCut) {
			restoreSelectedElementsControl();
			_shift+=10;
		}
		if(isCut) {	
			for(int i=0;i<_selectedElements.size();i++) {
	//			if(((DiagramElement)deVector.elementAt(i)).getType().getCategory()==DiagramElementType.SHAPE)
					cloneElement(((DiagramElement)_selectedElements.elementAt(i)),isCut);
			}
			restoreSelectedElementsControl();
		}
		else{
			Vector copiedElements = new Vector();
			Vector referenceElements = new Vector();
			for(int i=0;i<_selectedElements.size();i++) {
				DiagramElement de=(DiagramElement)_selectedElements.elementAt(i);
				if(de.getType().isShape()) {
					Point pt = de.getLocation();
					referenceElements.add(de);
					de = addDiagramElement(de.getType(), pt.x+_shift, pt.y+_shift);
					de.setPane((JLayeredPane)this.getDiagram().getContentPane());
					de.setOpaque(true);
					((DiagramShape)de).setPort();
					de.requestFocus();
					copiedElements.add(de);
				}
			}
			System.err.println("as.size():"+as.size());
			for(int i=0;i<as.size();i++) {
				Association a = ((Association)as.elementAt(i));
				if(a.getConnector()!=null) {
					DiagramElement parentDe=null;
					DiagramElement childDe=null;
					DiagramElement connectorDe=null;
					parentDe = a.getParent();
					childDe = a.getChild();
					connectorDe = a.getConnector();
					connectorDe = addDiagramElement(connectorDe.getType(), _shift, _shift);
					connectorDe.setOpaque(false);
					connectorDe.setPane((JLayeredPane)this.getDiagram().getContentPane());					
				}
			}
//					for(int k=0;k<referenceElements.size();k++) {
//						Vector source = getAssociations().getAssociationsVector((DiagramElement)referenceElements.elementAt(k));
//						for(int l=0;l<source.size();l++) {
//							Association a2 = (Association)source.elementAt(l);
//							if(parentDe==a2.getChild()) {
//								//again!!
//								for(int j=0;j<referenceElements.size();j++) {
//									Vector target= getAssociations().getAssociationsVector((DiagramElement)referenceElements.elementAt(j));
//									for(int m=0;m<target.size();m++) {
//										Association a3 = (Association)target.elementAt(m);
//										if(childDe==a3.getChild()) {
//											this.getAssociations().add(
//												
//												(DiagramElement)copiedElements.elementAt(k).getChild(), a3.getChild(), (DiagramConnector)connectorDe, Association.ASSOCIATION, 0, 0);
//										}
//									}
//								}
//
//							}
//						}
//					}
//				}
//			}
		}
//		System.err.println("total number of diagram element: in cloneElements" +_selectedElements.size());
		for(int i=0;i<as.size();i++) {
			Association a = ((Association)as.elementAt(i));
			System.err.println("\n association:" + a);
			getAssociations().addAssociation(a);
		}
		for(int i=0;i<_selectedElements.size();i++) {
			if(((DiagramElement)_selectedElements.elementAt(i)).getType().getCategory()==DiagramElementType.SHAPE){
				((DiagramShape)_selectedElements.elementAt(i)).repaintAssociations();
			}
		}		
//		Vector enu = this.getAssociations().getAssociations();
//		for(int i=0;i<enu.size();i++) {
//			Association temp = (Association)enu.elementAt(i);
//			System.err.println("\nParent:" + temp.getParent());
//			System.err.println("\nChild:" + temp.getChild());
//			System.err.println("\nConnector:" + temp.getConnector());
//		}

	}
	
	public void hideDiagramElementsResizePoints() {
		for(int i=0;i<_elements.size();i++) {
			if(((DiagramElement)_elements.elementAt(i)).getType().isShape())
				((DiagramShape)_elements.elementAt(i)).hideResizePoints();
			else if(((DiagramElement)_elements.elementAt(i)).getType().isConnector())
				((DiagramConnector)_elements.elementAt(i)).setReshapePointsVisible(false);
			else {}
		}
	}
	
	public void restoreSelectedElementsControl() {
		if(_selectedElements!=null)
			for(int i=0;i<_selectedElements.size();i++) {
				if(((DiagramElement)_selectedElements.elementAt(i)).getType().isShape())
					((DiagramShape)_selectedElements.elementAt(i)).getShapeControl().restoreControl();
				else if(((DiagramElement)_selectedElements.elementAt(i)).getType().isConnector())
					((DiagramConnector)_selectedElements.elementAt(i)).getController().restoreControl();
				else{}
			}
	}
//added by matthew	
	public Vector getSelectedElementsAssociations() {
		return getElementAssociations(_selectedElements);
	}
}