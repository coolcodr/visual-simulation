package diagram;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

public class AssociationImporter extends Importer {

	private NamedNodeMap _associationAttributes;

	public AssociationImporter(Node _aNode) {
		_associationAttributes = ((Element)_aNode).getAttributes();
	}


	public String getType() {

		Attr _typeAttr = (Attr)_associationAttributes.item(0);
		return _typeAttr.getValue();
	}

	public int getParentId() {

		Attr _parentIdAttr = (Attr)_associationAttributes.item(1);
		return Integer.parseInt(_parentIdAttr.getValue());
	}

	public int getChildId() {


		Attr _childIdAttr = (Attr)_associationAttributes.item(2);
		return Integer.parseInt(_childIdAttr.getValue());
	}

	public int getConnectorId() {


		Attr _connectorIdAttr = (Attr)_associationAttributes.item(3);
		
		if(_connectorIdAttr.getValue().equals(""))
			return -1;
		else
			return Integer.parseInt(_connectorIdAttr.getValue());
	}

	public int getX() {

		Attr _xAttr = (Attr)_associationAttributes.item(4);
	    return Integer.parseInt(_xAttr.getValue());
	}
	
	
	public int getY() {

		Attr _yAttr = (Attr)_associationAttributes.item(5);
		return Integer.parseInt(_yAttr.getValue());
	}

}