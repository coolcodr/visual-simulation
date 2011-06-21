package light;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class LightTabbedPaneUI extends BasicTabbedPaneUI {

    public LightTabbedPaneUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightTabbedPaneUI();
    }
}
