package designer.deployment;

import java.awt.CardLayout;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

public class CardComponent implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3467549791730757216L;

    private String componentClass;

    private JPanel jComponent;
    private CardProperties properties;
    private String id;

    private Vector deployFrames = new Vector();

    public CardComponent(JPanel cardPane, String cardID, String text) {
        componentClass = cardPane.getClass().getSuperclass().getName();
        properties = new CardProperties(cardPane, text);
        id = cardID;// cardPane.getID();
    }

    public void addDeployFrame(DeployFrame deployFrame) {
        deployFrames.add(deployFrame);
    }

    public JPanel getComponent() {
        /*
         * try { Class newClass = Class.forName(componentClass); jComponent =
         * (JComponent)newClass.newInstance(); } catch ( Exception e ) {
         * System.out.println(e); }
         */
        JPanel jPanel = new JPanel();

        properties.setPropertise(jPanel);

        jPanel.setLayout(new CardLayout());
        for (int i = 0; i < deployFrames.size(); i++) {
            DeployFrame deployFrame = (DeployFrame) deployFrames.elementAt(i);
            JPanel pane = deployFrame.getBackPanel();
            // System.out.println("X - DEPLAY FRAM ID: "+deployFrame.getID());
            // System.out.println("X - THE PANE: "+pane);
            jPanel.add(pane, deployFrame.getID());
        }
        JPanel defaultPane = new JPanel();
        jPanel.add(defaultPane, "Default");
        jComponent = jPanel;
        return jPanel;
    }

    public CardProperties getCardProperties() {
        return properties;
    }

    public JPanel getCardPane() {
        return jComponent;
    }

    public String getID() {
        return id;
    }
}
