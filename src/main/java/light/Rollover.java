package light;

import java.awt.Component;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;

public class Rollover extends MouseAdapter implements MouseListener
{
    Component component;
    public Rollover( Component component )
    {
        this.component = component;
        component.addMouseListener(this);
    }
    /*
    public void focusGained(FocusEvent focusevent)
    {
        //System.out.println("!!!");
    }

    public void ancestorMoved(AncestorEvent ancestorevent)
    {
    }

    public void ancestorAdded(AncestorEvent ancestorevent)
    {
    }

    public void ancestorRemoved(AncestorEvent ancestorevent)
    {
    }

    public void hierarchyChanged(HierarchyEvent hierarchyevent)
    {
    }*/
    public void mouseEntered(MouseEvent mouseevent)
    {
        ((AbstractButton)component).getModel().setRollover(true);
        ((AbstractButton)component).repaint();
    }

    public void mouseExited(MouseEvent mouseevent)
    {
        ((AbstractButton)component).getModel().setRollover(false);
        ((AbstractButton)component).repaint();
    }

}