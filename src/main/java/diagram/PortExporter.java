package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;

public class PortExporter extends Exporter {

    public PortExporter(DiagramElement _diagramElement, Document _document) {
        super(_diagramElement, _document);
    }

    public Attr createParentXAttr() {
        Attr _parentXAttr = _document.createAttribute("parentX");
        _parentXAttr.setValue(Integer.toString(((DiagramPort) _diagramElement).getParentPointX()));
        return _parentXAttr;
    }

    public Attr createParentYAttr() {
        Attr _parentYAttr = _document.createAttribute("parentY");
        _parentYAttr.setValue(Integer.toString(((DiagramPort) _diagramElement).getParentPointY()));
        return _parentYAttr;
    }

    public Attr createPortIndexAttr() {
        Attr _portIndexAttr = _document.createAttribute("portIndex");
        _portIndexAttr.setValue(Integer.toString(((DiagramPort) _diagramElement).getPortIndex()));
        return _portIndexAttr;
    }

}
