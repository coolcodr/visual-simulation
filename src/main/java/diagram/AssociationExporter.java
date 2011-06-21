package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AssociationExporter extends Exporter {

    Association _association;
    Document _document;

    public AssociationExporter(Association _association, Document _document) {
        this._association = _association;
        this._document = _document;
    }

    public Element createAssociationTag() {
        Element _associationTag = _document.createElement("Association");

        Attr _typeAttr = _document.createAttribute("Type");
        Attr _parentIdAttr = _document.createAttribute("ParentID");
        Attr _childIdAttr = _document.createAttribute("ChildID");
        Attr _connectorIdAttr = _document.createAttribute("ConnectorID");
        Attr _xAttr = _document.createAttribute("X");
        Attr _yAttr = _document.createAttribute("Y");

        _typeAttr.setValue(_association.getType());
        _parentIdAttr.setValue(Integer.toString(_association.getParent().getId()));
        _childIdAttr.setValue(Integer.toString(_association.getChild().getId()));

        if (!_association.isGeneration()) {
            _connectorIdAttr.setValue(Integer.toString(_association.getConnector().getId()));
        }
        _xAttr.setValue(Integer.toString(_association.getX()));
        _yAttr.setValue(Integer.toString(_association.getY()));

        _associationTag.setAttributeNode(_typeAttr);
        _associationTag.setAttributeNode(_parentIdAttr);
        _associationTag.setAttributeNode(_childIdAttr);
        _associationTag.setAttributeNode(_connectorIdAttr);
        _associationTag.setAttributeNode(_xAttr);
        _associationTag.setAttributeNode(_yAttr);
        return _associationTag;
    }
}
