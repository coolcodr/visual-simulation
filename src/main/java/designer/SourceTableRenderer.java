package designer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import util.ImageIconHelper;

class SourceTableRenderer extends DefaultTreeCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -5175664370993052690L;
    private ImageIcon imageIcon;

    public SourceTableRenderer() {
        imageIcon = new ImageIconHelper().getImageIcon("/designer/images/test.gif");
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        // if (leaf && isTutorialBook(value))
        // {

        setIcon(null);
        // setToolTipText("This book is in the Tutorial series.");
        // }
        // else
        // {
        // setToolTipText(null); //no tool tip
        // }

        return this;
    }
    /*
     * protected boolean isTutorialBook(Object value) { DefaultMutableTreeNode
     * node = (DefaultMutableTreeNode) value; BookInfo nodeInfo = (BookInfo)
     * (node.getUserObject()); String title = nodeInfo.bookName; if
     * (title.indexOf("Tutorial") >= 0) { return true; }
     * 
     * return false; }
     */
}
