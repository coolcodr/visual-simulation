package designer;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import util.ImageIconHelper;
import designer.deployment.SimPropertyChoice;

class SourceTreeRenderer extends DefaultTreeCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = -7357248804310789848L;
    private ImageIcon inputG;
    private ImageIcon inputR;
    private ImageIcon pane;

    public SourceTreeRenderer() {
        inputG = new ImageIconHelper().getImageIcon("/designer/images/Input-g16.gif");
        // inputR = new ImageIconHelper().getImageIcon("/designer/images/Input-r16.gif");
        pane = new ImageIconHelper().getImageIcon("/designer/images/Pane16.gif");
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object userObject = node.getUserObject();
        if (node.equals(tree.getModel().getRoot())) {
            ;//
        }
        if (userObject instanceof DiagramComponentSetPanel) {
            setIcon(inputG);
        } else if (userObject instanceof SimPropertyChoice) {
            setIcon(pane);
        } else if (userObject instanceof ChartSet) {
            setIcon(inputG);
        } else if (userObject instanceof AnalysisSet) {
            setIcon(inputG);
        } else if (userObject instanceof String) {
            ;// setIcon(null);
        }
        return this;
    }
}
