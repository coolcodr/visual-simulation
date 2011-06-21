package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DActionExit extends DAction
{
    public void actionPerformed(ActionEvent e)
    {
        mainFrame.setVisible(false);
    }
    public String toString ()
    {
        return "System Exit";
    }
}
