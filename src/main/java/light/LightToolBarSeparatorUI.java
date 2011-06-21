package light;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

public class LightToolBarSeparatorUI extends BasicToolBarSeparatorUI {
    public LightToolBarSeparatorUI() {
    }

    public static ComponentUI createUI(JComponent c) {
        return new LightToolBarSeparatorUI();
    }

    public void paint(Graphics g1, JComponent jcomponent) {
        System.out.println("!!!");
        Dimension dimension = jcomponent.getSize();
        int j = dimension.height / 2;
        /*
         * Dimension dimension = jcomponent.getSize(); if ( ( (JSeparator)
         * jcomponent).getOrientation() == 0) { int i = dimension.width / 2;
         * g1.setColor(c); g1.fillRect(i, 1, 1, dimension.height - 2);
         * g1.setColor(a); g1.fillRect(i, 0, 1, 1); g1.fillRect(i,
         * dimension.height - 1, 1, 1); g1.setColor(b); g1.fillRect(i + 1, 1, 1,
         * dimension.height - 1); if (g.c == 0) break label0; } int j =
         * dimension.height / 2; g1.setColor(c); g1.fillRect(1, j,
         * dimension.width - 2, 1); g1.setColor(a); g1.fillRect(0, j, 1, 1);
         * g1.fillRect(dimension.width - 1, j, 1, 1); g1.setColor(b);
         * g1.fillRect(1, j + 1, dimension.width - 1, 1);
         */
        g1.setColor(Color.red);
        g1.fillRect(1, j + 1, dimension.width - 1, 1);
    }

}
