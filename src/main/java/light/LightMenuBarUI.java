package light;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class LightMenuBarUI extends BasicMenuBarUI
{


    public LightMenuBarUI()
    {
        //menuBar.setPreferredSize(new Dimension((int)menuBar.getPreferredSize().getWidth(),12));
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new LightMenuBarUI();
    }
    public void installUI(JComponent c)
    {
        super.installUI(c);
        c.setPreferredSize(new Dimension(c.getWidth(),23));
    }
    /*
    public void update(Graphics g, JComponent c)
    {
        //g.setColor(Color.decode("#EEEEF1"));
        //g.fillRect(0, 0, c.getWidth(), c.getHeight());
        paint(g, c);
    }*/

}