package diagram;

import java.util.Vector;

import javax.swing.JLayeredPane;

import org.w3c.dom.Node;

public class ShapeImporterControl extends ImporterControl {

    public void importElement(Node _dNode, DiagramElementType _dType) {
        ShapeImporter _shapeImporter = new ShapeImporter();
        int _id = _shapeImporter.getId(_dNode);
        int _width = _shapeImporter.getWidth(_dNode);
        int _height = _shapeImporter.getHeight(_dNode);
        int _x = _shapeImporter.getX(_dNode);
        int _y = _shapeImporter.getY(_dNode);
        // import the properties
        Vector _properties = _shapeImporter.createProperties(_dNode);

        DiagramElement _d = _diagram.getDiagramControl().addDiagramElement(_dType, _x, _y);
        _d.setId(_id);
        _d.setSize(_width, _height);
        _d.setOpaque(true);
        _d.setPane((JLayeredPane) _diagram.getContentPane());
//		DiagramShape.setResizePointAdded(false);
        _d.repaint();
        ((DiagramShape) _d).setProperties(_properties);
    }

    public void importAssociation(Node _aNode) {
    }

}
