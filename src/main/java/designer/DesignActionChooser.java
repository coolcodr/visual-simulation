package designer;

import designer.deployment.*;

import javax.swing.JComboBox;

public class DesignActionChooser extends JComboBox
{
    /* private String[] choice =
         {
         "Close Dialog", "System Exit", "Start Simultation", "Stop Simulation"};*/

    public DesignActionChooser()
    {
        super(
            new
            String[]
            {"Close Dialog", "System Exit", "Start Simultation", "Stop Simulation"});
    }

    public Object getItemAt(int i)
    {
        switch (i)
        {
            case (0):
                return new DActionClose();
            case (1):
                return new DActionExit();
            case (2):
                return new DActionRun();
        }
        return null;
    }

}