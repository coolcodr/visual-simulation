package designer.deployment;

import java.awt.event.ActionEvent;

public class DActionClose extends DAction {
    /**
     * 
     */
    private static final long serialVersionUID = 8137600040441445451L;

    public void actionPerformed(ActionEvent e) {
        dialog.setVisible(false);
    }

    public String toString() {
        return "Close Dialog";
    }
}
