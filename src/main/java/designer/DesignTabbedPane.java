package designer;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import designer.report.PrintEditor;

public class DesignTabbedPane extends JTabbedPane {
    /**
     * 
     */
    private static final long serialVersionUID = 3543610396517594906L;

    private int previousSelectedIndex;

    JPanel temp = new JPanel();;

    public DesignTabbedPane() {
        temp.setVisible(true);
        addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                System.out.println("TAB PANE MOUSE PRESSED");
                DesignerControl.currentDesignPane.deselectAll();
                DesignTabbedPane pane = (DesignTabbedPane) e.getSource();
                if (pane.getSelectedIndex() < 0) {
                    return;
                }
                if (pane.getComponent(pane.getSelectedIndex()) instanceof DesignBackPane) {
                    DesignPane currentDesignPane = ((DesignBackPane) pane.getComponent(pane.getSelectedIndex())).getDesignPane();
                    ((DesignBackPane) pane.getComponent(pane.getSelectedIndex())).refresh();
                    currentDesignPane.setVisible(true);
                    currentDesignPane.refresh();
                    UIDesigner.getControl().setCurrentDesignPane(currentDesignPane);

                    UIDesigner.mainWindow.setFrameEnable();
                } else {
                    System.out.println("REPOR ENABLED");
                    UIDesigner.mainWindow.setReportEnable();
                }
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public void updateUI() {
        int selected = getSelectedIndex();
        if (selected >= 0) {
            if (getComponent(getSelectedIndex()) instanceof DesignBackPane) {
                DesignBackPane pane = (DesignBackPane) getComponent(getSelectedIndex());
                pane.setName(pane.getDesignPane().toString());
                pane.updateUI();
                pane.repaint();

                int count = getComponentCount();

                Component[] temp = new Component[count];

                for (int i = 0; i < temp.length; i++) {
                    temp[i] = getComponent(i);
                }
                int current = 0;

                removeAll();

                while (current < count) {
                    add(temp[current]);
                    ++current;
                }
                setSelectedIndex(selected);
            } else {
                UIDesigner.mainWindow.setReportEnable();
            }

        }
        super.updateUI();
    }

    public void setSelectedIndex(int i) {
        DesignerControl.currentDesignPane.deselectAll();

        try {
            previousSelectedIndex = getSelectedIndex();
            super.setSelectedIndex(i);

        } catch (Exception e) {
            System.out.println(e);
        }

        i = getSelectedIndex();

        if (i >= 0) {
            // System.out.println("TEST 7");
            if (getComponent(i) instanceof DesignBackPane) {
                DesignPane currentDesignPane = ((DesignBackPane) getComponent(i)).getDesignPane();
                // System.out.println("TEST 8");
                ((DesignBackPane) getComponent(i)).refresh();
                // System.out.println("TEST 9");
                currentDesignPane.setVisible(true);
                currentDesignPane.refresh();

                // System.out.println("TEST 10");
                UIDesigner.getControl().setCurrentDesignPane(currentDesignPane);
                UIDesigner.mainWindow.getFrameList().setSelectedItem(currentDesignPane);
                UIDesigner.mainWindow.setFrameEnable();
            } else {
                JComboBox jComboBox = UIDesigner.mainWindow.getFrameList();
                for (int j = 0; j < jComboBox.getItemCount(); j++) {
                    if (jComboBox.getItemAt(j) instanceof PrintEditor) {
                        if (((PrintEditor) jComboBox.getItemAt(j)).getDesignPane().equals(getComponent(i))) {
                            UIDesigner.mainWindow.getFrameList().setSelectedIndex(j);
                        }
                    }
                }
                UIDesigner.mainWindow.setReportEnable();
            }
            DesignerControl.removePropertiesTable();
        }

    }

    public void remove(Component c) {
        super.remove(c);
        setSelectedIndex(previousSelectedIndex);
    }
    /*
     * public void removeTab ( DesignBackPane backPane ) { for ( int i = 0 ; i <
     * getTabCount() ; i ++ ) { if ( getComponent(i).equals(backPane) )
     * remove(i); } }
     */
}
