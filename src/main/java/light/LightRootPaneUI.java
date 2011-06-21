package light;

import java.awt.LayoutManager;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;

public class LightRootPaneUI extends BasicRootPaneUI
{
    public LightRootPaneUI()
    {
    }

    public static ComponentUI createUI(JComponent c)
    {
        return new LightRootPaneUI();
    }

}