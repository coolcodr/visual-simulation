package designer.deployment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.JComponent;

public class InputComponentPropertise implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1896670977872899686L;

    private Rectangle bounds;

    private String text;
    private Object[] objects;

    private boolean visible;
    private Color foreground;
    private Color background;
    private Font font;

    public InputComponentPropertise(JComponent jComponent) {
        bounds = jComponent.getBounds();
        visible = jComponent.getParent() != null;
        foreground = jComponent.getForeground();
        background = jComponent.getBackground();
        font = jComponent.getFont();
    }

    public InputComponentPropertise(JComponent jComponent, String text) {
        this(jComponent);
        this.text = text;
    }

    public InputComponentPropertise(JComponent jComponent, Object[] objects) {
        this(jComponent);
        this.objects = objects;
    }

    public void setPropertise(JComponent jComponent) {
        jComponent.setBounds(bounds);
        jComponent.setVisible(visible);
        jComponent.setForeground(foreground);
        jComponent.setBackground(background);
        jComponent.setFont(font);
    }

    public String getText() {
        return text;
    }

    public Object[] getModel() {
        return objects;
    }
}
