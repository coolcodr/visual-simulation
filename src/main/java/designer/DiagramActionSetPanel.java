package designer;

import designer.deployment.*;
import javax.swing.*;

public class DiagramActionSetPanel extends JButton
{
    private DAction action;

    public DiagramActionSetPanel( )
    {
        this(null);
    }
    public DiagramActionSetPanel( DAction action )
    {
        this.action = action;
    }
    public void setDAction ( DAction action )
    {
        this.action = action;
    }
    public DAction getDAction ()
    {
        return action;
    }
    public JButton getButton()
    {
        return (JButton) this;
    }
}