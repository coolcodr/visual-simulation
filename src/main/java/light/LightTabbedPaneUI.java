package light;

import javax.swing.plaf.metal.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LightTabbedPaneUI extends BasicTabbedPaneUI
{

    public LightTabbedPaneUI()
    {
    }
    public static ComponentUI createUI(JComponent jcomponent)
    {
        return new LightTabbedPaneUI();
    }
}