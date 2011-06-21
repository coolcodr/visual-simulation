package designer.deployment;

import javax.swing.JButton;
import javax.swing.JDialog;

public class DeployActionComponent extends DeployComponent {
    /**
     * 
     */
    private static final long serialVersionUID = 1261490136950416774L;
    private String componentClass;
    private JButton button;
    private String text;
    private DAction action;

    public DeployActionComponent(JButton jButton, DAction action) {
        componentClass = jButton.getClass().getSuperclass().getName();
        componentPropertise = new InputComponentPropertise(jButton);
        text = jButton.getText();
        this.action = action;
    }

    public void setControl(MainControlHandler mainControl) {
        if (action != null) {
            action.setMainControl(mainControl);
        }
    }

    public void setControl(MainFrame mainFrame) {
        if (action != null) {
            action.setMainFrame(mainFrame);
        }
    }

    public void setDialog(JDialog jDialog) {
        if (action != null) {
            action.setJDialog(jDialog);
        }
    }

    public DAction getDAction() {
        return action;
    }

    /*
     * public void setActionInput( InputComponent inputComponent ) {
     * action.setInputComponent(inputComponent); }
     */
    public String getText() {
        return text;
    }

    public JButton getButton() {
        try {
            Class newClass = Class.forName(componentClass);
            button = (JButton) newClass.newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }

        componentPropertise.setPropertise(button);

        button.addActionListener(action);
        button.setText(text);
        button = button;
        return button;
    }
    /*
     * public JButton getButton ( JButton jButton ) {
     * componentPropertise.setPropertise(jButton); button = jButton;
     * 
     * button.setText(text);
     * 
     * button.addActionListener( action ); return button; }
     */
    /*
     * public JTextField getTextField ( JTextField jTextField ) {
     * textFieldPropertise.setPropertise(jTextField); textField = jTextField;
     * 
     * return jTextField; }
     */
}
