package designer;

public class ReshapCommand extends PropertyCommand
{
    DesignerComponentMemento memento;
    public ReshapCommand(DesignerComponent designComponent)
    {
        super(designComponent);
        memento = new DesignerComponentMemento ( designComponent );
    }

    public boolean setValue(Object object) throws InvalidPropertyException
    {
        try
        {
            DesignerComponentMemento memento = (DesignerComponentMemento) object;
            memento.restore();
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            throw new InvalidPropertyException("Invalid Value");
        }
    }

    public Object getValue()
    {
        return memento;
    }

    public PropertyCommand createUndoCommand()
    {
        ReshapCommand undoCommand = new ReshapCommand ( designerComponent );
        undoCommand.oldValue = new DesignerComponentMemento ( designerComponent );
        return undoCommand;
    }

}