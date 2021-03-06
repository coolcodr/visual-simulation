package designer;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import util.ImageIconHelper;

public class FrameListRenderer extends DefaultTreeCellRenderer {/*
                                                                 * private
                                                                 * ImageIcon
                                                                 * label = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/label16.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * combo = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/combo16.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * button = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/button16.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * text = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/text16.gif"
                                                                 * ); private
                                                                 * ImageIcon set
                                                                 * = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/set48.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * title = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/title16.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * card = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/card16.gif"
                                                                 * ); private
                                                                 * ImageIcon
                                                                 * pane = new
                                                                 * ImageIcon(
                                                                 * "D:/UD icon/pane16.gif"
                                                                 * );
                                                                 */
    /**
     * 
     */
    private static final long serialVersionUID = 5284699404432042806L;

    public FrameListRenderer() {
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        // if (leaf && isTutorialBook(value))
        // {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

        if (node.getUserObject() instanceof CoverComponent) {
            setIcon(((CoverComponent) node.getUserObject()).getImageIcon());
        } else {
            setIcon(new ImageIconHelper().getImageIcon("/designer/images/Pane16.gif"));
        }

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
