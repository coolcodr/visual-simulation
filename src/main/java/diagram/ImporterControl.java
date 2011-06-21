package diagram;

import java.awt.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

abstract public class ImporterControl {
	
	protected Diagram _diagram;
	
	public void setDiagram(Diagram _diagram) {
		this._diagram = _diagram;
	}

abstract public void importElement(Node _dNode, DiagramElementType _dType);

abstract public void importAssociation(Node _aNode);
	

}
