package light;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class LightComboBoxBorder extends LightButtonBorder
{
    public static Border getButtonBorder()
    {
        return new LightComboBoxBorder();
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(2, 2, 2, 2);
    }
    public static class LightComboTextBorder extends LightTextFieldBorder
    {
        public static Border getTextFieldBorder()
        {
            return new LightComboTextBorder();
        }

        public Insets getBorderInsets(Component c)
        {
            return new Insets(2, 2, 2, 2);
        }
    }
}

    class LightLoweredComboBoxBorder extends LightLoweredButtonBorder
    {
        public static Border getLoweredBorder()
        {
            return new LightLoweredComboBoxBorder();
        }

        public Insets getBorderInsets(Component c)
        {
            return new Insets(2, 2, 2, 2);
        }

    }
