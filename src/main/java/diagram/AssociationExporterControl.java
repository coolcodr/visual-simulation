package diagram;

import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

public class AssociationExporterControl extends ExporterControl {


	public Element exportAssociation(Association _association)
	{
		AssociationExporter _associationExporter = new AssociationExporter(_association, _document);
		Element _associationTag = _associationExporter.createAssociationTag();
		return _associationTag;
	}

	public Element exportElement(DiagramElement _diagramElement){return null;}
}


