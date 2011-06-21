package designer.report;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import javax.swing.UIManager;

public class PrintMain
{
    public static void main(String args[])
    {

        try
        {

            String lnfName = "light.LightLookAndFeel";
            UIManager.setLookAndFeel(lnfName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        PrintEditor printEditor = new PrintEditor();
        printEditor.setVisible(true);
    }
}