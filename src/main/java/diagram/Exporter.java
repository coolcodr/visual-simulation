package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Exporter {

    protected DiagramElement _diagramElement;
    protected Document _document;

    public Exporter() {
    }

    public Exporter(DiagramElement _diagramElement, Document _document) {
        this._diagramElement = _diagramElement;
        this._document = _document;
    }

    public Element createDiagramElementTag() {
        // Create DiagramElement element
        // Element _diagramElementTag = createDiagramElementTag();
        // parentNode.appendChild(_diagramElementTag); //append DiagramElement
        // element to document
        Element _diagramElementTag = _document.createElement("DiagramElement");
        Attr _idAttr = _document.createAttribute("id");
        Attr _typeAttr = _document.createAttribute("type");
        Attr _heightAttr = _document.createAttribute("height");
        Attr _widthAttr = _document.createAttribute("width");
        Attr _xAttr = _document.createAttribute("x");
        Attr _yAttr = _document.createAttribute("y");

        _idAttr.setValue(Integer.toString(_diagramElement.getId()));
        _typeAttr.setValue(_diagramElement.getType().getName());
        _heightAttr.setValue(Integer.toString(_diagramElement.getHeight()));
        _widthAttr.setValue(Integer.toString(_diagramElement.getWidth()));
        _xAttr.setValue(Integer.toString(_diagramElement.getX()));
        _yAttr.setValue(Integer.toString(_diagramElement.getY()));

        _diagramElementTag.setAttributeNode(_idAttr);
        _diagramElementTag.setAttributeNode(_typeAttr);
        _diagramElementTag.setAttributeNode(_heightAttr);
        _diagramElementTag.setAttributeNode(_widthAttr);
        _diagramElementTag.setAttributeNode(_xAttr);
        _diagramElementTag.setAttributeNode(_yAttr);

        // Create elementType element

        /*
         * Element _elementTypeTab = (Element)createDiagramElementType(e);
         * ((Element)_diagramElementTag).appendChild(_elementTypeTab); //Append
         * child elemntType to element
         */
        return _diagramElementTag;
    }

    // abstract public void exportDetail(Element _diagramElementTag,
    // DiagramElement e);

    // abstract public void exportElement(DiagramElement _diagramElement, Node
    // _parentNode);
    // abstract public Document exportAssociation(Association _association, Node
    // _parentNode);

}
