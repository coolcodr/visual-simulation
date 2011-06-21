package diagram;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PortImporter extends Importer {

    public int getParentX(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _parentxAttr = (Attr) _attributes.item(6);
        return Integer.parseInt(_parentxAttr.getValue());
    }

    public int getParentY(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _parentyAttr = (Attr) _attributes.item(7);
        return Integer.parseInt(_parentyAttr.getValue());
    }

    public int getPortIndex(Node _dNode) {
        NamedNodeMap _attributes = ((Element) _dNode).getAttributes();
        Attr _portIndexAttr = (Attr) _attributes.item(8);
        return Integer.parseInt(_portIndexAttr.getValue());
    }

}
