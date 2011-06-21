package designer;

import javax.swing.JButton;

import designer.deployment.DAction;

public class DiagramActionSetPanel extends JButton {
    /**
     * 
     */
    private static final long serialVersionUID = -32213176959709781L;
    private DAction action;

    public DiagramActionSetPanel() {
        this(null);
    }

    public DiagramActionSetPanel(DAction action) {
        this.action = action;
    }

    public void setDAction(DAction action) {
        this.action = action;
    }

    public DAction getDAction() {
        return action;
    }

    public JButton getButton() {
        return this;
    }
}
