package designer;

import javax.swing.JComponent;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class KeyControl implements KeyListener
{
    boolean isMoving = false;

    public KeyControl()
    {
    }

    public void keyPressed(KeyEvent e)
    {
        if ( !isMoving)
            UIDesigner.getControl().currentDesignPane.createBatchMoveUndo();
        isMoving = true;

        int kc = e.getKeyCode();
        int grid = DesignerControl.gridSize;

        Vector covers = UIDesigner.getControl().currentDesignPane.getSelecledObject();
        if (kc >= 37 && kc <= 40)
        {
            for (int i = 0; i < covers.size(); i++)
            {
                CoverComponent cover = (CoverComponent) covers.elementAt(i);
                switch (kc)
                {
                    case 37:
                        cover.moveXY(cover.getX() - grid, cover.getY());
                        break;
                    case 38:
                        cover.moveXY(cover.getX(), cover.getY() - grid);
                        break;
                    case 39:
                        cover.moveXY(cover.getX() + grid, cover.getY());
                        break;
                    case 40:
                        cover.moveXY(cover.getX(), cover.getY() + grid);
                        break;
                }
                cover.setXY();
                cover.getFocus(true);
            }
        }

        if (kc == 127)
        {
            DesignerControl.currentDesignPane.removeDesignComponent();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        isMoving = false;
        int kc = e.getKeyCode();
    }

    public void keyTyped(KeyEvent e)
    {
        int kc = e.getKeyCode();
    }
}