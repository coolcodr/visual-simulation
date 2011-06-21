package diagram;

import org.w3c.dom.Element;

public class PortExporterControl extends ExporterControl {

    public Element exportElement(DiagramElement _diagramElement) {
        PortExporter _portExporter = new PortExporter(_diagramElement, _document);
        Element _diagramElementTag = _portExporter.createDiagramElementTag();

        _diagramElementTag.setAttributeNode(_portExporter.createParentXAttr());
        _diagramElementTag.setAttributeNode(_portExporter.createParentYAttr());
        _diagramElementTag.setAttributeNode(_portExporter.createPortIndexAttr());

        return _diagramElementTag;
    }

    public Element exportAssociation(Association _association) {
        return null;
    }
}
