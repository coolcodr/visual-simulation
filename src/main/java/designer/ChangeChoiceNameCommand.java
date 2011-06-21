package designer;

import designer.deployment.*;

import javax.swing.JComponent;

public class ChangeChoiceNameCommand extends PropertyCommand
{
    protected SimPropertyChoice simChoice;

    public ChangeChoiceNameCommand( DesignerComponent jComponent, SimPropertyChoice simChoice )
    {
        super(jComponent);
        this.simChoice = simChoice;
        name = simChoice.getValue();
        type = "Display";
    }
    public Object getValue()
    {
        return simChoice;
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new ChangeChoiceNameCommand ( designerComponent, simChoice );
        undoCommand.oldValue = new String ( getValue().toString() );
        return undoCommand;
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            //SimPropertyChoice newValue = (SimProper
            simChoice.setDisplayName(object.toString());
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Invalid Value");
        }
    }
    public JComponent getEditor ()
    {
        ChangeChoiceNameEditor editor = new ChangeChoiceNameEditor(simChoice);
        editor.setText(getValue().toString());
        editor.setSelected(simChoice.getDisplay());
        editor.setPanel((DiagramComponentSetPanel)jComponent.getParent());
        return editor;
    }
    public void refresh ()
    {
        ((DiagramComponentSetPanel)designerComponent).refreshComboxBoxChoice();
    }
}