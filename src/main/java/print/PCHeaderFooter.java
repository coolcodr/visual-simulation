package print;

import javax.swing.*;
import java.awt.print.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.Serializable;

public class PCHeaderFooter implements Serializable
{
    private PageFormat pageFormat;
    private PCHeaderText pHeader;
    private PCHeaderText pFooter;

    private PCLine lineHeader;
    private PCLine lineFooter;

	public PCHeaderFooter( PageFormat pageFormat )
	{
        this.pageFormat = pageFormat;
	}
    public void setHeader ( StyledDocument styleDocument )
    {
        if ( pHeader == null )
        {
            pHeader = new PCHeaderText();
            pHeader.setSize((int)pageFormat.getImageableWidth(), 24);
            setHeaderBounds();
            setHeaderLine(true);
        }
        pHeader.setParagraph(styleDocument);
    }
    public StyledDocument getHeader ()
    {
        if ( pHeader != null )
            return pHeader.getParagraph();
        else
            return null;
            //return new JTextPane().getStyledDocument();
    }
    public void setFooter ( StyledDocument styleDocument )
    {
        if ( pFooter == null)
        {
            pFooter = new PCHeaderText();
            pFooter.setSize((int)pageFormat.getImageableWidth(), 24);
            setFooterBounds();
            setFooterLine(true);
        }
        pFooter.setParagraph(styleDocument);
    }
    public StyledDocument getFooter ()
    {
        if ( pFooter != null )
            return pFooter.getParagraph();
        else
            return null;
            //return new JTextPane().getStyledDocument();
    }
    public void removeHeader ()
    {
        pHeader = null;
        setHeaderLine(false);
    }
    public void removeFooter ()
    {
        pFooter = null;
        setFooterLine(false);
    }
    public void print ( Graphics2D g )
    {
        //setBounds();
        //Graphics2D g2 = (Graphics2D) g;


        if ( pHeader != null)
        {
            pHeader.print(g);
            setHeaderBounds();
        }
        if ( pFooter != null)
        {
            pFooter.print(g);
            setFooterBounds();
        }

        if ( pHeader != null && lineHeader != null )
        {
            lineHeader.setXY(0, getHeaderHeight() - 9 );
            lineHeader.setSize((int)pageFormat.getImageableWidth(), 1);
            lineHeader.print(g);
        }
        if ( pFooter != null && lineFooter != null )
        {
            lineFooter.setXY(0, (int)pageFormat.getImageableHeight() - getFooterHeight() + 9);
            lineFooter.setSize((int)pageFormat.getImageableWidth(), 1);
            lineFooter.print(g);
        }
    }
    public void setHeaderLine ( boolean b )
    {
        if (b)
            lineHeader = new PCLine ();
        else
            lineHeader = null;
    }
    public void setFooterLine ( boolean b )
    {
        if (b)
            lineFooter = new PCLine ();
        else
            lineFooter = null;
    }
    public void setPageFormat ( PageFormat pageFormat )
    {
        this.pageFormat = pageFormat;
    }
    public int getHeaderHeight ()
    {
        if ( pHeader == null )
            return 10;
        else
            return pHeader.getHeight() + 10;
    }
    public void setHeaderHeight( int i )
    {
        pHeader.setSize(pHeader.getWidth(), i);
        setHeaderBounds();
    }
    public void setFooterHeight( int i )
    {
        pFooter.setSize(pFooter.getWidth(), i);
        setFooterBounds();
    }
    public void setFooterY ( int y )
    {
        pFooter.setXY(pFooter.getX(), y);
    }
    public int getFooterY()
    {
        return pFooter.getY();
    }
    public int getHeaderY()
    {
        return pHeader.getY();
    }
    public boolean getHeaderLine()
    {
        return (lineHeader != null);
    }
    public boolean getFooterLine()
    {
        return (lineFooter !=null);
    }
    public int getFooterHeight ()
    {
        if ( pFooter == null )
            return 10;
        else
            return pFooter.getHeight() + 10;
    }
    private void setHeaderBounds()
    {
        pHeader.setSize((int)pageFormat.getImageableWidth(), pHeader.getHeight());
        if ( pHeader.getHeight()<24)
            pHeader.setSize((int)pageFormat.getImageableWidth(), 24);
        if ( pHeader.getHeight()>100)
            pHeader.setSize((int)pageFormat.getImageableWidth(), 100);
        pHeader.setXY(0, 0);
    }
    private void setFooterBounds()
    {
        pFooter.setSize((int)pageFormat.getImageableWidth(), pFooter.getHeight());

        if ( pFooter.getHeight()<24)
            pFooter.setSize((int)pageFormat.getImageableWidth(), 24);

        if ( pFooter.getHeight()>100)
            pFooter.setSize((int)pageFormat.getImageableWidth(), 100);

        pFooter.setXY(0, (int)pageFormat.getImageableHeight() - pFooter.getHeight());
    }
}