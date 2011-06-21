package diagram;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramSelection extends JComponent {

    /**
     * 
     */
    private static final long serialVersionUID = -1656530737307545612L;

    public DiagramSelection() {
        setSize(0, 0);
    }

    public void paint(Graphics g) {
        g.setColor(Color.gray);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
