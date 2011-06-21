package light;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.metal.MetalToggleButtonUI;

public class LightToggleButtonUI extends MetalToggleButtonUI {
    private final static Border defaultBorder = LightButtonBorder.getButtonBorder();
    private final static Border loweredBorder = LightLoweredButtonBorder.getButtonBorder();

    protected static LightToggleButtonUI buttonUI;

    public static ComponentUI createUI(JComponent c) {
        if (buttonUI == null) {
            buttonUI = new LightToggleButtonUI();
        }
        return buttonUI;
    }

    public LightToggleButtonUI() {
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        c.setBorder(defaultBorder);
        AbstractButton ab = (AbstractButton) c;
        ab.setRolloverEnabled(true);
        ab.setOpaque(false);
    }

    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        AbstractButton abstractbutton = (AbstractButton) c;
        abstractbutton.setRolloverEnabled(false);
        abstractbutton.getModel().setRollover(false);
    }

    public void installListeners(AbstractButton abstractbutton) {
        super.installListeners(abstractbutton);
        Rollover rollover = new Rollover(abstractbutton);
        abstractbutton.addMouseListener(rollover);
    }

    public void update(Graphics g, JComponent c) {
        Dimension size = c.getSize();
        Insets insets = c.getBorder().getBorderInsets(c);

        if ((c instanceof AbstractButton) && ((AbstractButton) c).getModel().isSelected()) {
            c.setBorder(loweredBorder);
            PaintGrad.lowered(g, 0, 0, size.width, size.height);
            g.translate(1, 1);
        } else if ((c instanceof AbstractButton) && ((AbstractButton) c).getModel().isPressed()) {
            c.setBorder(loweredBorder);
            PaintGrad.lowered(g, 0, 0, size.width, size.height);
            g.translate(1, 1);
        } else if ((c instanceof AbstractButton) && ((AbstractButton) c).getModel().isRollover()) {
            c.setBorder(defaultBorder);
            PaintGrad.rollOver(g, 0, 0, size.width, size.height);
        } else {
            c.setBorder(defaultBorder);
            PaintGrad.noraml(g, 0, 0, size.width, size.height);
        }
        paint(g, c);
    }

    public void paint(Graphics g, JComponent c) {
        AbstractButton abstractbutton;
        abstractbutton = (AbstractButton) c;
        ButtonModel buttonmodel = abstractbutton.getModel();
        Dimension dimension = abstractbutton.getSize();
        Insets insets = abstractbutton.getInsets();
        // g.setFont(new Font("Verdana", Font.PLAIN, 11));
        FontMetrics fontmetrics = g.getFontMetrics();
        int ig = abstractbutton.getIconTextGap();
        Rectangle d = new Rectangle(0, 0, 0, 0);
        Rectangle e = new Rectangle(0, 0, 0, 0);
        Rectangle f = new Rectangle(insets.left, insets.top, dimension.width - (insets.right + insets.left), dimension.height - (insets.bottom + insets.top) + 4);
        String s = SwingUtilities.layoutCompoundLabel(c, fontmetrics, abstractbutton.getText(), abstractbutton.getIcon(), abstractbutton.getVerticalAlignment(), abstractbutton.getHorizontalAlignment(), abstractbutton.getVerticalTextPosition(), abstractbutton.getHorizontalTextPosition(), f, d, e, ig);
        paintText(g, abstractbutton, e, s);
        Rectangle r = new Rectangle(0, 0, abstractbutton.getWidth(), abstractbutton.getHeight());
        paintIcon(g, c, d);
    }

    protected void paintText(Graphics g, AbstractButton abstractbutton, Rectangle r, String s) {
        FontMetrics fontmetrics;

        fontmetrics = g.getFontMetrics();
        if (abstractbutton.isEnabled()) {
            if (abstractbutton.getModel().isRollover() && !abstractbutton.getModel().isPressed()) {
                g.setColor(Color.decode("#6A5886"));
            } else {
                g.setColor(abstractbutton.getForeground());
            }
        } else {
            g.setColor(getDisabledTextColor());
        }

        BasicGraphicsUtils.drawString(g, s, abstractbutton.getMnemonic(), r.x, r.y + fontmetrics.getAscent() - 1);
    }

    public void paintFocus(Graphics g, AbstractButton abstractbutton, Rectangle rectangle, Rectangle rectangle1, Rectangle rectangle2) {
        Container container = abstractbutton.getParent();
        // if(!(container instanceof JToolBar))
        {
            g.setColor(Color.red);
            g.drawRect(0, 0, 10, 10);
        }
    }

    protected void paintButtonPressed(Graphics g, AbstractButton b) {
    }

}
