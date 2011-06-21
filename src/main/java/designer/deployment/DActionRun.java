package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DActionRun extends DAction
{
    public void actionPerformed(ActionEvent e)
    {
        mainFrame.startRun();
    }
    public String toString ()
    {
        return "Run Simultation";
    }
}

