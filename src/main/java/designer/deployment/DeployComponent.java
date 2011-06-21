package designer.deployment;

import java.io.Serializable;
import javax.swing.JComponent;

public class DeployComponent implements Serializable
{
    protected InputComponentPropertise componentPropertise = null;

    public DeployComponent()
    {
    }

    public InputComponentPropertise getComponentProperties ()
    {
        return componentPropertise;
    }
}