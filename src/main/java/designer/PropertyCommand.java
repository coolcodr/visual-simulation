package designer;

import designer.deployment.*;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public abstract class PropertyCommand
{
    protected JComponent jComponent;
    protected DesignerComponent designerComponent;
    protected CoverComponent cover;
    protected String name;
    protected String type;

    protected Object oldValue;

    public abstract boolean setValue(Object object) throws InvalidPropertyException;
    public abstract Object getValue();
    public PropertyCommand ()
    {
    }
    public PropertyCommand(DesignerComponent jComponent)
    {
        this.designerComponent = jComponent;
        this.jComponent = (JComponent) jComponent;
    }
    public abstract PropertyCommand createUndoCommand ();
    public PropertyCommand createRedoCommand ()
    {
        return createUndoCommand();
    }
    public void redo ()
    {
        undo();
    }
    public void undo ()
    {
        try
        {
            setValue(oldValue);
        }
        catch (InvalidPropertyException ex)
        {
        }
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String toString()
    {
        return name;
    }
    public Object getObject()
    {
        return jComponent;
    }
    public JComponent getEditor()
    {
        JTextField jTextField = new JTextField(getValue().toString());
        jTextField.setBorder(null);
        return jTextField;
    }
    public void refresh()
    {
    }
}
class SetTextCommand extends PropertyCommand
{
    public SetTextCommand ( DesignerComponent jComponent )
    {
        this(jComponent, "Text", "Display");
    }
    public SetTextCommand ( DesignerComponent jComponent, String title, String group )
    {
        super(jComponent);
        name = title;
        type = group;
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetTextCommand ( designerComponent );
        undoCommand.oldValue = new String ( getValue().toString() );
        return undoCommand;
    }
    public Object getValue ()
    {
        return designerComponent.getText();
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            designerComponent.setText(object.toString());
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Invalid Value");
        }
    }

}
class SetHeightCommand extends PropertyCommand
{
    public SetHeightCommand ( DesignerComponent jComponent )
    {
        super(jComponent);
        name = "Height";
        type = "Location";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetHeightCommand ( designerComponent );
        undoCommand.oldValue = new Integer ( ((Integer)getValue()).intValue() );
        return undoCommand;
    }
    public Object getValue ()
    {
        cover = designerComponent.getCover();
        int h = cover.getSize().height;
        return new Integer(h);
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            cover = designerComponent.getCover();
            int newH = Integer.parseInt(object.toString());
            cover.changeSize2(cover.getSize().width, newH);
            cover.changeResize();
            cover.refreshResizePoint();
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class SetWidthCommand extends PropertyCommand
{
    public SetWidthCommand ( DesignerComponent jComponent )
    {
        super(jComponent);
        name = "Width";
        type = "Location";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetWidthCommand ( designerComponent );
        undoCommand.oldValue = new Integer ( ((Integer)getValue()).intValue() );
        return undoCommand;
    }
    public Object getValue ()
    {
        cover = designerComponent.getCover();
        int w = cover.getSize().width;
        return new Integer(w);
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            cover = designerComponent.getCover();
            int newW = Integer.parseInt(object.toString());
            cover.changeSize2(newW, cover.getSize().height);
            cover.changeResize();
            cover.refreshResizePoint();
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class SetYCommand extends PropertyCommand
{
    public SetYCommand ( DesignerComponent jComponent )
    {
        super(jComponent);
        name = "Top";
        type = "Location";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetYCommand ( designerComponent );
        undoCommand.oldValue = new Integer ( ((Integer)getValue()).intValue() );
        return undoCommand;
    }
    public Object getValue ()
    {
        cover = designerComponent.getCover();
        int y = cover.getLocation().y;
        return new Integer(y);
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            cover = designerComponent.getCover();
            int newY = Integer.parseInt(object.toString());
            cover.moveXY2(cover.getLocation().x, newY);
            cover.setXY();
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Not a Integer Value");
        }
    }
}

class SetXCommand extends PropertyCommand
{
	public SetXCommand ( DesignerComponent jComponent )
	{
		super(jComponent);
		name = "Left";
        type = "Location";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetXCommand ( designerComponent );
        undoCommand.oldValue = new Integer ( ((Integer)getValue()).intValue() );
        return undoCommand;
    }
	public Object getValue ()
    {
        cover = designerComponent.getCover();
		int x = cover.getLocation().x;
		return new Integer(x);
	}
	public boolean setValue ( Object object ) throws InvalidPropertyException
	{
		try
        {
            cover = designerComponent.getCover();
			int newX = Integer.parseInt(object.toString());
			cover.moveXY2(newX, cover.getLocation().y);
            cover.setXY();
			return true;
		}
		catch ( Exception ex )
		{
			throw new InvalidPropertyException("Not a Integer Value");
		}
	}
}

class InvalidPropertyException extends Exception
{
    public InvalidPropertyException()
    {
        super();
    }
    public InvalidPropertyException(String message)
    {
        super(message);
    }
}