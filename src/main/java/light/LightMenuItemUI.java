package light;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LightMenuItemUI extends BasicMenuItemUI {
    private final static Border defaultBorder = LightBorders.MenuItemBorder.getButtonBorder();

    public LightMenuItemUI() {
    }

    public static ComponentUI createUI(JComponent c) {
        return new LightMenuItemUI();
    }

    public void installComponents(JMenuItem jMenuItem) {
        super.installComponents(jMenuItem);
        jMenuItem.setBorder(defaultBorder);
        // jMenuItem.setPreferredSize(new Dimension(jMenuItem.getWidth(),22));

        // jMenuItem.setPreferredSize(new Dimension(
        // (int) jMenuItem.getPreferredSize().getWidth() <
        // jMenuItem.getParent().getPreferredSize().getw,
        // (int) jMenuItem.getPreferredSize().getHeight() - 2));
    }

    /*
     * public void update(Graphics g, JComponent c) { Dimension size =
     * c.getSize(); Insets insets = c.getBorder().getBorderInsets(c);
     * c.setBorder(defaultBorder); /* if ( (c instanceof AbstractButton) &&
     * ((AbstractButton)c).getModel().isRollover()) { PaintGrad.rollOver(g, 0,
     * 0, size.width, size.height); } else { if (c.isOpaque())
     * PaintGrad.noraml(g, 0, 0, size.width, size.height); }* paint(g, c); }
     */

    public void paintBackground(Graphics g, JMenuItem jMenuItem, Color c) {
        int x = 0;
        int y = 0;
        int width = jMenuItem.getWidth();
        int height = jMenuItem.getHeight();
        // ((Graphics2D)g).translate(0,-2);
        g.setColor(MetalLookAndFeel.getControl());
        g.fillRect(x, y, width, height);
        if (jMenuItem.getModel().isRollover() || jMenuItem.getModel().isArmed()) {
            g.setColor(MetalLookAndFeel.getPrimaryControl());
            // g.setColor(Color.decode("#C8C8E6"));
            g.fillRect(2, 1, width - 4, height - 3);
            g.setColor(MetalLookAndFeel.getPrimaryControlDarkShadow());
            // g.setColor(Color.decode("#8989C4"));
            g.drawRect(x + 1, y, width - 3, height - 2);
        }
        // Font font = new Font("Dotum", Font.PLAIN, 11);
        ((Graphics2D) g).translate(0, 1);
        // g.setFont(font);
        g.setColor(Color.black);

    }

    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
    }

}
