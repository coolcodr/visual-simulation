package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DActionSave extends DAction
{
    public void actionPerformed(ActionEvent e)
    {
        mainFrame.saveData();
    }
    public String toString ()
    {
        return "Save Data";
    }
}
