package light;

import java.awt.*;
import java.awt.font.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuUI;

public class LightMenuUI extends BasicMenuUI
{
    private final static Border defaultBorder = LightBorders.MenuItemBorder.getButtonBorder();

    public LightMenuUI()
    {
    }

    public static ComponentUI createUI(JComponent jcomponent)
    {
        return new LightMenuUI();
    }
    public void installComponents ( JMenuItem jMenuItem )
    {
        super.installComponents(jMenuItem);
        jMenuItem.setBorder(defaultBorder);
    }

    protected void installListeners()
    {
        super.installListeners();
        Rollover rollover = new Rollover(menuItem);
        menuItem.addMouseListener(rollover);
    }
    protected void paintBackground(Graphics g, JMenuItem jMenuItem, Color c)
    {
        int x = 0;
        int y = 0;
        int width = jMenuItem.getWidth();
        int height = jMenuItem.getHeight();
        //((Graphics2D)g).translate(0,-2);
        g.setColor(LightLookAndFeel.getControl());
        g.fillRect(x, y, width, height);
        if ( jMenuItem.getModel().isRollover() )
        {
            //g.setColor(Color.decode("#E0D4F5"));
            g.setColor(LightLookAndFeel.getPrimaryControl());
            g.fillRect(2,1,width-4,height-3);
            //g.setColor(Color.decode("#907FAC"));
            g.setColor(LightLookAndFeel.getPrimaryControlDarkShadow());
            g.drawRect(x+1,y,width-3,height-2);
        }
        //Font font = new Font("Dotum", Font.PLAIN, 11);
        ((Graphics2D)g).translate(0,1);
        //g.setFont(font);
        g.setColor(Color.black);
    }
}