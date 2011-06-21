package designer;

import java.awt.Color;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.FocusManager;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CoverControl extends MouseAdapter implements MouseMotionListener, MouseListener {
    protected int dx, dy;
    protected CoverComponent cover;

    protected boolean isDragging = false;
    protected boolean multiSelected = false;

    protected JPopupMenu popup;

    public CoverControl() {
        this(null);
    }

    public CoverControl(CoverComponent cover) {
        this.cover = cover;

        popup = new JPopupMenu("Dialog");

        JMenuItem mi;
        mi = new JMenuItem("Generate all Frame for Internal Value Input");
        GenerateInternalFrameControl menuAdapter = new GenerateInternalFrameControl(this);
        mi.addActionListener(menuAdapter);

        popup.add(mi);

        popup.setInvoker(cover);
    }

    public void setCover(CoverComponent cover) {
        this.cover = cover;
    }

    public static CoverControl createNormalControl() {
        return new CoverControl();
    }

    public static CoverControl createNormalControl(CoverComponent cover) {
        return new CoverControl(cover);
    }

    public static CoverControl createDraggableControl() {
        return new DraggableControl();
    }

    public static CoverControl createDraggableControl(CoverComponent cover) {
        return new DraggableControl(cover);
    }

    public static CoverControl createEditTextControl() {
        return new EditTextControl();
    }

    public static CoverControl createInvisibleControl() {
        return new InvisibleControl();
    }

    public static CoverControl createInvisibleControl(CoverComponent cover) {
        return new InvisibleControl(cover);
    }

    public static CoverControl createCardControl() {
        return new CardControl();
    }

    public static CardControl createCardControl(CoverComponent cover) {
        return new CardControl(cover);
    }

    public static CoverControl createEditTextControl(CoverComponent cover) {
        return new EditTextControl(cover);
    }

    public static CoverControl createDraggableEditTextControl() {
        return new DraggableEditTextControl();
    }

    public static CoverControl createDraggableEditTextControl(CoverComponent cover) {
        return new DraggableEditTextControl(cover);
    }

    public static CoverControl createNormalEditTextControl() {
        return new NormalEditTextControl();
    }

    public static CoverControl createNormalEditTextControl(CoverComponent cover) {
        return new NormalEditTextControl(cover);
    }

    public void deselect() {
    }

    public void editText() {
    }

    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            cover.setVisible(false);
        }
    }

    public void mousePressed(MouseEvent event) {
        if (!cover.isSelected()) {
            DesignerControl.currentDesignPane.deselectAll();
            cover.deselectInternal();
            cover.setVisible(true);
            cover.getFocus(true);
        }

        dx = event.getX();
        dy = event.getY();

    }

    public void mouseReleased(MouseEvent event) {
        if (isDragging) {
            cover.setXY();
            isDragging = false;
        }

        cover.setVisible(true);
        cover.getFocus(true);

        if (event.isPopupTrigger()) {
            DesignerComponent designerComponent = (DesignerComponent) (cover).getRelateComponent();
            if (designerComponent instanceof DiagramComponentSetPanel && ((DiagramComponentSetPanel) designerComponent).getComboBox().isVisible()) {
                int x = event.getX();
                int y = event.getY();
                popupMenu((DiagramComponentSetPanel) designerComponent, x, y);
            }
        }
    }

    public void popupMenu(DiagramComponentSetPanel setPane, int x, int y) {
        popup.show(cover, x, y);
    }

    public void mouseDragged(MouseEvent event) {
        UIDesigner.getControl();
        Vector covers = DesignerControl.currentDesignPane.getSelecledObject();

        if (!isDragging && covers.size() > 1) {
            Vector alls = new Vector();
            for (int i = 0; i < covers.size(); i++) {
                alls.add(((CoverComponent) covers.elementAt(i)).getRelateComponent());
            }
            BatchMoveCommand batchMoveCommand = new BatchMoveCommand((DesignerComponent) cover.getRelateComponent(), alls);
            UIDesigner.getControl().addUndoCommand(batchMoveCommand.createUndoCommand());
        }

        if (covers.size() == 1) {
            multiSelected = false;

            if (!(((CoverComponent) covers.elementAt(0)).getRelateComponent() instanceof DesignPane) && !(((CoverComponent) covers.elementAt(0)).getRelateComponent() instanceof CardPane)) {
                return;
            } else if (!isDragging) {
                ReshapCommand reshapCommand = new ReshapCommand((DesignerComponent) ((CoverComponent) covers.elementAt(0)).getRelateComponent());
                UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());
            }
        }

        isDragging = true;

        for (int i = 0; i < covers.size(); i++) {
            CoverComponent scover = (CoverComponent) covers.elementAt(i);
            if (scover.tryMoveXY(scover.getX() + (event.getX() - dx), scover.getY() + (event.getY() - dy))) {
                scover.moveXY(scover.getX() + (event.getX() - dx), scover.getY() + (event.getY() - dy));
            } else {
                if (scover.getX() + (event.getX() - dx) < 0) {
                    scover.moveXY(0, scover.getY() + (event.getY() - dy));
                }
                if (scover.getY() + (event.getY() - dy) < 0) {
                    scover.moveXY(scover.getX() + (event.getX() - dx), 0);
                }
            }

            if (DesignerControl.realTimMove) {
                scover.setXY();
            }
        }
        if (covers.size() > 1) {
            multiSelected = true;
        } else {
            multiSelected = false;
        }
    }

    public void mouseMoved(MouseEvent event) {
    }
}

class InvisibleControl extends CoverControl {
    public InvisibleControl() {
        this(null);
    }

    public InvisibleControl(CoverComponent cover) {
        super(cover);
    }

    public void mouseClicked(MouseEvent event) {
    }

    public void mousePressed(MouseEvent event) {
        if (!cover.isSelected()) {
            // DesignerControl.currentDesignPane.deselectAll();
            cover.deselectInternal();
            cover.setVisible(true);
            cover.getFocus(true);
        }
        dx = event.getX();
        dy = event.getY();

    }

    public void mouseReleased(MouseEvent event) {
        if (isDragging) {
            cover.setXY();
        }

        cover.setVisible(true);
        cover.getFocus(true);
    }

    public void mouseDragged(MouseEvent event) {
    }

    public void mouseMoved(MouseEvent event) {
    }
}

class NormalEditTextControl extends DraggableEditTextControl {
    public NormalEditTextControl() {
        this(null);
    }

    public NormalEditTextControl(CoverComponent cover) {
        super(cover);
    }

    public void dragGestureRecognized(DragGestureEvent event) {
    }
}

class DraggableEditTextControl extends DraggableControl {
    private JTextField textField = new JTextField();
    private JComponent designerComponent;

    public DraggableEditTextControl() {
        this(null);
    }

    public DraggableEditTextControl(CoverComponent cover) {
        super(cover);
        if (cover != null) {
            setCover(cover);
        }
    }

    public void setCover(CoverComponent cover) {
        super.setCover(cover);
        designerComponent = (JComponent) cover.getRelateComponent();

        textField.setNextFocusableComponent(textField);
        textField.addKeyListener(new EditTextAdapter(cover));
        textField.setBackground(Color.red);
        textField.setBorder(null);
        if (designerComponent instanceof JLabel) {
            textField.setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            textField.setHorizontalAlignment(SwingConstants.CENTER);

        }
    }

    public void editText() {
        ReshapCommand reshapCommand = new ReshapCommand((DesignerComponent) designerComponent);
        UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());

        cover.add(textField);
        String text = ((DesignerComponent) designerComponent).getText();
        textField.setBounds(5, 5, cover.getWidth() - 11, cover.getHeight() - 11);
        textField.setText(text);
        textField.setFont((designerComponent).getFont());
        textField.setForeground((designerComponent).getForeground());
        textField.setBackground((designerComponent).getBackground());
        textField.setVisible(true);
        textField.setSelectionStart(0);
        textField.setSelectionEnd(textField.getText().length());
        // textField.paint(cover.getGraphics());
        FocusManager.getCurrentManager().focusNextComponent(textField);
    }

    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            editText();
        }
    }

    public void deselect() {
        if (textField.isShowing()) {
            cover.remove(textField);
            ((DesignerComponent) designerComponent).setText(textField.getText());
        }
        cover.getRelateComponent().repaint();
        cover.repaint();
    }

}

class EditTextAdapter implements KeyListener {
    private CoverComponent cover;

    public EditTextAdapter(CoverComponent cover) {
        this.cover = cover;
    }

    public void keyReleased(KeyEvent event) {
    }

    public void keyPressed(KeyEvent event) {
    }

    public void keyTyped(KeyEvent event) {
        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
            ((DesignerComponent) cover.getRelateComponent()).setText(((JTextField) event.getSource()).getText());
            cover.remove((JComponent) event.getSource());
            cover.getRelateComponent().repaint();
            cover.repaint();
            DesignerControl.refreshFrameList();
        }
    }
}

class CardControl extends EditTextControl {
    public CardControl() {
        this(null);
    }

    public CardControl(CoverComponent cover) {
        super(cover);
    }

    /*
     * public void deselect() { if (textField.isShowing()) {
     * cover.remove(textField); ( (DesignerComponent)
     * designerComponent).setText(textField.getText());
     * cover.getRelateComponent().repaint(); } //super.deselect(); }
     */
    public void mouseReleased(MouseEvent event) {/*
                                                  * if (isDragging)
                                                  * cover.setXY();
                                                  * 
                                                  * cover.setVisible(true);
                                                  * cover.getFocus(true);
                                                  */
    }

}

class EditTextControl extends CoverControl {
    protected JTextField textField = new JTextField();
    protected JComponent designerComponent;

    public EditTextControl() {
        this(null);
    }

    public EditTextControl(CoverComponent cover) {
        super(cover);
    }

    public void setCover(CoverComponent cover) {
        super.setCover(cover);
        designerComponent = (JComponent) cover.getRelateComponent();

        textField.setNextFocusableComponent(textField);
        textField.addKeyListener(new EditTextAdapter(cover));
        // textField.setBackground(Color.red);
        textField.setBorder(null);
        textField.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void editText() {
        ReshapCommand reshapCommand = new ReshapCommand((DesignerComponent) designerComponent);
        UIDesigner.getControl().addUndoCommand(reshapCommand.createUndoCommand());

        cover.add(textField);
        String text = ((DesignerComponent) designerComponent).getText();
        textField.setBounds(5, 5, cover.getWidth() - 10, 24);
        textField.setText(text);
        textField.setFont((designerComponent).getFont());
        textField.setForeground((designerComponent).getForeground());
        textField.setBackground((designerComponent).getBackground());
        textField.setVisible(true);
        textField.setSelectionStart(0);
        textField.setSelectionEnd(textField.getText().length());
        FocusManager.getCurrentManager().focusNextComponent(textField);
    }

    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getY() > 0 && event.getY() < 24) {
            editText();
        } else {
            super.mouseClicked(event);
            if (((CoverComponent) event.getSource()).getRelateComponent() instanceof DesignPane) {
                DesignerControl.currentDesignPane = (DesignPane) ((CoverComponent) event.getSource()).getRelateComponent();
            }
        }
    }

    public void deselect() {
        if (textField.isShowing()) {
            cover.remove(textField);
            ((DesignerComponent) designerComponent).setText(textField.getText());
        }
        cover.getRelateComponent().repaint();
        cover.repaint();
    }
}

class DraggableControl extends CoverControl implements DragSourceListener, DragGestureListener {
    protected DragSource dragSource = null;

    public DraggableControl() {
        this(null);
    }

    public DraggableControl(CoverComponent cover) {
        super(cover);
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(cover, DnDConstants.ACTION_MOVE, this);
    }

    public void setCover(CoverComponent cover) {
        this.cover = cover;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(cover, DnDConstants.ACTION_MOVE, this);
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
        StringSelection text = new StringSelection("");
        if (!multiSelected) {
            dragSource.startDrag(event, DragSource.DefaultMoveDrop, text, cover);
            DiagramSourceList.selectedObject = cover.getRelateComponent();
            DesignerControl.currentDesignPane.clearSelected();
        }
    }
}
