package light;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class PaintGrad {
    public PaintGrad() {
    }

    public static void full(Graphics g, int x, int y, int w, int h) {
        paintBack(g, x, y, w, h);
    }

    public static void noraml(Graphics g, int x, int y, int w, int h) {
        paintBack(g, x, y, w, h);
        int i = 2;
        // g.setColor(Color.decode("#DBDBE2"));
        g.setColor(MetalLookAndFeel.getControl());
        g.fillRect(x + i, y + i, w, h);
        g.fillRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1);
    }

    public static void lowered(Graphics g, int x, int y, int w, int h) {
        paintBack(g, x, y, w, h);
        int i = 2;
        g.setColor(Color.decode("#DBDBE2"));
        g.fillRect(x + i, y + i, w, h);
        g.fillRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1);
    }

    public static void paintText(Graphics g, JComponent c, Rectangle r, String s) {
        g.setColor(Color.decode("#000000"));
        g.drawRect(0, 0, 10, 10);
    }

    public static void rollOver(Graphics g, int x, int y, int w, int h) {
        paintBack(g, x, y, w, h);
        int i = 2;
        // g.setColor(Color.decode("#F8F8FF"));
        g.setColor(Color.decode("#FFFFFF"));
        g.fillRect(x + i, y + i, w - i * 2 - 1, h - i * 2 - 1);

        /*
         * g.setColor(Color.decode("#B48BF3")); //g.drawRect(x, y + 2, w - i * 2
         * + 1, h - i * 2 - 3); //g.drawLine(x + 3, y + 2, w - i * 2 + 1 - 3 , y
         * + 2); //g.drawLine(x + 3, h - i * 2 - 1, w - i * 2 + 1 - 3 , h - i *
         * 2 - 1); int[] x2 = { w - i * 2, w - i * 2, w - i * 2 - 6 }; int[] y2
         * = { h - i * 2 - 6, h - i * 2, h - i * 2 }; g.fillPolygon(x2, y2, 3);
         * /* int[] x3 = { x + 2 , x + 2 , x + 2 + 5 }; int[] y3 = { y + 2 , y +
         * 2 + 5, y + 2}; g.fillPolygon(x3, y3, 3);
         */
    }

    private static void paintBack(Graphics g, int x, int y, int w, int h) {
        g.setColor(MetalLookAndFeel.getControl());
        g.fillRect(x, y, w, h);
    }
}
