package diagram;

import java.awt.Point;
import java.util.Vector;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConnectorExporter extends Exporter {

    Vector _points;
    Vector _reshapePoints;

    public ConnectorExporter(DiagramElement _diagramElement, Document _document) {
        super(_diagramElement, _document);
        _points = ((DiagramConnector) _diagramElement).getPoints();
        _reshapePoints = ((DiagramConnector) _diagramElement).getReshapePoints();
    }

    public Vector createPointElementTags() {

        Vector _pointElementTags = new Vector();

        for (int i = 0; i < _points.size(); i++) {
            Element _pointElementTag = _document.createElement("Point");

            Attr _xAttr = _document.createAttribute("X");
            Attr _yAttr = _document.createAttribute("Y");

            _xAttr.setValue(Double.toString(((Point) _points.elementAt(i)).getX()));
            _yAttr.setValue(Double.toString(((Point) _points.elementAt(i)).getY()));

            _pointElementTag.setAttributeNode(_xAttr);
            _pointElementTag.setAttributeNode(_yAttr);

            _pointElementTags.add(_pointElementTag);
            System.out.println("Size()           " + _pointElementTags.size());
        }

        return _pointElementTags;
    }

    public Vector createReshapePointElementTags() {

        Vector _reshapePointElementTags = new Vector();

        for (int j = 0; j < _reshapePoints.size(); j++) {
            Element _reshapePointElementTag = _document.createElement("ReshapePoint");

            Attr _rxAttr = _document.createAttribute("X");
            Attr _ryAttr = _document.createAttribute("Y");

            _rxAttr.setValue(Double.toString(((ReshapePoint) _reshapePoints.elementAt(j)).getX()));
            _ryAttr.setValue(Double.toString(((ReshapePoint) _reshapePoints.elementAt(j)).getY()));

            _reshapePointElementTag.setAttributeNode(_rxAttr);
            _reshapePointElementTag.setAttributeNode(_ryAttr);

            _reshapePointElementTags.add(_reshapePointElementTag);
        }

        return _reshapePointElementTags;
    }

}
