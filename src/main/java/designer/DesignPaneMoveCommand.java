package designer;

public class DesignPaneMoveCommand extends PropertyCommand {
    DesignPaneMemento memento;
    DesignPane designPane;

    public DesignPaneMoveCommand(DesignPane designPane) {
        this.designPane = designPane;
        memento = new DesignPaneMemento(designPane);
    }

    public boolean setValue(Object object) throws InvalidPropertyException {
        try {
            DesignPaneMemento memento = (DesignPaneMemento) object;
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
        DesignPaneMoveCommand undoCommand = new DesignPaneMoveCommand(designPane);
        undoCommand.oldValue = new DesignPaneMemento(designPane);
        return undoCommand;
    }

}

class DesignPaneMemento {
    private DesignPane designPane;

    private int x;
    private int y;
    private int width;
    private int height;
    private String text;

    public DesignPaneMemento(DesignPane designPane) {
        this.designPane = designPane;

        x = designPane.getX();
        y = designPane.getY();
        width = designPane.getWidth();
        height = designPane.getHeight();

    }

    public void restore() {
        designPane.setBounds(x, y, width, height);
        designPane.repaint();
    }

}
