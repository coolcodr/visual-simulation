package designer.deployment;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class InputComponentPropertise implements Serializable
{
    private Rectangle bounds;

    private String text;
    private Object[] objects;

    private boolean visible;
    private Color foreground;
    private Color background;
    private Font font;

    public InputComponentPropertise( JComponent jComponent )
    {
        this.bounds = jComponent.getBounds();
        this.visible = jComponent.getParent() != null ;
        this.foreground = jComponent.getForeground();
        this.background = jComponent.getBackground();
        this.font = jComponent.getFont();
    }
    public InputComponentPropertise( JComponent jComponent, String text )
    {
        this(jComponent);
        this.text = text;
    }
    public InputComponentPropertise( JComponent jComponent, Object[] objects )
    {
        this(jComponent);
        this.objects = objects;
    }
    public void setPropertise ( JComponent jComponent )
    {
        jComponent.setBounds(bounds);
        jComponent.setVisible(visible);
        jComponent.setForeground(foreground);
        jComponent.setBackground(background);
        jComponent.setFont(font);
    }
    public String getText ()
    {
        return text;
    }
    public Object[] getModel()
    {
        return objects;
    }
}

