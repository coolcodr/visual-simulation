package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Importer {
    protected Diagram _diagram;

    public void setDiagram(Diagram _diagram) {

        this._diagram = _diagram;
    }

    public int getId(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _idAttr = (Attr) _attributes.item(0);
        return Integer.parseInt(_idAttr.getValue());
    }

    public String getType(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _typeAttr = (Attr) _attributes.item(1);
        return _typeAttr.getValue();
    }

    public int getHeight(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _heightAttr = (Attr) _attributes.item(2);
        return Integer.parseInt(_heightAttr.getValue());
    }

    public int getWidth(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _widthAttr = (Attr) _attributes.item(3);
        return Integer.parseInt(_widthAttr.getValue());
    }

    public int getX(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _xAttr = (Attr) _attributes.item(4);
        return Integer.parseInt(_xAttr.getValue());
    }

    public int getY(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _yAttr = (Attr) _attributes.item(5);
        return Integer.parseInt(_yAttr.getValue());
    }
}
