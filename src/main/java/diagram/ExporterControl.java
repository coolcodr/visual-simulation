package diagram;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

abstract public class ExporterControl {

    protected Document _document;

    public void setDocument(Document _document) {
        this._document = _document;
    }

    abstract public Element exportElement(DiagramElement _diagramElement);

    abstract public Element exportAssociation(Association _association);

}
