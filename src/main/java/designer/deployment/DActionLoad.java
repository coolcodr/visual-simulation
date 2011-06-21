package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DActionLoad extends DAction
{
    public void actionPerformed(ActionEvent e)
    {
        mainFrame.loadData();
    }
    public String toString ()
    {
        return "Load Data";
    }
}
