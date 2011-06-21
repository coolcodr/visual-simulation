package designer;

import designer.deployment.*;

import javax.swing.UIManager;

public class TestMain3
{
    public TestMain3()
    {
        try {

            String lnfName = "light.LightLookAndFeel";
            UIManager.setLookAndFeel(lnfName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        new CodeViewer().setVisible(true);
    }

    public static void main(String[] args)
    {
        TestMain3 testMain31 = new TestMain3();
    }

}