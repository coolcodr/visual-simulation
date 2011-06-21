package designer;

import diagram.*;

import designer.deployment.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.tree.*;
import java.awt.event.*;

public class DiagramComponentSetPanel extends JLayeredPane implements DesignerComponent
{
    private String name;
    //private String setMode;
    private Object defaultValue;
    private boolean autoResize;

    private Property property;
    private GetDataInput getMode;

    private Vector propertise;

    private CoverInternalComponent jLabelCover;
    private CoverInternalComponent jTextFieldCover;
    private CoverInternalComponent jComboBoxCover;
    private CoverInternalComponent jButtonCover;

    private CLabel jLabel;
    private CTextField jTextField;
    private CComboBox jComboBox;
    private CButton jButton;

    private Vector DiagramComponentSetPanel = new Vector();
    private Object internalValueNode = null;

    private CoverComponent cover;
    private CoverControl coverControl;

    private CardPane cardPane;
    private ItemListener cardListener;

    private ElementProperties properties;

    private String savedName;

    public DiagramComponentSetPanel(Property property)
    {
        this("", property);
    }

    public void setProperties(ElementProperties properties)
    {
        this.properties = properties;
    }

    public ElementProperties getProperties()
    {
        return properties;
    }
    public DiagramComponentSetPanel ( String name, Property property)
    {
        init( name, property);
    }
    public void init (String name, Property property)
    {
        propertise = new Vector();

        this.savedName = name;
        //This is the unique variable name generated, SHOULD BE the same as generte code ( see DiagramElementSource for how this work )
        this.name = name;

        //The property this set pane related for
        this.property = property;

        //Main 3 component, use 2 only//4
        jLabel = new CLabel();
        jLabel.setText(property.getName() + ":");
        jTextField = new CTextField();
        jComboBox = new CComboBox();
        jButton = new CButton();
        jButton.setText("...");

        jButton.addActionListener(new SetPanelButtonListener(this, jComboBox));
        //defaultValue = property.getValue().toString();
        //System.out.println("DEFAULT VALUE 2: " + defaultValue );
        //System.out.println(">>>>>"+name);
        this.setBounds(0, 0, 280, 32);
        this.setLayout(null);
        //this.setOpaque(true);

        this.setLayer(jButton, JLayeredPane.DEFAULT_LAYER.intValue());
        this.setLayer(jTextField, JLayeredPane.DEFAULT_LAYER.intValue());
        this.setLayer(jComboBox, JLayeredPane.DEFAULT_LAYER.intValue());
        this.setLayer(jLabel, JLayeredPane.DEFAULT_LAYER.intValue());
        this.add(jLabel);

        if (!isPrimitive(property.getType()))
        {
            String value = property.getValue().getClass().getName();
            defaultValue = value.substring(value.lastIndexOf(".") + 1); //This is wrong
            jButton.setVisible(true);
            this.add(jButton);
			this.add(jComboBox);
            //setComboBoxObject(); //Add later when set the node to this set pane
            //The node will never set //13 feb 2003 //?
        }
        else
        {
            defaultValue = property.getValue();
            jTextField.setText(property.getValue().toString());
            jButton.setVisible(false);
            this.add(jTextField);
        }

        //System.out.println("DEFAULT VALUE 2: " + defaultValue);

        //Greate a object that get Double, Integer or String
        setGetMode(property.getType());

        //Default size
        //this.setPreferredSize(new Dimension(260,32));


        //Only jLabel must visible
        jLabel.setVisible(true);



        //Cover control for designer
        jLabelCover = new CoverInternalComponent(jLabel, 4, true, this);
        jLabelCover.setImageIcon("designer/images/label16.gif");
        jTextFieldCover = new CoverInternalComponent(jTextField, 4, true, this);
        jTextFieldCover.setImageIcon("designer/images/text16.gif");
        jComboBoxCover = new CoverInternalComponent(jComboBox, 4, true, this);
        jComboBoxCover.setImageIcon("designer/images/ombo16.gif");
        jButtonCover = new CoverInternalComponent(jButton, 4, true, this);
        jButtonCover.setImageIcon("designer/images/button16.gif");

        //Cover add to upper layer
        this.setLayer(jLabelCover, JLayeredPane.DRAG_LAYER.intValue());
        this.add(jLabelCover);
        jLabelCover.setVisible(true);
        jLabelCover.setCoverControl(CoverControl.createNormalEditTextControl(jLabelCover));
        jLabel.setCoverLink(jLabelCover);

        //jLabelCover.removeListener();

        this.setLayer(jTextFieldCover, JLayeredPane.DRAG_LAYER.intValue());
        this.add(jTextFieldCover);
        jTextFieldCover.setVisible(true);
        jTextFieldCover.setDefaultCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        jTextField.addMouseListener(CoverControl.createInvisibleControl(jTextFieldCover));
        jTextField.setCoverLink(jTextFieldCover);
        //jTextFieldCover.setCoverControl(CoverControl.createNormalControl(jTextFieldCover));
        //jTextFieldCover.removeListener();

        this.setLayer(jComboBoxCover, JLayeredPane.DRAG_LAYER.intValue());
        this.add(jComboBoxCover);
        jComboBoxCover.setVisible(true);
        CoverControl comboBoxControl = CoverControl.createInvisibleControl(jComboBoxCover);
        jComboBox.getComponent(0).addMouseListener(comboBoxControl);
        jComboBox.addMouseListener(comboBoxControl);
        jComboBox.getComponent(1).addMouseListener(comboBoxControl);

		jComboBox.addItemListener(new SetDefaultListener(this));
        jComboBox.setCoverLink(jComboBoxCover);
        //jComboBoxCover.setCoverControl(CoverControl.createNormalControl(jComboBoxCover));
        //jComboBoxCover.removeListener();

        this.setLayer(jButtonCover, JLayeredPane.DRAG_LAYER.intValue());
        this.add(jButtonCover);
        jButtonCover.setVisible(true);
        jButton.addMouseListener(CoverControl.createInvisibleControl(jButtonCover));
        jButton.setCoverLink(jButtonCover);
        //jButtonCover.setCoverControl(CoverControl.createNormalEditTextControl(jButtonCover));
        //jButtonCover.removeListener();

        autoResize = true;
        autoResize();

        this.setVisible(true);
        this.repaint();

        //temp
        this.setVisible(false);

        setProperties(PropertiesFactory.createSetPaneProperties(this));
    }

    public void setAutoResize(boolean b)
    {
        autoResize = b;
    }

    public void addPaneToCardPane(DesignPane designPane)
    {
        System.out.println("CARD PANE: " + cardPane);
        if (cardPane != null)
            cardPane.addPane(designPane, true);

    }

    public void initCardPaneContents()
    {

    }
	/*
	public void refreshCardPane ()
	{
		cardLayout.show(cardPane, ((SimPropertyChoice)jComboBox.getSelectedItem()).getFrameID());
	}
	*/

    public void resetCardPaneContents(boolean autoResize)
    {
        if (cardPane == null)
            return;

        cardPane.setPanes(new Vector());

        for (int i = 0; i < jComboBox.getItemCount(); i++)
        {
            SimPropertyChoice choice = (SimPropertyChoice) jComboBox.getItemAt(i);
            String id = choice.getFrameID();
            DesignPane designPane;
            if (!id.equalsIgnoreCase("-1"))
            {
                designPane = DesignerControl.getDesignPane(id);
                if (designPane != null)
                    cardPane.addPane(designPane, autoResize);
            }
        }
        cardPane.refresh();

        CardLayout cardLayout = cardPane.getCardLayout();


        cardLayout.show(cardPane, ((SimPropertyChoice)jComboBox.getSelectedItem()).getFrameID());
        /*
              cardPane.removeAll();
             Vector designPanes = cardPane.getDesignPanes();
             for ( int i = 0 ; i < designPanes.size() ; i ++ )
             {
               DesignPane designPane = (DesignPane) designPanes.elementAt(i);
               ((CardLayout)cardPane.getLayout()).addLayoutComponent(designPane, designPane.getID());
             } *.
           /*
               //Vector designPanes =  DesignerControl.designPanes;
               Vector designPanes = cardPane.getDesignPanes();
               for ( int i = 0 ; i < designPanes.size() ; i ++ )
               {
                   DesignPane designPane = (DesignPane) designPanes.elementAt(i);
                   if ( designPane.getParent() != cardPane )
                       cardPane.add(designPane, designPane.getID());
               }*/
         /*
               for ( int i = 0 ; i < jComboBox.getItemCount() ; i ++ )
               {
             /*
                 SimPropertyChoice choice = (SimPropertyChoice)jComboBox.getItemAt(i);
                 System.out.println("CHOICE: "+choice+", VALUE: "+choice.getFrameID());
                 if ( ! choice.equals("-1") )
                 {
                  for ( int j = 0 ; j < designPanes.size() ; j ++ )
                  {
                   DesignPane designPane = (DesignPane) designPanes.elementAt(j);
                   if ( designPane.getID().equalsIgnoreCase(choice.getFrameID() ) )
                   {
           System.out.println("ADD: "+designPane.getID());
           cardPane.add(designPane, designPane.getID() );
                   }
                 //DesignPane designPane = (DesignPane) DesignerControl.currentDesignPane.
              //cardLayout.show(cardPane, choice.getFrameID());
            }
           }*
                }*/
    }

    public void setCardPane(CardPane cardPane)
    {
        if (cardListener != null)
            jComboBox.removeItemListener(cardListener);

        if (cardPane == null )
        {
            if ( this.cardPane != null )
            {
                this.cardPane.setComboBox(null);
                Vector designPanes = this.cardPane.getDesignPanes();
                for (int i = 0; i < designPanes.size(); i++)
                {
                    DesignPane designPane = (DesignPane) designPanes.elementAt(i);
                    designPane.setParentCardPane(null);
                }
                this.cardPane.removeAllPanes();
                this.cardPane.refresh();
            }
            this.cardPane = null;
            return;
        }

        this.cardPane = cardPane;
		cardPane.setComboBox(jComboBox);

        if (this.cardPane != null)
        {
            cardPane.setPanes(this.cardPane.getDesignPanes());
            this.cardPane.setPanes(new Vector());
            cardPane.refresh();
        }

        //System.out.println("SETING CARD PANE: "+cardPane);

        //jComboBox.removeItemListener(cardListener);
        if (cardPane != null)
        {
            cardListener = new InputItemListener(this, cardPane);

            jComboBox.addItemListener(cardListener);

            resetCardPaneContents(false);
        }

        //resetCardPaneContents ( DesignerControl.designPanes );

        //SUPER TEMP
        /*
                 Vector allFrame = DesignerControl.designPanes;
                 for (int i = 1; i < allFrame.size(); i++)
                 {
            DesignPane designe = (DesignPane) allFrame.elementAt(i);
            cardPane.add(designe, designe.getID());
                 }
         */
    }

    public CardPane getCardPane()
    {
        return cardPane;
    }

    public CoverControl getCoverControl()
    {
        return coverControl;
    }

    public void setCoverControl(CoverControl coverControl)
    {
        this.coverControl = coverControl;
    }

    public void setCover(CoverComponent cover)
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/Input-g16.gif");
    }

    public CoverComponent getCover()
    {
        return this.cover;
    }

    public void deselectInternal()
    {
        jComboBoxCover.setVisible(true);
        jComboBoxCover.getFocus(false);
        jButtonCover.setVisible(true);
        jButtonCover.getFocus(false);
        jTextFieldCover.setVisible(true);
        jTextFieldCover.getFocus(false);
        jLabelCover.setVisible(true);
        jLabelCover.getFocus(false);
    }

    public CLabel getLabel()
    {
        return this.jLabel;
    }

    public CTextField getTextField()
    {
        return this.jTextField;
    }

    public CComboBox getComboBox()
    {
    	setDefaultValue(jComboBox.getSelectedItem());
        /*if (!isPrimitive(property.getType()))
            setComboBoxObject(); *///refresh those FALSE value in choice
        return this.jComboBox;
    }

    public CButton getButton()
    {
        return this.jButton;
    }

    public Vector getNestElement()
    {
        return DiagramComponentSetPanel;
    }

    public void addNestElement(DiagramComponentSetPanel setPanel)
    {
        DiagramComponentSetPanel.add(setPanel);
    }

    public void setSize(int width, int height)
    {
        super.setSize(width, height);
        autoResize();
    }

    /*
         public void setBounds ( int x, int y, int width, int height )
         {
        int girdValue = DesignerUI.gridValue;
        x = x - x % girdValue + girdValue / 2;
        y = y - y % girdValue + girdValue / 2;
        width = width - width % girdValue + girdValue / 2;
        height = height = height % girdValue + girdValue / 2;
        super.setBounds(x, y, width, height);
         }*/
    public void setUseButton(boolean b)
    {
        if ( b )
        {
            this.add(jButton);
            jButton.setVisible(true);
            jButtonCover.setVisible(true);

            System.out.println("SET CARD PANE TO NULL");
            setCardPane(null);
        }
        else
        {
            this.remove(jButton);
            jButton.setVisible(false);
            jButtonCover.setVisible(false);

        }

        autoResize();
    }

    public boolean getAutoResize()
    {
        return autoResize;
    }

    public void autoResize()
    {

        if (!autoResize)
            return;

        int grid = jLabelCover.getGridSize() == 1 ? 1 : jLabelCover.getGridSize() - 1;

        this.setLayout(null);
        int width = this.getWidth();
        int height = this.getHeight();
        jLabelCover.changeBounds2(0, 0, width / 5 * 2 - grid, height - grid);
        //jLabel.setBounds(5, 5, width/5*2-10, height-10);

        //jButton.setVisible(false);
        if (isPrimitive(property.getType()))
        {
            jTextField.setVisible(true);
            //jTextFieldCover.changeBounds(width/5*2, 0, width/5*3 - grid, height - grid);
            jTextFieldCover.changeBounds2(width - 5 - (width / 5 * 3 - grid), 0, width / 5 * 3 - grid, height - grid);
            //jTextField.setBounds(width/5*2+5, 5, width/5*3-10, height-10);
            jComboBox.setVisible(false);
        }
        else
        {
            jComboBox.setVisible(true);
            if (jButton.isVisible()) //( propertise.size() > 0 ) //TEMP
            {
                //jComboBoxCover.changeBounds(width/5*2 , 0, width/5*3-4-28 , height - grid);

                //jComboBox.setBounds(new Rectangle(width/5*2+5, 5, width/5*3-10-30, height-10));
                //jButton.setVisible(true);
                //jButtonCover.changeBounds(jComboBox.getX()+jComboBox.getWidth()-4, 0, 32, height - grid);

                jButtonCover.changeBounds2(width - 5 - 32, 0, 32, height - grid);
                jComboBoxCover.changeBounds2(width - 32 - (width / 5 * 3 - 4 - 28), 0, width / 5 * 3 - 4 - 28, height - grid);

                //jButton.setBounds(jComboBox.getX()+jComboBox.getWidth()+5, 5, 25, height - 10);
            }
            else
            {
                //jComboBoxCover.changeBounds(width/5*2+5, 0, width/5*3-10 - grid, height - grid);
                //jComboBoxCover.changeBounds(width-10-(width/5*3-10 - grid), 0,  width/5*3-10 - grid, height - grid);
                jComboBoxCover.changeBounds2(width - 5 - (width / 5 * 3 - grid), 0, width / 5 * 3 - grid, height - grid);
                //jComboBox.setBounds(new Rectangle(width/5*2+5, 5, width/5*3-10, height-10));
            }
            jTextField.setVisible(false);
        }
    }

    public String toString()
    {
        return property.getName(); // + " (Testing: " + name + ")";
    }

    public String getSetMode()
    {
        return property.getName();
    }

    public String getName()
    {
        return name;
    }

    public Vector getPropertise()
    {
        return propertise;
    }

    public void setPropertise(Vector vc)
    {
        this.propertise = vc;
    }

    public Object getInternalValueNode()
    {
        return internalValueNode;
    }

    public void setInternalValueNode(Object object)
    {
        internalValueNode = object;
        int childCount = ( (DefaultMutableTreeNode) internalValueNode).getChildCount();
        SimPropertyChoice[] simChoice = new SimPropertyChoice[childCount];
        for (int i = 0; i < childCount; i++)
        {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) ( (DefaultMutableTreeNode) internalValueNode).getChildAt(i);
            simChoice[i] = (SimPropertyChoice) rootNode.getUserObject();
        }
        PropertiesFactory.appendChoiceProperties(jComboBox, simChoice);
        setComboBoxObject();
    }

    public GetDataInput getGetMode()
    {
        return getMode;
    }

    public Object getDefultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(Object object)
    {
        defaultValue = object;
    }

    public String getText()
    {
        return name;
    }

    public void setText(String text)
    {
        name = text;
    }

    String[] primitiveAry =
        {
        "java.lang.String", "java.lang.Integer", "java.lang.Double"};
    private boolean isPrimitive(String type)
    {
        boolean isPrimitiveType = false;
        int i = 0;
        while (!isPrimitiveType && i < primitiveAry.length)
        {
            if (type.equalsIgnoreCase(primitiveAry[i]))
            {
                isPrimitiveType = true;
            }
            i++;
        }
        return isPrimitiveType;
    }

    private void setGetMode(String type)
    {
        if (type.equalsIgnoreCase(primitiveAry[0]))
            getMode = new GetStringInput();
        else if (type.equalsIgnoreCase(primitiveAry[1]))
            getMode = new GetIntegerInput();
        else if (type.equalsIgnoreCase(primitiveAry[2]))
            getMode = new GetDoubleInput();
        else
            getMode = new GetStringInput();
    }

    public void refreshComboxBoxChoice()
    {
    	setDefaultValue(jComboBox.getSelectedItem());
    	if (!isPrimitive(property.getType()))
        	setComboBoxObject();
    }

    private void setComboBoxObject()
    {
        /*
                 PropertiesTableReader pReader = new PropertiesTableReader();
                 String [] typeAry = pReader.getType(this.property.getType());
                 String [] choiceAry = new String[typeAry.length];
                 for(int j = 0; j < typeAry.length; j++) {
            StringTokenizer st = new StringTokenizer(typeAry[j], ".");
            int count = st.countTokens();
            while(count != 1) {
                st.nextToken();
                count--;
            }
            choiceAry[j] = st.nextToken();

                 }*/
                 //System.out.println("TEST DEFALUT VALUE: "+defaultValue);
        jComboBox.removeAllItems();
        int childCount = ( (DefaultMutableTreeNode) internalValueNode).getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) ( (DefaultMutableTreeNode) internalValueNode).getChildAt(i);
            SimPropertyChoice choice = (SimPropertyChoice) rootNode.getUserObject();
            if (choice.getDisplay())
                jComboBox.addItem(choice);
				//System.out.println("DISPLAY NAME: "+choice.getDisplayName());
				//System.out.println("DEFAULT VALUE: "+defaultValue.toString());
			if (choice.getDisplayName().equalsIgnoreCase(defaultValue.toString()))
			{
				setDefaultValue(choice);
			}
        }
        //System.out.println(defaultValue);
        jComboBox.setSelectedItem(defaultValue);
    }
}
class SetDefaultListener implements ItemListener
{
	private DiagramComponentSetPanel setPane;
	public SetDefaultListener (DiagramComponentSetPanel setPane)
	{
		this.setPane = setPane;
	}
	public void itemStateChanged(ItemEvent e)
    {
		//setPane.setDefaultValue(e.getItem());
	}
}
class InputItemListener implements ItemListener
{
    private CardPane cardPane;
    private DiagramComponentSetPanel setPane;
    private CardLayout cardLayout;

    public InputItemListener(DiagramComponentSetPanel setPane, CardPane cardPane)
    {
        this.setPane = setPane;
        this.cardPane = cardPane;
        this.cardLayout = cardPane.getCardLayout();
    }

    public void itemStateChanged(ItemEvent e)
    {
        CComboBox comboBox = (CComboBox) e.getSource();

        //System.out.println("TRIGGERED");
        SimPropertyChoice choice = (SimPropertyChoice) comboBox.getSelectedItem();

        System.out.println("TEST SELECTED 1: " + comboBox.getCover().isSelected());
        if (DesignerControl.getDesignPane(choice.getFrameID())==null)
            choice.setFrameID("-1");
        if (choice.getFrameID().equals("-1"))
            cardPane.showDefault();
        if (!comboBox.getCover().isSelected())
            ;
        else

        if (choice.getFrameID().equals("-1"))
        {
            System.out.println("DISPLAY: " + choice.getDisplayName());
            cardPane.showDefault();
            String id = choice.getFrameID();
            comboBox.getCover().getFocus(false);
            DesignPane newPane = CreateFrameControl.CreateFrame(setPane, choice);
            System.out.println("TEST SELECTED 2: " + comboBox.getCover().isSelected());
            System.out.println("TEST SELECTED 3: " + comboBox.getCover().isSelected());
            if (newPane != null)
            {
                setPane.addPaneToCardPane(newPane);
                UIDesigner.getControl().showFrame(newPane);
                System.out.println("TRI");
            }
            return;
        }
        System.out.println("SELECTED: " + choice.getFrameID());

        System.out.println(cardPane.getComponentCount());
        try
        {
            cardLayout.show(cardPane, choice.getFrameID());
        }
        catch (Exception ex)
        {
            choice.setFrameID("-1");
            cardPane.showDefault();
            String id = choice.getFrameID();
            comboBox.getCover().getFocus(false);
            DesignPane newPane = CreateFrameControl.CreateFrame(setPane, choice);
            System.out.println("TEST SELECTED 2: " + comboBox.getCover().isSelected());
            System.out.println("TEST SELECTED 3: " + comboBox.getCover().isSelected());
            if (newPane != null)
            {
                setPane.addPaneToCardPane(newPane);
                UIDesigner.getControl().showFrame(newPane);
                System.out.println("TRI");
            }
            return;
        }

    }
}

class SetPanelButtonListener implements ActionListener
{
    private JComboBox jComboBox;
    private DiagramComponentSetPanel setPane;

    public SetPanelButtonListener(DiagramComponentSetPanel setPane, JComboBox jComboBox)
    {
        this.jComboBox = jComboBox;
        this.setPane = setPane;
    }

    public void actionPerformed(ActionEvent e)
    {
        String id = ( (SimPropertyChoice) jComboBox.getSelectedItem()).getFrameID();
        if (DesignerControl.getDesignPane(id)==null)
            ( (SimPropertyChoice) jComboBox.getSelectedItem()).setFrameID("-1");
        DesignPane newPane = CreateFrameControl.CreateFrame(setPane, (SimPropertyChoice) jComboBox.getSelectedItem());
        if (newPane != null)
        {
            setPane.addPaneToCardPane(newPane);
            try
            {
                UIDesigner.getControl().showFrame(newPane);
            }

            catch (Exception ex)
            {
                ( (SimPropertyChoice) jComboBox.getSelectedItem()).setFrameID("-1");
            }

        }
    }
}