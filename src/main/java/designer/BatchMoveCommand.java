package designer;

import java.util.Vector;

public class BatchMoveCommand extends PropertyCommand
{
    Vector mementos = new Vector();
    Vector alls = new Vector();

    public BatchMoveCommand(DesignerComponent designComponent, Vector alls)
    {
        super(designComponent);
        this.alls = alls;
        for ( int i = 0 ; i < alls.size() ; i ++ )
        {
            DesignerComponentMemento memento = new DesignerComponentMemento ( (DesignerComponent) alls.elementAt(i) );
            mementos.add(memento);
        }
    }

    public boolean setValue(Object object) throws InvalidPropertyException
    {
        try
        {
            Vector mementos = (Vector)object;
            for ( int i = 0 ; i < mementos.size() ; i ++ )
            {
                DesignerComponentMemento memento = (DesignerComponentMemento) mementos.elementAt(i);
                memento.restore();
            }
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
        return mementos;
    }

    public PropertyCommand createUndoCommand()
    {
        Vector undoMementos = new Vector();
        for ( int i = 0 ; i < alls.size() ; i ++ )
        {
            DesignerComponentMemento memento = new DesignerComponentMemento ( (DesignerComponent) alls.elementAt(i) );
            undoMementos.add(memento);
        }

        BatchMoveCommand undoCommand = new BatchMoveCommand ( designerComponent, alls );
        undoCommand.oldValue = undoMementos;
        return undoCommand;
    }

}