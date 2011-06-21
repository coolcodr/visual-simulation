package designer;

import designer.deployment.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.CardLayout;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class CardPane extends JPanel implements DesignerComponent
{
    private Border border;
    private Border titledBorder;
    private String text;
    private CardLayout cardLayout;
    private JPanel defaultPane;
	private JComboBox comboBox;

    private CoverComponent cover;
    private CoverControl coverControl;

    private Vector designPanes = new Vector();
    private String id;

	private ElementProperties properties;

    public CardPane()
    {
        this("C" + DesignerControl.getNewID());
    }
	public void setProperties ( ElementProperties properties ){
		this.properties = properties;
	}
	public ElementProperties getProperties (){
		return properties;
	}
    public CardPane(String id)
    {
        super();
        this.id = id;
        defaultPane = new JPanel();
        //defaultPane.setBackground(UIManager.getColor("Button.focus"));
        border = BorderFactory.createEtchedBorder();
        setBorder(border);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        text = "";
        setText(text);
        setCoverControl(CoverControl.createEditTextControl());
        add(defaultPane, "Default");
        setProperties( PropertiesFactory.createCardPaneProperties(this) );
    }

    public String getID()
    {
        return id;
    }

    public void showDefault()
    {
        cardLayout.show(this, "Default");
    }

    public void addPane(DesignPane designPane, boolean autoResize )
    {
        designPanes.add(designPane);
        int height = text.equalsIgnoreCase("") ? 0 : 19;
        if ( autoResize )
            designPane.setBounds(0,0,getWidth()-14,getHeight()-14 - height);
        designPane.setParentCardPane(this);
        refresh();
    }
    public void setPanes ( Vector designPanes )
    {
        for ( int i = 0 ; i <  this.designPanes.size() ; i ++ )
        {
            DesignPane designPane = (DesignPane) this.designPanes.elementAt(i);
            designPane.setParentCardPane(null);
        }
        this.designPanes = designPanes;
        for ( int i = 0 ; i <  designPanes.size() ; i ++ )
        {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            designPane.setParentCardPane(this);
        }

    }
    public Dimension getPanelSize ()
    {
        int height = text.equalsIgnoreCase("") ? 0 : 19;
        Dimension d = new Dimension ( getWidth() - 14, getHeight() - 14 - height );
        return d;
    }
    public void removeAllPanes ()
    {
        this.removeAll();
        for (int i = 0; i < designPanes.size(); i++)
        {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            if ( designPane.getBackPane() != null )
                designPane.getBackPane().add(designPane);
        }
        designPanes.removeAllElements();
        add(defaultPane, "Default");
    }
    public void refresh()
    {
        this.removeAll();
        add(defaultPane, "Default");

        for (int i = 0; i < designPanes.size(); i++)
        {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            JPanel panel = new JPanel();
            panel.add(designPane);
            panel.setVisible(true);
            panel.setLayout(null);
            this.add(panel, designPane.getID());
        }
        if ( comboBox == null )
        	cardLayout.show(this, "Default");
        else
        	cardLayout.show(this, ((SimPropertyChoice)comboBox.getSelectedItem()).getFrameID());
    }

    public void removePane(DesignPane designPane)
    {
        designPanes.remove(designPane);
        designPane.setParentCardPane(null);
    }

    public Vector getDesignPanes()
    {
        return designPanes;
    }

    public CoverControl getCoverControl()
    {
        return coverControl;
    }

    public void setCoverControl(CoverControl coverControl)
    {
        this.coverControl = coverControl;
    }

    public CoverComponent getCover()
    {
        return cover;
    }

    public String toString()
    {
        if ( getText().equalsIgnoreCase("") )
            return "Untitled Pane ("+id+")";
        return getText();
    }

    public void setCover(CoverComponent cover)
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon( "designer/images/card16.gif" ) ;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String s)
    {
        text = s;
        titledBorder = new TitledBorder(border, s);
        setBorder(titledBorder);
    }

    public CardLayout getCardLayout()
    {
        return (CardLayout) getLayout();
    }

    public void setComboBox ( JComboBox jComboBox )
    {
    	this.comboBox = jComboBox;
    }
    public JComboBox getComboBox ()
    {
    	return this.comboBox;
    }
}