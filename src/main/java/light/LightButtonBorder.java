package light;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class LightButtonBorder extends AbstractBorder {
    /**
     * 
     */
    private static final long serialVersionUID = -3821430987011415806L;

    private static Border buttonBorder = new LightButtonBorder();

    public int cover = 2;

    public static Border getButtonBorder() {
        return buttonBorder;
    }

    public static Border getScrollButtonBorder() {
        LightButtonBorder buttonBorder = new LightButtonBorder();
        buttonBorder.setCoverSize(1);
        return buttonBorder;
    }

    public void setCoverSize(int i) {
        cover = i;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        /*
         * boolean pressed = false; boolean focused = false;
         * 
         * if (c instanceof AbstractButton) { AbstractButton b =
         * (AbstractButton) c; ButtonModel bm = b.getModel();
         * 
         * pressed = bm.isPressed(); focused = (pressed && bm.isArmed()) ||
         * (b.isFocusPainted() && b.hasFocus()); }
         * 
         * Insets in = getBorderInsets(c); Polygon p1 = new Polygon();
         * p1.addPoint(x + in.left, y); p1.addPoint(x, y + (height / 2));
         * p1.addPoint(x + in.left, y + height);
         * 
         * Polygon p2 = new Polygon(); p2.addPoint(x + width - in.right, y);
         * p2.addPoint(x + width, y + (height / 2)); p2.addPoint(x + width -
         * in.right, y + height);
         * 
         * if (pressed) { g.setColor(c.getForeground()); } else if (focused) {
         * g.setColor(SystemColor.green); } else { g.setColor(SystemColor.red);
         * } g.fillPolygon(p1); g.fillPolygon(p2);
         */
        int i = 1;
        int j = cover;
        // g.setColor(c.getBackground());
        // g.drawRect(0, 0, width-1, height-1);

        g.setColor(Color.decode("#FFFFFF"));
        g.drawLine(x + i, y + i, x + i, height - i - j);
        g.drawLine(x + i, y + i, width - i - j, y + i);
        g.setColor(Color.decode("#BEBEC8"));
        g.drawLine(width - i - j, height - i - j, width - i - j, y + i);
        g.drawLine(width - i - j, height - i - j, x + i, height - i - j);

        g.setColor(Color.decode("#B4B4BE"));
        g.drawRect(x, y, width - j, height - j);

        g.setColor(Color.decode("#6E6E78"));
        g.drawLine(x + 1, y, width - 3, y);
        g.drawLine(x, y + 1, x, height - 3);
        g.drawLine(width - j, y + 1, width - j, height - j - 1);
        g.drawLine(x + 1, height - j, width - j - 1, height - j);
        // g.drawRect(x, y, width - 2, height - 2);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(3, 3, 4, 4);
    }
}

class LightLoweredButtonBorder extends AbstractBorder {
    /**
     * 
     */
    private static final long serialVersionUID = 5441019645033201099L;
    private static Border buttonBorder = new LightLoweredButtonBorder();
    private int cover = 1;

    public static Border getButtonBorder() {
        return buttonBorder;
    }

    public static Border getScrollButtonBorder() {
        LightButtonBorder buttonBorder = new LightButtonBorder();
        buttonBorder.setCoverSize(1);
        return buttonBorder;
    }

    public void setCoverSize(int i) {
        cover = i;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int i = 1;

        g.setColor(Color.decode("#BEBEC8"));
        g.drawLine(x + i, y + i, x + i, height - i - 2);
        g.drawLine(x + i, y + i, width - i - 2, y + i);
        g.setColor(Color.decode("#FFFFFF"));
        g.drawLine(width - i - 2, height - i - 2, width - i - 2, y + i);
        g.drawLine(width - i - 2, height - i - 2, x + i, height - i - 2);

        g.setColor(Color.decode("#B4B4BE"));
        g.drawRect(x, y, width - 2, height - 2);

        g.setColor(Color.decode("#6E6E78"));
        g.drawLine(x + 1, y, width - 3, y);
        g.drawLine(x, y + 1, x, height - 3);
        g.drawLine(width - 2, y + 1, width - 2, height - 3);
        g.drawLine(x + 1, height - 2, width - 3, height - 2);
        // g.drawRect(x, y, width - 2, height - 2);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(3, 3, 4, 4);
    }

}
