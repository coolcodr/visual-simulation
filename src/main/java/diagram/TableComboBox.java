package diagram;

import javax.swing.JComboBox;

public class TableComboBox extends JComboBox implements PropertyEditorComponent {

    /**
     * 
     */
    private static final long serialVersionUID = -8703194738899849098L;
    private Object _object;

    public TableComboBox(Object[] items) {
        super(items);
    }

    public void setObject(Object object) {
        _object = object;
    }

    public Object getObject() {
        return _object;
    }
}
