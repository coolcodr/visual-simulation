package light;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class LightSplitPaneDivider extends BasicSplitPaneDivider {
    /**
     * 
     */
    private static final long serialVersionUID = -4617204718490420844L;

    public LightSplitPaneDivider(BasicSplitPaneUI basicsplitpaneui) {
        super(basicsplitpaneui);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.decode("#F1F1F4"));
        g.fillRect(0, 0, getWidth(), getHeight());
        getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());
        // g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
