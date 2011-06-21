package designer.report;

import designer.*;
import print.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.image.*;
import java.awt.dnd.*;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class WorkEditPane extends JPanel implements  MouseListener, MouseMotionListener, KeyListener, DropTargetListener
{
    //Componet Page
    private ReportPage reportPage;
    private int reportPageNumber;

    //Work Pane (Layer)
    private WorkPane workPane;

    //Control
    private ControlPaneEdit controlPaneEdit;

    private int x;
    private int y;
    private int width;
    private int height;

    private boolean isDragging = false;
    private Component draggingObject = null;
    private DragBound dragBound= null;

    public WorkEditPane( ReportPage reportPage, int reportPageNumber,  WorkPane workPane, ControlPaneEdit controlPaneEdit )
    {
        this.reportPage = reportPage;
        this.workPane = workPane;
        this.controlPaneEdit = controlPaneEdit;
        this.reportPageNumber = reportPageNumber;

        this.dragBound = new DragBound();
        add(dragBound);
        DiagramSourceList.dropTarget1 = new DropTarget (this , this);
        init();
    }

    private void init()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        //this.setBackground(Color.lightGray);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension ( (int)reportPage.getPageFormat().getWidth() + 3 , (int)reportPage.getPageFormat().getHeight() + 3 ));
        this.setBounds( 0 , 0 , (int)reportPage.getPageFormat().getWidth() + 3 , (int)reportPage.getPageFormat().getHeight() + 3 );

    }
    public int getReportPageNumber ()
    {
        return reportPageNumber;
    }
    public void paint (Graphics g)
    {
        //super.paint (g);

        Dimension size = this.getPreferredSize();

        Graphics2D g2d = (Graphics2D) g;
        //Graphics g2d = g;
        //BufferedImage doubleBuffer = new BufferedImage (size.width, size.height, BufferedImage.TYPE_INT_RGB);
        //Graphics2D g2d = (Graphics2D) doubleBuffer.getGraphics ();

        g2d.setColor (Color.white);
        g2d.fillRect (0, 0, size.width, size.height);

        g2d.setColor(Color.darkGray);
        g2d.fillRect (size.width - 3, 3, 3, size.height - 3);
        g2d.fillRect (3, size.height - 3, size.width - 3, size.height + 3);

        g2d.setColor(Color.lightGray);
        g2d.fillRect (size.width - 3, 0, 3, 3);
        g2d.fillRect (0, size.height - 3, 3, 3);

        g2d.setColor (Color.darkGray);
        g2d.drawRect (0, 0, size.width -4, size.height -4);

        int iX = (int)reportPage.getPageFormat().getImageableX();
        int iY = (int)reportPage.getPageFormat().getImageableY();
        int iW = (int)reportPage.getPageFormat().getImageableWidth();
        int iH = (int)reportPage.getPageFormat().getImageableHeight();

        g2d.setColor (Color.lightGray);

        g2d.drawLine (iX,iY,iX - 25, iY );
        g2d.drawLine (iX,iY,iX, iY -25);

        g2d.drawLine (iX + iW,iY,iX + iW + 25, iY );
        g2d.drawLine (iX + iW,iY,iX + iW, iY - 25);

        g2d.drawLine (iX,iY + iH,iX - 25, iY + iH );
        g2d.drawLine (iX,iY + iH,iX, iY + iH + 25);

        g2d.drawLine (iX + iW,iY + iH,iX + iW + 25, iY + iH );
        g2d.drawLine (iX + iW,iY + iH,iX + iW, iY + iH + 25);

        try
        {
            reportPage.print ( g, reportPage.getPageFormat(), reportPageNumber);
        }
        catch (Exception PrintException)
        {
            PrintException.printStackTrace();
        }
        if ( controlPaneEdit.getWorkPane() == workPane)
        {
            g2d.setColor (Color.lightGray);
            controlPaneEdit.drawSelected(g2d);
        }

        if ( dragBound.isVisible() )
        {
            g2d.setColor(Color.gray);
            g2d.drawRect(dragBound.getX(), dragBound.getY(), dragBound.getWidth() - 1, dragBound.getHeight()- 1);
        }
    }
    public void mouseEntered ( MouseEvent event )
    {
    }
    public void mouseExited  ( MouseEvent event )
    {
    }
    public void mousePressed ( MouseEvent event )
    {
        System.out.println("Mouse pressed");
        repaint();
        //Globle X Y
        x = event.getX();
        y = event.getY();
        controlPaneEdit.setCurrentPane(workPane);
        controlPaneEdit.mousePressed(x, y);
        repaint();
    }
    public void mouseReleased( MouseEvent event )
    {
        repaint();
        //Not globle
        int x = event.getX();
        int y = event.getY();
        controlPaneEdit.mouseReleased(x, y);
        repaint();
    }
    public void mouseClicked( MouseEvent event )
    {
        controlPaneEdit.mouseClicked(x, y, event.getClickCount() == 2);
        repaint();
    }
    public void mouseDragged(MouseEvent event)
    {
        repaint();
        //Globle width, height
        width = event.getX() - x;
        height = event.getY() - y;
        controlPaneEdit.setDraggedSize(event.getX(), event.getY());
        repaint();
    }
    public void mouseMoved(MouseEvent event)
    {
        //Not globle
        int x = event.getX();
        int y = event.getY();
        controlPaneEdit.setCursor(workPane, x, y);
    }
    public void drop (DropTargetDropEvent event)
    {
        isDragging = false;
        dragBound.setVisible(false);
        System.out.println("DROP");

        controlPaneEdit.setCurrentPane(workPane);

        String text = "NO DATA"; // event.getTransferable().getTransferDataFlavors()[0].get
        try
        {
            text = event.getTransferable().getTransferData(event.getTransferable().getTransferDataFlavors()[0].stringFlavor).toString();
            //System.out.println("LENGHT: "+event.getTransferable().getTransferDataFlavors().length);
        }
        catch (Exception ex)
        {
            //System.out.println(event.getTransferable().getTransferDataFlavors().length);
        }

        if ( DiagramSourceList.selectedObject == null &&  DiagramSourceAnalysisList.selectedObject != null && DiagramSourceAnalysisList.selectedObject instanceof ChartSet )
        {
            //diagram.setObjectDiagram((Chart)charts.get(diagram.getObjectComponent().getID()));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(440, 330));
            panel.setSize(440, 330);
            panel.setBounds(0,0,440, 330);
            panel.setVisible(true);
            panel.setBackground(Color.lightGray);
            panel.setLayout(new BorderLayout());
            String s = ((ChartSet)DiagramSourceAnalysisList.selectedObject).getDisplay();
            JLabel label = new JLabel( "Simultation Chart " + s, JLabel.CENTER);
            label.setVisible(true);
            label.setPreferredSize(new Dimension(440,330));
            label.setSize(440, 330);
            label.setBounds(0,0,440, 330);
            label.setBackground(Color.lightGray);
            label.setOpaque(true);
            //panel.add(label, BorderLayout.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.darkGray));

            controlPaneEdit.getWorkPane().getReportPage().getDiagram()[0].setObjectDiagram(label, text);
            controlPaneEdit.getWorkPane().getReportPage().getDiagram()[0].setSize(440, 330);
            controlPaneEdit.getWorkPane().getReportPage().getDiagram()[0].repaint();
            controlPaneEdit.getWorkPane().repaint();
        }
        else
        {
            controlPaneEdit.addText(dragBound.getX(), dragBound.getY(), dragBound.getWidth(), dragBound.getHeight(), text);
        }
    }

    public void dragOver(DropTargetDragEvent event)
    {

        Component object = (Component) (DiagramSourceList.selectedObject);

        int x, y;

        x = event.getLocation().x;
        y = event.getLocation().y;

        int grid = controlPaneEdit.getGridSize();

        x = x - x % grid + grid / 2;
        y = y - y % grid + grid / 2;

        String text = "NO DATA"; // event.getTransferable().getTransferDataFlavors()[0].get
        try
        {
            DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) object;
            text = DiagramSourceList.stringSelection;

        }
        catch ( Exception ex ) {}

        Dimension d = controlPaneEdit.testSize(text);

        if ( DiagramSourceList.selectedObject == null &&  DiagramSourceAnalysisList.selectedObject != null && DiagramSourceAnalysisList.selectedObject instanceof ChartSet )
            d = new Dimension( 440, 330 );

        int width = d.width;
        int height = d.height;

        x = x - 10;
        y = y - height / 2;

        dragBound.setBounds(x, y, width, height);
        dragBound.setVisible(true);

    }
    public void dragExit (DropTargetEvent event)
    {
        isDragging = false;
        dragBound.setVisible(false);
        System.out.println("DRAG EXIT");

    }
    public void dragEnter (DropTargetDragEvent event)
    {
        isDragging = true;
        int x, y;

        x = event.getLocation().x;
        y = event.getLocation().y;

        int grid = controlPaneEdit.getGridSize();

        x = x - x % grid + grid / 2;
        y = y - y % grid + grid / 2;

        controlPaneEdit.setCurrentPane(workPane);
        Dimension d = controlPaneEdit.testSize(event.getCurrentDataFlavors().toString());

        if ( DiagramSourceList.selectedObject == null && DiagramSourceAnalysisList.selectedObject != null && DiagramSourceAnalysisList.selectedObject instanceof ChartSet )
            d = new Dimension( 440, 330 );

        int width = d.width;
        int height = d.height;

        x = x - 10;
        y = y - height / 2;

        dragBound.setBounds(x, y, width, height);
        dragBound.setVisible(true);
        System.out.println("DRAG ENTER");

        //event.acceptDrag (DnDConstants.ACTION_MOVE);
    }
    public void dropActionChanged ( DropTargetDragEvent event )
    {
    }
    public void keyReleased(KeyEvent event)
    {
    }

    public void keyPressed(KeyEvent event)
    {
        System.out.println(event.getKeyCode());
    }

    public void keyTyped(KeyEvent event)
    {
        System.out.println(event.getKeyCode());
        controlPaneEdit.keyTyped(event.getKeyCode());
    }
}

class DragBound extends JComponent
{
    /*
    public void paint ( Graphics g )
    {
        g.setColor(Color.gray);
        g.drawRect(0, 0, getSize().width-1, getSize().height-1);
    }*/
}