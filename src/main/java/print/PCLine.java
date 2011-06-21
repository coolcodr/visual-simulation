package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.awt.*;
public class PCLine extends PComponent
{
    public PCLine()
    {
        this ( 0, 0, 0, 0);
    }
    public PCLine ( int x2, int y2, int width2, int height2 )
    {
        this.x = x2;
        this.y = y2;
        this.width = width2;
        this.height = height2;
    }
    public void print ( Graphics2D g2d )
    {
        //Graphics2D g2d = (Graphics2D) g;
        g2d.translate((double)x,(double)y);
        g2d.fillRect(0,0,width,height);
        g2d.translate(-(double)x,-(double)y);
    }
}