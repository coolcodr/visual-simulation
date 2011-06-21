package designer;

import designer.deployment.*;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import java.util.Vector;

class SelectCardPaneCommand extends PropertyCommand
{
    //private CardPane cardPane;

    public SelectCardPaneCommand(DesignerComponent jComponent)
    {
        super(jComponent);
        name = "Card Panel";
        type = "Input Methods";
    }

    public Object getValue()
    {
        return ( (DiagramComponentSetPanel) designerComponent).getCardPane();
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SelectCardPaneCommand ( designerComponent );
        undoCommand.oldValue = getValue();
        return undoCommand;
    }
    public boolean setValue(Object object) throws InvalidPropertyException
    {
        try
        {
            DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) designerComponent;
            setPane.setCardPane( (CardPane) object);
            return true;
        }
        catch (Exception ex)
        {
            throw new InvalidPropertyException("Error in associating card pane.");
        }
    }
	private Vector getCardPanes ( DesignUpperPane designUpperPane, Vector cardPanes )
	{
		DesignPane designPane = designUpperPane.getDesignPane();

		Vector thisCardPanes = designPane.getCardPane();

		for ( int i = 0 ; i < thisCardPanes.size() ; i ++ )
		{
			cardPanes.add( thisCardPanes.elementAt(i) );
		}

		if ( designPane.getParent() instanceof DesignUpperPane )
		{
			getCardPanes ( (DesignUpperPane) designPane.getParent(), cardPanes );
		}

		return cardPanes;
	}
    public JComponent getEditor()
    {
        if ( ( (DiagramComponentSetPanel) designerComponent).getButton().isVisible())
            return null;

            DesignUpperPane upperPane = (DesignUpperPane) ( (JComponent) designerComponent).getParent();

        Vector allCardPanes = new Vector ();
        allCardPanes = getCardPanes( upperPane, allCardPanes);

        //Vector allCardPanes = upperPane.getDesignPane().getCardPane();


        CardPane[] cardPanes = new CardPane[allCardPanes.size()];
        for (int i = 0; i < allCardPanes.size(); i++)
            cardPanes[i] = (CardPane) allCardPanes.elementAt(i);

        JComboBox jComboBox = new JComboBox(cardPanes);
        jComboBox.setSelectedItem(getValue());
        jComboBox.setBorder(null);
        return jComboBox;
    }

    public void refresh()
    {
        cover = designerComponent.getCover();
        cover.getFocus(true);
    }
}

class SelectInputMethodCommand extends PropertyCommand
{
    public SelectInputMethodCommand(DesignerComponent jComponent)
    {
        super(jComponent);
        name = "Use Card";
        type = "Input Methods";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SelectInputMethodCommand ( designerComponent );
        undoCommand.oldValue = new Boolean ( !((Boolean) getValue() ).booleanValue() );
        return undoCommand;
    }
    public Object getValue()
    {
        return new Boolean(! ( (DiagramComponentSetPanel) designerComponent).getButton().isVisible());
    }

    public boolean setValue(Object object) throws InvalidPropertyException
    {
        try
        {
            Boolean newValue = (Boolean) object;
            DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) designerComponent;
            setPane.setUseButton(newValue.booleanValue());
            setPane.updateUI();
            return true;
        }
        catch (Exception ex)
        {
            throw new InvalidPropertyException("Not a valid boolean value.");
        }
    }

    public void refresh()
    {
        cover = designerComponent.getCover();
        cover.getFocus(true);
        DesignerControl.refreshFrameList();
    }

    public JComponent getEditor()
    {
        JCheckBox jCheckBox = new JCheckBox();
        boolean value = ( (DiagramComponentSetPanel) designerComponent).getButton().isVisible();
        jCheckBox.setSelected(value);
        jCheckBox.setHorizontalAlignment(JCheckBox.CENTER);
        return jCheckBox;
    }
}

class SetAutoLayoutCommand extends PropertyCommand
{
    public SetAutoLayoutCommand(DesignerComponent jComponent)
    {
        super(jComponent);
        name = "Auto Layout";
        type = "Layout Component";
    }
    public PropertyCommand createUndoCommand ()
    {
        PropertyCommand undoCommand = new SetAutoLayoutCommand ( designerComponent );
        undoCommand.oldValue = new Boolean(((Boolean)getValue()).booleanValue());
        return undoCommand;
    }
    public Object getValue()
    {
        return new Boolean( ( (DiagramComponentSetPanel) designerComponent).getAutoResize());
    }

    public boolean setValue(Object object) throws InvalidPropertyException
    {
        try
        {
            Boolean newValue = (Boolean) object;
            DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) designerComponent;
            setPane.setAutoResize(newValue.booleanValue());
            setPane.autoResize();
            setPane.repaint();
            return true;
        }
        catch (Exception ex)
        {
            throw new InvalidPropertyException("Not a valid boolean value.");
        }
    }
    public void refresh()
        {
            cover = designerComponent.getCover();
            cover.getFocus(true);
        }

    public JComponent getEditor()
    {
        JCheckBox jCheckBox = new JCheckBox();
        boolean value = ( (DiagramComponentSetPanel) designerComponent).getAutoResize();
        jCheckBox.setSelected(value);
        jCheckBox.setHorizontalAlignment(JCheckBox.CENTER);
        return jCheckBox;
    }

 }