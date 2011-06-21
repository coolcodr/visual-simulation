package designer;

import java.util.Vector;

import javax.swing.JComponent;

public class ElementProperties {
    private Vector properties = new Vector();
    private JComponent jComponent;

    public ElementProperties(JComponent jComponent) {
        this.jComponent = jComponent;
    }

    public void addProperty(PropertyCommand command) {
        properties.add(command);
    }

    public Vector getProperties() {
        return properties;
    }
}
