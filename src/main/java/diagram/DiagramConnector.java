package diagram;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramConnector extends DiagramElement {

    /**
     * 
     */
    private static final long serialVersionUID = -1660105076570609605L;
    protected Vector _points;
    private Vector _reshapePoints = new Vector();

    private MoveConnectorControl _moveConnectorControl;

    private static Vector _resizePoints;
    protected ConnectorRules _rules;

    private boolean _pointCount = true;
    private int _focusedPoint;

    static {

        _resizePoints = new Vector();
    }

//added by matthew
    protected static Diagram _diagram = null;

    public static void setDiagram(Diagram diagram) {
        _diagram = diagram;
    }

    public static Diagram getDiagram() {
        return _diagram;
    }

    public DiagramConnector() {
        super();
        setSize(0, 0);
        _moveConnectorControl = new MoveConnectorControl();
        addMouseListener(_moveConnectorControl);
        addMouseMotionListener(_moveConnectorControl);
        // Testing!!!!
        addKeyListener(_moveConnectorControl);
        _points = new Vector();
        addPoint(0, 0, 0);
        addPoint(0, 0, 0);

        _rules = new ConnectorRules();
    }

    public DiagramConnector(DiagramElementType t) {
        super(t);
        setSize(0, 0);

        _points = new Vector();
        _points.add(new Point(0, 0));
        _points.add(new Point(0, 0));
        addMouseListener(_moveConnectorControl);
        addMouseMotionListener(_moveConnectorControl);

        _rules = new ConnectorRules();
    }

    public void paint(Graphics g) {
        _renderer.paint(this, g);
        paintChildren(g);
    }

    public int getSourceX() {
        Point p = (Point) _points.elementAt(0);
        return (p.x);
    }

    public int getSourceY() {
        Point p = (Point) _points.elementAt(0);
        return (p.y);
    }

    public int getTargetX() {
        Point p = (Point) _points.elementAt(_points.size() - 1);
        return (p.x);
    }

    public int getTargetY() {
        Point p = (Point) _points.elementAt(_points.size() - 1);
        return (p.y);
    }

    // Testing!!!!!
    void addPoint(int x, int y, int index) // absolute x, y
    {
        _points.add(index, new Point(x, y));
    }

    void setPoint(int sx, int sy, int index) {
        int x = getX();
        int y = getY();
        int x1, y1, x2, y2;
        Point p = new Point(0, 0);

        // update point i
        if (index == -1) { // last point
            p = (Point) _points.elementAt(_points.size() - 1);
            p.x = sx;
            p.y = sy;
        } else {
            p = (Point) _points.elementAt(index);
            p.x = sx;
            p.y = sy;
        }
        /*
         * if (index == -1) // last point { p = (Point)
         * _points.elementAt(_points.size()-1); p.x = sx - x; p.y = sy - y; }
         * else if (index == 0) { p = (Point) _points.elementAt(index); p.x = sx
         * - x; p.y = sy - y; } else { p = (Point) _points.elementAt(index); p.x
         * = sx; p.y = sy; }
         */

        // re-calculate location
        p = (Point) _points.elementAt(0);
        x1 = x2 = p.x;
        y1 = y2 = p.y;
        for (int i = 1; i < _points.size(); i++) {
            p = (Point) _points.elementAt(i);
            x1 = Math.min(p.x, x1);
            y1 = Math.min(p.y, y1);
            x2 = Math.max(p.x, x2);
            y2 = Math.max(p.y, y2);
        }

        this.setLocation(x1 - 10, y1 - 10);
        /*
         * int dx = x - x1; int dy = y - y1;
         * 
         * p = (Point) _points.elementAt(0); p.x = p.x + dx; p.y = p.y + dy;
         * 
         * p = (Point) _points.elementAt(_points.size()-1); p.x = p.x + dx; p.y
         * = p.y + dy;
         */

        // Testing!!!!

        // set the size
        this.setSize(Math.max(x2 - x1 + 1, 1), Math.max(y2 - y1 + 1, 1));

        // set the size + 20
        this.setSize((int) (this.getSize().getWidth() + 20), (int) (this.getSize().getHeight() + 20));
    }

    public void removePoint(int index) {
        if (index == -1) {
            index = _points.size() - 1;
        }

        _points.removeElementAt(index);
    }

    public Vector getPoints() {
        return _points;
    }

    public Point getPoint(int index) {
        if (index == -1) {
            index = _points.size() - 1;
        }

        return (Point) _points.elementAt(index);
    }

    public Point findPoint(int x, int y, DiagramElement d) {
        int x1, y1, x2, y2, rx, ry;

        x1 = d.getX();
        y1 = d.getY();
        x2 = x1 + d.getWidth() - 1;
        y2 = y1 + d.getHeight() - 1;

        if (x1 <= x && x <= x2) // vertical line joining d and the current point
        {
            rx = x;
            if (y < y1) {
                ry = y1;
            } else {
                ry = y2;
            }
        } else if (y1 <= y && y <= y2) // horizontal line joining d and the
                                       // current point
        {
            ry = y;
            if (x < x1) {
                rx = x1;
            } else {
                rx = x2;
            }
        } else {
            if (x < x1) {
                rx = x1;
            } else {
                rx = x2;
            }

            if (y < y1) {
                ry = y1;
            } else {
                ry = y2;
            }
        }

        return (new Point(rx, ry));
    }

    public void setFocusedPoint(int index) {
        _focusedPoint = index;
    }

    public int getFocusedPoint() {
        return _focusedPoint;
    }

    public int contain2(int x, int y) {
        DiagramConnector dc = this;

        double p1x = 0;
        double p1y = 0;
        double p2x = 0;
        double p2y = 0;
        double slope = 0;
        double eSlope1 = 0;
        double eSlope2 = 0;
        int result = -1;

        int i = 0;

        for (i = 0; i < dc._points.size() - 1; i++) {
            p1x = ((Point) dc._points.elementAt(i)).getX() - dc.getX();
            p1y = ((Point) dc._points.elementAt(i)).getY() - dc.getY();

            p2x = ((Point) dc._points.elementAt(i + 1)).getX() - dc.getX();
            p2y = ((Point) dc._points.elementAt(i + 1)).getY() - dc.getY();

            if ((x >= Math.min(p1x, p2x) - 5) && (x <= Math.max(p1x, p2x) + 5) && (y >= Math.min(p1y, p2y) - 5) && (y <= Math.max(p1y, p2y) + 5)) {
                slope = (p2y - p1y) / (p2x - p1x);

                if (p1x == p2x) { // for slope = infinity
                    if (Math.abs(x - p1x) < 3) {
                        result = i;
                    }
                } else if (p1y == p2y) { // for slope = 0
                    if (Math.abs(y - p1y) < 3) {
                        result = i;
                    }
                } else {
                    if ((slope >= -1) && (slope <= 1)) {
                        eSlope1 = (y - (p1y - 5)) / (x - p1x);
                        eSlope2 = (y - (p1y + 5)) / (x - p1x);

                        if ((Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope)) {
                            result = i;
                        }
                    } else {
                        eSlope1 = (y - p1y) / (x - (p1x - 5));
                        eSlope2 = (y - p1y) / (x - (p1x + 5));

                        if ((eSlope1 / eSlope2) > 0) {
                            if ((Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) >= slope)) {
                                result = i;
                            }
                        } else if (eSlope1 != eSlope2) {
                            if (slope > 0) {
                                if ((Math.min(eSlope1, eSlope2) <= slope) && (Math.max(eSlope1, eSlope2) <= slope)) {
                                    result = i;
                                }
                            } else {
                                if ((Math.min(eSlope1, eSlope2) >= slope) && (Math.max(eSlope1, eSlope2) >= slope)) {
                                    result = i;
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public void addReshapePoint(int x, int y, int index) { // absolute x, y
        ReshapePoint reshapePoint = new ReshapePoint();

        if (index == -1) {
            index = _points.size() - 1;
        }

        _reshapePoints.add(index, reshapePoint);
        getPane().add(reshapePoint, 0);
        reshapePoint.setDiagramElement(this);
        reshapePoint.setLocation(x - reshapePoint.getWidth() / 2, y - reshapePoint.getHeight() / 2);
    }

    public ReshapePoint getReshapePoint(int index) {
        if (index == -1) {
            index = _reshapePoints.size() - 1;
        }

        return (ReshapePoint) _reshapePoints.elementAt(index);
    }

    public void removeReshapePoint(int index) {
        if (index == -1) {
            index = _reshapePoints.size() - 1;
        }

        getPane().remove(getReshapePoint(index));
        _reshapePoints.removeElementAt(index);
    }

    public Vector getReshapePoints() {
        return _reshapePoints;
    }

    public void movePoint(int x, int y, int index) // absolute x, y
    {
        setPoint(x, y, index);

        if (index == -1) {
            index = _points.size() - 1;
        }

        ReshapePoint reshapePoint = getReshapePoint(index);
        reshapePoint.setLocation(x - reshapePoint.getWidth() / 2, y - reshapePoint.getHeight() / 2);
//		System.err.println("reshapePoint.x" + reshapePoint.getX() + "reshapePoint.y" + reshapePoint.getY());
    }

    public void setReshapePointsVisible(boolean valid) {
        for (int i = 0; i < _reshapePoints.size(); i++) {
            getReshapePoint(i).setVisible(valid);
        }
    }

    // Testing!!!!
    public void remove() {

        try { // 'try' is used to prevent the Source of Connector is Null
            getAssociations().getConnectorSource(this).connected(false);
        } catch (Exception e) {
            System.out.println(e);
        }

        setReshapePointsVisible(false);
        for (int i = 0; i < getReshapePoints().size(); i++) {
            DiagramShape.getDiagram().removeDiagramElement(getReshapePoint(i));
        }
    }

    public ConnectorRules getRules() {
        return _rules;
    }

    public void setPoints(Vector _points) {
        this._points = _points;
    }

    public void setReshapePoints(Vector _reshapePoints) {
        this._reshapePoints = _reshapePoints;
    }

    public MoveConnectorControl getController() {
        return _moveConnectorControl;
    }

}
