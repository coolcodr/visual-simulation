package light;

import java.awt.Component;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class LightSpinnerUI extends BasicSpinnerUI
{
    public LightSpinnerUI()
    {
    }

    public static ComponentUI createUI(JComponent jcomponent)
    {
        return new LightSpinnerUI();
    }

}