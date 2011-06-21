package diagram;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class Frame1 extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 8642756309120504505L;
    JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();

    /** Construct the frame */
    public Frame1() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Component initialization */

    private void jbInit() throws Exception {
        // setIconImage(Toolkit.getDefaultToolkit().createImage(Frame1.class.getResource("[Your Icon]")));
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(borderLayout1);
        this.setSize(new Dimension(1000, 730));
        setTitle("vSim Prototype 22-JAN-2003-2047");
    }

    /** Overridden so we can exit when window is closed */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }
}
