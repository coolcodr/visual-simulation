package designer.deployment;

import java.awt.event.ActionEvent;

public class DActionRun extends DAction {
    /**
     * 
     */
    private static final long serialVersionUID = 2376068287598854503L;

    public void actionPerformed(ActionEvent e) {
        mainFrame.startRun();
    }

    public String toString() {
        return "Run Simultation";
    }
}
