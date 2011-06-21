package light;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class LightTextFieldBorder extends AbstractBorder
{
    public LightTextFieldBorder()
    {
    }

    public static Border getTextFieldBorder()
    {
        return new LightTextFieldBorder();
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        /*
            int i = 1;
    g.setColor(Color.decode("#FFFFFF"));
    g.drawLine(x + i, y + i, x + i, height - i - 2);
    g.drawLine(x + i, y + i, width - i - 2, y + i);
    g.setColor(Color.decode("#BEBEC8"));
    g.drawLine(width - i - 2, height - i - 2, width - i - 2, y + i);
    g.drawLine(width - i - 2, height - i - 2, x + i, height - i-  2);

    g.setColor(Color.decode("#B4B4BE"));
    g.drawRect(x, y, width - 2, height - 2);

    g.setColor(Color.decode("#6E6E78"));
    g.drawLine(x + 1, y, width - 3, y);
    g.drawLine(x, y + 1, x, height - 3);
    g.drawLine(width - 2, y + 1, width - 2, height - 3);
    g.drawLine(x + 1, height - 2, width - 3, height - 2);
*/
        int i = 1;
        g.setColor(Color.decode("#BEBEC8"));
        g.drawLine(x + i, y + i, x + i, height - i - 2);
        g.drawLine(x + i, y + i, width - i - 2, y + i);
        g.setColor(Color.decode("#FFFFFF"));
        g.drawLine(width - i - 2, height - i - 2, width - i - 2, y + i);
        g.drawLine(width - i - 2, height - i - 2, x + i, height - i - 2);

        g.setColor(Color.decode("#B4B4BE"));
        g.drawRect(x, y, width - 2, height - 2);

        g.setColor(Color.decode("#6E6E78"));
        g.drawLine(x + 1, y, width - 3, y);
        g.drawLine(x, y + 1, x, height - 3);
        g.drawLine(width - 2, y + 1, width - 2, height - 3);
        g.drawLine(x + 1, height - 2, width - 3, height - 2);
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(3, 3, 3, 3);
    }

}