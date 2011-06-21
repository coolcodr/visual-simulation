package light;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSpinnerUI;

public class LightSpinnerUI extends BasicSpinnerUI {
    public LightSpinnerUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightSpinnerUI();
    }

}
