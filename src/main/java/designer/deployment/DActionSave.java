package designer.deployment;

import java.awt.event.ActionEvent;

public class DActionSave extends DAction {
    /**
     * 
     */
    private static final long serialVersionUID = 2162692161147100762L;

    public void actionPerformed(ActionEvent e) {
        mainFrame.saveData();
    }

    public String toString() {
        return "Save Data";
    }
}
