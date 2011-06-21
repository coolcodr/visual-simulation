package designer.deployment;

import java.io.Serializable;

import javax.swing.JComponent;

public class DeployComponent2 implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2497423201681814262L;

    private String componentClass;

    private JComponent jComponent;
    private DeployComponent2Propertes properties;

    public DeployComponent2(JComponent jComponent) {
        componentClass = jComponent.getClass().getSuperclass().getName();

        // System.out.println("COMPONENT CLASS: "+componentClass);
        properties = new DeployComponent2Propertes(jComponent);
    }

    public DeployComponent2(JComponent jComponent, String text) {
        componentClass = jComponent.getClass().getSuperclass().getName();

        // System.out.println("COMPONENT CLASS: "+componentClass);
        properties = new DeployComponent2Propertes(jComponent, text);
    }

    public JComponent getCompoent() {
        try {
            Class newClass = Class.forName(componentClass);
            jComponent = (JComponent) newClass.newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }

        properties.setPropertise(jComponent);
        jComponent = jComponent;
        return jComponent;
    }

    public DeployComponent2Propertes getComponentProperties() {
        return properties;
    }
}
