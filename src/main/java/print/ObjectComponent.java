package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */
import chart.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public class ObjectComponent extends JComponent
{
    BufferedImage image;
    double scale;
    Component component;
    String id = "";

    public ObjectComponent( Component component, String id )
    {
        this(component);
        this.id = id;
    }
    public String getID()
    {
        return id;
    }
    public void setID ( String id )
    {
        this.id = id;
    }
    public ObjectComponent( Component component )
    {
        this.component = component;
        this.id = id;
        this.image = new BufferedImage(component.getWidth()+1, component.getHeight()+1, BufferedImage.TYPE_INT_ARGB);
        this.setPreferredSize(new Dimension(image.getWidth(this),image.getHeight(this)));
        System.out.println("PRS SIZE: "+this.getPreferredSize());
        //this.setPreferredSize(new Dimension(component.getPreferredSize().width, component.getPreferredSize().height));
        this.setLayout(new FlowLayout());

        component.paint(image.getGraphics());
        setScale(1);
    }
    public void paint(Graphics g)
    {
        //super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale,scale);
        //if ( image != null)
            //g.drawImage( image, 0, 0, this );
        component.paint(g2d);

        g2d.scale(1/scale,1/scale);
    }
    public void restoreImage ()
    {
        this.image = new BufferedImage(component.getWidth()+1, component.getHeight()+1, BufferedImage.TYPE_INT_ARGB);
        component.paint(image.getGraphics());
        setScale(scale);
    }
    public void addComponent ()
    {
        add(component);
        /*
        ((JComponent)component).updateUI();
        ((JComponent)component).repaint();
        updateUI();
        repaint();*/
    }
    public Chart getChart ()
    {
        if ( component instanceof Chart )
            return (Chart)component;
        return null;
    }
    public void nullifyImage ()
    {
        image = null;
        if ( component instanceof Chart )
        	((Chart)component).prepareImage();
    }
    public void setChart ( JPanel panel )
    {
        this.component = panel;
        //this.setBounds(0,0,panel.getWidth()+100,panel.getHeight()+100);
        /*
        int width = getWidth();
        int height = getHeight();

        int scale1 = (int) ( ( (double) width / (double) panel.getWidth()) * 100);
        int scale2 = (int) ( ( (double) height / (double) panel.getHeight()) * 100);

        if (scale1 < scale2)
            setScale(scale1);
        else
            setScale(scale2);*/

    }

    public void setScale ( double scale )
    {
        image = new BufferedImage((int)(Math.ceil(component.getWidth()*scale))+10,(int)(Math.ceil(component.getHeight()*scale))+10,BufferedImage.TYPE_INT_ARGB);
        this.setPreferredSize(new Dimension(image.getWidth(this)-8,image.getHeight(this)-8));

		//this.setPreferredSize(new Dimension(component.getPreferredSize().width, component.getPreferredSize().height));
        this.scale = scale;/*
        Graphics2D g2 = (Graphics2D) getGraphics();//image.getGraphics();

        g2.scale(scale,scale);
        g2.translate(1,1);
        component.paint(g2);*/
    }
}