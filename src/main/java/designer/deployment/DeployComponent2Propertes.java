package designer.deployment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.swing.JComponent;

public class DeployComponent2Propertes implements Serializable {
    // private Class componentClass;

    /**
     * 
     */
    private static final long serialVersionUID = -1822196729063127008L;
    private Rectangle bounds;
    private String text;
    private boolean visible;
    private Color foreground;
    private Color background;
    private Font font;

    public DeployComponent2Propertes(JComponent jComponent) {
        initValue(jComponent);
    }

    public DeployComponent2Propertes(JComponent jComponent, String text) {
        initValue(jComponent);
        this.text = text;
    }

    private void initValue(JComponent jComponent) {
        Class componentClass = jComponent.getClass();
        try {
            Method getTextMethod = componentClass.getMethod("getText", null);
            text = (String) getTextMethod.invoke(jComponent, null);
        } catch (Exception e) {
            System.out.println(e);
        }

        bounds = jComponent.getBounds();
        visible = true;// jComponent.isVisible();
        foreground = jComponent.getForeground();
        background = jComponent.getBackground();
        font = jComponent.getFont();

    }

    public void setPropertise(JComponent jComponent) {
        Class componentClass = jComponent.getClass();

        try {
            Class[] classArray = new Class[] { String.class };
            Method setTextMethod = componentClass.getMethod("setText", classArray);

            Object[] objectArray = new Object[] { text };
            setTextMethod.invoke(jComponent, objectArray);
        } catch (Exception e) {
            System.out.println(e);
        }
        jComponent.setOpaque(true);
        jComponent.setBounds(bounds);
        jComponent.setVisible(visible);
        jComponent.setForeground(foreground);
        jComponent.setBackground(background);
        jComponent.setFont(font);
    }

}
