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

public class ConnectorImporterControl extends ImporterControl
{
	public void importElement(Node _dNode, DiagramElementType _dType) {
		ConnectorImporter _connectorImporter = new ConnectorImporter();

		int _id = _connectorImporter.getId(_dNode);
		int _width = _connectorImporter.getWidth(_dNode);
		int _height = _connectorImporter.getHeight(_dNode);
		int _x = _connectorImporter.getX(_dNode);
		int _y = _connectorImporter.getY(_dNode);
		Vector _points = _connectorImporter.getPoints(_dNode);
		Vector _reshapePoints = _connectorImporter.getReshapePoints(_dNode);
		
		DiagramConnector _dc = (DiagramConnector)_diagram.getDiagramControl().addDiagramElement(_dType, _x, _y);
		//_diagram.setGlassPaneVisible(false);
		_dc.setId(_id);
		_dc.setSize(_width, _height);
		_dc.setOpaque(false);
		_dc.setPane((JLayeredPane) _diagram.getContentPane());
		((JLayeredPane)_diagram.getContentPane()).moveToFront(_dc);
		_dc.setPoints(_points);
		_dc.setReshapePoints(_reshapePoints);
		for(int j=0; j<_dc.getReshapePoints().size();j++)
			_dc.getPane().add((ReshapePoint)_dc.getReshapePoints().elementAt(j), 0);
		_dc.repaint();
	
	}

	public void importAssociation(Node _aNode){}
}