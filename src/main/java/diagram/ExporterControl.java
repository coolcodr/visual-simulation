package diagram;

import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

abstract public class ExporterControl {
	
	protected Document _document;
	
	
	public void setDocument(Document _document) {
		this._document = _document;
	}
	

		
	abstract public Element exportElement(DiagramElement _diagramElement);
	
	abstract public Element exportAssociation(Association _association);
	
}