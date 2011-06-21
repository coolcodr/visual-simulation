package designer.deployment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JDialog;

abstract public class DAction implements ActionListener, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3832252628647401666L;
    protected MainControlHandler mainControl;
    protected MainFrame mainFrame;
    protected JDialog dialog;

    public DAction() {
    }

    public DAction createNewObject() {
        try {
            return (DAction) getClass().newInstance();
        } catch (Exception ex) {
            return null;
        }
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setMainControl(MainControlHandler mainControl) {
        this.mainControl = mainControl;
    }

    public void setJDialog(JDialog dialog) {
        this.dialog = dialog;
    }

    abstract public void actionPerformed(ActionEvent e);
}
