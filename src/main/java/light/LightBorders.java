package light;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.ButtonModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LightBorders {
    public static class MenuItemBorder extends AbstractBorder {
        /**
         * 
         */
        private static final long serialVersionUID = -4698594264226314285L;
        protected static Insets borderInsets = new Insets(2, 2, 2, 2);

        public static Border getButtonBorder() {
            return new MenuItemBorder();
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            ButtonModel model = ((JMenuItem) c).getModel();
            if (model.isRollover() || model.isArmed() || (model.isSelected() && !(c instanceof JCheckBoxMenuItem))) {
                // g.setColor(LightLookAndFeel.getPrimaryControl());
                // g.fillRect(2, 1, w - 4, h - 3);
                g.setColor(MetalLookAndFeel.getPrimaryControlDarkShadow());
                g.drawRect(x + 1, y, w - 3, h - 2);
            }
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(2, 2, 2, 2);
        }
    }

}
