package diagram;

import java.util.*;
/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Associations {
	
	Vector _associations;
	
	public Associations() {
		_associations = new Vector();
	}
	
	public void add(DiagramElement parent, DiagramElement child, DiagramConnector dc, String type, int x, int y) {
		_associations.add(new Association(parent, child, dc, type, x, y));
	}
	
	public Enumeration getAssociations(DiagramElement d) {
		Vector result = new Vector();
		
		for (int i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if (d == a.getParent() || d == a.getChild() || d == a.getConnector())
				result.add(a);
		}
		return(result.elements());
	}

	public Vector getAssociationsVector(DiagramElement d) {
		Vector result = new Vector();
		
		for (int i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if (d == a.getParent() || d == a.getChild() || d == a.getConnector())
				result.add(a);
		}
		return result;
	}
	
	public Enumeration getConnections(DiagramElement d) {
		int i;
		Vector result = new Vector();
		
		for (i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if ((d == a.getParent() || d == a.getChild()) && a.isConnection())
				result.add(a);
		}
		return(result.elements());
	}
	
	public Enumeration getGeneration(DiagramElement d) {
		int i;
		Vector result = new Vector();
		
		for (i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if ((d == a.getParent() || d == a.getChild()) && a.isGeneration())
				result.add(a);
		}
		
		return(result.elements());
	}
	
	public DiagramElement getParent(DiagramElement d) {  // for the port find out the parent
		DiagramElement result = null;
		for (int i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if (a.isGeneration() && (a.getChild() == d)) {
				result = a.getParent();
				i = _associations.size();
			}
		}
		
		return(result);
	}
	
	public DiagramElement getConnectorSource(DiagramElement d) {  // for the connector find out the source
		DiagramElement result = null;
		for (int i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if ((a.getConnector() == d)) {
				result = a.getParent();
				i = _associations.size();
			}
		}
		
		return(result);
	}
	
	public DiagramElement getConnectorTarget(DiagramElement d) {  // for the connector find out the target
		DiagramElement result = null;
		for (int i = 0; i < _associations.size(); i++) {
			Association a = (Association) _associations.elementAt(i);
			if ((a.getConnector() == d)) {
				result = a.getChild();
				i = _associations.size();
			}
		}
		
		return(result);
	}
	
	public void remove(Object object) {
		_associations.remove(object);
	}

 public Vector getAssociations() {
	  return _associations;
  }
//added by matthew
	public void addAssociation(Association as) {
		_associations.add(as);
	}
}