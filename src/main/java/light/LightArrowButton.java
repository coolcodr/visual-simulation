package light;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicArrowButton;

public class LightArrowButton extends BasicArrowButton {
    /**
     * 
     */
    private static final long serialVersionUID = 2927835858976408212L;
    private final static Border defaultBorder = LightButtonBorder.getButtonBorder();
    private final static Border loweredBorder = LightLoweredButtonBorder.getButtonBorder();

    public LightArrowButton(int i) {
        super(i);
        Dimension d = new Dimension(16, 16);
        setSize(getWidth(), 16);
        setDirection(i);
        setRequestFocusEnabled(false);
        setRolloverEnabled(true);
        addMouseListener(new Rollover(this));
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int direction = getDirection();

        int i = getWidth() / 2 - 5;
        int j = getHeight() / 2;

        if (getModel().isPressed()) {
            setBorder(loweredBorder);
            PaintGrad.lowered(g, 0, 0, getWidth(), getHeight());
        } else if (!getModel().isRollover()) {
            setBorder(defaultBorder);
            PaintGrad.noraml(g, 0, 0, getWidth(), getHeight());
        } else {
            setBorder(defaultBorder);
            PaintGrad.rollOver(g, 0, 0, getWidth(), getHeight());
        }

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
        if (i <= 1) {
            i = getWidth() / 2 - 3;
            x = new int[] { i, i + 6, i + 2 };
            y = new int[] { j, j, j + 3 };
            g.fillPolygon(x, y, 3);
            g.fillRect(i + 1, j - 2, 4, 2);
        } else {
            x = new int[] { i, i + 9, i + 4 };
            y = new int[] { j, j, j + 5 };
            g.fillPolygon(x, y, 3);
            g.fillRect(i + 2, j - 3, 5, 4);
        }

        paintBorder(g);
    }

}
