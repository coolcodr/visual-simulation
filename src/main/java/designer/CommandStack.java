package designer;

import java.util.Vector;

public class CommandStack
{
    private Vector undo = new Vector();
    private Vector redo = new Vector();

    public CommandStack()
    {
    }

    public void undo()
    {
        if (undo.size() > 0)
        {
            PropertyCommand undoCommand = (PropertyCommand) undo.lastElement();
            undo.remove(undoCommand);
            addRedoCommand(undoCommand.createRedoCommand());
            undoCommand.undo();
        }
    }

    public void redo()
    {
        if (redo.size() > 0)
        {
            PropertyCommand redoCommand = (PropertyCommand) redo.lastElement();
            redo.remove(redoCommand);
            addUndoCommand(redoCommand.createUndoCommand(), false );
            redoCommand.redo();
        }
    }
    public int getUndoCommandCount ()
    {
        return undo.size();
    }
    public int getRedoCommandCount ()
    {
        return redo.size();
    }
    private void addRedoCommand ( PropertyCommand command )
    {
        if ( command != null )
            redo.add(command);
    }
    public void addUndoCommand(PropertyCommand command)
    {
        addUndoCommand(command, true);
        System.out.println("UNDO SIZE: "+undo.size());
    }
    public void addUndoCommand(PropertyCommand command, boolean clearRedo)
    {
        if (command != null)
            undo.add(command);
        if (clearRedo)
            clearRedo();
    }
    public void clearUndo ()
    {
        undo.clear();
    }
    public void clearRedo ()
    {
        redo.clear();
    }

}