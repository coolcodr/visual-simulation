package designer;

import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import designer.deployment.DActionClose;
import designer.deployment.DActionExit;
import designer.deployment.DActionLoad;
import designer.deployment.DActionRun;
import designer.deployment.DActionSave;
import designer.deployment.MainFrame;

public class DesignerControl {
    protected static int gridSize = 4;
    protected static DesignPane currentDesignPane;
    protected static boolean realTimMove = true;

    // All designPane created
    protected static Vector designPanes = new Vector();
    protected static Hashtable allDesignPane = new Hashtable();

    private Object[] actions = { new DActionRun(), new DActionClose(), new DActionExit(), new DActionSave(), new DActionLoad() };
    // private JComboBox actionChooser = new JComboBox ( object );

    private UIGeneratorControl uIGeneratorControl;
    private CodeGeneratorControl codeGeneratorControl;
    private DiagramElementSource diagramElementSource;

    private ProjectControl projectControl;

    private static FrameComponentList list;

    // All created frame count, for unique id generate
    protected static int count = 0;

    private MainFrame testFrame;

    public DesignerControl(UIGeneratorControl uICodeControl, CodeGeneratorControl codeGeneratorControl, DiagramElementSource diagramElementSource) {
        uIGeneratorControl = uICodeControl;
        this.codeGeneratorControl = codeGeneratorControl;
        this.diagramElementSource = diagramElementSource;

        // this.commandStack = new CommandStack();

        projectControl = new ProjectControl();
    }

    public void viewCode() {
        // generateCode();
        CodeViewer codeViewer = new CodeViewer();
        codeViewer.setVisible(true);
    }

    public void stopRun() {
        if (testFrame != null) {
            testFrame.setVisible(false);
            testFrame = null;
            UIDesigner.mainWindow.setStartButtonEnable(true);
        }
    }

    public void testRun() {
        generateUI();
        testFrame = new MainFrame(false);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        testFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                UIDesigner.mainWindow.setStartButtonEnable(true);
            }

            public void windowClosed(WindowEvent e) {
                UIDesigner.mainWindow.setStartButtonEnable(true);
            }
        });

        UIDesigner.mainWindow.setStartButtonEnable(false);
    }

    public DiagramComponentSetPanel getSetPanel(String name, String setMode) {
        DiagramSourceList list = UIDesigner.sourceList;
        return list.getComponent(name + "|" + setMode);
    }

    public void generateUI() {
        uIGeneratorControl.generateUI();
    }

    public void closeCurrentWorkSpace(ReportDesignerControl reportControl) {
        UIDesigner.workPaneTab.removeAll();
        UIDesigner.mainWindow.getFrameList().removeAllItems();
        designPanes.removeAllElements();
        allDesignPane.clear();
        currentDesignPane = null;
        count = 0;

        reportControl.close();
        // UIDesigner.mainWindow.setDiagramSourceList(diagramElementSource.regenerateList());
    }

    public void selectAll() {
        if (currentDesignPane.coverComponent.size() > 0) {
            currentDesignPane.selectAll();
            setEditButtonEnable(true);
            setDeleteButtonEnable(true);
        }
    }

    public void deslectAll() {
        currentDesignPane.deselectAll();
    }

    public void newWorkSpace() {
        // UIDesigner.mainWindow.setDiagramSourceList(diagramElementSource.regenerateList("Loading new designer component..."));
        newPane("main", "Main");
        UIDesigner.workPaneTab.removeAll();
        showFrame("main");
    }

    public void saveAsObject() {
        // TEMP
        projectControl.saveAsObeject(uIGeneratorControl, this, UIDesigner.mainWindow);
    }

    public void saveObject() {
        // TEMP
        projectControl.saveObject(uIGeneratorControl, this);
    }

    public void loadObject(ReportDesignerControl reportControl) {
        closeCurrentWorkSpace(reportControl);
        // UIDesigner.mainWindow.setDiagramSourceList(diagramElementSource.regenerateList("Loading designer project component..."));
        projectControl.loadObject(this, reportControl, UIDesigner.mainWindow); // TEMP
        closeAllTab();
        // projectControl.revertDeployObject(this);
    }

    public void closeAllTab() {
        UIDesigner.workPaneTab.removeAll();
        showFrame("main");
    }

    public void importObject(ReportDesignerControl reportControl) {
        closeCurrentWorkSpace(reportControl);
        projectControl.importDeployObject(this, reportControl, UIDesigner.mainWindow);
        closeAllTab();
    }

    public void deployObject() {
        // this.generateCode();
        uIGeneratorControl.exportDeploy(UIDesigner.mainWindow);
    }

    public void exportObject() {
        // TEMP
        // projectControl.exportDeployObject(this, UIDesigner.mainWindow);
        // this.code
        uIGeneratorControl.exportObject(UIDesigner.mainWindow);
    }

    public void undo() {
        currentDesignPane.deselectAll();
        currentDesignPane.commandStack.undo();
        UIDesigner.mainWindow.repaintTable();
        refreshFrameList();
        currentDesignPane.repaint();
        setUndoRedoButton();
    }

    public static void setGenerateEnalble(boolean b) {
        if (currentDesignPane.selectedCovers.size() == 1) {
            UIDesigner.mainWindow.setGenerateMenuEnable(b);
        } else {
            UIDesigner.mainWindow.setGenerateMenuEnable(false);
        }
    }

    public static void setEditButtonEnable(boolean b) {
        UIDesigner.mainWindow.setEditButtonEnable(b);
    }

    public static void setDeleteButtonEnable(boolean b) {
        UIDesigner.mainWindow.setDeleteButtonEnable(b);
    }

    private void setUndoRedoButton() {
        UIDesigner.mainWindow.setUndoButtonEnable(currentDesignPane.commandStack.getUndoCommandCount() > 0);
        UIDesigner.mainWindow.setRedoButtonEnable(currentDesignPane.commandStack.getRedoCommandCount() > 0);
    }

    public void addUndoCommand(PropertyCommand command) {
        currentDesignPane.commandStack.addUndoCommand(command);
        setUndoRedoButton();
    }

    public void redo() {
        currentDesignPane.deselectAll();
        currentDesignPane.commandStack.redo();
        UIDesigner.mainWindow.repaintTable();
        refreshFrameList();
        currentDesignPane.repaint();
        setUndoRedoButton();
    }

    public void generateCode() {
        codeGeneratorControl.generateCode();
    }

    public static int getGridSize() {
        return gridSize;
    }

    public void alignLeft() {
        currentDesignPane.alignLeft();
    }

    public void alignRight() {
        currentDesignPane.alignRight();
    }

    public void alignTop() {
        currentDesignPane.alignTop();
    }

    public void alignBottom() {
        currentDesignPane.alignBottom();
    }

    public void autoLayout() {
        currentDesignPane.autoLayout();
    }

    /*
     * public void setPropertiesTable(ElementProperties properties) {
     * UIDesigner.sourceTable.setProperties(properties); }
     */
    // Show frame by giving the frame id
    public void showFrame(String id) {
        for (int i = 0; i < designPanes.size(); i++) {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            if (designPane.getID().equals(id)) {
                showFrame(designPane);
                return;
            }
        }
    }

    public void setCurrentDesignPane(DesignPane designPane) {
        resetAction();
        currentDesignPane = designPane;
        list = new FrameComponentList(designPane);
        list.refresh();
        UIDesigner.mainWindow.setFrameComponentList(list);
        resetAction();

        setEditButtonEnable(false);
        setUndoRedoButton();
    }

    public static void refreshFrameList() {
        if (list != null) {
            list.refresh();
        }
    }

    public static void removePropertiesTable() {
        UIDesigner.mainWindow.setDiagramSourceTable(new DiagramSourceTable());
        if (UIDesigner.sourceTable.isShowing()) {
            UIDesigner.sourceTable.repaint();
        }
    }

    public static void refreshPropertiesTable() {
        if (UIDesigner.sourceTable.isShowing()) {
            UIDesigner.sourceTable.repaint();
        }
    }

    public Object[] getActionChooser() {
        return actions;
    }

    private void resetAction() {
        if (currentDesignPane != null) {
            currentDesignPane.getUpperPane().resetControl();
            currentDesignPane.restoreButtonStatus();
        }
    }

    // Show fram by giving the actual frame (DesignPane in design time)
    public void showFrame(DesignPane designPane) {
        // Reset all the control to prevent bug when user uncompleted an action
        resetAction();
        currentDesignPane.deselectAll();

        setCurrentDesignPane(designPane);

        // currentDesignPane = designPane;

        for (int i = 0; i < UIDesigner.workPaneTab.getTabCount(); i++) {
            if (UIDesigner.workPaneTab.getComponent(i) instanceof DesignBackPane) {
                DesignBackPane back = (DesignBackPane) UIDesigner.workPaneTab.getComponent(i);
                DesignPane pane = back.getDesignPane();
                back.refresh();
                if (pane.equals(designPane)) {
                    UIDesigner.workPaneTab.setSelectedIndex(i);
                    /*
                     * if ( currentDesignPane.getParent() != null )
                     * currentDesignPane.getParent().remove(currentDesignPane);
                     */
                    /*
                     * System.out.println(pane.isShowing());
                     * System.out.println(pane.getBounds());
                     * System.out.println("SHOW 1 :"
                     * +currentDesignPane.getParent());
                     * System.out.println(pane.isShowing());
                     * System.out.println(pane.getBounds());
                     */
                    pane.setVisible(true);

                    setCurrentDesignPane(designPane);
                    return;
                }
            }
        }
        /*
         * if ( currentDesignPane.getParent() != null )
         * currentDesignPane.getParent().remove(currentDesignPane);
         */
        DesignBackPane designBackPane = new DesignBackPane(currentDesignPane);
        UIDesigner.workPaneTab.add(designBackPane);

        UIDesigner.workPaneTab.setSelectedIndex(UIDesigner.workPaneTab.getTabCount() - 1);

        designBackPane.getDesignPane().setVisible(true);

        designBackPane.refresh();

        removePropertiesTable();

        refreshFrameList();

        /*
         * if ( currentDesignPane.getParent() != null )
         * currentDesignPane.getParent().remove(currentDesignPane);
         */
        System.out.println("SHOW 2 :" + currentDesignPane.getParent());
        resetAction();

        setCurrentDesignPane(designPane);
    }

    public static String getNewID() {
        return String.valueOf(count++);
    }

    public void forceDeletePane(DesignPane designPane) {
        designPane.getUpperPane().removeAll();
        designPanes.remove(designPane);
        allDesignPane.remove(designPane.getID());
        System.out.println("SIZE 1 " + designPanes.size());
        System.out.println("SIZE 2 " + allDesignPane.size());
        JTabbedPane tabbedPane = UIDesigner.workPaneTab;

        for (int i = 0; i < tabbedPane.getComponentCount(); i++) {
            if (tabbedPane.getComponent(i) instanceof DesignBackPane) {
                if (((DesignBackPane) tabbedPane.getComponent(i)).getDesignPane().equals(designPane)) {
                    tabbedPane.remove(tabbedPane.getComponent(i));
                    break;
                }
            }
        }

        JComboBox jComboBox = UIDesigner.mainWindow.jComboBoxFrame;

        Object[] objects = new Object[jComboBox.getItemCount()];
        for (int j = 0; j < objects.length; j++) {
            objects[j] = jComboBox.getItemAt(j);
        }
        jComboBox.removeAllItems();
        for (int j = 0; j < objects.length; j++) {
            if (!objects[j].equals(designPane)) {
                jComboBox.addItem(objects[j]);
            }
        }

        /*
         * JComboBox jComboBox = UIDesigner.mainWindow.jComboBoxFrame;
         * jComboBox.removeAllItems(); for (int i = 0; i < designPanes.size();
         * i++) { jComboBox.addItem(designPanes.elementAt(i)); }
         */
    }

    public void deletePane(DesignPane designPane) {
        if (designPane.getID().equalsIgnoreCase("main")) {
            JOptionPane.showMessageDialog(UIDesigner.mainWindow, "Main frame for start up cannot be deleted.", "Delete Frame", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedValue = JOptionPane.showConfirmDialog(UIDesigner.mainWindow, "Permanently delete the selected frame?", "Delete Frame", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selectedValue == JOptionPane.NO_OPTION) {
            return;
        }

        forceDeletePane(designPane);
    }

    public DesignPane newPane() {
        return newPane(getNewID(), "Utitled Frame " + (designPanes.size() + 1));
    }

    public DesignPane newPane(String id) {
        return newPane(id, "Utitled Frame " + (designPanes.size() + 1));
    }

    public DesignPane newPane(String id, String name) {
        // Reset all the control to prevent bug when user uncompleted an action
        resetAction();

        currentDesignPane = new DesignPane(id, name);
        // currentUpperPane = currentDesignPane.getUpperPane();

        designPanes.add(currentDesignPane);
        allDesignPane.put(id, currentDesignPane);

        currentDesignPane.upperPane.addMouseListener(new UpperPaneResizeListener());
        /*
         * currentDesignPane.upperPane.addMouseListener( new MouseAdapter() {
         * public void mousePressed(MouseEvent e) { DesignUpperPane pane =
         * (DesignUpperPane) e.getSource(); DesignPane designPane =
         * pane.getDesignPane(); designPane.showPaneResize(true);
         * designPane.deselectAll(); } });
         */

        DesignBackPane designBackPane = new DesignBackPane(currentDesignPane);
        UIDesigner.workPaneTab.add(designBackPane);

        UIDesigner.workPaneTab.repaint();

        UIDesigner.mainWindow.addFrame(currentDesignPane);
        UIDesigner.workPaneTab.setSelectedIndex(UIDesigner.workPaneTab.getTabCount() - 1);

        designBackPane.refresh();

        resetAction();

        // UIDesigner.jList2.getContents().addElement(currentDesignPane.getID());
        return currentDesignPane;

    }

    public static DesignPane getDesignPane(String id) {
        return (DesignPane) allDesignPane.get(id);
    }

    public void changeAddComponentControl(AddComponentControl control) {
        // resetAction();
        currentDesignPane.getUpperPane().setControl(control);
    }

    // Temp
    public void newButton() {
        DiagramActionSetPanel jButton1 = new DiagramActionSetPanel();
        jButton1.setText("Start");
        jButton1.setBounds(20, 20, 60, 25);
        jButton1.setVisible(true);

        // TEMP
        jButton1.setDAction(new DActionRun());

        currentDesignPane.addActionComponent(jButton1);

        currentDesignPane.repaint();

        // DiagramSourceList.dropTarget2.removeDropTargetListener(currentUpperPane);
    }
}
