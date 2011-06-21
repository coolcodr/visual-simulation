package designer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class CoverComponentFrame extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -6546317019729528818L;
    private int position;
    private CoverComponent coverComponent;

    private MoveListener moveListener;
    private Color color;
    private int gap;
    private Cursor cursor;

    private BufferedImage image;
    private boolean visible = false;;

    public CoverComponentFrame(int position, CoverComponent coverComponent) {
        this.position = position;
        this.coverComponent = coverComponent;

        moveListener = new MoveListener(position, coverComponent);
        addMouseListener(moveListener);
        addMouseMotionListener(moveListener);
        color = Color.darkGray;
        cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        setCursor(cursor);
        resetFrame();
    }

    public void resetFrame() {
        Dimension size = coverComponent.getSize();
        int borderX = 0;
        int borderY = 0;
        int borderWidth = size.width;
        int borderHeight = 4;

        gap = (coverComponent.coverSize == 0) ? 5 : 6;

        if (position == 0) {
            this.setBounds(borderX, borderY, borderWidth, borderHeight + 1);
        } else if (position == 1) {
            this.setBounds(borderX, size.height - gap, borderWidth, borderHeight + 1);
        } else if (position == 2) {
            this.setBounds(borderX, borderY, 5, size.height);
        } else if (position == 3) {
            this.setBounds(size.width - gap, borderY, 5, size.height);
        }
        repaintImage();
    }

    public void setColor(Color color) {
        this.color = color;
        repaintImage();
    }

    public void setVisible(boolean b) {
        super.setVisible(true);
        visible = b;
    }

    public void repaintImage() {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = (Graphics2D) image.getGraphics();

        // g2d.setColor(Color.red);
        // g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        g2d.setColor(color);
        // g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        int size = 2;
        int i = 0;
        int width = getWidth();
        int height = getHeight();

        // if ( position == 1 && coverComponent.coverSize == 4 ) i = 1 ;//-
        // coverComponent.getSize().height % size ;

        if (position == 1 && coverComponent.getSize().height % 2 == 0) {
            i = 1;
        }

        if (coverComponent.coverSize == 4 || gap == 6) {
            width -= 1;
        }
        // if ( coverComponent.coverSize == 4 && position == 1 ) width -=1;
        if (coverComponent.getSize().height % 2 == 0 && position == 1) {
            width -= 1;
            // if ( (position == 1 && gap == 6) ) i = 1;
        }

        if (position == 0 || position == 1) {
            while (i < width) {
                g2d.drawLine(i, 0, i - 4, 0 + 4);
                i += size;
            }
            return;
        }
        int j = 0;

        // if ( position == 3 && coverComponent.coverSize == 4 ) j = 1;

        if (position == 3 && coverComponent.getSize().width % 2 == 0) {
            j = 1;
        }
        // if ( (position == 3 && gap == 6) ) j = 1;
        if (position == 3 && gap == 5 && coverComponent.getSize().width % 2 == 0) {
            ++height;
        }
        if (coverComponent.coverSize == 4 || gap == 6) {
            height -= 1;
        }
        if (coverComponent.coverSize == 4 && position == 3) {
            height -= 1;
            // if ( coverComponent.getSize().width % 2 == 0 && position == 3 )
            // height -= 1;
        }

        while (j < height) {
            g2d.drawLine(0, j, 0 + 4, j - 4);
            j += size;
        }
        j -= size;
        int x = 0;
        int f = 0;
        if (coverComponent.coverSize == 4) {
            j = j;
            f = 0;
        }

        if (position == 3) {
            g2d.drawLine(x + 2 - f, j, x + 4, j - 2 - f);
            g2d.drawLine(x + 4 - f, j, x + 4, j - f);
        }

    }

    public void paint(Graphics g) {
        if (!visible) {
            return;
        }

        // if ( position == 0 || position == 2 )
        if ((position == 1 && gap == 6)) {
            g.drawImage(image, 1, 0, this);
        } else if ((position == 3 && gap == 6)) {
            g.drawImage(image, 0, 1, this);
        } else {
            g.drawImage(image, 0, 0, this);
            // g.setColor(Color.blue);
            // g.fillRect(0,0,getWidth(),getHeight());
            // else if ( position == 1 || position == 3 )
            // g.drawImage(image, 3, 3, this);
        }
    }
}

class MoveListener extends MouseAdapter implements MouseListener, MouseMotionListener {
    private int position;
    private CoverComponent coverComponent;

    private int dx, dy, ox, oy, oWidth, oHeight;
    private boolean isDragging = false;

    public MoveListener(int position, CoverComponent coverComponent) {
        this.position = position;
        this.coverComponent = coverComponent;
    }

    public void mousePressed(MouseEvent e) {
        DesignerControl.currentDesignPane.deselectAll();
        coverComponent.deselectInternal();
        coverComponent.getFocus(true);
        dx = e.getX();
        dy = e.getY();

        // o-original ox - original x ...
        ox = coverComponent.getX();
        oy = coverComponent.getY();

        oWidth = coverComponent.getWidth();
        oHeight = coverComponent.getHeight();

        coverComponent.getParent().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        coverComponent.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }

    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            coverComponent.setXY();
            coverComponent.refreshResizePoint();
            coverComponent.getFocus(true);
            isDragging = false;
        }
        coverComponent.getParent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        coverComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        // UIDesigner.mainWindow.repaint();
    }

    /*
     * isDragging = true; Vector covers =
     * UIDesigner.getControl().currentDesignPane.getSelecledObject(); for (int i
     * = 0; i < covers.size(); i++) { CoverComponent scover = (CoverComponent)
     * covers.elementAt(i); if (scover.tryMoveXY(scover.getX() + (event.getX() -
     * dx), scover.getY() + (event.getY() - dy))) scover.moveXY(scover.getX() +
     * (event.getX() - dx), scover.getY() + (event.getY() - dy)); else { if
     * (scover.getX() + (event.getX() - dx) < 0) scover.moveXY(0, scover.getY()
     * + (event.getY() - dy)); if (scover.getY() + (event.getY() - dy) < 0)
     * scover.moveXY(scover.getX() + (event.getX() - dx), 0); }
     * 
     * if (DesignerControl.realTimMove) scover.setXY(); }
     */
    public void mouseDragged(MouseEvent e) {
        if (!isDragging) {
            ReshapCommand reshapCommand = new ReshapCommand((DesignerComponent) coverComponent.getRelateComponent());
            UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());
        }
        isDragging = true;

        int gridValue = coverComponent.getGridSize();

        int x = e.getX();// - e.getX() % gridValue + gridValue / 2;
        int y = e.getY();// - e.getY() % gridValue + gridValue / 2;

        // switch (position)
        // {
        // case 0:
        coverComponent.moveXY(coverComponent.getX() + (x - dx), coverComponent.getY() + (y - dy));
        /*
         * break; case 1: coverComponent.moveXY(coverComponent.getX() + (x -
         * dx), coverComponent.getY()); break; case 2:
         * coverComponent.moveXY(coverComponent.getX() + (x - dx),
         * coverComponent.getY()); break; case 3:
         * coverComponent.moveXY(coverComponent.getX(), coverComponent.getY() +
         * (y - dy)); break;
         */
        // }

        if (DesignerControl.realTimMove) {
            // coverComponent.changeResize();
            coverComponent.setXY();
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

}
