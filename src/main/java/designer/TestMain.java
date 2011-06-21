package designer;
import javax.swing.UIManager;

public class TestMain
{
    public TestMain()
    {

        try {

            String lnfName = "light.LightLookAndFeel";
            UIManager.setLookAndFeel(lnfName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new UIDesigner().setVisible(true);
    }

    public static void main(String[] args)
    {
        TestMain testMain1 = new TestMain();
    }

}