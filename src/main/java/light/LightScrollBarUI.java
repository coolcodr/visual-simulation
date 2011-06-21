package light;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LightScrollBarUI extends BasicScrollBarUI {
    protected LightIncreaseButton increaseButton;
    protected LightDescraseButton decreaseButton;

    public LightScrollBarUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightScrollBarUI();
    }

    protected void installDefaults() {
        super.installDefaults();
        scrollbar.setBorder(LightLoweredButtonBorder.getScrollButtonBorder());
    }

    protected JButton createDecreaseButton(int orientation) {
        decreaseButton = new LightDescraseButton(orientation);
        return decreaseButton;
    }

    /** Returns the view that represents the increase view. */
    protected JButton createIncreaseButton(int orientation) {
        increaseButton = new LightIncreaseButton(orientation);
        return increaseButton;
    }

    protected void paintThumb(Graphics g1, JComponent jcomponent, Rectangle r) {
        int i = 1;
        int j = 0;

        Graphics g = g1;
        int x = r.x;
        int y = r.y;
        int width = r.width;
        int height = r.height;
        /*
         * g.setColor(Color.decode("#FFFFFF")); g.drawLine(x + i, y + i, x + i,
         * height - i - j); g.drawLine(x + i, y + i, width - i - j, y + i);
         * g.setColor(Color.decode("#BEBEC8")); g.drawLine(width - i - j, height
         * - i - j, width - i - j, y + i); g.drawLine(width - i - j, height - i
         * - j, x + i, height - i- j);
         * 
         * g.setColor(Color.decode("#B4B4BE")); g.drawRect(x, y, width - j,
         * height - j);
         * 
         * g.setColor(Color.decode("#6E6E78")); g.drawLine(x + 1, y, width - 3,
         * y); g.drawLine(x, y + 1, x, height - 3); g.drawLine(width - j, y + 1,
         * width - j, height - j - 1); g.drawLine(x + 1, height - j, width - j -
         * 1, height - j);
         */

        // g.setColor(Color.decode("#8B69C2"));
        g.setColor(MetalLookAndFeel.getPrimaryControl());
        g.fillRect(x + j, y + j, width - j - 1, height - j - 1);

        g.setColor(Color.gray);
        g.drawRect(x + j, y + j, width - j - 1, height - j - 1);

        g.setColor(MetalLookAndFeel.getPrimaryControlHighlight());
        // g.drawLine(x + j + 1, y + j + 1, x + j + 1, y + j + height - j - 2);
        // g.drawLine(x + j + 1, y + j + 1, x + j + width - j - 2 , y + j + 1);

        g.setColor(Color.lightGray);
        // g.drawLine(x + j + 1, y + j + 1, x + j + 1, y + j + 1 + height - j -
        // 1);
        // g.drawLine(x + j + 1, y + j + 1 + height - j - 2, x + j + 1 + width -
        // j - 1 ,y + j + 1 + height - j - 2);

        /*
         * g.setColor(Color.decode("#6E6E78")); g.drawLine(x + 1, y, width - 3,
         * y); g.drawLine(x, y + 1, x, height - 3); g.drawLine(width - j, y + 1,
         * width - j, height - j - 1); g.drawLine(x + 1, height - j, width - j -
         * 1, height - j);
         */

        // jcomponent.setBorder(LightLoweredButtonBorder.getScrollButtonBorder());
    }

    protected void paintTrack(Graphics g1, JComponent jcomponent, Rectangle r) {
        g1.setColor(Color.white);
        g1.fillRect(r.x, r.y, r.width - 1, r.height - 1);
        g1.setColor(Color.gray);
        g1.drawRect(r.x, r.y, r.width - 1, r.height - 1);
        // g1.drawLine(r.width, r.y, r.width, r.height);
        // g.setColor(Color.decode("#6E6E78"));
        // g.drawLine(x + 1, y, width - 3, y);
        // g.drawLine(x, y + 1, x, height - 3);
        // g.drawLine(width - 2, y + 1, width - 2, height - 3);
        // g.drawLine(x + 1, height - 2, width - 3, height - 2);

    }
    /*
     * public void layoutContainer(Container container) {
     * 
     * }
     */
}
