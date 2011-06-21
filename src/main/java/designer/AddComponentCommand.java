package designer;

import java.awt.Container;

import javax.swing.JComponent;

public class AddComponentCommand extends PropertyCommand {
    DesignerComponentMemento memento;
    JComponent parent = null;

    public AddComponentCommand(DesignerComponent designComponent) {
        super(designComponent);
        memento = new DesignerComponentMemento(designComponent);
        Container container = ((JComponent) designComponent).getParent();
        if (container != null) {
            parent = (JComponent) container;
        }
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            DesignerComponentMemento memento = (DesignerComponentMemento) object;
            memento.restore();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new InvalidPropertyException("Invalid Value");
        }
    }

    public Object getValue() {
        return memento;
    }

    public PropertyCommand createUndoCommand() {
        ReshapCommand undoCommand = new ReshapCommand(designerComponent);
        undoCommand.oldValue = new DesignerComponentMemento(designerComponent);
        return undoCommand;
    }

}
