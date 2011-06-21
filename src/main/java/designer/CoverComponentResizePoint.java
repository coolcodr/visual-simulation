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

import javax.swing.JComponent;

public class CoverComponentResizePoint extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -2356718360984254354L;
    private int position;
    // 0-----6-----3
    // 1-----------4
    // 2-----7-----5
    private CoverComponent coverComponent;
    private ResizePointMouseListener resizeListener;
    private Cursor cursor;

    public CoverComponentResizePoint(int i, CoverComponent newCoverComponent) {
        position = i;
        coverComponent = newCoverComponent;

        resetResizePoint();

        resizeListener = new ResizePointMouseListener(coverComponent, position, cursor);
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);

    }

    public void resetResizePoint() {
        Dimension size = coverComponent.getSize();

        boolean trim = coverComponent.coverSize != 0;
        int borderX = 2;
        int borderY = 2;
        int borderWidth = trim ? size.width - 3 : size.width - 2;
        int borderHeight = trim ? size.height - 3 : size.height - 2;

        switch (position) {
            case 0:
                this.setBounds(borderX - 2, borderY - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 1:
                this.setBounds(borderX - 2, (borderHeight + borderY) / 2 - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 2:
                this.setBounds(borderX - 2, borderHeight - 3, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 3:
                this.setBounds(borderWidth - 3, borderY - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 4:
                this.setBounds(borderWidth - 3, (borderHeight + borderY) / 2 - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 5:
                this.setBounds(borderWidth - 3, borderHeight - 3, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 6:
                this.setBounds((borderWidth + borderX) / 2 - 2, borderY - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                cursor = getCursor();
                break;
            case 7:
                this.setBounds((borderWidth + borderX) / 2 - 2, borderHeight - 3, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                cursor = getCursor();
                break;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(getX(), getY(), getWidth() - 1, getHeight() - 1);
        g2d.setColor(Color.decode("#202020"));
        g2d.drawRect(getX(), getY(), getWidth() - 1, getHeight() - 1);
    }
}

class ResizePointMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener

{
    private CoverComponent coverComponent;
    private int dx, dy, ox, oy, oWidth, oHeight, position;

    private boolean isDragging = false;
    private Cursor cursor;

    private int gridValue;// = 8; //TEMP

    public ResizePointMouseListener(CoverComponent coverComponent, int i, Cursor cursor) {
        position = i;
        this.coverComponent = coverComponent;
        this.cursor = cursor;
    }

    public void mousePressed(MouseEvent e) {
        isDragging = false;

        dx = e.getX();
        dy = e.getY();

        // o-original ox - original x ...
        ox = coverComponent.getX();
        oy = coverComponent.getY();
        oWidth = coverComponent.getWidth();
        oHeight = coverComponent.getHeight();
        coverComponent.setCursor(cursor);
    }

    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            coverComponent.setXY();
            coverComponent.changeResize();
            coverComponent.refreshResizePoint();
            coverComponent.getFocus(true);
            isDragging = false;
        }
        // UIDesigner.mainWindow.repaint();
        coverComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void mouseDragged(MouseEvent e) {
        if (!isDragging) {
            ReshapCommand reshapCommand = new ReshapCommand((DesignerComponent) coverComponent.getRelateComponent());
            UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());
        }

        isDragging = true;

        int gridValue = coverComponent.getGridSize();

        int x = e.getX() - e.getX() % gridValue + gridValue / 2;
        int y = e.getY() - e.getY() % gridValue + gridValue / 2;
        switch (position) {
            case 0:
                coverComponent.changeSize(oWidth + (ox - x - coverComponent.getX() + dx), oHeight + (oy - y - coverComponent.getY() + dy));
                coverComponent.moveXY(coverComponent.getX() + (x - dx), coverComponent.getY() + (y - dy));
                break;
            case 1:
                coverComponent.changeSize(oWidth + (ox - x - coverComponent.getX() + dx), oHeight);
                coverComponent.moveXY(coverComponent.getX() + (x - dx), coverComponent.getY());
                break;
            case 2:
                coverComponent.changeSize(oWidth + (ox - x - coverComponent.getX() + dx), oHeight + (y + oy - coverComponent.getY() - dy));
                coverComponent.moveXY(coverComponent.getX() + (x - dx), coverComponent.getY());
                break;
            case 3:
                coverComponent.changeSize(oWidth + (x + ox - coverComponent.getX() - dx), oHeight + (oy - y - coverComponent.getY() + dy));
                coverComponent.moveXY(coverComponent.getX(), coverComponent.getY() + (y - dy));
                break;
            case 4:
                coverComponent.changeSize(oWidth + (x + ox - coverComponent.getX() - dx), oHeight);
                // Nothing moved
                coverComponent.moveXY(coverComponent.getX(), coverComponent.getY());
                break;
            case 5:
                coverComponent.changeSize(oWidth + (x + ox - coverComponent.getX() - dx), oHeight + (y + oy - coverComponent.getY() - dy));
                // Nothing moved
                coverComponent.moveXY(coverComponent.getX(), coverComponent.getY());
                break;
            case 6:
                coverComponent.changeSize(oWidth, oHeight + (oy - y - coverComponent.getY() + dy));
                coverComponent.moveXY(coverComponent.getX(), coverComponent.getY() + (y - dy));
                break;
            case 7:
                coverComponent.changeSize(oWidth, oHeight + (y + oy - coverComponent.getY() - dy));
                // Nothing moved
                coverComponent.moveXY(coverComponent.getX(), coverComponent.getY());
                break;
        }
        coverComponent.refreshFrame();
        if (DesignerControl.realTimMove) {
            coverComponent.changeResize();
            coverComponent.setXY();
            // DesignerUI.mainWindow.repaint();
        }
        // coverComponent.getParent().repaint();
        // coverComponent.refreshFrame();

        // coverComponent.showBorder(true);
    }

    public void mouseMoved(MouseEvent e) {
    }
}
