package print;

import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.print.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.text.*;
import java.io.Serializable;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ReportPage implements Printable, Serializable
{
    private static final float EXPAND_FACTOR = 1.5f;

    //PageFormat
    private PageFormat pageFormat;

    private PCHeaderFooter pCHeaderFooter;
    //private PCHeaderText pcPageNumber;

    //For printing page number
    private int totalPageNumber;
    private int startPageNumber;
    private boolean preview;

    //Page component
    private PCDiagram[] pcDiagram;
    private int numOfPCDiagram;
    private PCParagraph[] pcParagraph;
    private int numOfPCParagraph;
    private PCPageNumber pcPageNumber;
    private PCPageNumber pcDateTime;

    //A variable keep track of update of page number
    //is to prevent alway update when repaint
    private boolean pageUpdate;

    public void setHeaderFooter ( PCHeaderFooter pcHeaderFooter )
    {
        pCHeaderFooter = pcHeaderFooter;
    }
    public ReportPage( PCHeaderFooter headerFooter )
    {
        pageFormat = new PageFormat();
        pCHeaderFooter = headerFooter;//new PCHeaderFooter(pageFormat);
        pcDiagram = new PCDiagram[2];
        numOfPCDiagram = 0;
        pcParagraph = new PCParagraph[2];
        numOfPCParagraph = 0;
        pcPageNumber = new PCPageNumber();
        pcDateTime =  new PCPageNumber();

        pageUpdate = true;
        ///////////////////////////////////////////TEMP///////////////////////////////////////////
        /*
        String tempS = new String("needs\nto know the rendering hints for the targeted device and the font ");
        //String tempH = new String("Page 10 of 100");
        String tempH = new String("Testing Header and Footer");
        JTextPane jTextPane1 = new JTextPane();
        jTextPane1.setText(tempH);
        StyledDocument styleddocument = jTextPane1.getStyledDocument();

        Style style1 = styleddocument.addStyle("bold", null);
        StyleConstants.setFontFamily(style1, "SansSerif");
        StyleConstants.setForeground(style1, Color.red);

        Style style2 = styleddocument.addStyle("beauty", null);
        StyleConstants.setAlignment(style2, StyleConstants.ALIGN_RIGHT);

        Style styleLEFT = styleddocument.addStyle("LEFT", null);
        StyleConstants.setAlignment(styleLEFT, StyleConstants.ALIGN_LEFT);
        Style styleCENTER = styleddocument.addStyle("CENTER", null);
        StyleConstants.setAlignment(styleCENTER, StyleConstants.ALIGN_CENTER);
        Style styleRIGHT = styleddocument.addStyle("RIGHT", null);
        StyleConstants.setAlignment(styleRIGHT, StyleConstants.ALIGN_RIGHT);

        styleddocument = jTextPane1.getStyledDocument();

        //styleddocument.setParagraphAttributes(1,8,style1,false);
        //styleddocument.setParagraphAttributes(1,8,style2,false);

        //styleddocument.removeStyle("beauty");

        styleddocument.setParagraphAttributes(0,108,styleLEFT,false);
        //styleddocument.setParagraphAttributes(1,8,styleRIGHT,false);
        //styleddocument.setParagraphAttributes(0,108,styleRIGHT,false);

        pCHeaderFooter.setHeader(styleddocument);
        pCHeaderFooter.setFooter(styleddocument);

        pCHeaderFooter.setHeaderLine(true);
        pCHeaderFooter.setFooterLine(true);
        */
        //pCHeaderFooter.setHeaderHeight(50);
        //pCHeaderFooter.setFooterHeight(70);

        //pCHeaderFooter.removeFooter();
        //pCHeaderFooter.removeHeader();

    }
    public void addDiagram ( ObjectComponent component )
    {
        pcDiagram[0] = new PCDiagram( component, this );
        numOfPCDiagram++;
    }
    public void addParagraph ( StyledDocument styledDocument, int pi, int x, int y, int width, int height )
    {
        if ( numOfPCParagraph == this.pcParagraph.length )
			expandParagraph();
        pcParagraph[numOfPCParagraph] = new PCParagraph( styledDocument, this, pi );
        pcParagraph[numOfPCParagraph].setXY(x - (int)pageFormat.getImageableX() - 2, y - (int) pageFormat.getImageableX() - 2);
        pcParagraph[numOfPCParagraph].setSize(width, height);
        pcParagraph[numOfPCParagraph].setParagraph(styledDocument);
        numOfPCParagraph++;
    }

    public PCDiagram[] getDiagram ()
    {
        PCDiagram tempPCDiagram[] = new PCDiagram[numOfPCDiagram];
      	for (int i = 0; i < numOfPCDiagram ; i++)
      	{
        	tempPCDiagram[i] = pcDiagram[i];
      	}
        return tempPCDiagram;
    }
    public PCParagraph[] getParagraph()
    {
        PCParagraph tempPCParagraph[] = new PCParagraph[numOfPCParagraph];
      	for (int i = 0; i < numOfPCParagraph ; i++)
      	{
        	tempPCParagraph[i] = pcParagraph[i];
      	}
        return tempPCParagraph;
    }
    public PageFormat getPageFormat ()
    {
        return pageFormat;
    }
    public void setPageFormat ( PageFormat pageFormat )
    {
        this.pageFormat = pageFormat;
        pcDiagram[0].calculatePages();




        pCHeaderFooter.setPageFormat(pageFormat);
        pcPageNumber.setPageFormat(pageFormat);
        pcDateTime.setPageFormat(pageFormat);
    }
    public void setPageNumberFormat ( PCPageNumber pcPageNumber )
    {
        this.pcPageNumber = pcPageNumber;
    }
    public void setDateTimeFormat ( PCPageNumber pcPageNumber )
    {
        this.pcDateTime = pcPageNumber;
    }

    private void expandParagraph()
   	{
      	int newSize = (int) (pcParagraph.length * EXPAND_FACTOR);

      	PCParagraph tempPCParagraph[] = new PCParagraph[newSize];
      	for (int i = 0; i < pcParagraph.length; i++)
      	{
        	tempPCParagraph[i] = pcParagraph[i];
      	}
      	pcParagraph = tempPCParagraph;
   	}
    public int getHeaderHeight()
    {
        return pCHeaderFooter.getHeaderHeight();
    }
    public int getFooterHeight()
    {
        return pCHeaderFooter.getFooterHeight();
    }
    public int getHeaderY()
    {
        return pCHeaderFooter.getHeaderY();
    }
    public int getFooterY()
    {
        return pCHeaderFooter.getFooterY();
    }
    public boolean getHaveHeaderLine()
    {
        return pCHeaderFooter.getHeaderLine();
    }
    public boolean getHaveFooterLine()
    {
        return pCHeaderFooter.getFooterLine();
    }
    public StyledDocument getHeader()
    {
        return pCHeaderFooter.getHeader();
    }
    public StyledDocument getFooter()
    {
        return pCHeaderFooter.getFooter();
    }
    public void setHeaderHeight ( int i )
    {
        pCHeaderFooter.setHeaderHeight( i );
    }
    public void setFooterHeight ( int i )
    {
        pCHeaderFooter.setFooterHeight( i );
    }
    public void setFooterY ( int y )
    {
        pCHeaderFooter.setFooterY( y );
    }
    public void setHeader( StyledDocument styledDocument )
    {
        if ( styledDocument == null)
            pCHeaderFooter.removeHeader();
        else
            pCHeaderFooter.setHeader(styledDocument);
    }
    public void setHaveHeaderLine ( boolean b )
    {
        pCHeaderFooter.setHeaderLine(b);
    }
    public void setHaveFooterLine ( boolean b )
    {
        pCHeaderFooter.setFooterLine(b);
    }
    public void setFooter(StyledDocument styledDocument )
    {
        if ( styledDocument == null)
            pCHeaderFooter.removeFooter();
        else
            pCHeaderFooter.setFooter(styledDocument);
    }
    public int getMaxImagaebleHeight()
    {
        return (int)pageFormat.getImageableHeight() - pCHeaderFooter.getFooterHeight();
    }
    public int getNumOfPage()
    {
        return pcDiagram[0].getNumOfPages();
    }
    public void setTotalPageNumber( int i)
    {
        totalPageNumber = i;
    }
    public void setStartPageNumner ( int i )
    {
        startPageNumber = i;
    }
    public void setPreview ( boolean b )
    {
        preview = b;
    }
    public void setPageUpdate ( boolean b )
    {
        pageUpdate = b;
    }
    public void removeParagraph ( PCParagraph pcParagraph )
    {
        int removeIndex = 0;
        for ( int i = 0 ; i < numOfPCParagraph ; i ++ )
            if ( this.pcParagraph[i] == pcParagraph )
                removeIndex = i;
        for ( int i = removeIndex ; i < numOfPCParagraph - 1 ; i ++ )
            this.pcParagraph[i] = this.pcParagraph[i+1];
        this.pcParagraph[numOfPCParagraph-1] = null;
        numOfPCParagraph--;
    }
    /*
    public void updatePageNumberAndTime ( int pageNumber, PageFormat pf )
    {
        //pcPageNumber.getpcPageNumber.
        JTextPane style = pcPageNumber.getStyle();
        String s1 = pcPageNumber.getFirstString();
        String s2 = pcPageNumber.getSecondString();
        pcPageNumber = new PCPageNumber();
        pcPageNumber.addPageNumber(style);
        pcPageNumber.setPageFormat(pf);
        pcPageNumber.setFirstString(s1);
        pcPageNumber.setSecondString(s2);
        pcPageNumber.setText( pageNumber, totalPageNumber );
        pcDateTime.setDateTime();
    }*/
    public int print(Graphics g, java.awt.print.PageFormat pf, int pi) throws PrinterException
	{
        Graphics2D g2 = (Graphics2D) g;
        //Graphics2D g2 = g;

        g2.translate( pf.getImageableX() , pf.getImageableY() );

        pCHeaderFooter.print(g2);

        //addPageNumber(styleddocument,0);
        //styleddocument.getLogicalStyle(0).
        //pcPageNumber.print(g2);
        //new PCParagraph(styleddocument).print(g2);
        ///////////////////////////////////////////TEMP///////////////////////////////////////////
        //String tempH = new String("Page 10 of 100");
        /*
        String tempH = new String("Page 10 of 28");

        JTextPane jTextPane1 = new JTextPane();

        MutableAttributeSet attr = new SimpleAttributeSet();
        //StyleConstants.setBold (attr, true);
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT );
        //StyleConstants.setFontSize(attr, 14);
        //StyleConstants.setFontFamily(attr,"Times New Roman");
        jTextPane1.setParagraphAttributes(attr, false);

        pcPageNumber.addPageNumber(jTextPane1);
        pcPageNumber.setPageNumberLocation(PCPageNumber.AT_FOOTER);
        */

        int pageNumber;

        if ( preview )
        {
            pageNumber = startPageNumber + pi + 1;
            //pi = pi;
        }
        else
        {
            pageNumber =  pi + 1;
            pi = pi - startPageNumber;
        }

        if ( pageUpdate )
        {
            pcPageNumber.setPageFormat((PageFormat)pf);
            pcDateTime.setPageFormat((PageFormat)pf);
            pcPageNumber.setText( pageNumber, totalPageNumber );
            pcDateTime.setDateTime();
        }
        pageUpdate = false;
        pcPageNumber.setText2( pageNumber, totalPageNumber );

        //if ( preview )
            pcDiagram[0].print(g2, pi);
        //else
            //pcDiagram[0].print(g2, pi - startPageNumber);

        for ( int i = 0 ; i < numOfPCParagraph ; i ++ )
        {
            if ( pcParagraph[i].getPI() == pi )
                pcParagraph[i].print(g2);
        }

        //g2.translate( 0 , -pCHeaderFooter.getHeaderHeight());

        pcPageNumber.print(g2);
        pcDateTime.print(g2);

        g2.translate( - pf.getImageableX() , - pf.getImageableY() );

        return Printable.PAGE_EXISTS;
    }

    public static void disableDoubleBuffering(Component c)
	{
    	RepaintManager currentManager = RepaintManager.currentManager(c);
    	currentManager.setDoubleBufferingEnabled(false);
  	}

  	public static void enableDoubleBuffering(Component c)
  	{
    	RepaintManager currentManager = RepaintManager.currentManager(c);
    	currentManager.setDoubleBufferingEnabled(true);
  	}
}