package print;

import java.awt.*;
import javax.swing.*;

public class ObjectTest1 extends JPanel
{
    private int fontSize = 90;
    public ObjectTest1()
    {
        setBackground(Color.white );
        setBounds(0,0,0,0);
        setPreferredSize(new Dimension(0, 0));
    }
    public void paint(Graphics g)
    {
        /*
        super.paintComponent(g);
        g.setColor(Color.red);
        g.drawOval(30,30,100,100);
        g.drawRect(0,0,160,180);
        g.drawString("Test", 70, 160);*/
    }
}