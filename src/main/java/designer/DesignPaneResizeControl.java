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

public class DesignPaneResizeControl extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -2947199569691309309L;
    private int position;
    // 4-----------3
    // ------------2
    // 5-----0-----1
    private DesignPane designPane;
    private PaneResizeMouseListener resizeListener;

    public DesignPaneResizeControl(int i, DesignPane designPane) {
        position = i;
        this.designPane = designPane;

        resizeListener = new PaneResizeMouseListener(designPane, position);

        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);

        resetResizePoint();
    }

    public void resetResizePoint() {
        Dimension size = designPane.getSize();
        int borderX = 0;
        int borderY = 0;
        int borderWidth = size.width;
        int borderHeight = size.height;

        switch (position) {
            case 0:
                this.setBounds(borderWidth / 2 - 2, borderHeight - 5, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                break;
            case 1:
                this.setBounds(borderWidth - 5, borderHeight - 5, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                break;
            case 2:
                this.setBounds(borderWidth - 5, borderHeight / 2 - 2, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                break;
            case 3:
                this.setBounds(borderWidth - 5, 0, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
            case 4:
                this.setBounds(0, 0, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
            case 5:
                this.setBounds(0, borderHeight - 5, 5, 5);
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        switch (position) {
            case 0:
            case 1:
            case 2:
                g2d.setColor(Color.black);
                break;
            case 3:
            case 4:
            case 5:
                g2d.setColor(Color.gray);
                break;
        }
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

class PaneResizeMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener {
    private DesignPane designPane;
    private int dx, dy, position;

    private boolean isDragging = false;

    public PaneResizeMouseListener(DesignPane designPane, int i) {
        position = i;
        this.designPane = designPane;
    }

    public void mousePressed(MouseEvent e) {
        dx = e.getX();
        dy = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        designPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        isDragging = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (!isDragging) {
            UIDesigner.getControl().addUndoCommand(new DesignPaneMoveCommand(designPane).createUndoCommand());
        }

        isDragging = true;

        int x = e.getX();
        int y = e.getY();
        switch (position) {
            case 0:
                designPane.setSize(designPane.getWidth(), designPane.getHeight() + (y - dy));
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                break;
            case 1:
                designPane.setSize(designPane.getWidth() + (x - dx), designPane.getHeight() + (y - dy));
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                break;
            case 2:
                designPane.setSize(designPane.getWidth() + (x - dx), designPane.getHeight());
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                break;
            case 3:
                designPane.setLocation(designPane.getX() + x - dx, designPane.getY() + y - dy);
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
            case 4:
                designPane.setLocation(designPane.getX() + x - dx, designPane.getY() + y - dy);
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
            case 5:
                designPane.setLocation(designPane.getX() + x - dx, designPane.getY() + y - dy);
                designPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                break;
        }

    }

    public void mouseMoved(MouseEvent e) {
    }
}
