package designer;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class Test extends Frame {
    /**
     * 
     */
    private static final long serialVersionUID = 1259345634758427908L;
    private JComboBox jComboBox1 = new JComboBox();
    private JRadioButton jRadioButton1 = new JRadioButton();

    public Test() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        setLayout(null);
        jComboBox1.setBounds(new Rectangle(40, 163, 262, 61));
        jRadioButton1.setText("jRadioButton1");
        jRadioButton1.setBounds(new Rectangle(52, 76, 192, 44));
        this.add(jRadioButton1, null);
        this.add(jComboBox1, null);
    }
}
