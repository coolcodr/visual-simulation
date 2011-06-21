package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DActionClose extends DAction
{
    public void actionPerformed(ActionEvent e)
    {
        dialog.setVisible(false);
    }
    public String toString ()
    {
        return "Close Dialog";
    }
}
