package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class Association {
    private String _type;
    private DiagramElement _parent, _child;
    private DiagramConnector _connector;
    private int _x, _y;
    static public final String ASSOCIATION = "association";
    static public final String AGGREGATION = "aggregation";
    static public final String GENERATION = "genration";

    public Association(DiagramElement e1, DiagramElement e2, DiagramConnector e3, String type, int x, int y) {
        _type = type;
        _parent = e1;
        _child = e2;
        _connector = e3;
        _x = x;
        _y = y;
    }

    public String getType() {
        return _type;
    }

    public DiagramElement getParent() {
        return _parent;
    }

    public DiagramElement getChild() {
        return _child;
    }

    public DiagramConnector getConnector() {
        return _connector;
    }

    public boolean isConnection() {
        return (_connector != null);
    }

    public boolean isGeneration() {
        return (_type.equals(Association.GENERATION));
    }

    public boolean isAssociation() {
        return (_type.equals(Association.ASSOCIATION));
    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

}
