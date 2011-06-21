package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */

import java.awt.print.*;
import java.awt.*;
import java.io.Serializable;

public class ReportMultiPage implements Printable, Serializable
{
    private ReportPage[] reportPage;
    private int pagePerPage;
    private int pageDirection;
    private int numOfVer;
    private int numOfHor;
    private int boarder;
    /*public ReportMultiPage()
    {
        this ( new ReportPage[1], 1 );
    }*/
    public ReportMultiPage ( ReportPage[] reportPage, int hor, int ver, int direction, int boarder )
    {
        this.reportPage = reportPage;
        pageDirection = direction;
        numOfVer = ver;
        numOfHor = hor;
        this.boarder = boarder;
        pagePerPage = hor * ver;
    }
    public int print(Graphics g, java.awt.print.PageFormat pf, int pi) throws PrinterException
    {
        Graphics2D g2d = (Graphics2D) g;

        double scale, scale1, scale2;
        scale1 = (pf.getImageableHeight()/numOfVer)/reportPage[0].getPageFormat().getHeight();
        scale2 = (pf.getImageableWidth()/numOfHor)/reportPage[0].getPageFormat().getWidth();

        if ( scale1 < scale2 )
            scale = scale1;
        else
            scale = scale2;

        PageFormat pageFormat = reportPage[0].getPageFormat();

        g2d.translate( (pf.getWidth() - (pageFormat.getWidth() * scale   * numOfHor )) /2  , (pf.getHeight() - (pageFormat.getHeight() * scale  * numOfVer))/2 );

        //g2d.drawRect( (int)(pf.getImageableWidth() - (pageFormat.getWidth() * scale   * numOfHor )) /2  , (int)(pf.getImageableHeight() - (pageFormat.getHeight() * scale  * numOfVer))/2, 100,100 );

        g2d.scale(scale,scale);

        int currentHor, currentVer;
        for ( int i = 0 ; i < numOfHor*numOfVer ; i ++ )
        {
            //if ( pageDirection == DIRECTION_LEFT_RIGHT)
            if ( reportPage[i] == null )
                break;

            currentHor = i % numOfHor;
            currentVer = (int)Math.floor((double)i / (double) numOfHor );

            if ( pageDirection == DialogMultiPage.DIRECTION_DOWN )
            {
                currentVer = i % numOfVer;
                currentHor = (int)Math.floor((double)i / (double) numOfVer );
            }

            if ( numOfHor==1 ) currentHor = 0;
            if ( numOfVer==1 ) currentVer = 0;

            g2d.translate( pageFormat.getWidth() * currentHor , pageFormat.getHeight() * currentVer );
            reportPage[i].print(g2d, pageFormat, pi * pagePerPage + i );

            int borderWidth = (int)pageFormat.getWidth() ;//- 20 ;//- (int)pageFormat.getImageableX();
            int borderHeight = (int)pageFormat.getHeight() ;//- 20 ;//- (int)pageFormat.getImageableY();
            if ( boarder == DialogMultiPage.BOARDER_ON )
                g2d.drawRect( 0, 0, borderWidth, borderHeight);
            g2d.translate( -pageFormat.getWidth() * currentHor , -pageFormat.getHeight() * currentVer );
        }

        return Printable.PAGE_EXISTS;
    }/*
    public void setMultiPage ( int i )
    {
        pagePerPage = i;
    }
    /*
    public int getMultiPage ( )
    {
        return pagePerPage;
    }*
    public void setDirection ( int i)
    {
        pageDirection = i;
    }*/
}