package designer;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import designer.deployment.DAction;

class SelectActionCommand extends PropertyCommand {
    public SelectActionCommand(DesignerComponent jComponent) {
        super(jComponent);
        name = "Action";
        type = "Function";
    }

    public Object getValue() {
        return ((CButton) designerComponent).getDAction();
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new SelectActionCommand(designerComponent);
        undoCommand.oldValue = getValue();
        return undoCommand;
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            ((CButton) designerComponent).setDAction((DAction) object);
            return true;
        } catch (Exception ex) {
            throw new InvalidPropertyException("Invalid Value");
        }
    }

    public JComponent getEditor() {
        JComboBox jComboBox = new JComboBox(UIDesigner.getControl().getActionChooser());
        jComboBox.setSelectedItem(getValue());
        jComboBox.setBorder(null);
        return jComboBox;
    }

    public void refresh() {
        cover = designerComponent.getCover();
        cover.getFocus(true);
    }
}
