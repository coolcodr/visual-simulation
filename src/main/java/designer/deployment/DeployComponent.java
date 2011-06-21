package designer.deployment;

import java.io.Serializable;

public class DeployComponent implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6633586802896855362L;
    protected InputComponentPropertise componentPropertise = null;

    public DeployComponent() {
    }

    public InputComponentPropertise getComponentProperties() {
        return componentPropertise;
    }
}
