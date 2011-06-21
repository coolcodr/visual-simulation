package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import designer.deployment.SimPropertyChoice;

public class ChangeChoiceNameEditor extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 6977721171006428405L;
    protected JCheckBox jCheckBox = new JCheckBox();
    protected JTextField jTextField = new JTextField();
    private SimPropertyChoice simChoice;

    public ChangeChoiceNameEditor(SimPropertyChoice simChoice) {
        this.simChoice = simChoice;
        JPanel jPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 4, 16);
        leftPanel.setBackground(Color.white);
        jPanel.setBounds(4, 0, 200, 16);
        jPanel.setVisible(true);
        jPanel.setLayout(new BorderLayout(2, 2));
        jPanel.setBackground(Color.white);
        add(leftPanel, BorderLayout.WEST);
        add(jPanel, BorderLayout.CENTER);
        setBorder(null);
        setLayout(new BorderLayout(2, 2));
        setBackground(Color.white);
        jPanel.add(jCheckBox, BorderLayout.WEST);
        jCheckBox.setVisible(true);
        jCheckBox.setBorder(null);
        jCheckBox.setBackground(Color.white);
        jPanel.add(jTextField, BorderLayout.CENTER);
        jTextField.setVisible(true);
        jTextField.setBorder(null);
        jCheckBox.setBounds(4, 0, 16, 16);
        jTextField.setBounds(20, 0, 200, 16);
    }

    public void setPanel(DiagramComponentSetPanel setPanel) {
        jCheckBox.addActionListener(new ChangeNameCheckListener(simChoice, setPanel));
    }

    public void setSelected(boolean selected) {
        jCheckBox.setSelected(selected);
    }

    public void setText(String text) {
        jTextField.setText(text);
    }

    public boolean getSelected() {
        return jCheckBox.isSelected();
    }

    public String getText() {
        return jTextField.getText();
    }
}

class ChangeNameDisplayCommand extends ChangeChoiceNameCommand {
    public ChangeNameDisplayCommand(DesignerComponent designComponent, SimPropertyChoice simChoice) {
        super(designComponent, simChoice);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            Boolean newValue = (Boolean) object;
            simChoice.setDisplay(newValue.booleanValue());
            ((DiagramComponentSetPanel) designerComponent).refreshComboxBoxChoice();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new InvalidPropertyException("Invalid Value");
        }
    }

    public Object getValue() {
        return new Boolean(simChoice.getDisplay());
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new ChangeNameDisplayCommand(designerComponent, simChoice);
        undoCommand.oldValue = new Boolean(simChoice.getDisplay());
        return undoCommand;
    }

}

class ChangeNameCheckListener implements ActionListener {
    private SimPropertyChoice simChoice;
    private DiagramComponentSetPanel setPanel;

    public ChangeNameCheckListener(SimPropertyChoice simChoice, DiagramComponentSetPanel setPanel) {
        this.simChoice = simChoice;
        this.setPanel = setPanel;
    }

    public void actionPerformed(ActionEvent e) {
        JCheckBox chechBox = (JCheckBox) e.getSource();

        if (chechBox.isSelected() != simChoice.getDisplay()) {
            PropertyCommand undoCommand = new ChangeNameDisplayCommand(setPanel, simChoice).createUndoCommand();
            UIDesigner.getControl().addUndoCommand(undoCommand);
        }

        simChoice.setDisplay(chechBox.isSelected());
        setPanel.refreshComboxBoxChoice();
    }
}
