package designer;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import designer.deployment.CardComponent;
import designer.deployment.DAction;
import designer.deployment.DeployActionComponent;
import designer.deployment.DeployComponent2;
import designer.deployment.DeployFrame;
import designer.deployment.DeployObject;
import designer.deployment.GenerateProgress;
import designer.deployment.InputComponent;
import designer.deployment.SimPropertyChoice;

public class ProjectControl {
    private DeployObject deployObject;
    private DesignerControl designerControl;
    private ReportDesignerControl reportControl;
    private Vector refreshSetPane;

    private GenerateProgress progress;
    private String path = null;

    public ProjectControl() {
    }

    public void saveAsObeject(UIGeneratorControl uIGeneratorControl, DesignerControl designerControl, Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new DesFileFilter());
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();

        if (file != null) {
            if (file.isFile()) {
                path = file.getAbsolutePath();
            } else {
                path = file.getAbsolutePath() + ".vsd";
            }

            saveObject(uIGeneratorControl, designerControl, path);
        }

    }

    public void saveObject(UIGeneratorControl uIGeneratorControl, DesignerControl designerControl) {
        if (path != null) {
            saveObject(uIGeneratorControl, designerControl, path);
        } else {
            saveAsObeject(uIGeneratorControl, designerControl, UIDesigner.mainWindow);
        }
    }

    public void saveObject(UIGeneratorControl uIGeneratorControl, DesignerControl designerControl, String path) {
        DeployObject deployObject = uIGeneratorControl.generateDeployObject();

        DesignerProject deignerProject = new DesignerProject();

        deignerProject.setCount(DesignerControl.count);
        deignerProject.setGridSize(DesignerControl.gridSize);
        deignerProject.setRealTimeMove(DesignerControl.realTimMove);

        deignerProject.setDeployObject(deployObject);

        try {
            File outFile = new File(path);
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);

            outObjectStream.writeObject(deignerProject);
            outObjectStream.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void loadObject(DesignerControl designerControl, ReportDesignerControl reportControl, Component parent) {
        DesignerProject designerProject = null;

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new DesFileFilter());
        fc.showOpenDialog(parent);
        File file = fc.getSelectedFile();

        if (file.isFile()) {
            path = file.getAbsolutePath();
        }

        try {
            File inFile = new File(path);
            FileInputStream inFileStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            designerProject = (DesignerProject) inObjectStream.readObject();

        } catch (Exception e) {
            System.out.println(e);
        }

        DesignerControl.count = designerProject.getCount();
        DesignerControl.gridSize = designerProject.getGridSize();
        DesignerControl.realTimMove = designerProject.getRealTimeMove();

        deployObject = designerProject.getDeployObject();

        progress = new GenerateProgress(UIDesigner.mainWindow, "Please wait...", false);

        revertDeployObject(designerControl);
        revertDeployObject(reportControl);
    }

    public void importDeployObject(DesignerControl designerControl, ReportDesignerControl reportControl, Component parent) {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new DepFileFilter());
        fc.showOpenDialog(parent);
        File file = fc.getSelectedFile();
        String path = null;
        if (file.isFile()) {
            path = file.getAbsolutePath();
        }

        try {
            File inFile = new File(path);
            FileInputStream inFileStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            deployObject = (DeployObject) inObjectStream.readObject();

            DesignerControl.count = deployObject.getFrameObjectConunt() + 1;

            progress = new GenerateProgress(UIDesigner.mainWindow, "Please wait...", false);
            revertDeployObject(designerControl);
            revertDeployObject(reportControl);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void revertDeployObject(ReportDesignerControl reportControl) {
        if (deployObject != null) {
            revertDeployObject(deployObject, reportControl);
        }
    }

    public void revertDeployObject(DeployObject deployObject, ReportDesignerControl reportControl) {
        this.reportControl = reportControl;
        for (int i = 0; i < deployObject.getReportCount(); i++) {
            reportControl.newReport(deployObject.getReport(i));
        }

    }

    public void revertDeployObject(DesignerControl designerControl) {
        if (deployObject != null) {
            revertDeployObject(deployObject, designerControl);
        }
    }

    public void revertDeployObject(DeployObject deployObject, DesignerControl designerControl) {
        this.designerControl = designerControl;
        refreshSetPane = new Vector();

        int count = deployObject.getFrameObjectConunt();

        progress.setVisible(true);
        progress.setText("Loading project...");
        progress.setMaximun(count - 1);
        progress.setValue(0);

        for (int i = 0; i < deployObject.getFrameObjectConunt(); i++) {
            progress.setValue(i);

            DeployFrame frameComponent = deployObject.getFrameObject(i);
            DesignPane designPane = null;

            if (frameComponent.getID().substring(0, 1).equalsIgnoreCase("E")) {
            } else {
                String id = frameComponent.getID();
                String title = frameComponent.getTitle();
                String name = frameComponent.getName();
                designPane = designerControl.newPane(id, name);
                designPane.setTitle(title);
                frameComponent.getComponentProperties().setPropertise(designPane);
                loadDesignPane(designPane, frameComponent);
                designPane.deselectAll();
            }
        }

        progress.setText("Initializing...");
        // int newSize = refreshSetPane.size()-1 > 0 ? refreshSetPane.size()-1 :
        // 1;
        progress.setMaximun(refreshSetPane.size());
        progress.setValue(0);

        for (int i = 0; i < refreshSetPane.size(); i++) {
            progress.setValue(i + 1);
            DiagramComponentSetPanel setPanel = (DiagramComponentSetPanel) refreshSetPane.elementAt(i);
            setPanel.resetCardPaneContents(false);
        }

        progress.setVisible(false);
        progress.dispose();
    }

    private void loadDesignPane(DesignPane designPane, DeployFrame frameComponent) {
        Hashtable cardTable = new Hashtable();
        Vector cardComponents = frameComponent.getCardComponent();

        for (int j = 0; j < cardComponents.size(); j++) {
            CardComponent cardComponent = (CardComponent) cardComponents.elementAt(j);
            CardPane cardPane = new CardPane(cardComponent.getID());
            cardComponent.getCardProperties().setPropertise(cardPane);

            cardTable.put(cardComponent, cardPane);

            designPane.addDesignComponent(cardPane);
        }

        Vector inputComponents = frameComponent.getInputComponent();

        for (int j = 0; j < inputComponents.size(); j++) {
            InputComponent inputComponent = (InputComponent) inputComponents.elementAt(j);

            DiagramComponentSetPanel setPanel = designerControl.getSetPanel(inputComponent.getName(), inputComponent.getSetMode());
            System.out.println("GET NAME: " + inputComponent.getName() + ", " + inputComponent.getSetMode());
            inputComponent.getComponentProperties().setPropertise(setPanel);
            inputComponent.setButton(setPanel.getButton());
            inputComponent.setLabel(setPanel.getLabel());
            inputComponent.setComboBox(setPanel.getComboBox());
            inputComponent.setTextField(setPanel.getTextField());

            JComboBox jComboBox = setPanel.getComboBox();
            Object defaultValue = inputComponent.getDefaultValue();
            Object[] object = inputComponent.getModel(InputComponent.COMBO_BOX);
            // String defaultValue = inputComponent.setcom
            int modelCount = 0;
            for (int k = 0; k < object.length; k++) {
                SimPropertyChoice oChoice = (SimPropertyChoice) object[k];

                boolean done = false;
                do {
                    SimPropertyChoice jChoice = (SimPropertyChoice) jComboBox.getItemAt(modelCount);
                    if (oChoice.getValue().equalsIgnoreCase(jChoice.getValue())) {
                        jChoice.setDisplayName(oChoice.getDisplayName());
                        jChoice.setFrameID(oChoice.getFrameID());

                        if (defaultValue.toString().equalsIgnoreCase(oChoice.getDisplayName())) {
                            defaultValue = jChoice;
                        }
                        done = true;
                    } else {
                        jChoice.setDisplay(false);
                    }
                    ++modelCount;
                } while (!done);
            }
            setPanel.setDefaultValue(defaultValue);
            setPanel.getComboBox().setSelectedItem(defaultValue);
            designPane.addDesignComponent(setPanel);

            if (inputComponent.getCardComponent() != null) {
                setPanel.setCardPane((CardPane) cardTable.get(inputComponent.getCardComponent()));
                refreshSetPane.add(setPanel);
            }
        }

        Vector otherComponents = frameComponent.getOtherObject();

        for (int j = 0; j < otherComponents.size(); j++) {
            DeployComponent2 deployComponent = (DeployComponent2) otherComponents.elementAt(j);

            CLabel label = new CLabel();
            deployComponent.getComponentProperties().setPropertise(label);

            designPane.addDesignComponent(label);
            // System.out.println(deployComponent);
        }

        Vector actionComponents = frameComponent.getActionObject();

        for (int j = 0; j < actionComponents.size(); j++) {
            DeployActionComponent actionComponent = (DeployActionComponent) actionComponents.elementAt(j);

            CButton button = new CButton();
            button.setText(actionComponent.getText());
            actionComponent.getComponentProperties().setPropertise(button);

            Object[] object = designerControl.getActionChooser();
            DAction action = actionComponent.getDAction();

            if (action != null) {
                for (int k = 0; k < object.length; k++) {
                    if (object[k].getClass().getName().equals(action.getClass().getName())) {
                        button.setDAction((DAction) object[k]);
                    }
                }
            }

            designPane.addDesignComponent(button);
        }

        Vector frameComponents = frameComponent.getFrameObject();

        for (int j = 0; j < frameComponents.size(); j++) {
            DeployFrame framePane = (DeployFrame) frameComponents.elementAt(j);

            TitlePane titlePane = new TitlePane(framePane.getID());

            framePane.getComponentProperties().setPropertise(titlePane);
            titlePane.setText(framePane.getTitle());

            loadDesignPane(titlePane, framePane);

            designPane.addDesignComponent(titlePane);
        }
        designPane.commandStack.clearUndo();
        designPane.commandStack.clearRedo();
    }

}
