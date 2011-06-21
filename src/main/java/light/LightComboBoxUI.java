package light;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class LightComboBoxUI extends BasicComboBoxUI {
    private final static Border loweredBorder = LightLoweredComboBoxBorder.getLoweredBorder();
    private final static Border defaultBorder = LightComboBoxBorder.getButtonBorder();

    public LightComboBoxUI() {
    }

    public static ComponentUI createUI(JComponent jcomponent) {
        return new LightComboBoxUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        if (!((JComboBox) c).isEditable()) {
            c.setBorder(defaultBorder);
        } else {
            c.setBorder(LightComboBoxBorder.LightComboTextBorder.getTextFieldBorder());
        }
    }

    protected ComboPopup createPopup() {
        BasicComboPopup basiccombopopup = (BasicComboPopup) super.createPopup();
        basiccombopopup.setBorder(new LineBorder(Color.darkGray));
        return basiccombopopup;
    }

    public void paintCurrentValue(Graphics g, Dimension r, boolean flag) {
        try {
            // if (comboBox.isEnabled() && !isPopupVisible(comboBox)) {
            ListCellRenderer listcellrenderer = comboBox.getRenderer();
            Component component = listcellrenderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, flag, isPopupVisible(comboBox));
            component.setForeground(Color.black);
            component.setBackground(Color.white);
            // Font font = new Font("Verdana", Font.PLAIN, 11);
            // component.setFont(font);
            boolean flag1 = false;
            if (component instanceof JPanel) {
                flag1 = true;
            }
            int x = 5;
            // int y = (r.height - g.getFontMetrics(g.getFont()).getHeight())/2
            // + 1;
            int y = (r.height / 2) - (g.getFontMetrics(g.getFont()).getAscent()) + 4;
            currentValuePane.paintComponent(g, component, comboBox, x, y, r.width - 10, g.getFontMetrics(g.getFont()).getHeight(), flag1);
            // }
            // g1.setColor(Color.red);
            // super.paintCurrentValue(g1, rectangle, flag);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /*
     * public void update(Graphics g1, JComponent jcomponent) { /if
     * (jcomponent.isEnabled()) { super.update(g1, jcomponent); } //if
     * (jcomponent.isOpaque()) { g1.setColor(Color.blue); g1.fillRect(0, 0,
     * jcomponent.getWidth(), jcomponent.getHeight()); //} paint(g1,
     * jcomponent); }
     */
    public void update(Graphics g, JComponent c) {
        paint(g, c);
    }

    protected void configureEditor() {
        super.configureEditor();
        comboBox.setBorder(LightComboBoxBorder.LightComboTextBorder.getTextFieldBorder());
    }

    public void paint(Graphics g, JComponent c) {
        // super.paint(g, c);

        g.setColor(Color.white);
        // g1.fillRect(0, 0, jcomponent.getWidth(), jcomponent.getHeight());
        int i = 2;
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
        // g.setColor(Color.darkGray);
        // g.drawRect(1,1,c.getWidth()-2,c.getHeight()-2);
        // PaintGrad.noraml(g, c.getX() + i, c.getY() + i, c.getWidth() + i * 2,
        // c.getHeight() + i * 2);
        this.paintCurrentValue(g, c.getSize(), true);
        /*
         * if (hasFocus && !isPopupVisible(comboBox)) paintFocus(g, c);
         */
    }

    protected JButton createArrowButton() {
        return new LightArrowButton(5);
    }

}
