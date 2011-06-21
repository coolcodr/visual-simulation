package print;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ObjectTest1 extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 8117733151288266854L;
    private int fontSize = 90;

    public ObjectTest1() {
        setBackground(Color.white);
        setBounds(0, 0, 0, 0);
        setPreferredSize(new Dimension(0, 0));
    }

    public void paint(Graphics g) {
        /*
         * super.paintComponent(g); g.setColor(Color.red);
         * g.drawOval(30,30,100,100); g.drawRect(0,0,160,180);
         * g.drawString("Test", 70, 160);
         */
    }
}
