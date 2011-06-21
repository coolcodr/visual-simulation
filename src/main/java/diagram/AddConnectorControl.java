package diagram;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class AddConnectorControl extends Control {

    protected DiagramConnector _dc;
    protected int _sx, _sy, _tx, _ty;
    DiagramElement _source, _target;
    static public UndoCommand undoCommand;// new undoredo[
    static private LinkedList undoList;

    public AddConnectorControl() {
        super();
    }

    static public void setLinkedList(LinkedList vSimUndoList) {
        undoList = vSimUndoList;
    }// new undoredo

    public void mousePressed(MouseEvent e) {

        Component component = SwingUtilities.getDeepestComponentAt(_diagram.getContentPane(), e.getX(), e.getY());

        _dc = (DiagramConnector) _diagramControl.addDiagramElement(_type, e.getX(), e.getY());

        _dc.setOpaque(false);
        _dc.setPane((JLayeredPane) e.getSource());

        ConnectorRules rules = _dc.getRules();

        try {
            if (rules.matchOne((DiagramElement) component)) {

                _source = (DiagramElement) component;
                // add a connector

                _sx = e.getX();
                _sy = e.getY();

                _dc.addReshapePoint(0, 0, 0);
                _dc.addReshapePoint(0, 0, -1);

                _dc.getReshapePoint(0).setVisible(false);
                _dc.getReshapePoint(-1).setVisible(false);

                _dc.movePoint(_sx, _sy, 0);
                _dc.movePoint(_sx, _sy, -1);
                undoCommand = new UndoCommand(_dc);// new undoredo
                undoList.add(undoCommand);// new

            } else { // terminate this use case
                System.out.println("end use case");
                _diagram.setGlassPaneVisible(false);
                _diagramControl.restoreControl();

                _diagram.getContentPane().remove(_dc);
            }
        } catch (Exception ex) {
            _source = null;

            System.out.println("end use case");
            _diagram.setGlassPaneVisible(false);
            _diagramControl.restoreControl();

            _diagram.getContentPane().remove(_dc);
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (_source != null) {
            int x, y, x1, x2, y1, y2;

            x = e.getX();
            y = e.getY();
            x1 = _source.getX();
            y1 = _source.getY();
            x2 = x1 + _source.getWidth() - 1;
            y2 = y1 + _source.getHeight() - 1;
            _tx = x;
            _ty = y;

            if (_source.contains(x - x1, y - y1)) {
                _dc.setSize(0, 0);
            } else {
                Point p = _dc.findPoint(_tx, _ty, _source);
                _sx = p.x;
                _sy = p.y;

                _dc.movePoint(_sx, _sy, 0);
                _dc.movePoint(_tx, _ty, -1);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

        _diagram.setGlassPaneVisible(false);

        // create the association between the connector and the diagram elements
        _tx = e.getX();
        _ty = e.getY();
        JLayeredPane lpane = (JLayeredPane) e.getSource();
        lpane.moveToBack(_dc);
        lpane.moveToBack(_dc.getReshapePoint(-1));
        Component component = SwingUtilities.getDeepestComponentAt(_diagram.getContentPane(), _tx, _ty);
        lpane.moveToFront(_dc);
        lpane.moveToFront(_dc.getReshapePoint(0));
        lpane.moveToFront(_dc.getReshapePoint(-1));

        ConnectorRules rules = _dc.getRules();

        try {
            if (rules.matchTwo((DiagramElement) component)) {

                _source.connected(true);

                _target = (DiagramElement) component;
                _dc.getAssociations().add(_source, _target, _dc, Association.ASSOCIATION, 0, 0);

                Point p = _dc.findPoint(_sx, _sy, _target);
                _tx = p.x;
                _ty = p.y;

                _dc.movePoint(_sx, _sy, 0);
                _dc.movePoint(_tx, _ty, -1);
            } else {
                // remove connector
                _diagram.getContentPane().remove(_dc);
                _diagram.getContentPane().remove(_dc.getReshapePoint(0));
                _diagram.getContentPane().remove(_dc.getReshapePoint(-1));
            }
        } catch (Exception ex) {
            System.out.println(e);
            _diagram.getContentPane().remove(_dc);
            _diagram.getContentPane().remove(_dc.getReshapePoint(0));
            _diagram.getContentPane().remove(_dc.getReshapePoint(-1));
        }

        _diagramControl.restoreControl();
    }
}
