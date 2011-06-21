package designer;

public class DesignCommand {
    public DesignCommand() {
    }

}

class PaneSetYCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetYCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Top";
        type = "Location";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetYCommand(designPane);
        undoCommand.oldValue = new Integer(((Integer) getValue()).intValue());
        return undoCommand;
    }

    public Object getValue() {
        int y = designPane.getBounds().y;
        return new Integer(y);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            int newY = Integer.parseInt(object.toString());
            designPane.setBounds(designPane.getBounds().x, newY, designPane.getBounds().width, designPane.getBounds().height);
            return true;
        } catch (Exception ex) {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class PaneSetXCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetXCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Left";
        type = "Location";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetXCommand(designPane);
        undoCommand.oldValue = new Integer(((Integer) getValue()).intValue());
        return undoCommand;
    }

    public Object getValue() {
        int x = designPane.getBounds().x;
        return new Integer(x);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            int newX = Integer.parseInt(object.toString());
            designPane.setBounds(newX, designPane.getBounds().y, designPane.getBounds().width, designPane.getBounds().height);
            return true;
        } catch (Exception ex) {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class PaneSetHeightCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetHeightCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Height";
        type = "Location";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetHeightCommand(designPane);
        undoCommand.oldValue = new Integer(((Integer) getValue()).intValue());
        return undoCommand;
    }

    public Object getValue() {
        int h = designPane.getSize().height;
        return new Integer(h);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            int newH = Integer.parseInt(object.toString());
            designPane.setSize(designPane.getWidth(), newH);
            return true;
        } catch (Exception ex) {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class PaneSetWidthCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetWidthCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Width";
        type = "Location";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetWidthCommand(designPane);
        undoCommand.oldValue = new Integer(((Integer) getValue()).intValue());
        return undoCommand;
    }

    public Object getValue() {
        int w = designPane.getSize().width;
        return new Integer(w);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            int newW = Integer.parseInt(object.toString());
            designPane.setSize(newW, designPane.getHeight());
            return true;
        } catch (Exception ex) {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class PaneSetNameCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetNameCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Frame ID";
        type = "Name";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetNameCommand(designPane);
        undoCommand.oldValue = new String(getValue().toString());
        return undoCommand;
    }

    public Object getValue() {
        return designPane.getFrameName();
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            designPane.setFrameName(object.toString());
            UIDesigner.workPaneTab.updateUI();
            UIDesigner.mainWindow.getFrameList().updateUI();

            designPane.selectThis(designPane.getUpperPane().getProperties());
            designPane.getUpperPane().requestFocus();
            designPane.showPaneResize(true);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
            // throw new InvalidPropertyException("Invalid Value");
        }
    }

    public void refresh() {
        System.out.println("REFRESH!!");
    }
}

class PaneSetTextCommand extends PropertyCommand {
    private DesignPane designPane;

    public PaneSetTextCommand(DesignPane jComponent) {
        super(null);
        designPane = jComponent;
        name = "Frame Title";
        type = "Display";
    }

    public PropertyCommand createUndoCommand() {
        PropertyCommand undoCommand = new PaneSetTextCommand(designPane);
        undoCommand.oldValue = new String(getValue().toString());
        return undoCommand;
    }

    public Object getValue() {
        return designPane.getTitle();
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        // try
        // {
        designPane.setTitle(object.toString());
        designPane.refreshTitle();
        return true;
        // }
        // catch ( Exception ex )
        // {
        // throw new InvalidPropertyException("Invalid Value");
        // }
    }
}
