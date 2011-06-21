package designer;

import javax.swing.JComboBox;

import designer.deployment.DActionClose;
import designer.deployment.DActionExit;
import designer.deployment.DActionRun;

public class DesignActionChooser extends JComboBox {
    /*
     * private String[] choice = { "Close Dialog", "System Exit",
     * "Start Simultation", "Stop Simulation"};
     */

    /**
     * 
     */
    private static final long serialVersionUID = -5909049969473467240L;

    public DesignActionChooser() {
        super(new String[] { "Close Dialog", "System Exit", "Start Simultation", "Stop Simulation" });
    }

    public Object getItemAt(int i) {
        switch (i) {
            case (0):
                return new DActionClose();
            case (1):
                return new DActionExit();
            case (2):
                return new DActionRun();
        }
        return null;
    }

}
