package designer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import designer.deployment.GenerateProgress;
import designer.deployment.SimPropertyChoice;

public class GenerateInternalFrameControl implements ActionListener {
    CoverControl coverControl;

    public GenerateInternalFrameControl(CoverControl coverControl) {
        this.coverControl = coverControl;
    }

    public void generate() {
        DiagramComponentSetPanel setPane = (DiagramComponentSetPanel) coverControl.cover.getRelateComponent();

        ComboBoxModel model = setPane.getComboBox().getModel();

        boolean asked = false;
        int count = 0;

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) setPane.getInternalValueNode();
        int childCount = node.getChildCount();
        int childCurrent = 0;

        GenerateProgress progress = new GenerateProgress();

        progress.setMaximun(model.getSize());
        progress.setVisible(true);

        for (int i = 0; i < model.getSize(); i++) {
            SimPropertyChoice choice = (SimPropertyChoice) model.getElementAt(i);

            while (!((DefaultMutableTreeNode) node.getChildAt(childCurrent)).toString().equalsIgnoreCase(choice.getValue())) {
                ++childCurrent;
            }

            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) node.getChildAt(childCurrent);

            DiagramComponentSetPanel[] setPanes = new DiagramComponentSetPanel[currentNode.getChildCount()];

            progress.setText("Generating frame for " + choice.getDisplayName() + "...");
            progress.setValue(i);

            for (int j = 0; j < setPanes.length; j++) {
                setPanes[j] = (DiagramComponentSetPanel) ((DefaultMutableTreeNode) currentNode.getChildAt(j)).getUserObject();
            }

            if (setPanes.length > 0) {
                CreateFrameControl.ForceCreateFrame2(setPanes, choice, setPane.getName().substring(1) + " - " + choice.getDisplayName(), setPane.toString() + " - " + choice.getDisplayName(), setPane.getButton().isVisible());
                ++count;
            }
            ++childCurrent;
        }

        if (setPane.getCardPane() != null) {
            CardPane old = setPane.getCardPane();
            setPane.setUseButton(true);
            setPane.setCardPane(null);
            setPane.setUseButton(false);
            setPane.setCardPane(old);
        }
        progress.setVisible(false);
        progress.dispose();
        UIDesigner.workPaneTab.setSelectedIndex(UIDesigner.workPaneTab.getTabCount() - 1);
        JOptionPane.showMessageDialog(UIDesigner.mainWindow, count + " frames successfully generated.", "Generate frames", JOptionPane.PLAIN_MESSAGE);

    }

    public void actionPerformed(ActionEvent event) {
        generate();
    }
}
