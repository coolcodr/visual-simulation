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

public class AssociationImporterControl extends ImporterControl
{
	public void importAssociation(Node _aNode){
		AssociationImporter _associationImporter = new AssociationImporter(_aNode);
		String _type = _associationImporter.getType()	;
		int _parentId = _associationImporter.getParentId();
		int _childId = _associationImporter.getChildId();
		int _connectorId = _associationImporter.getConnectorId();
		System.out.println(_parentId);
		System.out.println(_childId);
		System.out.println(_connectorId);
		int _x = _associationImporter.getX();
		int _y = _associationImporter.getY();

		DiagramElement _parent = _diagram.getDiagramControl().getDiagramElement(_parentId);
		DiagramElement _child = _diagram.getDiagramControl().getDiagramElement(_childId);
				
		DiagramConnector _connector;
		if(_connectorId == -1)
			_connector = null;
		else
			_connector = (DiagramConnector)_diagram.getDiagramControl().getDiagramElement(_connectorId);

		_diagram.getDiagramControl().getAssociations().add(_parent, _child, _connector, _type, _x, _y);
	}

	public void importElement(Node _dNode, DiagramElementType _dType){}
}