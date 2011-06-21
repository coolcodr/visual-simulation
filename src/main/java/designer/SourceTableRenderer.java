package designer;

import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;

class SourceTableRenderer extends DefaultTreeCellRenderer
{
    private ImageIcon imageIcon;

    public SourceTableRenderer()
    {
        imageIcon = new ImageIcon("designer/images/test.gif");
    }

    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean sel,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus)
    {

        super.getTreeCellRendererComponent(
            tree, value, sel,
            expanded, leaf, row,
            hasFocus);
        //if (leaf && isTutorialBook(value))
        //{

            setIcon(null);
            //setToolTipText("This book is in the Tutorial series.");
        //}
        //else
        //{
            //setToolTipText(null); //no tool tip
        //}

        return this;
    }
    /*
    protected boolean isTutorialBook(Object value)
    {
        DefaultMutableTreeNode node =
            (DefaultMutableTreeNode) value;
        BookInfo nodeInfo =
            (BookInfo) (node.getUserObject());
        String title = nodeInfo.bookName;
        if (title.indexOf("Tutorial") >= 0)
        {
            return true;
        }

        return false;
    }*/
}
