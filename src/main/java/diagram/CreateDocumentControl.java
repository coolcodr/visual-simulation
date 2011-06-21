package diagram;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateDocumentControl {

    private Document _document;

    public Document createDocumentNode() {

        // Document _document=null;
        DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder _builder = _factory.newDocumentBuilder();
            _document = _builder.newDocument();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

        return _document;
    }

    public Element createProjectTag() {
        return _document.createElement("Project");
    }

    public Element createDiagramTag() {
        return _document.createElement("Diagram");
    }

    /*
     * public Element createDiagramElementTag() { return
     * _document.createElement("DiagramElement"); }
     */

    // create the document with the path provided
    public void createDocument(String path) {
        try {
            ((XmlDocument) _document).write(new FileOutputStream(path));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void export(DiagramControl dc, Vector _elements, Vector _associations, String path) {

        _document = createDocumentNode();
        Element _projectTag = createProjectTag();
        _document.appendChild(_projectTag);
        Element _diagramTag = createDiagramTag();
        _projectTag.appendChild(_diagramTag);
        Attr _elementNumAttr = _document.createAttribute("maxElementId");
        Attr _simulationTimeAttr = _document.createAttribute("simulationTime");

        _diagramTag.setAttributeNode(_elementNumAttr);
        _diagramTag.setAttributeNode(_simulationTimeAttr);

        _simulationTimeAttr.setValue(String.valueOf(((Property) dc.getProperties().elementAt(0)).getValue()));
        int maxElementId = 0;
        for (int _i = 0; _i < _elements.size(); _i++) {
            DiagramElement e = (DiagramElement) _elements.elementAt(_i);
            if (maxElementId < e.getId()) {
                maxElementId = e.getId();
            }
            e.getType().getExporterControl().setDocument(_document);
            Element _diagramElementTag = e.getType().getExporterControl().exportElement(e);
            _diagramTag.appendChild(_diagramElementTag);
        }
        _elementNumAttr.setValue(Integer.toString(maxElementId));

        for (int _j = 0; _j < _associations.size(); _j++) {
            // Association a = (Association)_associations.elementAt(_j);
            AssociationExporterControl aec = new AssociationExporterControl();
            aec.setDocument(_document);
            Element _associationTag = aec.exportAssociation(((Association) _associations.elementAt(_j)));
            _diagramTag.appendChild(_associationTag);
        }
        createDocument(path);
    }

    // abstract public Document exportElement(DiagramElement _diagramElement);

}
