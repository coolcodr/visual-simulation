package designer.deployment;

import java.awt.event.ActionEvent;

public class DActionExit extends DAction {
    /**
     * 
     */
    private static final long serialVersionUID = -6217919922631416305L;

    public void actionPerformed(ActionEvent e) {
        mainFrame.setVisible(false);
    }

    public String toString() {
        return "System Exit";
    }
}
