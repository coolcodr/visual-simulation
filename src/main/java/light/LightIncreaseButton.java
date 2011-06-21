package light;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;

public class LightIncreaseButton extends BasicArrowButton {

    /**
     * 
     */
    private static final long serialVersionUID = -7585238955589526332L;
    private final static Border defaultBorder = LightButtonBorder.getScrollButtonBorder();
    private final static Border loweredBorder = LightLoweredButtonBorder.getScrollButtonBorder();

    private int orientation;

    public LightIncreaseButton(int i) {
        super(i);
        orientation = i;
        Dimension d = new Dimension(16, 16);
        setSize(getWidth(), 16);
        setDirection(i);
        setRequestFocusEnabled(false);
        setRolloverEnabled(true);
        addMouseListener(new Rollover(this));
        setBorder(null);
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int direction = getDirection();

        int i = getWidth() / 2 - 4;
        int j = getHeight() / 2;

        PaintGrad.full(g, 0, 0, getWidth(), getHeight());

        /*
         * g.setColor(Color.decode("#F1F1F4"));
         * g.fillRect(2,2,width-5,height-5);
         */
        if (isEnabled()) {
            g.setColor(Color.decode("#3C3C3C"));
        } else {
            g.setColor(Color.lightGray);
        }

        if (getModel().isRollover() && !getModel().isPressed()) {
            g.setColor(Color.decode("#907FAC"));
        }
        int[] x;
        int[] y;

        if (orientation == 5) {
            x = new int[] { i, i + 9, i + 4 };
            y = new int[] { j + 1, j + 1, j + 6 };
            g.fillRect(i + 2, j - 2, 5, 4);
        } else {
            x = new int[] { i + 5, i + 5, i + 10 };
            y = new int[] { j - 5, j + 5, j };
            g.fillRect(i + 2, j - 2, 4, 5);
        }
        g.fillPolygon(x, y, 3);

        paintBorder(g);
    }

}
