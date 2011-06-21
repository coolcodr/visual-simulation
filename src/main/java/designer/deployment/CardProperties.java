package designer.deployment;

import java.awt.Rectangle;
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class CardProperties implements Serializable {
    // private Class componentClass;

    /**
     * 
     */
    private static final long serialVersionUID = -1419801349431658667L;
    private Rectangle bounds;
    private String text;
    private boolean visible;

    public CardProperties(JComponent cardPane, String text) {
        initValue(cardPane);
        this.text = text;
    }

    private void initValue(JComponent cardPane) {
        /*
         * Class componentClass = cardPane.getClass(); try { Method
         * getTextMethod = componentClass.getMethod("getText", null); text =
         * (String)getTextMethod.invoke(jComponent, null); }
         */
        // text = cardPane.getText();

        // catch (Exception e)
        // { System.out.println(e);}

        bounds = cardPane.getBounds();
        visible = true;// cardPane.isVisible();
    }

    public void setPropertise(JPanel jPanel) {
        Class componentClass = jPanel.getClass();

        try {
            Class[] classArray = new Class[] { String.class };
            Method setTextMethod = componentClass.getMethod("setText", classArray);

            Object[] objectArray = new Object[] { text };
            setTextMethod.invoke(jPanel, objectArray);
        } catch (Exception e) {
            System.out.println(e);
        }

        Border border = BorderFactory.createEtchedBorder();
        Border titledBorder = new TitledBorder(border, text);
        jPanel.setBorder(titledBorder);

        jPanel.setBounds(bounds);
        jPanel.setVisible(true);
    }

}
