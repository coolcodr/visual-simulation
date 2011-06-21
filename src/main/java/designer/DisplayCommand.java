package designer;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;

class SetForegroundCommand extends PropertyCommand
{
    public SetForegroundCommand ( DesignerComponent jComponent )
    {
       super(jComponent);
       name = "Text Color";
       type = "Display";
    }

    public Object getValue ()
    {
        return jComponent.getForeground();
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            Color color = (Color) object;
            jComponent.setForeground(color);
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Invalid Value");
        }
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetForegroundCommand( designerComponent );
        Color color = (Color) getValue ();
        undoCommand.oldValue = new Color ( color.getRGB() );
        return undoCommand;
    }
    public JComponent getEditor()
    {
        SelectColorEditor editor = new SelectColorEditor(this);
        return editor;
    }

}

class SetBackgroundCommand extends PropertyCommand
{
    public SetBackgroundCommand ( DesignerComponent jComponent )
    {
       super(jComponent);
       name = "Background";
       type = "Display";
    }

    public Object getValue ()
    {
        return jComponent.getBackground();
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            Color color = (Color) object;
            jComponent.setBackground(color);
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Invalid Value");
        }
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetBackgroundCommand( designerComponent );
        Color color = (Color) getValue ();
        undoCommand.oldValue = new Color ( color.getRGB() );
        return undoCommand;
    }
    public JComponent getEditor()
    {
        SelectColorEditor editor = new SelectColorEditor(this);
        return editor;
    }

}

class SetFontCommand extends PropertyCommand
{
    public SetFontCommand ( DesignerComponent jComponent )
    {
       super(jComponent);
       name = "Text Font";
       type = "Display";
    }

    public Object getValue ()
    {
        return jComponent.getFont();
    }
    public boolean setValue ( Object object ) throws InvalidPropertyException
    {
        try
        {
            Font font = (Font) object;
            jComponent.setFont(font);
            return true;
        }
        catch ( Exception ex )
        {
            throw new InvalidPropertyException("Invalid Value");
        }
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetFontCommand( designerComponent );
        Font font = (Font) getValue ();
        undoCommand.oldValue = new Font( font.getName(), font.getStyle(), font.getSize() );
        return undoCommand;
    }
    public JComponent getEditor()
    {
        SelectFontEditor editor = new SelectFontEditor(this);
        return editor;
    }

}

