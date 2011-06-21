package designer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.JToggleButton;

public class DesignerComponentButton extends JToggleButton implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 5645357511013176529L;
    private Class componentClass;

    public DesignerComponentButton(Class componentClass) {
        this.componentClass = componentClass;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        AddComponentControl control = null;
        JComponent jComponent = null;
        try {
            jComponent = (JComponent) componentClass.newInstance();

            // Class[] classArray = new Class[] { String.class};
            Method setTextMethod = componentClass.getMethod("setText", null);

            // Object[] objectArray = new Object[] { new String("")
            // };//"Untitled " +
            // componentClass.getName().substring(componentClass.getName().lastIndexOf(".")+1)};
            setTextMethod.invoke(jComponent, null);

        } catch (Exception e) {
            System.out.println(e);
        }

        control = new AddComponentControl(jComponent);
        UIDesigner.getControl().changeAddComponentControl(control);
    }
}
/*
 * class AddControl extends AddComponentControl { private JComponent jComponent;
 * public AddControl(JComponent jComopnent) { this.jComponent = jComopnent; } }
 */
