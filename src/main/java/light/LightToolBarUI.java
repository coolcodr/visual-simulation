package light;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicToolBarUI;

public class LightToolBarUI  extends BasicToolBarUI
{
    public LightToolBarUI()
    {
    }
    public static ComponentUI createUI(JComponent jcomponent)
    {
        return new LightToolBarUI();
    }
    public void installUI(JComponent jcomponent)
    {

    }

    public void installListeners()
    {
    }

    public void uninstallListeners()
    {
    }

    public void paint(Graphics g)
    {

    }
    public void update(Graphics g, JComponent c)
    {
        /*
         java.awt.Color color;
         int i;
             i = com.incors.plaf.alloy.g.c;
             if (!jcomponent.isOpaque())
                 break label0;
             color = jcomponent.getBackground();
             if (color == null)
                 break label0;
             if (! (color instanceof c))
                 break label1;
             c c1 = (c) color;
             if (jcomponent.isEnabled()) {
                 color = c1.a();
                 if (i == 0)
                     break label1;
             color = c1.b();
         if (color instanceof h) {
             if ( (jcomponent instanceof JToolBar) && ( (JToolBar) jcomponent).getOrientation() == 0) {
                 ( (h) color).b(g1, 0, 0, jcomponent.getWidth(), jcomponent.getHeight());
                 if (i == 0)
                     break label0;
             }
             ( (h) color).a(g1, 0, 0, jcomponent.getWidth(), jcomponent.getHeight());
             if (i == 0)
                 break label0;
         }*/
         for ( int i = 0 ; i < c.getHeight() ; i += 2 )
         {
             g.setColor(LightLookAndFeel.getControl());
             g.fillRect(0, i, c.getWidth(), 1);
             g.setColor(Color.decode("#E4E4E8"));
             g.fillRect(0, i + 1, c.getWidth(), 1);
         }
    }


}