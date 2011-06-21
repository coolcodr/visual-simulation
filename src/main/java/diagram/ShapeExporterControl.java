package diagram;

import java.util.Vector;

import org.w3c.dom.Element;

public class ShapeExporterControl extends ExporterControl {

    public Element exportElement(DiagramElement _diagramElement) {
        ShapeExporter _shapeExporter = new ShapeExporter(_diagramElement, _document);
        Element _diagramElementTag = _shapeExporter.createDiagramElementTag();
        Element _propertiesTag = _shapeExporter.createPropertiesTag();
        // create Property Tags
        Vector _pTags = _shapeExporter.createPropertyTags();
        for (int i = 0; i < _pTags.size(); i++) {
            _propertiesTag.appendChild((Element) _pTags.elementAt(i));
        }

        _diagramElementTag.appendChild(_propertiesTag);
        return _diagramElementTag;
    }

    public Element exportAssociation(Association _association) {
        return null;
    }
    /*
     * 
     * public void exportDetail(Element _diagramElementTag, DiagramElement e) {
     * exportDiagramElementInfo(_diagramElementTag, e); }
     */
}
