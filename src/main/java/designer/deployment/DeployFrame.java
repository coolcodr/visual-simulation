package designer.deployment;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Container;
import java.util.Vector;

public class DeployFrame extends DeployComponent
{
    private String id;
    private String title;
    private String name;
    private JDialog frame;

    private Vector inputObject = new Vector(); //A link only, does not cause large size of storage/A link only, does not cause large size of storage
    private Vector actionObject = new Vector();//A link only, does not cause large size of storage
    private Vector otherObject = new Vector();
    private Vector deployFrame = new Vector(); //Recursive object
    private Vector cardObject = new Vector();

    private Container contentPane;
    private JPanel backPanel;

    public DeployFrame( JComponent jPanel, String title, String id , String name)
    {
        componentPropertise = new InputComponentPropertise ( jPanel );
        this.title = title;
        this.id = id;
        this.name = name;
    }
    /*
    public DeployFrame( JPanel pane, String title, String id, String name )
    {
        componentPropertise = new InputComponentPropertise ( pane );
        this.title = title;
        this.id = id;
        this.name = name;
    }*/
    public void addInputComponent ( InputComponent inputComponent )
    {
        inputObject.add(inputComponent);
    }
    public void addFrameComponent ( DeployFrame frameComponent )
    {
        deployFrame.add(frameComponent);
    }
    public void addCardComponent ( CardComponent cardComponent )
    {
        cardObject.add(cardComponent);
    }

    public void addActionComponent ( DeployActionComponent actionComponent )
    {
        actionObject.add(actionComponent);
    }
    public void addOtherComponent ( DeployComponent2 deployComponent2 )
    {
        otherObject.add(deployComponent2);
    }
    /*
    public void setComponent ( Vector input, Vector action, Vector other )
    {
        inputObject = input;
        actionObject = action;
        otherObject = other;
    }*/
    public Vector getInputComponent ()
    {
        return inputObject;
    }
    public Vector getCardComponent()
    {
        return cardObject;
    }

    public Vector getActionObject ()
    {
        return actionObject;
    }
    public Vector getFrameObject()
    {
        return deployFrame;
    }
    public Vector getOtherObject()
    {
        return otherObject;
    }
    public String getID ()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public String getName ()
    {
        return name;
    }
    public JPanel getBackPanel()
    {
        backPanel = new JPanel();
        backPanel.setVisible(true);
        backPanel.add(contentPane);
        backPanel.setLayout(null);

        return backPanel;
    }
    public Container getContentPane ()
    {
        return contentPane;
    }
    public JPanel getPane ( JPanel jPanel )
    {
        componentPropertise.setPropertise(jPanel);
        jPanel.setLayout(null);

        Border border = BorderFactory.createEtchedBorder();
        Border titledBorder = new TitledBorder(border, title);
        jPanel.setBorder(titledBorder);

        contentPane = jPanel;
        //System.out.println("PANE CONTAINER: "+contentPane);
        JPanel backPanel = new JPanel ();
        backPanel.setVisible(true);
        backPanel.add(jPanel);
        backPanel.setLayout(null);

        //this.backPanel = backPanel;
        return jPanel;
    }
    public JDialog getDialog ()
    {
        return frame;
    }
    public JDialog getFrame ( JDialog jFrame )
    {
        JPanel panel = new JPanel();
        componentPropertise.setPropertise(panel);
        panel.setLayout(null);

        frame = jFrame;
        frame.setContentPane(panel);
        frame.setTitle(title);
        //int titleHeight = Integer.parseInt((java.awt.Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.captionHeight")).toString());
        // int borderWidth =Integer.parseInt((java.awt.Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.sizingBorderWidth")).toString())
        //System.out.println(Integer.parseInt((java.awt.Toolkit.getDefaultToolkit().getDesktopProperty("win.frame.sizingBorderWidth")).toString()));
        frame.setSize(panel.getWidth() + 10, panel.getHeight() + 30);

        contentPane = panel;
        //System.out.println("FRAME CONTAINER: "+contentPane);
        return frame;
    }
    public void setVisible ( boolean b )
    {
        frame.setVisible(true);
    }

}