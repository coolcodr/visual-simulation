package designer;

import javax.swing.JToggleButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.*;
import java.lang.reflect.*;

public class DesignerComponentButton extends JToggleButton implements ActionListener
{
    private Class componentClass;

    public DesignerComponentButton( Class componentClass )
    {
        this.componentClass = componentClass;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event)
    {
        AddComponentControl control = null;
        JComponent jComponent = null;
        try
        {
            jComponent = (JComponent) componentClass.newInstance();

            //Class[] classArray = new Class[] { String.class};
            Method setTextMethod = componentClass.getMethod("setText", null);

            //Object[] objectArray = new Object[] { new String("") };//"Untitled " + componentClass.getName().substring(componentClass.getName().lastIndexOf(".")+1)};
            setTextMethod.invoke(jComponent, null);

        }
        catch (Exception e)
        { System.out.println(e);}

        control = new AddComponentControl(jComponent);
        UIDesigner.getControl().changeAddComponentControl(control);
    }
}
/*
 class AddControl extends AddComponentControl
 {
    private JComponent jComponent;
    public AddControl(JComponent jComopnent)
    {
        this.jComponent = jComopnent;
    }
}*/