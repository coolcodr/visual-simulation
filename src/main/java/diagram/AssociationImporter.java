package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class AssociationImporter extends Importer {

    private NamedNodeMap _associationAttributes;

    public AssociationImporter(Node _aNode) {
        _associationAttributes = ((Element) _aNode).getAttributes();
    }

    public String getType() {

        Attr _typeAttr = (Attr) _associationAttributes.item(0);
        return _typeAttr.getValue();
    }

    public int getParentId() {

        Attr _parentIdAttr = (Attr) _associationAttributes.item(1);
        return Integer.parseInt(_parentIdAttr.getValue());
    }

    public int getChildId() {

        Attr _childIdAttr = (Attr) _associationAttributes.item(2);
        return Integer.parseInt(_childIdAttr.getValue());
    }

    public int getConnectorId() {

        Attr _connectorIdAttr = (Attr) _associationAttributes.item(3);

        if (_connectorIdAttr.getValue().equals("")) {
            return -1;
        } else {
            return Integer.parseInt(_connectorIdAttr.getValue());
        }
    }

    public int getX() {

        Attr _xAttr = (Attr) _associationAttributes.item(4);
        return Integer.parseInt(_xAttr.getValue());
    }

    public int getY() {

        Attr _yAttr = (Attr) _associationAttributes.item(5);
        return Integer.parseInt(_yAttr.getValue());
    }

}
