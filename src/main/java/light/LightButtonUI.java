package light;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicButtonListener;

import javax.swing.plaf.metal.*;

public class LightButtonUI extends MetalButtonUI
{
    private final static Border defaultBorder = LightButtonBorder.getButtonBorder();
    private final static Border loweredBorder = LightLoweredButtonBorder.getButtonBorder();

    private Border savedBorder;
    protected static LightButtonUI buttonUI;

    public static ComponentUI createUI(JComponent c)
    {
        if (buttonUI == null) {
            buttonUI = new LightButtonUI();
        }
        return buttonUI;
    }

    public void installUI(JComponent c)
    {
        super.installUI(c);
        savedBorder = c.getBorder();
        c.setBorder(defaultBorder);
        AbstractButton ab = (AbstractButton) c;
        ab.setRolloverEnabled(true);
        ab.setOpaque(false);
    }

    public void uninstallUI(JComponent c)
    {
        if (c.getBorder() == defaultBorder)
            c.setBorder(savedBorder);
        super.uninstallUI(c);
        AbstractButton abstractbutton = (AbstractButton) c;
        abstractbutton.setRolloverEnabled(false);
        abstractbutton.getModel().setRollover(false);
    }

    public void installListeners(AbstractButton abstractbutton)
    {
        super.installListeners(abstractbutton);
        //Rollover roll0ver = (Rollover)abstractbutton.getClientProperty(this);
        Rollover rollover = new Rollover(abstractbutton);
        abstractbutton.addMouseListener(rollover);
    }
    /*
    protected BasicButtonListener createButtonListener(AbstractButton abstractbutton)
    {
        return new Rollover(abstractbutton);
    }
*/
    public void uninstallListeners(AbstractButton abstractbutton)
    {
        super.uninstallListeners(abstractbutton);
        /*
        Rollover roll0ver = (Rollover)abstractbutton.getClientProperty(this);
        abstractbutton.removeAncestorListener(roll0ver);
        abstractbutton.removeHierarchyListener(roll0ver);*/
    }

    //protected void paintIcon(Graphics g, JComponent c, Rectangle rectangle)
    public void update(Graphics g, JComponent c)
    {
        Dimension size = c.getSize();
        Insets insets = c.getBorder().getBorderInsets(c);
        if ( (c instanceof AbstractButton) && ((AbstractButton)c).getModel().isPressed())
        {
            c.setBorder(loweredBorder);
            PaintGrad.lowered(g, 0, 0, size.width, size.height);
            g.translate(1,0);
        }
        else if ( (c instanceof AbstractButton) && ((AbstractButton)c).getModel().isRollover())
        {
            c.setBorder(defaultBorder);
            PaintGrad.rollOver(g, 0, 0, size.width, size.height);
            g.translate(0,-1);
        }
        else
        {
            c.setBorder(defaultBorder);
            PaintGrad.noraml(g, 0, 0,  size.width, size.height);
            g.translate(0,-1);
        }
        paint(g, c);
    }
    public void paint(Graphics g, JComponent c)
    {
        AbstractButton abstractbutton;
        abstractbutton = (AbstractButton) c;
        ButtonModel buttonmodel = abstractbutton.getModel();
        Dimension dimension = abstractbutton.getSize();
        Insets insets = abstractbutton.getInsets();
        //g.setFont(new Font("Verdana", Font.PLAIN, 11));
        FontMetrics fontmetrics = g.getFontMetrics();
        int ig = abstractbutton.getIconTextGap();
        Rectangle d = new Rectangle(0, 0, 0, 0);
        Rectangle e = new Rectangle(0, 0, 0, 0);
        Rectangle f = new Rectangle(insets.left, insets.top, dimension.width - (insets.right + insets.left), dimension.height - (insets.bottom + insets.top) + 4);
        String s = SwingUtilities.layoutCompoundLabel(c, fontmetrics, abstractbutton.getText(), abstractbutton.getIcon(), abstractbutton.getVerticalAlignment(), abstractbutton.getHorizontalAlignment(), abstractbutton.getVerticalTextPosition(), abstractbutton.getHorizontalTextPosition(), f, d, e, ig);
        paintText(g, abstractbutton, e, s);
        Rectangle r =new Rectangle(0,0,abstractbutton.getWidth(),abstractbutton.getHeight());
        paintIcon(g,c,d);
    }

    protected void paintText(Graphics g, AbstractButton abstractbutton, Rectangle r, String s)
    {
        FontMetrics fontmetrics;

        fontmetrics = g.getFontMetrics();
        if (abstractbutton.isEnabled())
        {
            if ( abstractbutton.getModel().isRollover() && !abstractbutton.getModel().isPressed() )
                g.setColor(abstractbutton.getForeground());//g.setColor(Color.decode("#6A5886"));
            else
                g.setColor(abstractbutton.getForeground());
        }
        else
            g.setColor(getDisabledTextColor());

        BasicGraphicsUtils.drawString(g, s, abstractbutton.getMnemonic(), r.x, r.y + fontmetrics.getAscent());
    }

    public void paintFocus(Graphics g, AbstractButton abstractbutton, Rectangle rectangle, Rectangle rectangle1, Rectangle rectangle2)
    {
        Container container = abstractbutton.getParent();
        //if(!(container instanceof JToolBar))
        {
            g.setColor(Color.red);
            g.drawRect(0, 0, 10, 10);
        }
    }
    protected void paintIcon(Graphics g, JComponent c, Rectangle r)
    {
        super.paintIcon(g,c,r);
    }
    /*
    protected static void setRolloverEnabled(AbstractButton abstractbutton)
    {
        g.setColor(Color.red);
            g.drawRect(0, 0, 10, 10);

    }*/

    protected void paintButtonPressed(Graphics g, AbstractButton b)
    {
    }
}