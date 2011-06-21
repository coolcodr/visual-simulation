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

public class ConnectorImporter extends Importer
{
	public Vector getPoints(Node _dNode) {
		int _loc = 0;
		NodeList _pointTags = ((Element)_dNode).getElementsByTagName("Point"); 
		int _nodeLength = _pointTags.getLength(); 
		Vector _points = new Vector();
		Node _currentNode;
		if(_nodeLength > 0) {
			_currentNode = _pointTags.item(_loc);
			for(int i=0; i<_nodeLength; i++){
				int _x =(int)(Double.parseDouble(((Element)_currentNode).getAttributeNode("X").getValue()));
				int _y =(int)(Double.parseDouble(((Element)_currentNode).getAttributeNode("Y").getValue()));
				Point _point = new Point();
				_point.setLocation(_x, _y);
				_points.add(_point);
				_loc++;
				_currentNode = _pointTags.item(_loc);
			}
		}
		else
			System.out.println("no point exist");
		return _points;
	}

	public Vector getReshapePoints(Node _dNode) {

		int _loc = 0;
		NodeList _reshapePointTags = ((Element)_dNode).getElementsByTagName("ReshapePoint"); 
		int _nodeLength = _reshapePointTags.getLength(); 
		Vector _reshapePoints = new Vector();
		Node _currentNode;
		if(_nodeLength > 0) {
			_currentNode = _reshapePointTags.item(_loc);
			for(int i=0; i<_nodeLength; i++){
				int _x =(int)(Double.parseDouble(((Element)_currentNode).getAttributeNode("X").getValue()));
				int _y =(int)(Double.parseDouble(((Element)_currentNode).getAttributeNode("Y").getValue()));
				ReshapePoint _reshapePoint = new ReshapePoint();
				_reshapePoint.setLocation(_x, _y);
				_reshapePoints.add(_reshapePoint);
				_loc++;
				_currentNode = _reshapePointTags.item(_loc);
			}
		}
		else
			System.out.println("no reshapepoint exist");
		return _reshapePoints;
	}

}