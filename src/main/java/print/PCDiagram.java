package print;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

/**
 * <p>
 * Title: Print Editor
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class PCDiagram extends JPanel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 459622773623573971L;
    ObjectComponent component;
    ReportPage reportPage;
    JPanel jPanel;

    int x;
    int y;
    int width;
    int height;
    int originWidth;
    int originHeight;

    int scale;

    int diagramVerPage;
    int diagramHozPage;
    int numOfPages;

    public PCDiagram(ObjectComponent newComponent, ReportPage reportPage) {
        component = newComponent;
        this.reportPage = reportPage;
        setXY(0, 0);
        originWidth = (int) component.getPreferredSize().getWidth();
        originHeight = (int) component.getPreferredSize().getHeight();
        numOfPages = 1;
        setScale(100);
        // scale = 100; //100%
    }

    public ObjectComponent getObjectComponent() {
        return component;
    }

    public void print(Graphics2D g2, int pi) {
        Shape shape = g2.getClip();
        calculatePages();
        setXY(x, y);
        double scale = ((double) this.scale) / 100;
        double savedScale = 1;
        g2.translate(x, y);
        // g2.scale( scale, scale);

        int maxWidth = (int) reportPage.getPageFormat().getImageableWidth() - 10;
        int maxHeight = reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight();// +
                                                                                          // 10;
        /*
         * if (preview) thisPi = pi; else thisPi = pi - startPageNumber;
         */

        /*
         * int currentHozPage = pi % diagramHozPage; int currentVerPage =
         * (int)Math.floor( (double) pi / (double) diagramHozPage );
         */

        int currentVerPage = pi % diagramVerPage;
        int currentHozPage = (int) Math.floor((double) pi / (double) diagramVerPage);

        if (diagramVerPage == 1) {
            currentVerPage = 0;
        }
        if (diagramHozPage == 1) {
            currentHozPage = 0;
        }

        // Orignial method //Deleted
        // g2.translate( - (int)( ( maxWidth ) * currentVerPage / scale) , -
        // (int)( maxHeight * currentHozPage / scale));
        g2.translate(-((maxWidth) * currentVerPage), -(maxHeight * currentHozPage));

        // g2.setClip( -(int) ( ( maxWidth ) * currentVerPage / scale) -1 ,
        // -(int)( maxHeight * currentHozPage / scale) -1, (int)((float)
        // (maxWidth)/scale) , (int)((float) maxHeight/scale));
        // g2.setClip( 0 , 0, (int)((float) (maxWidth)/scale) , (int)((float)
        // maxHeight/scale));

        disableDoubleBuffering(component);

        // Orignial method //Deleted
        // g2.clipRect((int) ( ( maxWidth ) * currentVerPage / scale) -1 ,
        // (int)( maxHeight * currentHozPage / scale) -1, (int)((float)
        // (maxWidth)/scale) , (int)((float) maxHeight/scale));
        // New
        g2.clipRect(((maxWidth) * currentVerPage) - 1, (maxHeight * currentHozPage) - 1, (int) ((maxWidth)), (int) (maxHeight));

        component.paint(g2);

        enableDoubleBuffering(component);

        // Reset
        // g2.setClip(shape);
        // Orignial method //Deleted
        // g2.translate( (int)( ( maxWidth ) * currentVerPage / scale) , (int)(
        // maxHeight * currentHozPage / scale));
        g2.translate(((maxWidth) * currentVerPage), (maxHeight * currentHozPage));

        // g2.scale( 1/scale, 1/scale);
        g2.translate(-x, -y);
        g2.setClip(shape);
        // g2.clipRect((int) ( ( maxWidth ) * currentVerPage / scale) -1 ,
        // (int)( maxHeight * currentHozPage / scale) -1, (int)((float)
        // (maxWidth)/scale) , (int)((float) maxHeight/scale));
        // disableDoubleBuffering(component);
    }

    public void setXY(int x, int y) {
        if (x + width + 10 > reportPage.getPageFormat().getImageableWidth()) {
            x = (int) reportPage.getPageFormat().getImageableWidth() - width - 10;
        }
        if (y + height > reportPage.getMaxImagaebleHeight()) {
            y = reportPage.getMaxImagaebleHeight() - height;
        }
        if (x < 10) {
            x = 10;
        }
        if (y < reportPage.getHeaderHeight()) {
            y = reportPage.getHeaderHeight();
        }

        this.x = x;
        this.y = y;
    }

    public void setObjectDiagram(JComponent c, String id) {
        setObjectDiagram(c);
        component.setID(id);
    }

    public void setObjectDiagram(JPanel panel, String id) {
        setObjectDiagram(panel);
        component.setID(id);
    }

    public void setObjectDiagram(JComponent panel) {
        int width = getWidth();
        int height = getHeight();

        originWidth = panel.getPreferredSize().width;
        originHeight = panel.getPreferredSize().height;

        String id = component.getID();
        component = new ObjectComponent(panel, id);

        // super.setSize(panel.getPreferredSize().width,
        // panel.getPreferredSize().height);
        // this.getObjectComponent().setChart(panel);

        setSize(width, height);
    }

    public void setSize(int width, int height) {
        if (width < 5) {
            width = 5;
        }
        if (height < 10) {
            height = 10;
        }
        if (width + 20 > reportPage.getPageFormat().getImageableWidth()) {
            width = (int) reportPage.getPageFormat().getImageableWidth() - 20;
        }
        if (height > reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight()) {
            height = reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight();
        }

        int scale1 = (int) (((double) width / (double) originWidth) * 100);
        int scale2 = (int) (((double) height / (double) originHeight) * 100);
        if (scale1 < scale2) {
            setScale(scale1);
        } else {
            setScale(scale2);
        }
        setXY(x, y);
    }

    public void setScale(int scale) {
        this.scale = scale;
        width = (int) (originWidth * ((double) scale / 100));
        height = (int) (originHeight * ((double) scale / 100));

        calculatePages();
        setXY(x, y);

        // New method
        (component).setScale((double) scale / 100);
    }

    public int getScale() {
        return scale;
    }

    public void calculatePages() {
        double scaledWidth = width;
        double scaledHeight = height;

        double imageableWidth = reportPage.getPageFormat().getImageableWidth() - 20;
        double imageableHeight = reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight();

        diagramVerPage = (int) Math.ceil(scaledWidth / imageableWidth);
        diagramHozPage = (int) Math.ceil(scaledHeight / imageableHeight);

        numOfPages = diagramHozPage * diagramVerPage;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }

}
