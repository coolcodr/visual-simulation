package designer.deployment;

import java.awt.event.ActionEvent;

public class DActionLoad extends DAction {
    /**
     * 
     */
    private static final long serialVersionUID = -7228312356531485843L;

    public void actionPerformed(ActionEvent e) {
        mainFrame.loadData();
    }

    public String toString() {
        return "Load Data";
    }
}
