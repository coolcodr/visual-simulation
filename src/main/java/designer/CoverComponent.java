package designer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class CoverComponent extends JComponent implements DragSourceListener, DragGestureListener {
    /**
     * 
     */
    private static final long serialVersionUID = 4215991617368458905L;
    protected Component relateComponent;
    protected CoverControl coverControl;

    protected int coverSize;
    protected boolean haveBorder;
    protected int borderSize;
    protected boolean borderVisible = false;
    private boolean resizePointVisible = false;
    private boolean focused = false;

    private CoverComponentResizePoint[] resizePoint;
    private CoverComponentFrame[] frame;

    private DragSource dragSource = null;

    private Color frameColor = Color.darkGray;
    private ImageIcon imageIcon;

    public CoverComponent(Component relateComponent, int coverSize, boolean haveBorder) {
        this.relateComponent = relateComponent;
        this.coverSize = coverSize;
        this.haveBorder = haveBorder;
        borderSize = coverSize;

        setLayout(null);

        this.setBounds(relateComponent.getX() - coverSize, relateComponent.getY() - coverSize, relateComponent.getWidth() + coverSize * 2, relateComponent.getHeight() + coverSize * 2);

        changeSize(getWidth(), getHeight());
        moveXY((int) getBounds().getX(), (int) getBounds().getY());
        changeResize();
        setXY();

        resizePoint = new CoverComponentResizePoint[8];
        frame = new CoverComponentFrame[4];

        if (!(relateComponent instanceof DesignPane)) {
            // this.dragSource = new DragSource();
            // this.dragSource.createDefaultDragGestureRecognizer(this,
            // DnDConstants.ACTION_MOVE, this);
        }

        for (int i = 0; i < 8; i++) {
            resizePoint[i] = new CoverComponentResizePoint(i, this);
            resizePoint[i].setVisible(false);
            this.add(resizePoint[i]);
        }

        for (int i = 0; i < 4; i++) {
            frame[i] = new CoverComponentFrame(i, this);
            frame[i].setVisible(false);
            this.add(frame[i]);
        }

        // getFocus(false);

        // removeListener();
    }

    public void setImageIcon(String path) {
        imageIcon = new ImageIcon(path);
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setCoverControl(CoverControl coverControl) {
        this.coverControl = coverControl;
        addMouseListener(coverControl);
        addMouseMotionListener(coverControl);

        addKeyListener(new KeyControl());
    }

    public CoverControl getCoverControl() {
        return coverControl;
    }

    public void deselectInternal() {
        if (relateComponent instanceof DiagramComponentSetPanel) {
            ((DiagramComponentSetPanel) relateComponent).deselectInternal();
        } else {
            ((DesignUpperPane) getParent()).getDesignPane().deselectAll();
        }
    }

    public Component getRelateComponent() {
        return relateComponent;
    }

    public boolean tryMoveXY(int x, int y) {
        boolean result = true;
        if (x < 0 || y < 0) {
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            result = false;
        }
        // if ( result )
        // moveXY ( x, y );
        return result;
    }

    public void setDefaultCursor(Cursor cursor) {
        super.setCursor(cursor);
    }

    public void setCursor(Cursor cursor) {
        super.setCursor(cursor);
        getParent().setCursor(cursor);

        if (cursor.equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
            cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
        }
        for (int i = 0; i < 4; i++) {
            frame[i].setCursor(cursor);
        }
    }

    public void moveXY2(int x, int y) {
        if (coverControl != null) {
            coverControl.deselect();
        }
        borderVisible = true;
        this.setLocation(x, y);
    }

    public void moveXY(int x, int y) {
        if (coverControl != null) {
            ;// coverControl.deselect();
        }
        int gridValue = getGridSize();
        // if (!DesignerControl.realTimMove)
        borderVisible = true;
        // resizePointVisible = false;
        x = x - x % gridValue + gridValue / 2;
        y = y - y % gridValue + gridValue / 2;
        this.setLocation(x, y);
    }

    public void changeSize2(int width, int height) {
        if (width < 10) {
            width = 10;
        }
        if (height < 10) {
            height = 10;
        }

        if (coverControl != null) {
            ;// coverControl.deselect();
        }
        if (DesignerControl.realTimMove) {
            borderVisible = haveBorder;
        }
        this.setSize(width, height);
    }

    public void changeSize(int width, int height) {
        if (width < 10) {
            width = 10;
        }
        if (height < 10) {
            height = 10;
        }
        if (coverControl != null) {
            ;// coverControl.deselect();
        }
        int gridValue = getGridSize();
        if (DesignerControl.realTimMove) {
            borderVisible = haveBorder;
        }
        width = width - width % gridValue + gridValue / 2;
        height = height - height % gridValue + gridValue / 2;
        this.setSize(width, height);
    }

    public void refreshResizePoint() {
        if (resizePoint == null) {
            return;
        }
        if (frame == null) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            resizePoint[i].resetResizePoint();
        }
        for (int i = 0; i < 4; i++) {
            frame[i].resetFrame();
        }
    }

    public void refreshFrame() {
        if (frame == null) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            frame[i].resetFrame();
        }
    }

    public void changeBounds2(int x, int y, int width, int height) {
        if (width < 10) {
            width = 10;
        }
        if (height < 10) {
            height = 10;
        }

        if (coverControl != null) {
            coverControl.deselect();
        }
        moveXY(x, y);
        setXY();
        changeSize(width, height);
        changeResize();
        refreshResizePoint();

    }

    public void changeBounds(int x, int y, int width, int height) {
        if (width < 10) {
            width = 10;
        }
        if (height < 10) {
            height = 10;
        }

        if (coverControl != null) {
            coverControl.deselect();
        }
        moveXY(x, y);
        setXY();
        changeSize(width, height);
        changeResize();
        refreshResizePoint();
    }

    public void setXY() {
        // borderVisible = (!DesignerControl.realTimMove);
        resizePointVisible = false;
        relateComponent.setLocation(getX() + coverSize, getY() + coverSize);
    }

    public void changeResize() {
        relateComponent.setSize(getWidth() - coverSize * 2, getHeight() - coverSize * 2);

        refreshFrame();
        // refreshResizePoint();

    }

    public void getHighLight(Color color) {
        frameColor = color;
        viewFrame(true);
    }

    public void getHighLight(boolean b) {
        frameColor = Color.darkGray;
        viewFrame(false);
    }

    public void viewFrame(boolean b) {
        focused = b;
        borderVisible = (b && haveBorder);
        resizePointVisible = false;
        for (int i = 0; i < 8; i++) {
            resizePoint[i].setVisible(resizePointVisible);
        }
        repaint();
    }

    public void getFocus(boolean b) {
        UIDesigner.getControl();
        DesignerControl.setGenerateEnalble(false);
        focused = b;
        // this.changeSize(getWidth(), getHeight());
        moveXY((int) getBounds().getX(), (int) getBounds().getY());
        // this.changeResize();
        setXY();
        borderVisible = (b && haveBorder);
        resizePointVisible = b;
        for (int i = 0; i < 8; i++) {
            resizePoint[i].setVisible(resizePointVisible);
        }
        for (int i = 0; i < 4; i++) {
            frame[i].setVisible(borderVisible);
        }
        if (relateComponent instanceof DiagramComponentSetPanel) {
            ((DiagramComponentSetPanel) relateComponent).deselectInternal();
            if (((DiagramComponentSetPanel) relateComponent).getComboBox().isVisible()) {
                UIDesigner.getControl();
                DesignerControl.setGenerateEnalble(true);
            }
        }

        if (!b && coverControl != null) {
            coverControl.deselect();
        }

        if (b) {
            UIDesigner.getControl();
            DesignerControl.currentDesignPane.selectObject(this);
            UIDesigner.getControl();
            DesignerControl.setDeleteButtonEnable(true);
            requestFocus();
        }
        repaint();

        DiagramSourceList.selectedObject = relateComponent;
    }

    public void requestFocus() {
        super.requestFocus();
    }

    public void setLocation(int x, int y) {
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }

        super.setLocation(x, y);
    }

    public boolean isSelected() {
        return focused;
    }

    /*
     * public void showBorder ( boolean b ) { borderVisible = b; }
     */
    public void paint(Graphics g) {

        for (int i = 0; i < 4; i++) {
            if (focused && borderVisible && haveBorder) {
                frame[i].setColor(frameColor);
                // frame[i].paint(g2d);
                frame[i].setVisible(borderVisible);
            } else {
                frame[i].setVisible(false);
            }
        }

        super.paint(g);

        Dimension size = this.getSize();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(frameColor);
        int borderX = (borderSize == 0 ? 2 : borderSize);
        int borderY = (borderSize == 0 ? 2 : borderSize);
        int borderWidth = size.width - borderX - (borderSize == 0 ? 3 : borderSize);
        int borderHeight = size.height - borderY - (borderSize == 0 ? 3 : borderSize);
        /*
         * if (borderVisible) { g2d.drawRect(borderX, borderY, borderWidth,
         * borderHeight); }
         */

        for (int i = 0; i < 8; i++) {
            if (resizePointVisible) {
                resizePoint[i].paint(g2d);
                resizePoint[i].setVisible(resizePointVisible);
            } else {
                resizePoint[i].setVisible(false);
            }
        }

    }

    public int getGridSize() {
        return DesignerControl.getGridSize();
    }

    public String toString() {
        return relateComponent.toString();
    }

    public void dragDropEnd(DragSourceDropEvent event) {
    }

    public void dragExit(DragSourceEvent event) {
    }

    public void dropActionChanged(DragSourceDragEvent event) {
    }

    public void dragOver(DragSourceDragEvent event) {
    }

    public void dragEnter(DragSourceDragEvent event) {
    }

    public void dragGestureRecognized(DragGestureEvent event) {
        // StringSelection text = new StringSelection("");
        // dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, this);
    }
}
