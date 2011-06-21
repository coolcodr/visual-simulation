package designer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class DesignUpperPane extends JPanel implements DropTargetListener
{
    //DropTarget dropTarget = null;
    //private DiagramComponentSetPanel ownerSetPanel = null;

    private UpperPaneControl paneControl;
    private DragBound dragBound;

    private int grid;
    private DesignPane designPane;

    private UpperPaneMouseListener defaultControl;
    private ElementProperties properties;

	private int dx, dy;
	private boolean isDragging;

    public DesignUpperPane( DesignPane designPane )
    {
        this.designPane = designPane;
       // this.ownerSetPanel = ownerSetPanel;
        //dropTarget = new DropTarget (DesignerUI.currentDesignPane, DesignerUI.currentDesignPane);
        this.setOpaque(false);
        this.setLayout(null);

        defaultControl = new UpperPaneMouseListener();
        this.addMouseListener(defaultControl);
        this.addMouseMotionListener(defaultControl);

        this.addKeyListener(new KeyControl());

        dragBound = new DragBound();
        dragBound.setVisible(false);
        add(dragBound);
        setProperties( PropertiesFactory.createDesignPaneProperties(designPane) );

        isDragging = false;
    }
    public void setProperties ( ElementProperties properties ){
        this.properties = properties;
    }
    public ElementProperties getProperties (){
        return properties;
    }

    public DesignPane getDesignPane()
    {
        return designPane;
    }
    public JComponent getDrawBound()
    {
        return dragBound;
    }
    public void dragEnter (DropTargetDragEvent event)
    {
        Component object = (Component) (DiagramSourceList.selectedObject);

        if (!isDragging)
        {
            if ( object != null && object.getParent() != null && object.getParent().equals(this))
            {
                dx = (int) event.getLocation().getX();
                dy = (int) event.getLocation().getY();
                isDragging = true;
            }
            else
            {
                dx = event.getLocation().x;
                dy = event.getLocation().y;
                isDragging = true;
            }
        }

        event.acceptDrag (DnDConstants.ACTION_MOVE);
        grid = DesignerControl.getGridSize();
        //dragBound.setVisible(true);

        DesignerControl.currentDesignPane = designPane;

        if ( designPane instanceof DesignerComponent )
        {
            ((DesignerComponent)designPane).getCover().getHighLight(Color.blue);
        }
        else if ( designPane.getParent() instanceof CardPane )
        {
            ((CardPane)designPane.getParent()).getCover().getHighLight(Color.blue);
        }
    }
    public void remove ( Component component )
    {
        super.remove(component);
        designPane.coverComponent.remove(component);
        designPane.setPaneComponent.remove(component);
        designPane.cardPanes.remove(component);
        this.updateUI();
        this.repaint();
        //deleteComponent(component);
    }

    public void deleteComponent ( Component component )
    {
        Vector panes = designPane.coverComponent;
        for ( int i = 0 ; i < panes.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent) panes.elementAt(i);
            if ( cover.getRelateComponent().equals(component) )
            {
                System.out.println("Deleted");
                this.remove(cover);
                return;
            }
        }
    }
    public void dragExit (DropTargetEvent event)
    {
        dragBound.setVisible(false);
        if ( designPane instanceof DesignerComponent )
            ( (DesignerComponent) designPane).getCover().getHighLight(false);
        else if ( designPane.getParent() instanceof CardPane )
            ((CardPane)designPane.getParent()).getCover().getHighLight(false);

    }

    public void dragOver (DropTargetDragEvent event)
    {
        //System.out.println(event.getLocation());
        //DiagramComponentSetPanel object = (DiagramComponentSetPanel) (DiagramSourceList.selectedObject);

        Component object = (Component) (DiagramSourceList.selectedObject);
        if ( object == null )
            return;
        //CoverComponent cover;
        //if ( object instanceof CoverComponent )
        if ( ((DesignerComponent)object).getCover() != null )
			object = (CoverComponent) ((DesignerComponent)object).getCover();

		int x2, y2;
		int x, y;

		x2 = event.getLocation().x;
		y2 = event.getLocation().y;

		if ( object.isVisible() && ! DiagramSourceList.isSelected && object.getParent() != null && object.getParent().equals(this) )
		{
			x = object.getX() + x2 - dx;
			y = object.getY() + y2 - dy;
		}
		else
		{
        	x = (int) event.getLocation().getX() - 10;//- object.getWidth() / 2;
        	y = (int) event.getLocation().getY() - object.getHeight() / 2;
		}


        int width = object.getWidth();
        int height = object.getHeight();
        x = x - x % grid + grid / 2 ;
        y = y - y % grid + grid / 2 ;

        dragBound.setBounds( x, y, width, height);
		dragBound.setVisible(true);
    }

    public void setControl ( UpperPaneControl paneControl )
    {
        if ( this.paneControl == null )
        {
            this.paneControl = paneControl;
            //this.removeMouseListener(defaultControl);
            //this.removeMouseMotionListener(defaultControl);
            this.addMouseListener(paneControl);
            this.addMouseMotionListener(paneControl);
        }
    }
    public void resetControl ()
    {
        this.removeMouseListener(paneControl);
        this.removeMouseMotionListener(paneControl);
        paneControl = null;
        //this.addMouseListener(defaultControl);
        //this.addMouseMotionListener(defaultControl);

        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    public void drop (DropTargetDropEvent event)
    {
        Component object1 = (Component) (DiagramSourceList.selectedObject);
        if ( object1 == null )
            return;

    	isDragging = false;

        if ( designPane instanceof DesignerComponent )
            ( (DesignerComponent) designPane).getCover().getHighLight(false);
        else if ( designPane.getParent() instanceof CardPane )
            ((CardPane)designPane.getParent()).getCover().getHighLight(false);


        //try {
            Transferable transferable = event.getTransferable();

            // we accept only Strings
            if (transferable.isDataFlavorSupported (DataFlavor.stringFlavor)){

                event.acceptDrop(DnDConstants.ACTION_MOVE);
                String s = "";//(String)transferable.getTransferData ( DataFlavor.stringFlavor);

                //JButton jButton = new JButton();
                //jButton.setBounds(10,10,50,100);
                //jButton.setVisible(true);

                //DiagramComponentSetPanel object = (DiagramComponentSetPanel) (DiagramSourceList.selectedObject);

                Component object = (Component) (DiagramSourceList.selectedObject);
                object.setVisible(false);

				CoverComponent object2;
				if ( ((DesignerComponent)object).getCover() != null )
				{
					object2 = (CoverComponent)((DesignerComponent)object).getCover();
					object2.moveXY(dragBound.getX(), dragBound.getY());
                	object2.setXY();
            	}
            	else
            	{
            		object.setBounds(dragBound.getX(),dragBound.getY(),dragBound.getWidth(),dragBound.getHeight());
            	}
				//if ( object instanceof

                //System.out.println(DiagramSourceList.selectedObject.getClass().toString());
                //object.setBounds(10,10,object.getWidth(),object.getHeight());
                //object.setBounds((int)event.getLocation().getX() - object.getWidth() / 2 ,(int)event.getLocation().getY() - object.getHeight()/2,object.getWidth(),object.getHeight());
				//object2.setBounds2(dragBound.getX(), dragBound.getY(), dragBound.getWidth(), dragBound.getHeight());


                if ( object instanceof DesignerComponent )
                {
                    designPane.addDesignComponent( (DesignerComponent) object);
                }

                //Not used this method la may be hehe...
                //if ( ownerSetPanel != null)
                    //;//ownerSetPanel.addNestElement(object);

                event.getDropTargetContext().dropComplete(true);
                //if ( object instanceof DiagramComponentSetPanel )
                    //((DiagramComponentSetPanel)object).deselectInternal();

                dragBound.setVisible(false);
            }
            else{
                event.rejectDrop();
            }
		DiagramSourceList.isSelected = false;

        //}
        /*
        catch (IOException exception) {
            exception.printStackTrace();
            System.err.println( "Exception" + exception.getMessage());
            event.rejectDrop();
        }
        catch (UnsupportedFlavorException ufException ) {
            ufException.printStackTrace();
            System.err.println( "Exception" + ufException.getMessage());
            event.rejectDrop();
        }*/
    }
    public void dropActionChanged ( DropTargetDragEvent event )
    {
    }
    public void paint (Graphics g)
    {
        super.paint(g);
        g.setColor(Color.gray);
        if (getParent() != null &&
            getParent().getParent() != null &&
						getParent().getParent().getParent() !=null &&
            ! (getParent().getParent() instanceof DesignUpperPane) &&
            ! (getParent().getParent().getParent() instanceof CardPane))
           g.drawRect(0,0,getWidth()-1,getHeight()-1);
    }

}


class UpperPaneMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener
{
    private int dx;
    private int dy;
    private int ex;
    private int ey;
    private int width;
    private int height;
    private DesignPane designPane;
    private DesignUpperPane upperPane;
    private JComponent drawBound;

    public void mousePressed(MouseEvent e)
    {

        dx = e.getX();
        dy = e.getY();

        upperPane = (DesignUpperPane)e.getSource();
        designPane = upperPane.getDesignPane();
        drawBound = upperPane.getDrawBound();

        //DesignerControl.currentDesignPane.deselectAll();
        DesignerControl.currentDesignPane = designPane;
        DesignerControl.currentDesignPane.deselectAll();

        designPane.selectThis(upperPane.getProperties());

        upperPane.requestFocus();
        //upperPane = (DesignUpperPane) e.getSource();
        //designPane = upperPane.getDesignPane();
        //DesignerControl.currentDesignPane = pane.getDesignPane();
        //designPane.deselectAll();
        //DesignerControl.currentDesignPane.showPaneResize(true);
    }
    public void mouseReleased(MouseEvent e)
    {
        if ( !drawBound.isVisible() )
            return;
        drawBound.setVisible(false);
        dx = drawBound.getX();
        dy = drawBound.getY();
        ex = dx + drawBound.getWidth();
        ey = dy + drawBound.getHeight();
        int cx, cy, cx2, cy2;

        Vector coverComponents = null;
        coverComponents = designPane.coverComponent;
        int count = 0;
        for ( int i = 0 ; i < coverComponents.size() ; i ++ )
        {
            CoverComponent cover = (CoverComponent)coverComponents.elementAt(i);
            cx = cover.getX();
            cy = cover.getY();
            cx2 = cx + cover.getWidth();
            cy2 = cy + cover.getHeight();

            if ( cx > dx && cy > dy && cx2 < ex && cy2 < ey )
            {
                //System.out.println("ASD");
                count++;
                cover.getFocus(true);
            }
            System.out.println("COUNT: " + count);
        }

        if ( count > 1 )
            DesignerControl.setEditButtonEnable(true);

    }
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        width = x - dx;
        height = y - dy;
        /*
        if ( width < 0 )
        {
            width = dx - e.getX();
            dx = e.getX();
        }
        if ( height < 0 )
        {
            height = dy - e.getX();
            dy = e.getX();
        }*/

        drawBound.setBounds(dx < x ? dx : x , dy < y ? dy : y, dx < x ? width : -width, dy < y ? height : -height);
        drawBound.setVisible(true);
        drawBound.repaint();
    }
    public void mouseMoved(MouseEvent e)
    {
    }
}

class DragBound extends JComponent
{
    public void paint ( Graphics g )
    {
        g.setColor(Color.gray);
        g.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }
    /*
    public void setBounds ( int x, int y, int width, int height )
    {
        if ( x < 0 )
            x = 0;
        if ( y < 0 )
            y = 0;
        super.setBounds(x, y, width, height);

    }
    public void setLocation ( int x, int y )
    {
        if ( x < 0 )
            x = 0;
        if ( y < 0 )
            y = 0;
        super.setLocation(x, y);
    }*/
}
