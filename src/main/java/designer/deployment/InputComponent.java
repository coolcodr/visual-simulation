package designer.deployment;

import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JDialog;
import javax.swing.ComboBoxModel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.Rectangle;

public class InputComponent implements Serializable
{
    public static final int LABEL = 0;
    public static final int TEXT_FIELD = 1;
    public static final int COMBO_BOX = 2;
    public static final int BUTTON = 3;

    private InputComponentPropertise panelComponent = null;
    private InputComponentPropertise[] component = new InputComponentPropertise[4];

    private JPanel panel;

    private JLabel label;
    private JTextField textField;
    private JComboBox comboBox;
    private JButton button;

    private MainFrame mainFrame;
    private CardComponent cardComponent;
    private JPanel cardPane;

    private String name;
    private String setMode;

    private Object defaultValue;

    private GetDataInput getMode;

    public InputComponent( JLayeredPane jLayeredPane, String name, String setMode, GetDataInput getMode, Object defaultValue )
    {
        this.panelComponent = new InputComponentPropertise ( jLayeredPane );
        this.name = name;
        this.setMode = setMode;
        this.getMode = getMode;
        this.defaultValue = defaultValue;
    }/*
    public void addComponent ( JComponent jComponent, int type )
    {
        component[type] = new InputComponentPropertise ( jComponent );
    }*/
    public void addComponent ( JComponent jComponent, String text,  int type )
    {
        component[type] = new InputComponentPropertise ( jComponent, text );
    }
    public void addComponent ( JComponent jComponent, Object[] objects,  int type )
    {
        component[type] = new InputComponentPropertise ( jComponent, objects );
    }
    public int getNumberOfComponent ()
    {
        return component.length;
    }
    public void setLabel ( JLabel jComponent )
    {
        component[LABEL].setPropertise(jComponent);
        label = jComponent;
        label.setText(component[LABEL].getText());
    }
    public void setTextField ( JTextField jComponent )
    {
        component[TEXT_FIELD].setPropertise(jComponent);
        textField = jComponent;
        textField.setText(component[TEXT_FIELD].getText());
    }
    public void setComboBox ( JComboBox jComponent )
    {
        component[COMBO_BOX].setPropertise(jComponent);
        comboBox = jComponent;
        comboBox.setSelectedItem(this.defaultValue);
    }
    public Object getDefaultValue ()
    {
        return defaultValue;
    }
    public void setButton ( JButton jComponent )
    {
        component[BUTTON].setPropertise(jComponent);
        //dActionComponent = new DeployActionComponent(jComponent, new DActionShow() );
        //dActionComponent.setControl(mainFrame);
        button = jComponent;//dActionComponent.getButton(jComponent);
        button.setText(component[BUTTON].getText());
    }
    public String getText ( int i )
    {
        return component[i].getText();
    }
    public Object[] getModel( int i )
    {
        return component[i].getModel();
    }
    public String getName()
    {
        return name;
    }
    public String getSetMode ()
    {
        return setMode;
    }
    public JPanel getPanel ( JPanel jPanel )
    {
        panelComponent.setPropertise(jPanel);
        panel = jPanel;

        panel.setLayout(null);

        panel.add(label);
        panel.add(textField);
        panel.add(comboBox);
        panel.add(button);

        this.setActionListener();

        return panel;
    }
    public boolean isVisible ()
    {
        if ( panel != null )
            return panel.isVisible();
        else
            return false;
    }
    public void setFrame ( MainFrame mainFrame )
    {
        this.mainFrame = mainFrame;
    }
    public void setCardPane()
    {
        if ( cardComponent != null )
            setCardPane( cardComponent.getCardPane() );
    }

    public void setCardPane ( JPanel cardPane )
    {
        this.cardPane = cardPane;
        String frameID = ((SimPropertyChoice)comboBox.getSelectedItem()).getFrameID();
        if ( frameID.equalsIgnoreCase("-1") )
            frameID = "Default";

        ((CardLayout)cardPane.getLayout()).show(cardPane, frameID);
    }
    public void setCardComponent ( CardComponent cardComponent )
    {
        this.cardComponent = cardComponent;
    }
    public CardComponent getCardComponent ()
    {
        return cardComponent;
    }
    public InputComponentPropertise getComponentProperties ()
    {
        return panelComponent;
    }
    private void setComboBoxValue ( String text )
    {
        for ( int i = 0 ; i < comboBox.getItemCount() ; i ++ )
        {
            if ( comboBox.getItemAt(i).toString().equalsIgnoreCase(text) )
            {
                comboBox.setSelectedIndex(i);
                return;
            }
        }
    }
    public void setInputValue ( String text )
    {
        if ( textField.isVisible() )
            textField.setText(text);
        else if ( comboBox.isVisible() )
            setComboBoxValue(text);
    }
    public Object getInput () throws InvalidDataException
    {
        if ( textField.isVisible() )
            return getMode.getInput(textField.getText(), this);
        else if ( comboBox.isVisible() )
            return getMode.getInput(((SimPropertyChoice)comboBox.getSelectedItem()).getValue(), this);
        else
            return null;
    }
    public String getDiaplayValue ()
    {
        if ( textField.isVisible() )
            return textField.getText();
        else if (comboBox.isVisible())
            return comboBox.getSelectedItem().toString();
        else
            return "";
    }
    private void setActionListener()
    {
        if ( button != null && button.isVisible() )
        {
            button.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    button_actionPerformed(e);
                }
            });
        }
        //System.out.println("ADD LISTENER HERE ");
        if ( cardComponent != null && comboBox.isVisible() )
        {
            //System.out.println("LISTENER ADDED ");
            comboBox.addItemListener(new java.awt.event.ItemListener()
            {
                public void itemStateChanged(ItemEvent e)
                {
                    comboBox_itemStateChanged(e);
                }
            });
        }
    }

    void comboBox_itemStateChanged( ItemEvent e )
    {
        if ( cardPane == null )
            setCardPane(cardComponent.getCardPane());

        //System.out.println("TRIGGERED");
        SimPropertyChoice choice = (SimPropertyChoice) e.getItem();
        System.out.println("SELECTED: " + choice.getFrameID());
        //System.out.println("CARDPANE COMPONENT COUNT: " + cardPane.getComponentCount());
        CardLayout cardLayout = (CardLayout) cardPane.getLayout();
        if ( choice.getFrameID().equals("-1") )
        {
            //cardPane.showDefault();
            cardLayout.show(cardPane, "Default");
            System.out.println("No visible Pane");
            // return;
        }
        else
        {
            try
            {
                cardLayout.show(cardPane, choice.getFrameID());
                System.out.println("PANE SHOWN");
            }
            catch (Exception ex)
            {
                cardLayout.show(cardPane, "Default");
            }
        }
    }

    void button_actionPerformed(ActionEvent e)
    {
        if ( mainFrame != null)
        {
            Object object = mainFrame.frame.get(((SimPropertyChoice)comboBox.getSelectedItem()).getFrameID());
            if ( object !=null )
            {
                JDialog dialog = (JDialog)object;
                Rectangle bounds = dialog.getOwner().getBounds();
                dialog.setLocation((int)(bounds.getX()+(bounds.getWidth()-dialog.getWidth())/2), (int)(bounds.getY()+(bounds.getHeight()-dialog.getHeight())/2));
                //System.out.println(bounds);
                dialog.setVisible(true);
            }

            System.out.println(name+"|"+setMode+"|"+comboBox.getSelectedItem());
        }
        else //Test
            ;//System.out.println(this.getInput());
    }
}
