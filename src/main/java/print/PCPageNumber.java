package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */

import java.awt.*;
import javax.swing.*;
import java.lang.*;
import javax.swing.text.*;
import java.awt.print.*;
import java.util.*;
import java.text.*;
import java.io.Serializable;

public class PCPageNumber implements Serializable
{
    public static final int AT_HEADER = 0;
    public static final int AT_FOOTER = 1;

    private int pageNumberLocation;
    private boolean haveTotalNumber;
    private PCHeaderText pcPageNumber;

    private String firstString;
    private String secondString;

    private PageFormat pageFormat;

    private JTextPane style;

    private String dateTimeFormat[];

    private JTextPane textPane;

    public PCPageNumber()
    {
        pageNumberLocation = AT_FOOTER;
        textPane = new JTextPane();
    }
    public void addPageNumber ( JTextPane jTextPane )
    {
        if ( pcPageNumber == null )
            pcPageNumber = new PCHeaderText();

        style = jTextPane;
    }
    protected JTextPane getStyle ()
    {
        return style;
    }
    public void removePageNumber ()
    {
        pcPageNumber = null;
    }
    public void setDateTimeFormat( String[] s )
    {
        dateTimeFormat = s;
    }
    public void setText ( int current, int total )
    {
        if ( pcPageNumber == null )
            return;
        String text ;
        if ( haveTotalNumber )
            text =  firstString + " " + String.valueOf(current) + " " + secondString + " " + String.valueOf(total);
        else
            text =  firstString + " " + String.valueOf(current);
        setString ( text );
    }
    public void setText2 ( int current, int total )
    {
        if ( pcPageNumber == null )
            return;
        String text ;
        if ( haveTotalNumber )
            text =  firstString + " " + String.valueOf(current) + " " + secondString + " " + String.valueOf(total);
        else
            text =  firstString + " " + String.valueOf(current);

        textPane.setText(text);
        //StyledDocument styledDocument = textPane.getStyledDocument();
        //styledDocument.setParagraphAttributes(0,1, style.getParagraphAttributes(), true);
        pcPageNumber.setParagraph(textPane.getStyledDocument());

        FontMetrics fontMetrics = style.getFontMetrics(style.getStyledDocument().getFont(style.getParagraphAttributes()));

        int textWidth =  fontMetrics.stringWidth(text) + 10;
        int textHeight = fontMetrics.getHeight() + fontMetrics.getDescent();

        if ( textHeight < 24 ) textHeight = 24;
        pcPageNumber.setSize(textWidth,textHeight);
    }
    public void setDateTime ()
    {
        if ( pcPageNumber == null)
            return;
        String dateTime  = new String();
        if ( dateTimeFormat[0].compareToIgnoreCase("None") != 0 )
        {
            DateFormat formatter = new SimpleDateFormat(dateTimeFormat[0]);
            dateTime = formatter.format(java.util.Calendar.getInstance().getTime());
        }
        if ( dateTimeFormat[1].compareToIgnoreCase("None") != 0 )
        {
            DateFormat formatter = new SimpleDateFormat(dateTimeFormat[1]);
            dateTime = dateTime.concat(" ");
            dateTime = dateTime.concat( formatter.format(java.util.Calendar.getInstance().getTime()) );
        }
        setString ( dateTime );
    }
    public void setString ( String s )
    {
        String text = s;

        textPane.setText(text);

        StyledDocument styledDocument = textPane.getStyledDocument();
        styledDocument.setParagraphAttributes(0,1, style.getParagraphAttributes(), true);

        pcPageNumber.setParagraph(styledDocument);

        FontMetrics fontMetrics = style.getFontMetrics(styledDocument.getFont(style.getParagraphAttributes()));

        int textWidth =  fontMetrics.stringWidth(text) + 10;
        int textHeight = fontMetrics.getHeight() + fontMetrics.getDescent();

        if ( textHeight < 24 ) textHeight = 24;
        pcPageNumber.setSize(textWidth,textHeight);

        String alignment = String.valueOf(style.getParagraphAttributes().getAttribute(StyleConstants.Alignment));

        switch (pageNumberLocation)
        {
            case AT_HEADER:
                if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_LEFT)) == 0 )
                    pcPageNumber.setXY(0, 0);
                else if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_CENTER)) == 0 )
                    pcPageNumber.setXY((int)pageFormat.getImageableWidth() / 2 - textWidth / 2 , 0);
                else if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_RIGHT)) == 0 )
                    pcPageNumber.setXY((int)pageFormat.getImageableWidth() - textWidth, 0);
                break;
            case AT_FOOTER:
                int y = (int)pageFormat.getImageableHeight() - pcPageNumber.getHeight();
                if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_LEFT)) == 0 )
                    pcPageNumber.setXY(0, y);
                else if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_CENTER)) == 0 )
                    pcPageNumber.setXY((int)pageFormat.getImageableWidth() / 2 - textWidth / 2 , y);
                else if (  alignment.compareTo(String.valueOf(StyleConstants.ALIGN_RIGHT)) == 0 )
                    pcPageNumber.setXY((int)pageFormat.getImageableWidth() - textWidth, y);
                break;
        }
    }
    public void setPageFormat ( PageFormat pageFormat )
    {
        this.pageFormat = pageFormat;
    }
    public void print ( Graphics2D g2 )
    {
        if ( pcPageNumber != null )
            pcPageNumber.print(g2);
    }
    public void setPageNumberLocation ( int i )
    {
        pageNumberLocation = i;
    }
    public void setFirstString ( String s )
    {
        firstString = s;
    }
    public void setSecondString ( String s )
    {
        secondString = s;
    }
    protected String getFirstString ()
    {
        return firstString;
    }
    protected String getSecondString()
    {
        return secondString;
    }
    public void setHaveTotalNumber ( boolean b )
    {
        haveTotalNumber = b;
    }
}