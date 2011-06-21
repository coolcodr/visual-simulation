package diagram;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

public class PortImporterControl extends ImporterControl {


	public void importElement(Node _dNode, DiagramElementType _dType) {
		PortImporter _portImporter = new PortImporter();
		int _id = _portImporter.getId(_dNode);
		int _width = _portImporter.getWidth(_dNode);
		int _height = _portImporter.getHeight(_dNode);
		int _x = _portImporter.getX(_dNode);
		int _y = _portImporter.getY(_dNode);
		int _parentx = _portImporter.getParentX(_dNode);
		int _parenty = _portImporter.getParentY(_dNode);
		int _portIndex = _portImporter.getPortIndex(_dNode);
		
		DiagramPort _p = (DiagramPort)_diagram.getDiagramControl().addDiagramElement(_dType, _x, _y);
		_p.setId(_id);
		_p.setSize(_width, _height);
		_p.setOpaque(false);
		_p.setPane((JLayeredPane) _diagram.getContentPane());
		_p.setParentPointX(_parentx);
		_p.setParentPointY(_parenty);
		_p.setPortIndex(_portIndex);
		_p.repaint();
	}

	public void importAssociation(Node _aNode){}

}