package designer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public class AddComponentControl extends UpperPaneControl {
    private JComponent jComponent;
    private boolean isDragging;
    private int dx, dy, x, y;
    private int grid = DesignerControl.getGridSize();

    private JComponent drawBound;

    public AddComponentControl(JComponent jComponent) {
        this.jComponent = jComponent;
        isDragging = false;

        drawBound = DesignerControl.currentDesignPane.getUpperPane().getDrawBound();
    }

    public void mouseMoved(MouseEvent e) {
        ((DesignUpperPane) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public void mousePressed(MouseEvent e) {
        dx = e.getX() - e.getX() % grid + grid / 2;
        dy = e.getY() - e.getY() % grid + grid / 2;

        drawBound.setBounds(-10, -10, -10, -10);
        drawBound.setVisible(true);
    }

    public void mouseDragged(MouseEvent e) {
        isDragging = true;
        x = e.getX() - e.getX() % grid + grid / 2;
        y = e.getY() - e.getY() % grid + grid / 2;

        drawBound.setBounds(dx, dy, x - dx, y - dy);
    }

    public void mouseReleased(MouseEvent e) {
        if (isDragging) {
            jComponent.setBounds(dx, dy, x - dx, y - dy);
        }
        // DesignerControl.currentDesignPane.addComponent(jComponent);
        DesignerControl.currentDesignPane.addDesignComponent((DesignerComponent) jComponent);
        DesignerControl.currentDesignPane.restoreButtonStatus();
        System.out.println("REMVOING CONTROL");
        DesignerControl.currentDesignPane.getUpperPane().resetControl();
        ((DesignUpperPane) e.getSource()).resetControl();
        drawBound.setVisible(false);
        // ((DesignerComponent)jComponent).getCoverControl().editText();
        // UIDesigner.mainWindow.repaint();
    }
}

class DrawBound extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = 1403609472844423363L;

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
    }
}
