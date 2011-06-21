package light;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.metal.MetalSplitPaneUI;

public class LightSplitPaneUI extends MetalSplitPaneUI {
    public LightSplitPaneUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightSplitPaneUI();
    }

    public BasicSplitPaneDivider createDefaultDivider() {
        return new LightSplitPaneDivider(this);
    }

}
