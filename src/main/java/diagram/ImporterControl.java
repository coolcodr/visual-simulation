package diagram;

import org.w3c.dom.Node;

abstract public class ImporterControl {

    protected Diagram _diagram;

    public void setDiagram(Diagram _diagram) {
        this._diagram = _diagram;
    }

    abstract public void importElement(Node _dNode, DiagramElementType _dType);

    abstract public void importAssociation(Node _aNode);

}
