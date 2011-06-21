package designer.deployment;

import java.io.Serializable;
import java.awt.*;
import javax.swing.*;
import java.lang.reflect.*;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.Vector;

public class CardComponent implements Serializable
{
    private String componentClass;

    private JPanel jComponent;
    private CardProperties properties;
    private String id;

    private Vector deployFrames = new Vector();

    public CardComponent ( JPanel cardPane, String cardID, String text )
    {
        componentClass = cardPane.getClass().getSuperclass().getName();
        properties = new CardProperties (cardPane, text);
        id = cardID;//cardPane.getID();
    }
    public void addDeployFrame ( DeployFrame deployFrame )
    {
        deployFrames.add(deployFrame);
    }
    public JPanel getComponent ()
    {
    /*
           try {
               Class newClass = Class.forName(componentClass);
               jComponent = (JComponent)newClass.newInstance();
           } catch ( Exception e ) { System.out.println(e); }*/
        JPanel jPanel = new JPanel();

         properties.setPropertise(jPanel);

         jPanel.setLayout(new CardLayout());
         for (int i = 0; i < deployFrames.size(); i++)
         {
            DeployFrame deployFrame = (DeployFrame)deployFrames.elementAt(i);
            JPanel pane = (JPanel)deployFrame.getBackPanel();
            //System.out.println("X - DEPLAY FRAM ID: "+deployFrame.getID());
            //System.out.println("X - THE PANE: "+pane);
            jPanel.add(pane, deployFrame.getID());
        }
        JPanel defaultPane = new JPanel();
        jPanel.add(defaultPane, "Default");
        this.jComponent = jPanel;
        return jPanel;
    }
    public CardProperties getCardProperties ()
    {
        return properties;
    }
    public JPanel getCardPane ()
    {
        return jComponent;
    }
    public String getID()
    {
        return id;
    }
}