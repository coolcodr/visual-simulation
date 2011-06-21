package diagram;

import java.util.Vector;

import javax.swing.JTable;

public class PropertyTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 2690382655476612941L;
    private Vector _properties;

    public PropertyTable() {

    }

    public void setProperties(Vector ps) {
        _properties = ps;
    }

    public Vector getProperties() {
        return _properties;
    }
}
