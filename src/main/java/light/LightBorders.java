package light;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.text.JTextComponent;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Window;
import java.io.Serializable;

public class LightBorders
{
    public static class MenuItemBorder extends AbstractBorder
    {
        protected static Insets borderInsets = new Insets(2, 2, 2, 2);

        public static Border getButtonBorder()
        {
            return new MenuItemBorder();
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
        {
            ButtonModel model = ((JMenuItem)c).getModel();
            if ( model.isRollover() || model.isArmed() || ( model.isSelected() && !(c instanceof JCheckBoxMenuItem) ))
            {
                //g.setColor(LightLookAndFeel.getPrimaryControl());
                //g.fillRect(2, 1, w - 4, h - 3);
                g.setColor(LightLookAndFeel.getPrimaryControlDarkShadow());
                g.drawRect(x + 1, y, w - 3, h - 2);
            }
        }

        public Insets getBorderInsets(Component c)
        {
            return new Insets(2, 2, 2, 2);
        }
    }

}