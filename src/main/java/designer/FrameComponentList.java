package designer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class FrameComponentList extends JTree implements MouseListener {
    /**
     * 
     */
    private static final long serialVersionUID = 8612032877008496422L;
    private DesignPane designPane;
    private DefaultMutableTreeNode root;

    public FrameComponentList(DesignPane designPane) {
        super(new DefaultMutableTreeNode(designPane.toString()));

        root = (DefaultMutableTreeNode) getModel().getRoot();

        this.designPane = designPane;
        addMouseListener(this);
        setCellRenderer(new FrameListRenderer());
        refresh();
    }

    public void setPaneList(DesignPane designPane, DefaultMutableTreeNode node) {
        Vector componentSetPane = designPane.setPaneComponent;
        for (int i = 0; i < componentSetPane.size(); i++) {
            DefaultMutableTreeNode child;
            Object currentComponent = componentSetPane.elementAt(i);
            if (currentComponent instanceof DiagramComponentSetPanel) {
                DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) currentComponent;
                child = new DefaultMutableTreeNode(setPane.getName() + setPane.getSetMode()); // Real
                                                                                              // obejct
                                                                                              // or
                                                                                              // Srting
                                                                                              // is
                                                                                              // better?
                child.setUserObject(setPane.getCover());

                CLabel jLabel = setPane.getLabel();
                if (jLabel.isVisible()) {
                    child.add(new DefaultMutableTreeNode(jLabel.getCover()));
                }
                CComboBox jComboBox = setPane.getComboBox();
                if (jComboBox.isVisible()) {
                    child.add(new DefaultMutableTreeNode(jComboBox.getCover()));
                }
                CTextField jTextField = setPane.getTextField();
                if (jTextField.isVisible()) {
                    child.add(new DefaultMutableTreeNode(jTextField.getCover()));
                }
                CButton jButton = setPane.getButton();
                if (jButton.isVisible()) {
                    child.add(new DefaultMutableTreeNode(jButton.getCover()));
                }
            } else if (currentComponent instanceof CardPane) {
                Vector designPanes = ((CardPane) currentComponent).getDesignPanes();
                child = new DefaultMutableTreeNode(((CardPane) currentComponent).getCover());
                for (int j = 0; j < designPanes.size(); j++) {
                    DesignPane designPane2 = (DesignPane) designPanes.elementAt(j);
                    DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(designPane2);
                    child.add(child2);
                }
            } else if (currentComponent instanceof TitlePane) {
                TitlePane titlePane = (TitlePane) currentComponent;
                child = new DefaultMutableTreeNode(titlePane.getCover());
                setPaneList(titlePane, child);
            } else {
                try {
                    child = new DefaultMutableTreeNode(((DesignerComponent) currentComponent).getCover());
                } catch (Exception ex) {
                    child = new DefaultMutableTreeNode(currentComponent);
                }
            }
            node.add(child);
        }

    }

    public void refresh() {
        root.removeAllChildren();
        root.setUserObject(designPane.toString());

        setPaneList(designPane, root);
        /*
         * for ( int i = 0 ; i < root.getChildCount() ; i ++ ) { TreeNode node =
         * root.getChildAt(i); }
         */
        updateUI();
        repaint();
    }

    public void mousePressed(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
        FrameComponentList list = (FrameComponentList) event.getSource();
        TreePath selected = list.getSelectionPath();
        if (selected != null && selected != getPathForRow(0)) {
            designPane.deselectAll();
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) (selected.getLastPathComponent());
            if (selectedNode.getUserObject() instanceof CoverComponent) {
                ((CoverComponent) selectedNode.getUserObject()).getFocus(true);
                return;
            }
            // StringSelection text = new StringSelection(selected.toString());

            Object selectedObject = selectedNode.getUserObject();
            designPane.selectedObject((JComponent) selectedObject);
        }
        if (selected != null && selected == getPathForRow(0)) {
            designPane.deselectAll();
        }
    }

    public void mouseExited(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            FrameComponentList list = (FrameComponentList) event.getSource();
            TreePath selected = list.getSelectionPath();

            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) (selected.getLastPathComponent());

            if (!selectedNode.equals(root)) {
                UIDesigner.mainWindow.propertiesTabbedPane.setSelectedIndex(1);
                if (selectedNode.getUserObject() instanceof CoverComponent) {
                    ((CoverComponent) selectedNode.getUserObject()).getFocus(true);
                } else {
                    Object selectedObject = selectedNode.getUserObject();
                    designPane.selectedObject((JComponent) selectedObject);
                }
            }
        }
    }

}
