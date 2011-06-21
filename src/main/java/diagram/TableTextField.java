package diagram;

import javax.swing.JTextField;

public class TableTextField extends JTextField implements PropertyEditorComponent {

    /**
     * 
     */
    private static final long serialVersionUID = -266226189794307868L;
    Object _object;

    public TableTextField() {
        super();
    }

    public void setObject(Object object) {
        _object = object;
    }

    public Object getObject() {
        return _object;
    }
}
