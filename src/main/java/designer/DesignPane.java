package designer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import designer.deployment.*;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.util.*;
import java.awt.event.*;
import java.util.*;

public class DesignPane extends JLayeredPane
{
    protected CommandStack commandStack = new CommandStack();

    protected DesignBottomPane bottomPane;
    protected DesignUpperPane upperPane;
    protected JPanel backPane;
    protected DesignBackPane backFrame;

    protected String frameTitle;
    protected String name;
    protected String id;

    //For designer time only
    protected Vector coverComponent = new Vector();

    //Will be serialize out in deploy objects
    protected Vector setPaneComponent = new Vector();
    protected Vector actionComponent = new Vector();

    protected Vector cardPanes = new Vector();
    protected Vector titlePanes = new Vector();
    protected Vector basicComponents = new Vector();

    protected Hashtable componentToCover = new Hashtable();

    protected CardPane parentCardPane;

    //protected Vector designerComponents = new Vector();

    protected DesignPaneResizeControl[] resizePoint;
    protected Vector selectedCovers = new Vector();

    public DesignPane ()
    {
        this( new String() , "Untitled Frame");
    }
    public DesignPane ( String id,  String title )
    {
        this.frameTitle = "";
        this.name = title;
        this.id = id;

        this.bottomPane = new DesignBottomPane();
        this.upperPane = new DesignUpperPane(this);

        /*
        this.bottomPane.setBounds(0,0,540,360);
        this.upperPane.setBounds(0,0,540,360);
*/
        this.setLayer(bottomPane, JLayeredPane.DEFAULT_LAYER.intValue());
        this.add(bottomPane);
        this.bottomPane.setVisible(true);
        this.setLayer(upperPane, JLayeredPane.DRAG_LAYER.intValue());
        this.add(upperPane);
        this.upperPane.setVisible(true);
        this.setBackground(new JLabel().getBackground());
        DiagramSourceList.dropTarget2 = new DropTarget (upperPane , upperPane);

        //Temp, should not put here, should be in upper pane itself
        this.resizePoint = new DesignPaneResizeControl[6];
        for ( int i = 0; i < 6 ; i ++ )
        {
            resizePoint[i] = new DesignPaneResizeControl( i, this );
            upperPane.add(resizePoint[i]);
            resizePoint[i].setVisible(false);
        }

        this.setBounds(0,0,540,360);
        this.setSize(540,360);
    }
    public void setCommandStack ( CommandStack commandStack )
    {
    	this.commandStack  = commandStack;
    }
    public void setParentCardPane ( CardPane cardPane )
    {
        this.parentCardPane = cardPane;
    }
    public CardPane getParentCardPane()
    {
        return parentCardPane;
    }
    public void setBackPane ( JPanel jPanel, DesignBackPane backFrame )
    {
        this.backPane = jPanel;
        this.backFrame = backFrame;
    }
    public DesignBackPane getBackFrame ()
    {
        return this.backFrame;
    }
    public JPanel getBackPane ()
    {
        return this.backPane;
    }
    public void refreshTitle()
    {
        if ( backFrame != null )
            backFrame.refreshTitle();
    }
    public void restoreButtonStatus ( DesignPane designPane )
    {
        DesignBackPane backFrame = (DesignBackPane) designPane.getBackFrame();

        if (backFrame != null)
            backFrame.restoreButtonStatus();
        else
        {
            try
            {
                DesignPane designPane2 = ((DesignUpperPane)designPane.getParent()).getDesignPane();
                restoreButtonStatus(designPane2);
            }
            catch (Exception ex) {}
        }
    }
    public void restoreButtonStatus ()
    {
        restoreButtonStatus ( this );
    }
    public void selectObject ( CoverComponent cover )
    {
        if ( !selectedCovers.contains(cover) )
            selectedCovers.add(cover);
		ElementProperties properties = ((DesignerComponent)cover.getRelateComponent()).getProperties();
		System.out.println(cover.getRelateComponent());
		System.out.println(properties);
		if ( properties != null )
			UIDesigner.mainWindow.setDiagramSourceTable(new DiagramSourceTable(properties));
    }
    public void selectThis ( ElementProperties properties )
    {
        UIDesigner.mainWindow.setDiagramSourceTable(new DiagramSourceTable(properties));
    }
    public DesignUpperPane getUpperPane()
    {
        return upperPane;
    }
    public Vector getSelecledObject ()
    {
        return selectedCovers;
    }
    public DesignBottomPane getBottomPane()
    {
        return bottomPane;
    }
    public String getFrameName ()
    {
        return name;
    }
    public void setFrameName ( String name )
    {
        this.name = name;
    }
    public String getTitle()
    {
        return frameTitle;
    }
    public void setTitle( String title )
    {
        frameTitle = title;
    }
    public String getID()
    {
        return id;
    }
    public String toString ()
    {
        return getFrameName();
    }
		/*
		public void setLocation ( int x, int y )
		{
				//super.setLocation(x, y);
        if ( backPane != null )
        {
            backPane.setLocation(x, y);
            backPane.getParent().getParent().repaint();
        }
		}*/
    public void setBounds( int x, int y, int width, int height)
    {
        if ( width < 10 ) width = 10;
        if ( height < 10 ) height = 10;

        if ( x < 0 ) x = 0;
        if ( y < 0 ) y = 0;
        if ( parentCardPane != null )
        {
            if ( x < 10 ) x = 0;
            if ( y < 10 ) y = 0;
            Dimension d = parentCardPane.getPanelSize();
            if ( x + width > d.width - 10 && x + width < d.width + 10 ) x = d.width  - width ;
            if ( y + height > d.height - 10 && y + height < d.height + 10 ) y = d.height - height ;
        }
        super.setBounds(x, y, width, height);
        bottomPane.setBounds(0, 0, width, height);
        upperPane.setBounds(0, 0, width, height);
        if ( backPane != null )
        {
            backPane.setBounds(10, 10, width + x, height + y);

            JPanel parent = (JPanel) backPane.getParent();
            parent.setPreferredSize(new Dimension(backPane.getWidth()+10*2, backPane.getHeight()+10*2));
            //backPane.getParent().getParent().repaint();
        }
        refreshResizePoint ();
    }
    public void setSize ( int width, int height )
    {
        if ( width < 10 ) width = 10;
        if ( height < 10 ) height = 10;

        int x = getX() + width;
        int y = getY() + height;
        if ( parentCardPane != null )
        {
            Dimension d = parentCardPane.getPanelSize();
            if ( x > d.width - 10 && x < d.width + 10 ) width = d.width - getX() ;
            if ( y > d.height - 10 && y < d.height + 10 ) height = d.height - getY();
        }
        super.setSize(width, height);
        bottomPane.setSize(width, height);
        upperPane.setSize(width, height);
        if ( backPane != null )
        {
            backPane.setSize(width + getBounds().x, height + getBounds().y);
            JPanel parent = (JPanel) backPane.getParent();
            parent.setPreferredSize(new Dimension(backPane.getWidth()+10*2, backPane.getHeight()+10*2));
            //backPane.getParent().getParent().repaint();
        }
        refreshResizePoint ();
    }
    public void setCursor ( Cursor cursor )
    {
        super.setCursor(cursor);
        this.upperPane.setCursor(cursor);
        this.getParent().setCursor(cursor);
        this.getParent().getParent().setCursor(cursor);
    }
    public void refreshResizePoint ()
    {
        for ( int i = 0; i < 6 ; i ++ )
            resizePoint[i].resetResizePoint();
    }
    public void showPaneResize( boolean b )
    {
        for ( int i = 0; i < 6 ; i ++ )
            resizePoint[i].setVisible(b);
    }
    public void clearSelected()
    {
        selectedCovers.removeAllElements();
    }
    public void selectAll ()
    {
        deselectAll ();
        for ( int i = 0 ; i < coverComponent.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent) coverComponent.elementAt(i);
            cover.getFocus(true);
        }
    }
    public void deselectAll ()
    {
        DesignerControl.setEditButtonEnable(false);
        DesignerControl.setDeleteButtonEnable(false);

        for ( int i = 0; i < 6 ; i ++ )
            resizePoint[i].setVisible(false);

        for ( int i = 0 ; i < selectedCovers.size() ; i ++ )
        {
            //System.out.println("NO OF SEL COVERS: "+ i);
            ( (CoverComponent) (selectedCovers.elementAt(i))).getFocus(false);
            ( (CoverComponent) (selectedCovers.elementAt(i))).setVisible(false);
        }
        for ( int i = 0 ; i < coverComponent.size() ; i ++ )
        {
            //System.out.println("NO OF COVERS: "+ i);
            CoverComponent cover = (CoverComponent) coverComponent.elementAt(i);
            if ( cover.getRelateComponent() instanceof DesignPane )
                ((DesignPane)cover.getRelateComponent()).deselectAll();
            cover.getFocus(false);
            cover.setVisible(true);

        }
        selectedCovers.removeAllElements();// = new Vector();
    }
    public void addCardPane ( CardPane cardPane )
    {

    }
    public void addTitlePane ( TitlePane titlePane )
    {

    }
    public void removeDesignComponent ()
    {
        createBatchMoveUndo();

        for ( int i = 0 ; i < selectedCovers.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            Component designComponent = cover.getRelateComponent();

            if ( designComponent instanceof CardPane )
            	cardPanes.remove(designComponent);
            //coverComponent.remove(cover);
            //setPaneComponent.remove(designComponent);

            cover.getParent().remove(cover);
            designComponent.getParent().remove(designComponent);

        }
        repaint();
    }
    public void addDesignComponent ( DesignerComponent designerComponent )
    {
        addDesignComponent ( designerComponent, true );
    }
    public void addDesignComponent ( DesignerComponent designerComponent, boolean createUndo )
    {
        if ( createUndo )
        {
            ReshapCommand reshapCommand = new ReshapCommand(designerComponent);
            UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());
        }

        DesignerControl.currentDesignPane = this;

        //DesignerComponetn must be a JComponent
        JComponent jComponent = (JComponent) designerComponent;

        CoverComponent cover = designerComponent.getCover();
        //if ( cover == null) cover = new CoverComponent ( jComponent, 0, true );
        if ( cover != null && cover.getParent() != null)
            cover.getParent().remove(cover);

        if (designerComponent instanceof DiagramComponentSetPanel)
            cover = new CoverComponent(jComponent, 0, true);
        else
            cover = new CoverComponent(jComponent, 6, true);

        designerComponent.setCover(cover);
        System.out.println("TEST: "+cover);
        cover.setCoverControl(designerComponent.getCoverControl());

        this.setPaneComponent.add(designerComponent);
        this.coverComponent.add(cover);

        this.addComponentToPane(designerComponent, cover);


        //SUPER TEMP
        if ( designerComponent instanceof CardPane )
        {
        	cardPanes.add(designerComponent);
        }

        if ( designerComponent instanceof DesignPane )
        {
        	((DesignPane)designerComponent).setCommandStack(commandStack);
        }
        	//DiagramComponentSetPanel firstSetPane = (DiagramComponentSetPanel) setPaneComponent.elementAt(0);
        	//firstSetPane.setCardPane((CardPane) designerComponent);
        	/*
            DiagramComponentSetPanel firstSetPane = (DiagramComponentSetPanel) setPaneComponent.elementAt(0);
            firstSetPane.setCardPane( (CardPane) jComponent);
            firstSetPane.addPaneToCardPane( (DesignPane) DesignerControl.designPanes.elementAt(1) );
            //firstSetPane.addPaneToCardPane( (DesignPane) DesignerControl.designPanes.elementAt(2) );
            //firstSetPane.resetCardPaneContents();*/

        refresh();
    }
    public Vector getCardPane ()
    {
        return cardPanes;
    }
    public void refresh()
    {
    	//DiagramComponentSetPanel firstSetPane = (DiagramComponentSetPanel) setPaneComponent.elementAt(0);
        //firstSetPane.resetCardPaneContents();
        for ( int i = 0 ;  i < cardPanes.size() ; i ++ )
        {
        	CardPane cardPane = (CardPane) cardPanes.elementAt(i);
        	cardPane.refresh();
        }
        DesignerControl.refreshFrameList();
    }
    public void addActionComponent ( JComponent jComponent )
    {

    }
    public void addBasicComponent ( JComponent jComponent )
    {

    }
    public void alignLeft ()
    {
        createBatchMoveUndo();
        int x = 0;
        int y = -1;
        for ( int i = 0 ; i < selectedCovers.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            if ( y > cover.getY() || y == -1 )
            {
                x = cover.getX();
                y = cover.getY();
            }
        }
        for ( int i = 0 ; i < selectedCovers.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            cover.moveXY(x, cover.getY());
            cover.setXY();
        }
    }
    public void alignRight()
    {
        createBatchMoveUndo();
        int x2 = 0;
        int dx = -1;
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            if (dx < cover.getX() + cover.getWidth() || dx == -1)
            {
                x2 = cover.getX() + cover.getWidth();
                dx = cover.getX() + cover.getWidth();
            }
        }
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            cover.moveXY(x2 - cover.getWidth(), cover.getY());
            cover.setXY();
        }
    }
    public void alignTop ()
    {
        createBatchMoveUndo();
        int dy = -1;
        int y = 0;
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            if (dy > cover.getY() || dy == -1)
            {
                dy = cover.getY();
                y = cover.getY();
            }
        }
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            cover.moveXY(cover.getX(), y);
            cover.setXY();
        }
    }

    public void createBatchMoveUndo()
    {
        if ( selectedCovers.size() <= 0 )
            return;

        Vector covers = selectedCovers;
        Vector alls = new Vector();

        for (int i = 0; i < covers.size(); i++)
        {
            alls.add( ( (CoverComponent) covers.elementAt(i)).getRelateComponent());
        }

        BatchMoveCommand batchMoveCommand = new BatchMoveCommand( (DesignerComponent) ((CoverComponent)covers.elementAt(0)).getRelateComponent(), alls);
        UIDesigner.getControl().addUndoCommand(batchMoveCommand.createUndoCommand());

    }
    public void alignBottom ()
    {
        createBatchMoveUndo();

        int dy = -1;
        int y2 = 0;
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            if (dy < cover.getY() + cover.getHeight() || dy == -1)
            {
                dy = cover.getY() + cover.getHeight();
                y2 = cover.getY() + cover.getHeight();
            }
        }
        for (int i = 0; i < selectedCovers.size(); i++)
        {
            CoverComponent cover = (CoverComponent) selectedCovers.elementAt(i);
            cover.moveXY(cover.getX(), y2 - cover.getHeight());
            cover.setXY();
        }
    }
    public void repaint()
    {
        updateUI();
        super.repaint();
        upperPane.updateUI();
        upperPane.repaint();
    }
    public void autoLayout ()
    {
        alignLeft();

        CoverComponent[] covers = new CoverComponent[selectedCovers.size()];

        for ( int i = 0 ; i < covers.length ; i ++ )
        {
            covers[i] = (CoverComponent)selectedCovers.elementAt(i);
        }

        int bottom = covers.length - 2;
        boolean exchange = true;
        CoverComponent temp;

        while (exchange)
        {
            exchange = false;

            for ( int i = 0 ; i <= bottom ; i++ )
            {
                CoverComponent cover1 = covers[i];
                CoverComponent cover2 = covers[i+1];

                if (cover1.getY() > cover2.getY())
                {
                    //swap two person object
                    temp = covers[i];
                    covers[i] = covers[i + 1];
                    covers[i + 1] = temp;

                    exchange = true;
                }
            }
            bottom--;
        }

        int y = covers[0].getY();

        for ( int i = 0 ; i < covers.length ; i ++ )
        {
            covers[i].moveXY(covers[i].getX(), y);
            covers[i].setXY();
            y = covers[i].getY() + covers[i].getHeight() - 4;
        }

    }
    /*
    public void addObject ( Component component )
    {
        DiagramComponentSetPanel setPanel = null;
        if ( component instanceof DiagramComponentSetPanel )
            setPanel = (DiagramComponentSetPanel) component;
        else
        {
            addComponent((JComponent)component);
            return;
        }

        //CoverComponent coverComponent;

        if ( setPanel.getCover() != null )
        {
            setPanel.getCover().getParent().remove(setPanel.getCover());
        }

        CoverComponent  coverComponent = new CoverComponent ( component, 0, true );

        setPanel.setCover(coverComponent);
        coverComponent.addCoverListener();

        //Set pane component
        this.setPaneComponent.add( component );
        this.addComponentToPane( component, coverComponent);
    }*/
    /*
    public void addComponent ( JComponent jComponent )
    {
        CoverComponent coverComponent = new CoverComponent( jComponent, 4, true );

        if ( jComponent.getParent() != null )
            jComponent.getParent().remove(jComponent);

            //SUPER TEMP
        if ( jComponent instanceof CardPane )
        {
            DiagramComponentSetPanel firstSetPane = (DiagramComponentSetPanel) setPaneComponent.elementAt(0);
            firstSetPane.setCardPane( (CardPane) jComponent);
        }
        //Temp
        coverComponent.addCoverListener();
        //Temp

        //Other component
        this.otherComponents.add(jComponent);
        this.addComponentToPane(jComponent, coverComponent);
    }*/
    /*
    public void addActionComponent ( Component component )
    {
        CoverComponent coverComponent = new CoverComponent ( component, 4, false );

        //Action component
        this.actionComponent.add( component );
        this.addComponentToPane( component, coverComponent );
    }*/
    public void selectedObject ( Component component )
    {
        Object object = componentToCover.get(component);
        if ( object != null )
        {
            deselectAll();
            ((CoverComponent)object).getFocus(true);
        }
    }
    private void addComponentToPane ( DesignerComponent designerComponent, CoverComponent coverComponent )
    {
        coverComponent.getFocus(false);
        componentToCover.put(designerComponent, coverComponent);

        //DesignerComponetn must be a JComponent
        JComponent jComponent = (JComponent) designerComponent;

        upperPane.add( coverComponent );
        upperPane.add( jComponent );

        jComponent.repaint();
        DesignerControl.currentDesignPane.repaint();
        //UIDesigner.workPaneTab.repaint();

        deselectAll();
        coverComponent.getFocus(true);

        jComponent.setVisible(true);
        coverComponent.setVisible(true);
        deselectAll();
        if ( getParent() instanceof DesignUpperPane )
            coverComponent.getFocus(false);
        else
            coverComponent.getFocus(true);

        if (designerComponent instanceof DiagramComponentSetPanel )
            UIDesigner.getControl().setGenerateEnalble(((DiagramComponentSetPanel)designerComponent).getComboBox().isVisible());
        //this.upperPane.setVisible(false);
    }
}

class UpperPaneResizeListener extends MouseAdapter
{
    public void mousePressed(MouseEvent e)
    {
        System.out.println("PRESSED");
        DesignUpperPane pane = (DesignUpperPane) e.getSource();
        DesignPane designPane = ((DesignUpperPane)e.getSource()).getDesignPane();
        if ( designPane.getParent() != null &&
						designPane.getParent().getParent().getParent() !=null &&
            ! (designPane.getParent().getParent() instanceof CardPane) )
            pane.getDesignPane().showPaneResize(true);
        //DesignerControl.currentDesignPane = designPane;
    }

}
