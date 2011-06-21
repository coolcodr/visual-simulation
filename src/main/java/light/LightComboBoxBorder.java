package light;

import java.awt.Component;
import java.awt.Insets;

import javax.swing.border.Border;

public class LightComboBoxBorder extends LightButtonBorder {
    /**
     * 
     */
    private static final long serialVersionUID = 6716366379735009017L;

    public static Border getButtonBorder() {
        return new LightComboBoxBorder();
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(2, 2, 2, 2);
    }

    public static class LightComboTextBorder extends LightTextFieldBorder {
        /**
         * 
         */
        private static final long serialVersionUID = 7215175392379337215L;

        public static Border getTextFieldBorder() {
            return new LightComboTextBorder();
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2);
        }
    }
}

class LightLoweredComboBoxBorder extends LightLoweredButtonBorder {
    /**
         * 
         */
    private static final long serialVersionUID = 5093259139539619794L;

    public static Border getLoweredBorder() {
        return new LightLoweredComboBoxBorder();
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(2, 2, 2, 2);
    }

}
