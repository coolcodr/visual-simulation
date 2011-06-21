package light;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

public class LightTextFieldUI extends BasicTextFieldUI {
    private final static Border defaultBorder = LightTextFieldBorder.getTextFieldBorder();
    private Border savedBorder;

    public LightTextFieldUI() {
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        savedBorder = c.getBorder();
        c.setBorder(defaultBorder);
        // Font font = new Font("Verdana", Font.PLAIN, 11);
        // c.setFont(font);
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightTextFieldUI();
    }

    protected void paintBackground(Graphics g) {
        JTextComponent component;
        component = getComponent();
        /*
         * 
         * int i = g.c;
         * 
         * if (!jtextcomponent.isEnabled()) { g1.setColor(a); if (i == 0) break
         * label0; } if (!jtextcomponent.isEditable()) { g1.setColor(b); if (i
         * == 0) break label0; }
         */
        g.setColor(Color.white);
        g.fillRect(0, 0, component.getWidth(), component.getHeight());
    }

}
