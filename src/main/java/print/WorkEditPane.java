package print;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.awt.image.*;
/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class WorkEditPane extends JPanel implements  MouseListener, MouseMotionListener, KeyListener
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

    public WorkEditPane( ReportPage reportPage, int reportPageNumber,  WorkPane workPane, ControlPaneEdit controlPaneEdit )
    {
        this.reportPage = reportPage;
        this.workPane = workPane;
        this.controlPaneEdit = controlPaneEdit;
        this.reportPageNumber = reportPageNumber;

        init();
    }

    private void init()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        this.setBackground(Color.lightGray);
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


    }
    public void mouseEntered ( MouseEvent event )
    {
    }
    public void mouseExited  ( MouseEvent event )
    {
    }
    public void mousePressed ( MouseEvent event )
    {
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
        controlPaneEdit.setCursor ( workPane, x, y );
    }

    public void keyReleased ( KeyEvent event )
    {System.out.println(event.getKeyCode());
    }
    public void keyPressed ( KeyEvent event )
    {System.out.println(event.getKeyCode());
    }
    public void keyTyped ( KeyEvent event )
    {
        System.out.println(event.getKeyCode());
        controlPaneEdit.keyTyped( event.getKeyCode() );
    }
}