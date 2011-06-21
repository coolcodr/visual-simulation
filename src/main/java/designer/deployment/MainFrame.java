package designer.deployment;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.SimThread;

public class MainFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 204454183739058881L;

    private JPanel jPanel1 = new JPanel();

    public static Hashtable frame = new Hashtable();
    // private JDialog startDialog;

    private MainControl mainControl;
    private DeployObject deployObject;
    private DeployObjectFactory deployObjectFactory;

    private boolean exitFlag;

    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem4 = new JMenuItem();

    public MainFrame(boolean exitFlag) {
        this.exitFlag = exitFlag;
        init("temp_deploy_object.vsx");
    }

    public MainFrame(String deployObject, boolean exitFlag) {
        this.exitFlag = exitFlag;
        System.out.println("Loading: " + deployObject + ".vsx");
        init(deployObject + ".vsx");
    }

    private void init(String path) {
        setTitle("...");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        jPanel1.setLayout(null);

        jMenu1.setText("Action");
        jMenuItem1.setText("Run");
        jMenuItem2.setText("Exit");
        jMenu2.setText("Data");
        jMenuItem3.setText("Save");
        jMenuItem4.setText("Load");

        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu2.add(jMenuItem3);
        jMenu2.add(jMenuItem4);

        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem1_actionPerformed(e);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem2_actionPerformed(e);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem3_actionPerformed(e);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem4_actionPerformed(e);
            }
        });

        boolean haveMore = true;
        int current = 0;

        try {
            // File inFile = new
            // File("F:\\Kenny's folder\\Kenny's works\\Software Engineering year3\\project work3\\Horst\\DeploymentDesigner\\vSim1901\\src\\TestingDeploymentObjects.dat");
            File inFile = new File(path);
            // File inFile = new
            // File("E:\\Temp\\new vSim\\classes\\TestingDeploymentObjects.dat");
            FileInputStream inFileStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            deployObject = (DeployObject) inObjectStream.readObject();

        } catch (Exception e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        deployObjectFactory = new DeployObjectFactory(deployObject);
        // this.mainControl = new MainControl(deployObjectFactory);

        // To add all input component (Top level) to deployObject
        for (int i = 0; i < deployObject.getInputObjectCount(); i++) {
            InputComponent inputComponent = deployObject.getInputObject(i);

            inputComponent.setLabel(new JLabel(inputComponent.getText(InputComponent.LABEL)));
            inputComponent.setTextField(new JTextField(inputComponent.getText(InputComponent.TEXT_FIELD)));
            inputComponent.setComboBox(new JComboBox(inputComponent.getModel(InputComponent.COMBO_BOX)));
            inputComponent.setButton(new JButton(inputComponent.getText(InputComponent.BUTTON)));
            inputComponent.setFrame(this);

            JPanel jPanel = new JPanel();
            jPanel.setOpaque(false);
            // jPanel1.add(inputComponent.getPanel(jPanel));
            // System.out.println(inputComponent);
        }

        // The add all input component (nested or says internal parameter) to
        // deployObject
        for (int i = 0; i < deployObject.getInternalInputObjectCount(); i++) {
            InputComponent inputComponent = deployObject.getInternalInputObject(i);

            inputComponent.setLabel(new JLabel(inputComponent.getText(InputComponent.LABEL)));
            inputComponent.setTextField(new JTextField(inputComponent.getText(InputComponent.TEXT_FIELD)));
            inputComponent.setComboBox(new JComboBox(inputComponent.getModel(InputComponent.COMBO_BOX)));
            inputComponent.setButton(new JButton(inputComponent.getText(InputComponent.BUTTON)));
            inputComponent.setFrame(this);

            JPanel jPanel = new JPanel();
            jPanel.setOpaque(false);
            // jPanel1.add(inputComponent.getPanel(jPanel));
        }

        // To add all frame (JDialog), and also at these time, add those input
        // object to these frame
        for (int i = 0; i < deployObject.getFrameObjectConunt(); i++) {
            DeployFrame frameComponent = deployObject.getFrameObject(i);
            JDialog jDialog;
            Container contentPane;
            if (frameComponent.getID().equalsIgnoreCase("main")) {
                jDialog = frameComponent.getFrame(new JDialog());
                this.setSize(jDialog.getSize());
                contentPane = jDialog.getContentPane();
                setContentPane(contentPane);
                setTitle(jDialog.getTitle());
                setJMenuBar(jMenuBar1);
                this.setSize(getWidth(), getHeight() + 20);
            } else if (frameComponent.getID().substring(0, 1).equalsIgnoreCase("E")) {
                JPanel jPanel = new JPanel();
                JPanel backPane = frameComponent.getPane(jPanel);
                contentPane = frameComponent.getContentPane();
                frame.put(frameComponent.getID(), backPane);
            } else {
                jDialog = frameComponent.getFrame(new JDialog(this));
                jDialog.setModal(true);
                jDialog.setResizable(false);
                frame.put(frameComponent.getID(), frameComponent.getFrame(jDialog));
                contentPane = jDialog.getContentPane();
            }

            Vector inputComponents = frameComponent.getInputComponent();
            // System.out.println("Nest 1: "+inputComponents.size());

            for (int j = 0; j < inputComponents.size(); j++) {
                InputComponent inputComponent = (InputComponent) inputComponents.elementAt(j);

                JPanel jPanel = new JPanel();
                jPanel.setOpaque(false);
                contentPane.add(inputComponent.getPanel(jPanel));
                // System.out.println(inputComponent);
            }

            // "Other"Object actually is just "LABEL"!
            // The name should be "Label"Object
            Vector otherComponents = frameComponent.getOtherObject();

            for (int j = 0; j < otherComponents.size(); j++) {
                DeployComponent2 deployComponent = (DeployComponent2) otherComponents.elementAt(j);
                contentPane.add(deployComponent.getCompoent());
                // System.out.println(deployComponent);
            }

            Vector actionComponents = frameComponent.getActionObject();
            // System.out.println("Nest 2: "+actionComponents.size());
            for (int j = 0; j < actionComponents.size(); j++) {
                DeployActionComponent actionComponent = (DeployActionComponent) actionComponents.elementAt(j);
                contentPane.add(actionComponent.getButton());
                // actionComponent.setControl(mainControl);
                actionComponent.setControl(this);
                actionComponent.setDialog(frameComponent.getDialog());
            }
        }

        // Temp .. wrong implementaation
        // But this temp will add ALL ACTION OBJECT INTO THIS FRAME,
        // Actualy it is defined which frame own this action obecjt.
        /*
         * for ( int i = 0 ; i < deployObject.getActionObjectConunt() ; i ++ ) {
         * DeployActionComponent actionComponent =
         * deployObject.getActionObject(i);
         * 
         * actionComponent.setControl((MainControlHandler)mainControl);
         * actionComponent.setControl(this);
         * 
         * this.getContentPane().add(actionComponent.getButton(new JButton()));
         * //TEMP //actionComponent.setActionInput((InputComponent)deployObject.
         * getInputObject(0)); }
         */

        for (int i = 0; i < deployObject.getFrameObjectConunt(); i++) {
            DeployFrame frameComponent = deployObject.getFrameObject(i);
            // System.out.println("FOR FRAME OBJECT: "+frameComponent.getID());
            Container contentPane = frameComponent.getContentPane();

            Vector frameComponents = frameComponent.getFrameObject();

            for (int j = 0; j < frameComponents.size(); j++) {
                DeployFrame framePane = (DeployFrame) frameComponents.elementAt(j);
                JPanel pane = (JPanel) frame.get(framePane.getID());
                // System.out.println("GET CONTAINT PANE: "+contentPane);
                // System.out.println("ADD TEH PANE: "+framePane.getID());
                if (contentPane != null && pane != null)// TEMP
                {
                    // System.out.println("PANE BOUNDSL "+pane.getBounds());
                    contentPane.add(pane);
                }
            }

            Vector cardComponents = frameComponent.getCardComponent();

            for (int j = 0; j < cardComponents.size(); j++) {
                CardComponent cardComponent = (CardComponent) cardComponents.elementAt(j);
                JPanel jPanel = cardComponent.getComponent();
                contentPane.add(jPanel);
            }

            Vector inputComponents = frameComponent.getInputComponent();

            for (int j = 0; j < inputComponents.size(); j++) {
                InputComponent inputComponent = (InputComponent) inputComponents.elementAt(j);
                inputComponent.setCardPane();
            }
        }

        // saveData ();//temp
    }

    /*
     * private void resetActionControl( MainControl mainControl ) { for ( int i
     * = 0 ; i < deployObject.getActionObjectConunt() ; i ++ ) {
     * DeployActionComponent actionComponent = deployObject.getActionObject(i);
     * 
     * actionComponent.setControl(mainControl);
     * actionComponent.setControl(this);
     * 
     * //this.getContentPane().add(actionComponent.getButton(new JButton()));
     * //TEMP
     * //actionComponent.setActionInput((InputComponent)deployObject.getInputObject
     * (0)); } }
     */
    void jMenuItem1_actionPerformed(ActionEvent e) {
        startRun();
    }

    void jMenuItem2_actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void jMenuItem3_actionPerformed(ActionEvent e) {
        saveData();
    }

    void jMenuItem4_actionPerformed(ActionEvent e) {
        loadData();
    }

    public void startRun() {
        // this.mainControl = new MainControl(deployObjectFactory);
        // mainControl = null;
//        MainControl mainControl = null;

        // Method runMethod;

        /*
         * try{ designer.SimpleClassLoader classLoader = new
         * designer.SimpleClassLoader(); Class controlClass =
         * classLoader.loadClass("designer.MainControl");
         * System.out.println("++ Class: "+controlClass); Class[] classArray =
         * new Class[] { DeployObjectFactory.class };
         * System.out.println("++ ClassArray: "+classArray); Constructor
         * controlContructor = controlClass.getConstructor(classArray);
         * System.out.println("++ Contructor :"+controlContructor); Object[]
         * objectArray = new Object[] {deployObjectFactory};
         * System.out.println("++ ObejctArray :" +objectArray); mainControl =
         * (MainControl)controlContructor.newInstance(objectArray);
         * System.err.println("++ MainControl: "+mainControl); //runMethod =
         * controlClass.getMethod("startRun",null);
         * //System.err.println("++ Method :"+runMethod); //mainControl =
         * runMethod.invoke(mainControl, null);
         * //System.err.println("RUN FIN 1!!!! >> ");
         * 
         * } catch (Exception e2) { System.err.println(e2);
         * System.err.println(e2.getStackTrace().toString()); }
         */

        // SimThread.setStop(false);
        // SystemThread.setStop(false);
        SimThread.clearSystemData();

        mainControl = new MainControl(deployObjectFactory);
        // saveData ();//temp
        // MainControl main2 = new MainControl(this.deployObjectFactory);
        try {
            System.out.println("RUN >> " + mainControl);
            mainControl.startRun();
            System.out.println("RUN FIN >> ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Data Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void loadData() {
        ImportDataControl importData = new ImportDataControl(deployObject);
        importData.loadData(this);
    }

    public void saveData() {
        ExportDataControl exportData = new ExportDataControl(deployObject);
        exportData.saveData(this);
    }

    public void setVisible(boolean b) {
        if (b) {
            this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        }

        super.setVisible(b);

        if (exitFlag && !b) {
            System.exit(0);
        }
    }
}
