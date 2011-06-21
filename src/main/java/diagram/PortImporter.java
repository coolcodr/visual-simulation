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

public class PortImporter extends Importer
{
	
	public int getParentX(Node _dNode) {
		NamedNodeMap _attributes = ((Element)_dNode).getAttributes();
		Attr _parentxAttr = (Attr)_attributes.item(6);
		return Integer.parseInt(_parentxAttr.getValue());
	}
	
	public int getParentY(Node _dNode) {
		NamedNodeMap _attributes = ((Element)_dNode).getAttributes();		
		Attr _parentyAttr = (Attr)_attributes.item(7);
		return Integer.parseInt(_parentyAttr.getValue());
	}

	public int getPortIndex(Node _dNode) {
		NamedNodeMap _attributes = ((Element)_dNode).getAttributes();		
		Attr _portIndexAttr = (Attr)_attributes.item(8);
		return 	Integer.parseInt(_portIndexAttr.getValue());
	}


}