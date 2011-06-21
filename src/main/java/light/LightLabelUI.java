package light;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalLabelUI;

public class LightLabelUI extends MetalLabelUI {
    public LightLabelUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightLabelUI();
    }

    public void paint(Graphics g, JComponent c) {

        // Font font = new Font("Verdana", Font.PLAIN, 11);
        // g.setFont(font);
        FontMetrics fontmetrics = g.getFontMetrics(g.getFont());
        Insets insets = c.getInsets(new Insets(0, 0, 0, 0));
        Rectangle r1 = new Rectangle(insets.left, insets.right, c.getWidth() - (insets.left + insets.right), c.getHeight() - (insets.top + insets.bottom));

        Rectangle r2 = new Rectangle(0, 0, 0, 0);
        Rectangle r3 = new Rectangle(0, 0, 0, 0);

        JLabel jLabel = (JLabel) c;
        Icon icon = jLabel.isEnabled() ? jLabel.getIcon() : jLabel.getDisabledIcon();

        String text = layoutCL(jLabel, fontmetrics, jLabel.getText(), icon, r1, r2, r3);

        if (jLabel.isEnabled()) {
            paintEnabledText(jLabel, g, jLabel.getText(), r3.x, r3.y + fontmetrics.getAscent());
        } else {
            paintDisabledText(jLabel, g, jLabel.getText(), r3.x, r3.y + fontmetrics.getAscent());
        }
        if (icon != null) {
            icon.paintIcon(c, g, r2.x, r2.y);
        }
    }

}
