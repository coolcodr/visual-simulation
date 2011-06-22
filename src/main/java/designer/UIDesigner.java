package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.tree.DefaultMutableTreeNode;

import util.ImageIconHelper;
import designer.deployment.FrameIDandName;
import designer.report.PrintEditor;

public class UIDesigner extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -9109240726817613645L;
    private BorderLayout borderLayout1 = new BorderLayout();
    private JToolBar jToolBar1 = new JToolBar();

    private JPanel jPanel1 = new JPanel();
    private JSplitPane jSplitPane1 = new JSplitPane();
    private JSplitPane jSplitPane2 = new JSplitPane();
    private JPanel jPanel2 = new JPanel();
    protected static JTable propertiseTable = new JTable();
    private JScrollPane propertiesScrollPane = new JScrollPane(propertiseTable);
    private JPanel formListPanel = new JPanel();
    private JPanel tooBarPanel = new JPanel();
    // private static DefaultMutableTreeNode level0 = new
    // DefaultMutableTreeNode("Diagram Element");
    protected static DiagramSourceList sourceList; // = new
                                                   // DiagramSourceList(level0);
    protected static DiagramSourceAnalysisList analysisList;

    protected static FrameComponentList framComponentList;
    private JScrollPane listScrollPane = new JScrollPane();

    public static DesignTabbedPane workPaneTab = new DesignTabbedPane();
    private JButton createFrameButton = new JButton();

    protected static UIDesigner mainWindow;

    private static DesignerControl designerControl;
    private static ReportDesignerControl reportControl;

    private JButton jButton2 = new JButton();
    private TitledBorder titledBorder1;
    private TitledBorder titledBorder2;
    private JPanel jPanel3 = new JPanel();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JPanel jPanel4 = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private DesignerComponentButton jToggleButton1 = new DesignerComponentButton(CLabel.class);
    private DesignerComponentButton jToggleButton2 = new DesignerComponentButton(CButton.class);
    private DesignerComponentButton jToggleButton3 = new DesignerComponentButton(TitlePane.class);
    private DesignerComponentButton jToggleButton4 = new DesignerComponentButton(CardPane.class);

    private JButton jButton4 = new JButton();
    private JButton jButton5 = new JButton();
    private JButton jButton1 = new JButton();
    private JSplitPane jSplitPane3 = new JSplitPane();

    private int savedSplitHeight = 180;
    private JPanel jPanel5 = new JPanel();
    private JSplitPane jSplitPane4 = new JSplitPane();
    private BorderLayout borderLayout3 = new BorderLayout();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JScrollPane tableScrollPane = new JScrollPane();
    private JTabbedPane jTabbedPane2 = new JTabbedPane();

    private JPanel leftBottom1Panel = new JPanel();
    private JPanel leftBottomBottomPanel = new JPanel();
    private JPanel jTopPane = new JPanel();
    protected static JList jList1 = new JList();
    protected JTabbedPane propertiesTabbedPane = new JTabbedPane();
    private JPanel mutFrameList = new JPanel();
    protected static DiagramSourceTable sourceTable = new DiagramSourceTable();
    private JScrollPane propertiesScrollPane2 = new JScrollPane(sourceTable);
    // TEMP!!

    DefaultMutableTreeNode root = new DefaultMutableTreeNode(new TempTestBoolean(false));
    DefaultMutableTreeNode root1 = new DefaultMutableTreeNode(new TempTestBoolean(true));
    DefaultMutableTreeNode root2 = new DefaultMutableTreeNode(new TempTestBoolean(false));
    DefaultMutableTreeNode root3 = new DefaultMutableTreeNode(new TempTestBoolean(true));
    DefaultMutableTreeNode root4 = new DefaultMutableTreeNode(new TempTestBoolean(false));
    JPanel jPanel6 = new JPanel();
    JPanel jPanel7 = new JPanel();
    JPanel jPanel8 = new JPanel();
    JPanel jPanel9 = new JPanel();
    JPanel jPanel10 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    BorderLayout borderLayout4 = new BorderLayout();
    JComboBox jComboBox1 = new JComboBox();
    JTextField jTextField1 = new JTextField();
    BorderLayout borderLayout5 = new BorderLayout();
    BorderLayout borderLayout6 = new BorderLayout();
    JLabel jLabel1 = new JLabel();
    BorderLayout borderLayout7 = new BorderLayout();
    JLabel jLabel2 = new JLabel();
    JButton jButton7 = new JButton();
    BorderLayout borderLayout8 = new BorderLayout();
    JPanel jPanel11 = new JPanel();
    BorderLayout borderLayout9 = new BorderLayout();
    public static MutableList jList2 = new MutableList();
    JPanel jPanel12 = new JPanel();
    JLabel jLabel3 = new JLabel();
    BorderLayout borderLayout10 = new BorderLayout();
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenu jMenu2 = new JMenu();
    JMenu jMenu3 = new JMenu();
    JPanel jPanel13 = new JPanel();
    JLabel jLabel4 = new JLabel();
    BorderLayout borderLayout11 = new BorderLayout();
    JPanel propertyTablePane = new JPanel();
    JButton jButton3 = new JButton();
    JButton jButton6 = new JButton();
    JButton jButton8 = new JButton();
    JButton jButton9 = new JButton();
    JButton jButton11 = new JButton();
    JButton jButton12 = new JButton();
    JButton jButton13 = new JButton();
    JButton jButton14 = new JButton();
    JButton jButton15 = new JButton();
    JButton jButton16 = new JButton();
    JButton jButton17 = new JButton();
    JButton jButton10 = new JButton();
    TitledBorder titledBorder3;
    JButton jButton18 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/New16.gif"));
    JButton jButton19 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Open16.gif"));
    JButton jButton20 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Save16.gif"));
    JButton jButton21 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/SaveAs16.gif"));
    JButton jButton22 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Undo16.gif"));
    JButton jButton23 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Redo16.gif"));
    JButton jButton24 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/AlignLeft16.gif"));
    JButton jButton25 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/AlignRight16.gif"));
    JButton jButton26 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/AlignTop16.gif"));
    JButton jButton27 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/AlignBottom16.gif"));
    JButton jButton28 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/AlignAuto16.gif"));
    JButton jButton29 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Delete16.gif"));
    JButton jButton31 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Stop16.gif"));

    JButton jButton30 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Play16.gif"));
    JLabel jLabel5 = new JLabel();
    JComboBox jComboBoxFrame = new JComboBox();
    JButton jButton32 = new JButton(new ImageIconHelper().getImageIcon("/designer/images/Remove16.gif"));
    JLabel jLabel6 = new JLabel();
    JButton jButton37 = new JButton();
    TitledBorder titledBorder4;
    JMenuItem jMenuItem1 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/New16.gif"));
    JMenuItem jMenuItem5 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/SaveAs16.gif"));
    JMenuItem jMenuItem6 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Save16.gif"));
    JMenuItem jMenuItem7 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem8 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Open16.gif"));// BLANK
    JMenuItem jMenuItem11 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem9 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem4 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem12 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem13 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Delete16.gif"));
    JMenuItem jMenuItem15 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Redo16.gif"));
    JMenuItem jMenuItem16 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Undo16.gif"));
    JMenuItem jMenuItem17 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenu jMenu4 = new JMenu();
    JMenuItem jMenuItem14 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem18 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem19 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem20 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenu jMenu5 = new JMenu();
    JMenu jMenu6 = new JMenu();
    JMenuItem jMenuItem2 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/AlignAuto16.gif"));
    JMenuItem jMenuItem21 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/AlignLeft16.gif"));
    JMenuItem jMenuItem22 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/AlignRight16.gif"));
    JMenuItem jMenuItem23 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/AlignTop16.gif"));
    JMenuItem jMenuItem24 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/AlignBottom16.gif"));
    JMenuItem jMenuItem25 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem26 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem27 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem28 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Play16.gif"));
    JMenuItem jMenuItem29 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Stop16.gif"));
    JMenuItem jMenuItem30 = new JMenuItem();// new
                                            // ImageIcon("designer/images/Blank16.gif"));//BLANK
    JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
    JMenu jMenu7 = new JMenu();
    JMenuItem jMenuItem3 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Remove16.gif"));
    JMenuItem jMenuItem31 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));
    JMenuItem jMenuItem32 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenu jMenu8 = new JMenu();
    JMenuItem jMenuItem33 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem34 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem35 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JEditorPane jEditorPane1 = new JEditorPane();
    JMenu jMenu9 = new JMenu();
    JMenu jMenu10 = new JMenu();
    JMenu jMenu11 = new JMenu();
    JMenuItem jMenuItem36 = new JMenuItem();// new
                                            // ImageIcon("designer/images/Blank16.gif"));//BLANK
    JMenuItem jMenuItem37 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem38 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JCheckBoxMenuItem jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
    JMenuItem jMenuItem39 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem40 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JCheckBoxMenuItem jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
    JMenuItem jMenuItem41 = new JMenuItem();// new
                                            // ImageIcon("designer/images/Blank16.gif"));//BLANK
    JMenuItem jMenuItem10 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK
    JMenuItem jMenuItem42 = new JMenuItem(new ImageIconHelper().getImageIcon("/designer/images/Blank16.gif"));// BLANK

    // TEMP!!

    public UIDesigner() {
        this(null);
    }

    public UIDesigner(DesignerControl designerControl) {
        this.designerControl = designerControl;
        reportControl = new ReportDesignerControl(this);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        /*
         * root.add(root1); root.add(root2); root1.add(root3); root3.add(root4);
         */
        // sourceTable = new DiagramSourceTable(root);

        titledBorder1 = new TitledBorder("");
        titledBorder2 = new TitledBorder("");

        titledBorder3 = new TitledBorder("");
        titledBorder4 = new TitledBorder("");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setJMenuBar(jMenuBar1);
        this.setSize(new Dimension(1024, 768));

        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);
        getContentPane().setLayout(borderLayout1);
        setTitle("Deployment Application/Report Designer");
        createFrameButton.setBounds(new Rectangle(8, 61, 87, 26));
        createFrameButton.setText("Create Frame");
        createFrameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createFrameButton_actionPerformed(e);
            }
        });
        jComboBoxFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jComboBoxFrame_itemStateChanged(e);
            }
        });
        jPanel1.setPreferredSize(new Dimension(10, 20));
        jPanel2.setBackground(UIManager.getColor("Button.focus"));
        jPanel2.setVisible(true);
        jPanel2.setOpaque(true);
        jPanel2.setLayout(null);
        jButton2.setBounds(new Rectangle(103, 14, 73, 29));
        jButton2.setVisible(false);
        jButton2.setToolTipText("");
        jButton2.setText("Add Button");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton2_actionPerformed(e);
            }
        });
        propertiesTabbedPane.setBorder(null);
        propertiesTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                propertiesTabbedPane_mousePressed(e);
            }
        });
        jSplitPane1.setBorder(BorderFactory.createLoweredBevelBorder());
        jSplitPane1.setDividerSize(4);
        workPaneTab.setTabPlacement(JTabbedPane.BOTTOM);
        workPaneTab.setBorder(null);
        jPanel3.setLayout(borderLayout2);
        jPanel4.setBorder(BorderFactory.createRaisedBevelBorder());
        jPanel4.setLocale(java.util.Locale.getDefault());
        jPanel4.setVisible(false);
        jPanel4.setMinimumSize(new Dimension(95, 32));
        jPanel4.setPreferredSize(new Dimension(10, 32));
        jPanel4.setBounds(new Rectangle(0, 0, 748, 26));
        jPanel4.setLayout(null);
        jToggleButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton1.setText("Label");
        jToggleButton1.setBounds(new Rectangle(135, 4, 72, 24));
        jToggleButton2.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton2.setFocusPainted(false);
        jToggleButton2.setText("Button");
        jToggleButton2.setBounds(new Rectangle(209, 4, 70, 24));
        jToggleButton3.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton3.setText("Titled Panel");
        jToggleButton3.setBounds(new Rectangle(281, 4, 100, 24));
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButton3_actionPerformed(e);
            }
        });
        jToggleButton4.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton4.setDebugGraphicsOptions(0);
        jToggleButton4.setText("Card Panel");
        jToggleButton4.setBounds(new Rectangle(383, 4, 100, 24));
        jButton4.setBounds(new Rectangle(177, 61, 54, 26));
        jButton4.setText("  Make  ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton4_actionPerformed(e);
            }
        });
        jButton5.setBounds(new Rectangle(231, 61, 45, 26));
        jButton5.setText("  Run  ");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton5_actionPerformed(e);
            }
        });
        jButton1.setBounds(new Rectangle(95, 61, 82, 26));
        jButton1.setText("Generate Code");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setBorder(null);
        jSplitPane3.setDividerSize(4);
        // jSplitPane3.setLastDividerLocation(550);
        jSplitPane3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                jSplitPane3_propertyChange(e);
            }
        });
        jSplitPane3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                jSplitPane3_componentResized(e);
            }
        });
        // for (int i = 0; i < jSplitPane2.getComponentCount(); i++)
        jSplitPane2.setBorder(null);

        jSplitPane2.setDividerSize(4);
        jPanel5.setLayout(borderLayout3);
        jSplitPane4.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane4.setBorder(null);
        jSplitPane4.setDividerSize(4);
        jSplitPane4.setLastDividerLocation(-1);
        jScrollPane1.setBorder(null);
        propertiesScrollPane.setBorder(null);
        tableScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());

        leftBottomBottomPanel.setLocale(java.util.Locale.getDefault());
        leftBottomBottomPanel.setMaximumSize(new Dimension(200, 220));
        leftBottomBottomPanel.setMinimumSize(new Dimension(0, 0));
        jPanel6.setLayout(borderLayout6);
        jPanel6.setLocale(java.util.Locale.getDefault());
        jPanel6.setPreferredSize(new Dimension(165, 20));
        jPanel10.setLayout(borderLayout7);
        jPanel9.setLocale(java.util.Locale.getDefault());
        jPanel9.setPreferredSize(new Dimension(200, 20));
        jPanel9.setLayout(borderLayout5);
        jPanel8.setLayout(borderLayout4);
        jPanel7.setLayout(borderLayout8);
        jPanel7.setMinimumSize(new Dimension(80, 20));
        jPanel7.setPreferredSize(new Dimension(30, 20));
        jPanel8.setPreferredSize(new Dimension(200, 18));
        jPanel10.setPreferredSize(new Dimension(200, 18));
        jLabel1.setPreferredSize(new Dimension(39, 27));
        jLabel1.setText("Associated Dialog:");
        jLabel2.setMinimumSize(new Dimension(73, 17));
        jLabel2.setPreferredSize(new Dimension(180, 20));
        jLabel2.setText("Display Name:");
        jTextField1.setText("");
        jButton7.setSize(new Dimension(30, 10));
        jButton7.setMinimumSize(new Dimension(79, 10));
        jButton7.setPreferredSize(new Dimension(80, 20));
        jButton7.setText("...");
        jPanel11.setLayout(borderLayout9);
        jPanel11.setBorder(null);

        jPanel12.setBorder(BorderFactory.createRaisedBevelBorder());
        jPanel12.setMinimumSize(new Dimension(10, 20));
        jPanel12.setPreferredSize(new Dimension(10, 20));
        jPanel12.setLayout(borderLayout10);
        jLabel3.setAlignmentX((float) 0.0);
        jLabel3.setMaximumSize(new Dimension(83, 17));
        jLabel3.setMinimumSize(new Dimension(83, 15));
        jLabel3.setPreferredSize(new Dimension(83, 15));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setHorizontalTextPosition(SwingConstants.LEADING);
        jLabel3.setText("Created Dialog");
        formListPanel.setLayout(new BorderLayout());
        formListPanel.setVisible(false);
        formListPanel.setBorder(null);
        jMenu1.setText("File");
        jMenu2.setVisible(true);
        jMenu2.setText("Edit");
        jMenu3.setText("Option");
        jToolBar1.setMaximumSize(new Dimension(18, 30));
        jToolBar1.setMinimumSize(new Dimension(372, 30));
        jToolBar1.setPreferredSize(new Dimension(372, 30));

        jToolBar1.setFloatable(false);
        jToolBar1.setLayout(null);
        jPanel13.setBorder(BorderFactory.createRaisedBevelBorder());
        jPanel13.setMinimumSize(new Dimension(14, 20));
        jPanel13.setPreferredSize(new Dimension(14, 20));
        jPanel13.setRequestFocusEnabled(true);
        jPanel13.setLayout(borderLayout11);
        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setText("Designer Component Resources");

        jButton3.setBounds(new Rectangle(394, 61, 39, 26));
        jButton3.setText("Load");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3_actionPerformed(e);
            }
        });
        jButton6.setBounds(new Rectangle(276, 61, 44, 26));
        jButton6.setSelectedIcon(null);
        jButton6.setText("Close");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton6_actionPerformed(e);
            }
        });
        jButton8.setBounds(new Rectangle(320, 61, 36, 26));
        jButton8.setText("New");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton8_actionPerformed(e);
            }
        });
        jButton9.setBounds(new Rectangle(482, 61, 62, 26));
        jButton9.setText("AlignLeft");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton9_actionPerformed(e);
            }
        });
        jButton11.setBounds(new Rectangle(356, 61, 38, 26));
        jButton11.setText("Save");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton11_actionPerformed(e);
            }
        });
        jButton12.setBounds(new Rectangle(544, 61, 71, 26));
        jButton12.setText("Align Right");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton12_actionPerformed(e);
            }
        });
        jButton13.setBounds(new Rectangle(615, 61, 64, 26));
        jButton13.setText("Align Top");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton13_actionPerformed(e);
            }
        });
        jButton14.setBounds(new Rectangle(681, 62, 80, 26));
        jButton14.setText("AlignBottom");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton14_actionPerformed(e);
            }
        });
        jButton15.setBounds(new Rectangle(761, 62, 75, 26));
        jButton15.setText("AutoLayout");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton15_actionPerformed(e);
            }
        });
        jButton16.setBounds(new Rectangle(836, 62, 40, 26));
        jButton16.setText("Undo");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton16_actionPerformed(e);
            }
        });
        jButton17.setBounds(new Rectangle(876, 62, 40, 26));
        jButton17.setText("Redo");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton17_actionPerformed(e);
            }
        });
        jButton10.setBounds(new Rectangle(433, 61, 49, 26));
        jButton10.setText("Import");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton10_actionPerformed(e);
            }
        });
        jList1.setBounds(new Rectangle(935, 14, 0, 0));
        jButton18.setBounds(new Rectangle(8, 4, 24, 24));
        jButton18.setText("");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton18_actionPerformed(e);
            }
        });
        jButton19.setBounds(new Rectangle(34, 4, 24, 24));
        jButton19.setText("");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton19_actionPerformed(e);
            }
        });
        jButton20.setBounds(new Rectangle(60, 4, 24, 24));
        jButton20.setText("");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton20_actionPerformed(e);
            }
        });
        jButton21.setBounds(new Rectangle(86, 4, 24, 24));
        jButton21.setText("");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton21_actionPerformed(e);
            }
        });
        jButton22.setBounds(new Rectangle(120, 4, 24, 24));
        jButton22.setEnabled(false);
        jButton22.setText("");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton16_actionPerformed(e);
            }
        });
        jButton23.setBounds(new Rectangle(146, 4, 24, 24));
        jButton23.setEnabled(false);
        jButton23.setText("");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton17_actionPerformed(e);
            }
        });
        jButton24.setBounds(new Rectangle(180, 4, 24, 24));
        jButton24.setSelected(false);
        jButton24.setText("");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton9_actionPerformed(e);
            }
        });

        jButton25.setBounds(new Rectangle(206, 4, 24, 24));
        jButton25.setSelected(false);
        jButton25.setText("");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton25_actionPerformed(e);
            }
        });
        jButton26.setBounds(new Rectangle(232, 4, 24, 24));
        jButton26.setSelected(false);
        jButton26.setText("");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton13_actionPerformed(e);
            }
        });
        jButton27.setBounds(new Rectangle(258, 4, 24, 24));
        jButton27.setSelected(false);
        jButton27.setText("");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton14_actionPerformed(e);
            }
        });
        jButton28.setBounds(new Rectangle(284, 4, 24, 24));
        jButton28.setSelected(false);
        jButton28.setText("");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton15_actionPerformed(e);
            }
        });
        jButton29.setBounds(new Rectangle(318, 4, 24, 24));
        jButton29.setEnabled(false);
        jButton29.setText("");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton29_actionPerformed(e);
            }
        });
        jButton30.setBounds(new Rectangle(378, 4, 24, 24));
        jButton30.setText("");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton30_actionPerformed(e);
            }
        });
        jButton31.setBounds(new Rectangle(352, 4, 24, 24));
        jButton31.setEnabled(false);
        jButton31.setText("");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton31_actionPerformed(e);
            }
        });
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel5.setText("GO TO:");
        jLabel5.setBounds(new Rectangle(412, 4, 50, 24));
        jComboBoxFrame.setMaximumSize(new Dimension(32767, 32767));
        jComboBoxFrame.setBounds(new Rectangle(460, 4, 220, 24));
        jButton32.setBounds(new Rectangle(684, 4, 24, 24));
        jButton32.setText("");

        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton32_actionPerformed(e);
            }
        });
        tooBarPanel.setBounds(new Rectangle(0, 0, 1024, 62));
        tooBarPanel.setPreferredSize(new Dimension(200, 30));
        tooBarPanel.setVisible(true);
        tooBarPanel.setLayout(new BorderLayout());

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel6.setText("Add Component:");
        jLabel6.setBounds(new Rectangle(36, 4, 102, 24));
        jButton37.setBounds(new Rectangle(6, 4, 24, 24));
        jMenuItem1.setText("New...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem1_actionPerformed(e);
            }
        });
        jMenuItem8.setText("Open...");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem8_actionPerformed(e);
            }
        });
        jMenuItem7.setText("Close");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem7_actionPerformed(e);
            }
        });
        jMenuItem6.setText("Save");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem6_actionPerformed(e);
            }
        });
        jMenuItem5.setText("Save as...");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem5_actionPerformed(e);
            }
        });
        jMenuItem11.setVisible(true);
        jMenuItem11.setText("Import deployment...");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem11_actionPerformed(e);
            }
        });
        jMenuItem9.setText("Exit");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem9_actionPerformed(e);
            }
        });
        jMenuItem17.setVisible(false);
        jMenuItem17.setText("Select model...");
        jMenuItem16.setText("Undo");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem16_actionPerformed(e);
            }
        });
        jMenuItem15.setText("Redo");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem15_actionPerformed(e);
            }
        });
        jMenu4.setVisible(false);
        jMenu4.setText("Add Component");
        jMenuItem14.setText("Label");
        jMenuItem18.setText("Button");
        jMenuItem19.setText("Titled Panel");
        jMenuItem20.setText("Card Panel");
        jMenuItem13.setText("Delete Selected...");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem13_actionPerformed(e);
            }
        });
        jMenuItem12.setText("Selected All");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem12_actionPerformed(e);
            }
        });
        jMenuItem4.setText("Deselect...");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem4_actionPerformed(e);
            }
        });
        jMenu6.setText("Frame");
        jMenuItem2.setText("Auto Layout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem2_actionPerformed(e);
            }
        });
        jMenuItem21.setText("Align to Left");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem21_actionPerformed(e);
            }
        });
        jMenuItem22.setText("Align to Right");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem22_actionPerformed(e);
            }
        });
        jMenuItem23.setText("Align to Top");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem23_actionPerformed(e);
            }
        });
        jMenuItem24.setText("Align to Bottom");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem24_actionPerformed(e);
            }
        });
        jMenu5.setText("Compile");
        jMenuItem25.setText("Generate Code for Simultation model");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem25_actionPerformed(e);
            }
        });
        jMenuItem26.setText("View Code...");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem26_actionPerformed(e);
            }
        });
        jMenuItem27.setText("Generate Deplouyment UI");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem27_actionPerformed(e);
            }
        });
        jMenuItem28.setText("Run Deployment");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem28_actionPerformed(e);
            }
        });
        jMenuItem29.setEnabled(false);
        jMenuItem29.setText("Stop Running...");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem29_actionPerformed(e);
            }
        });
        jMenuItem30.setText("Edit Grid");
        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Snap to Grid");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCheckBoxMenuItem1_actionPerformed(e);
            }
        });
        jMenu7.setText("Help");
        jMenuItem31.setVisible(false);
        jMenuItem31.setText("New Frame");
        jMenuItem3.setText("Delete Frame");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem3_actionPerformed(e);
            }
        });
        jMenuItem32.setEnabled(false);
        jMenuItem32.setText("Generate Frame for Internal");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem32_actionPerformed(e);
            }
        });
        jMenu8.setText("Window");
        jMenuItem33.setText("Close All");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem33_actionPerformed(e);
            }
        });
        jMenuItem34.setText("Delete Report Sheet");
        jMenuItem35.setText("Create New Report Sheet");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem35_actionPerformed(e);
            }
        });
        jPanel5.setVisible(true);
        jPanel5.setMinimumSize(new Dimension(38, 0));
        jEditorPane1.setBorder(null);
        jEditorPane1.setText("Notes:");
        jMenu9.setText("Report");
        jMenu10.setText("Header");
        jMenu10.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(MenuEvent e) {
                jMenu10_menuSelected(e);
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });
        jMenu11.setText("Footer");
        jMenu11.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(MenuEvent e) {
                jMenu11_menuSelected(e);
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });
        jMenuItem36.setText("Page Number & Date");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem36_actionPerformed(e);
            }
        });
        jMenuItem37.setText("Edit Header");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem37_actionPerformed(e);
            }
        });
        jMenuItem38.setText("Remove Header");
        jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem38_actionPerformed(e);
            }
        });
        jCheckBoxMenuItem2.setText("Header Line");
        jCheckBoxMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCheckBoxMenuItem2_actionPerformed(e);
            }
        });
        jMenuItem39.setText("Edit Footer");
        jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem39_actionPerformed(e);
            }
        });
        jMenuItem40.setText("Remove Footer");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem40_actionPerformed(e);
            }
        });
        jCheckBoxMenuItem3.setText("Footer Line");
        jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jCheckBoxMenuItem3_actionPerformed(e);
            }
        });
        jMenuItem41.setText("New Page");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem41_actionPerformed(e);
            }
        });
        jMenuItem10.setText("Deploy program....");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem10_actionPerformed(e);
            }
        });
        jMenuItem42.setText("Export deployment...");
        jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jMenuItem42_actionPerformed(e);
            }
        });
        getContentPane().add(tooBarPanel, BorderLayout.NORTH);

        tooBarPanel.add(jToolBar1, BorderLayout.NORTH);

        getContentPane().add(jPanel1, BorderLayout.SOUTH);
        getContentPane().add(jSplitPane1, BorderLayout.CENTER);
        jToolBar1.add(jButton2, null);
        jToolBar1.add(jList1, null);
        jToolBar1.add(jButton18, null);
        jToolBar1.add(jButton19, null);
        jToolBar1.add(jButton20, null);
        jToolBar1.add(jButton21, null);
        jToolBar1.add(jButton22, null);
        jToolBar1.add(jButton23, null);
        jToolBar1.add(jButton24, null);
        jToolBar1.add(jButton25, null);
        jToolBar1.add(jButton26, null);
        jToolBar1.add(jButton27, null);
        jToolBar1.add(jButton28, null);
        jToolBar1.add(jButton29, null);
        jToolBar1.add(jButton31, null);
        jToolBar1.add(jButton30, null);
        jToolBar1.add(createFrameButton, null);
        jToolBar1.add(jButton1, null);
        jToolBar1.add(jButton4, null);
        jToolBar1.add(jButton5, null);
        jToolBar1.add(jButton6, null);
        jToolBar1.add(jButton8, null);
        jToolBar1.add(jButton11, null);
        jToolBar1.add(jButton3, null);
        jToolBar1.add(jButton10, null);
        jToolBar1.add(jButton9, null);
        jToolBar1.add(jButton12, null);
        jToolBar1.add(jButton13, null);
        jToolBar1.add(jButton14, null);
        jToolBar1.add(jButton15, null);
        jToolBar1.add(jButton16, null);
        jToolBar1.add(jButton17, null);
        jToolBar1.add(jLabel5, null);
        jToolBar1.add(jComboBoxFrame, null);
        jToolBar1.add(jButton32, null);

        jSplitPane1.setDividerLocation(270);

        jSplitPane2.setDividerLocation(340);
        jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);

        /*
         * jSplitPane1.setBackground(Color.red);
         * jSplitPane2.setBackground(Color.red);
         * jSplitPane3.setBackground(Color.red);
         * jSplitPane4.setBackground(Color.red);
         * this.getContentPane().setBackground(Color.red);
         */
        formListPanel.add(jPanel11, BorderLayout.NORTH);
        // formListPanel.add(propertiesScrollPane, BorderLayout.CENTER);

        jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
        jSplitPane4.add(formListPanel, JSplitPane.RIGHT);
        propertiesScrollPane.getViewport().add(jList2, null);
        // propertiesScrollPane.add(jList2, BorderLayout.CENTER);
        jPanel11.add(jPanel12, BorderLayout.CENTER);
        jPanel12.add(jLabel3, BorderLayout.SOUTH);
        jSplitPane1.add(jPanel3, JSplitPane.RIGHT);
        // TEMP//!!
        Vector v1 = new Vector();
        Object[] s1 = { "Property 1", null }; // new
                                              // FrameIDandName("0","Untitled Frame 1")};
        v1.add(s1);
        Object[] s2 = { "Property 2", new FrameIDandName("1", "Untitled Frame 2") };
        v1.add(s2);
        Object[] s3 = { "Property 3", new FrameIDandName("2", "Untitled Frame 3") };
        v1.add(s3);
        // TEMP//!!
        // propertiseTable.setModel(new TableAdapter(v1));
        propertiseTable.setRowHeight(18);
        propertiseTable.setRowMargin(0);

        /*
         * sourceTable.setModel(new TableAdapter(new Vector())); Box
         * boxPermissionList = Box.createVerticalBox(); for (int i = 0; i < 10;
         * i++) { boxPermissionList.add(new JCheckBox("Item " + i)); }
         * JScrollPane paneScrollPermission = new
         * JScrollPane(boxPermissionList); jList2.setCellRenderer(new
         * CustomCellRenderer()); jList2.getContents().addElement("AAA");
         * jList2.getContents().addElement("BBB");
         * jList2.getContents().addElement("CCC");
         */

        // TEMP//!!
        jTopPane.setVisible(true);
        jTopPane.setBorder(null);
        jTopPane.setLayout(new BorderLayout());
        jSplitPane2.add(jTopPane, JSplitPane.TOP);
        jTopPane.add(jPanel13, BorderLayout.NORTH);
        jPanel13.add(jLabel4, BorderLayout.CENTER);
        propertiseTable.setBorder(null);
        jPanel3.add(jPanel4, BorderLayout.NORTH);
        jPanel4.add(jButton37, null);
        jPanel4.add(jLabel6, null);
        jPanel4.add(jToggleButton1, null);
        jPanel4.add(jToggleButton2, null);
        jPanel4.add(jToggleButton3, null);
        jPanel4.add(jToggleButton4, null);
        jPanel3.add(jSplitPane3, BorderLayout.CENTER);
        jSplitPane3.add(workPaneTab, JSplitPane.TOP);
        jSplitPane3.add(jPanel5, JSplitPane.BOTTOM);
        jPanel5.add(jSplitPane4, BorderLayout.CENTER);
        jSplitPane4.add(jScrollPane1, JSplitPane.TOP);
        jScrollPane1.getViewport().add(jEditorPane1, null);

        jTabbedPane2.setVisible(true);
        jTabbedPane2.setBorder(null);
        jTopPane.add(jTabbedPane2, BorderLayout.SOUTH);

        leftBottom1Panel.setLayout(new BorderLayout());
        leftBottom1Panel.setVisible(true);

        // jSplitPane2.add(leftBottom1Panel, JSplitPane.BOTTOM);
        jSplitPane2.add(propertiesTabbedPane, JSplitPane.BOTTOM);
        propertiesTabbedPane.add(leftBottom1Panel, "Pane Component");
        leftBottom1Panel.add(tableScrollPane, BorderLayout.CENTER);

        leftBottomBottomPanel.setLayout(flowLayout1);
        leftBottomBottomPanel.setVisible(false);
        leftBottom1Panel.add(leftBottomBottomPanel, BorderLayout.SOUTH);
        leftBottomBottomPanel.add(jPanel8, null);
        jPanel8.add(jLabel2, BorderLayout.WEST);
        leftBottomBottomPanel.add(jPanel9, null);
        jPanel9.add(jTextField1, BorderLayout.CENTER);
        leftBottomBottomPanel.add(jPanel10, null);
        jPanel10.add(jLabel1, BorderLayout.CENTER);
        leftBottomBottomPanel.add(jPanel6, null);

        jPanel6.add(jComboBox1, BorderLayout.CENTER);
        leftBottomBottomPanel.add(jPanel7, null);
        jPanel7.add(jButton7, BorderLayout.SOUTH);
        propertiesTabbedPane.add(propertyTablePane, "Properties");

        leftBottomBottomPanel.setBorder(null);
        leftBottomBottomPanel.setPreferredSize(new Dimension(100, 110));

        tableScrollPane.getViewport().add(framComponentList, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenuBar1.add(jMenu6);
        jMenuBar1.add(jMenu9);
        jMenuBar1.add(jMenu5);
        jMenuBar1.add(jMenu3);
        jMenuBar1.add(jMenu8);
        jMenuBar1.add(jMenu7);

        jSplitPane3.setDividerLocation(3000);
        jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
        jPanel2.setBorder(null);
        jPanel3.setBorder(null);
        jPanel5.setBorder(null);
        /*
         * jList2.getContents().addElement("AAA");
         * jList2.getContents().addElement("BBB");
         * jList2.getContents().addElement("CCC");
         * jList2.getContents().addElement("DDD");
         * jList2.getContents().addElement("EEE");
         */

        mainWindow = this;
        if (savedSplitHeight > 0) {
            savedSplitHeight = 180;
        }
        repaint();
        jSplitPane4.setDividerLocation(400);

        leftBottom1Panel.setBackground(Color.white);
        leftBottom1Panel.setBorder(BorderFactory.createLoweredBevelBorder());
        tableScrollPane.setVisible(false);

        propertyTablePane.setLayout(new BorderLayout());
        propertyTablePane.add(propertiesScrollPane2, BorderLayout.CENTER);
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem8);
        jMenu1.add(jMenuItem7);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem6);
        jMenu1.add(jMenuItem5);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem17);
        jMenu1.add(jMenuItem11);
        jMenu1.add(jMenuItem42);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem10);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem9);
        jMenu2.add(jMenuItem16);
        jMenu2.add(jMenuItem15);
        jMenu2.addSeparator();
        jMenu2.add(jMenuItem32);
        jMenu2.add(jMenuItem31);
        jMenu2.add(jMenuItem3);
        jMenu2.addSeparator();
        jMenu2.add(jMenuItem35);
        jMenu2.add(jMenuItem34);
        jMenu2.addSeparator();
        jMenu2.add(jMenu4);
        jMenu2.add(jMenuItem13);
        jMenu2.addSeparator();
        jMenu2.add(jMenuItem12);
        jMenu2.add(jMenuItem4);
        jMenu4.add(jMenuItem14);
        jMenu4.add(jMenuItem18);
        jMenu4.add(jMenuItem19);
        jMenu4.add(jMenuItem20);
        jMenu6.add(jMenuItem2);
        jMenu6.addSeparator();
        jMenu6.add(jMenuItem21);
        jMenu6.add(jMenuItem22);
        jMenu6.add(jMenuItem23);
        jMenu6.add(jMenuItem24);
        jMenu5.add(jMenuItem25);
        jMenu5.add(jMenuItem26);
        jMenu5.addSeparator();
        jMenu5.add(jMenuItem27);
        jMenu5.add(jMenuItem28);
        jMenu5.add(jMenuItem29);
        jMenu3.add(jMenuItem30);
        jMenu3.add(jCheckBoxMenuItem1);
        jMenu8.add(jMenuItem33);
        jMenu9.add(jMenuItem41);
        jMenu9.add(jMenu10);
        jMenu9.add(jMenu11);
        jMenu9.addSeparator();
        jMenu9.add(jMenuItem36);
        jMenu10.add(jMenuItem37);
        jMenu10.add(jMenuItem38);
        jMenu10.add(jCheckBoxMenuItem2);
        jMenu11.add(jMenuItem39);
        jMenu11.add(jMenuItem40);
        jMenu11.add(jCheckBoxMenuItem3);
        propertiesScrollPane.setVisible(true);
        // propertyTablePane.add(sourceTable, BorderLayout.CENTER);
        propertyTablePane.setVisible(true);
        propertyTablePane.setBorder(null);

        /*
         * if (table == null) { tableScrollPane.setVisible(false);
         * leftBottomBottomPanel.setVisible(false); return; }
         * leftBottomBottomPanel.setVisible(true);
         * 
         * int savedSize = jSplitPane2.getDividerLocation();
         * 
         * leftBottom1Panel.remove(tableScrollPane);
         * 
         * tableScrollPane = new JScrollPane(sourceTable);
         * 
         * //jSplitPane2.add(tableScrollPane, JSplitPane.BOTTOM);
         * leftBottom1Panel.add(tableScrollPane, BorderLayout.CENTER);
         * 
         * tableScrollPane.setBorder(null);
         * 
         * sourceTable.setBorder(null);
         * 
         * tableScrollPane.getComponent(0).setBackground(Color.white);
         * tableScrollPane.setBorder(null);
         * 
         * jSplitPane2.setDividerLocation(savedSize);
         * //System.out.println(leftBottom1Panel.getPreferredSize().height -
         * 100); //System.out.println(sourceTable.getPreferredSize().height);
         */
        // leftBottomBottomPanel.setVisible(false);

        // createFrameButton.setEnabled(false);
        setEditButtonEnable(false);
        setDeleteButtonEnable(false);
        setDiagramSourceTable(sourceTable);
        repaint();
    }

    public void paint(Graphics g) {
        // this.jSplitPane3.setDividerLocation(jSplitPane3.getHeight() -
        // savedSplitHeight);
        super.paint(g);
        // sourceList.repaint();
    }

    public DiagramSourceList getList() {
        return sourceList;
    }

    public static DesignerControl getControl() {
        return designerControl;
    }

    public void setAnlaysisList(DiagramSourceAnalysisList list) {
        analysisList = list;

        list.expandPath(list.getPathForRow(0));
        list.setRootVisible(true);

        JScrollPane listScrollPane = new JScrollPane(list);

        jTabbedPane2.add(listScrollPane, "Analysis component");
        listScrollPane.setVisible(true);
        listScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        list.setBorder(null);
        repaint();
    }

    public void setDiagramSourceList(DiagramSourceList list) {
        sourceList = list;

        jTopPane.remove(jTabbedPane2);

        jTabbedPane2 = new JTabbedPane();

        sourceList.expandPath(sourceList.getPathForRow(0));

        sourceList.setRootVisible(true);

        listScrollPane = new JScrollPane(sourceList);

        jTopPane.add(jTabbedPane2, BorderLayout.CENTER);

        jTabbedPane2.add(listScrollPane, "Model Component");
        jTabbedPane2.setVisible(true);
        listScrollPane.setVisible(true);
        // jSplitPane2.add(listScrollPane, JSplitPane.TOP);
        listScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        sourceList.setBorder(null);

        repaint();
    }

    public void addFrame(DesignPane designPane) {
        jComboBoxFrame.addItem(designPane);
    }

    public void addReport(PrintEditor printEditor) {
        jComboBoxFrame.addItem(printEditor);
    }

    public void removeFrame(DesignPane designPane) {
        jComboBoxFrame.removeItem(designPane);
    }

    public JComboBox getFrameList() {
        return jComboBoxFrame;
    }

    public void setFrameComponentList(FrameComponentList list) {
        framComponentList = list;
        if (list == null) {
            tableScrollPane.setVisible(false);
            leftBottomBottomPanel.setVisible(false);
            return;
        }
        leftBottomBottomPanel.setVisible(false);
        int savedSize = jSplitPane2.getDividerLocation();
        leftBottom1Panel.remove(tableScrollPane);
        tableScrollPane = new JScrollPane(framComponentList);
        leftBottom1Panel.add(tableScrollPane, BorderLayout.CENTER);
        tableScrollPane.setBorder(null);
        framComponentList.setBorder(null);
        tableScrollPane.getComponent(0).setBackground(Color.white);
        tableScrollPane.setBorder(null);
        jSplitPane2.setDividerLocation(savedSize);
        repaint();
    }

    public void repaintTable() {
        if (propertiesTabbedPane.getSelectedIndex() == 1) {
            repaint();
        } else {
            framComponentList.repaint();
        }
    }

    public void setDiagramSourceTable(DiagramSourceTable table) {
        sourceTable = table;
        if (propertiesTabbedPane.getSelectedIndex() == 0) {
            return;
        }

        propertyTablePane.remove(propertiesScrollPane2);
        propertiesScrollPane2 = new JScrollPane(table);

        propertyTablePane.add(propertiesScrollPane2, BorderLayout.CENTER);
        // table.update(propertiesScrollPane2.getGraphics());

        if (propertiesTabbedPane.getSelectedIndex() == 1) {
            repaint();
        } else {
            framComponentList.repaint();
        }

    }

    /*
     * public void setDiagramSourceTable(DiagramSourceTable table) {
     * this.sourceTable = table; if (table == null) {
     * tableScrollPane.setVisible(false);
     * leftBottomBottomPanel.setVisible(false); return; }
     * leftBottomBottomPanel.setVisible(true);
     * 
     * int savedSize = jSplitPane2.getDividerLocation();
     * 
     * leftBottom1Panel.remove(tableScrollPane);
     * 
     * tableScrollPane = new JScrollPane(sourceTable);
     * 
     * //jSplitPane2.add(tableScrollPane, JSplitPane.BOTTOM);
     * leftBottom1Panel.add(tableScrollPane, BorderLayout.CENTER);
     * 
     * tableScrollPane.setBorder(null);
     * 
     * sourceTable.setBorder(null);
     * 
     * tableScrollPane.getComponent(0).setBackground(Color.white);
     * tableScrollPane.setBorder(null);
     * 
     * jSplitPane2.setDividerLocation(savedSize);
     * //System.out.println(leftBottom1Panel.getPreferredSize().height - 100);
     * //System.out.println(sourceTable.getPreferredSize().height); }
     */

    public void setDesignerControl(DesignerControl designerControl) {
        this.designerControl = designerControl;
    }

    void jButton2_actionPerformed(ActionEvent e) {
        designerControl.newButton();
    }

    void jButton4_actionPerformed(ActionEvent e) {
        designerControl.generateUI();
    }

    void jButton5_actionPerformed(ActionEvent e) {
        designerControl.testRun();
    }

    void jButton1_actionPerformed(ActionEvent e) {
        designerControl.generateCode();
    }

    void createFrameButton_actionPerformed(ActionEvent e) {
        designerControl.newPane();
    }

    void jSplitPane3_componentResized(ComponentEvent e) {
    }

    void jSplitPane3_propertyChange(PropertyChangeEvent e) {
        savedSplitHeight = jSplitPane3.getHeight() - jSplitPane3.getDividerLocation();
        // System.out.println(savedSplitWidth);
        if (savedSplitHeight < 0) {
            savedSplitHeight = 180;
        }
        repaint();
    }

    void jToggleButton3_actionPerformed(ActionEvent e) {

    }

    void propertiesTabbedPane_mousePressed(MouseEvent e) {
        if (propertiesTabbedPane.getSelectedIndex() == 1) {
            setDiagramSourceTable(sourceTable);
        }
    }

    void jButton3_actionPerformed(ActionEvent e) {
        designerControl.closeCurrentWorkSpace(reportControl);
        designerControl.loadObject(reportControl);
    }

    void jButton6_actionPerformed(ActionEvent e) {
        designerControl.closeCurrentWorkSpace(reportControl);
    }

    void jButton8_actionPerformed(ActionEvent e) {
        designerControl.closeCurrentWorkSpace(reportControl);
        designerControl.newWorkSpace();
    }

    void jButton11_actionPerformed(ActionEvent e) {
        designerControl.saveObject();
    }

    void jButton9_actionPerformed(ActionEvent e) {
        designerControl.alignLeft();

    }

    void jButton12_actionPerformed(ActionEvent e) {
        designerControl.alignRight();

    }

    void jButton13_actionPerformed(ActionEvent e) {
        designerControl.alignTop();

    }

    void jButton14_actionPerformed(ActionEvent e) {
        designerControl.alignBottom();

    }

    void jButton15_actionPerformed(ActionEvent e) {
        designerControl.autoLayout();
    }

    void jButton16_actionPerformed(ActionEvent e) {
        designerControl.undo();
    }

    void jButton17_actionPerformed(ActionEvent e) {
        designerControl.redo();

    }

    void jButton10_actionPerformed(ActionEvent e) {
        designerControl.importObject(reportControl);
    }

    void jComboBoxFrame_itemStateChanged(ActionEvent e) {
        Object object = ((JComboBox) e.getSource()).getSelectedItem();
        if (object instanceof DesignPane) {
            DesignPane designPane = (DesignPane) object;// ( (JComboBox)
                                                        // e.getSource()).getSelectedItem();
            designerControl.showFrame(designPane);
        } else {
            PrintEditor printEditor = (PrintEditor) object;
            reportControl.showReport(printEditor);
        }

        /*
         * try { Thread.sleep(1000); }catch ( Exception ex ) {}
         * System.out.println("SLEET DO NOW");
         */
        designerControl.refreshFrameList();

    }

    void jButton32_actionPerformed(ActionEvent e) {
        JComboBox comboBox = jComboBoxFrame;
        int index = comboBox.getSelectedIndex();
        Object object = comboBox.getSelectedItem();
        if (object instanceof DesignPane) {
            DesignPane designPane = (DesignPane) object;
            designerControl.deletePane(designPane);
        } else {
            PrintEditor printEditor = (PrintEditor) object;
            reportControl.deleteReport(printEditor);
        }
    }

    void jButton29_actionPerformed(ActionEvent e) {
        designerControl.currentDesignPane.removeDesignComponent();

    }

    void jButton31_actionPerformed(ActionEvent e) {
        designerControl.stopRun();
    }

    void jButton30_actionPerformed(ActionEvent e) {
        designerControl.testRun();
    }

    public void setEditButtonEnable(boolean b) {
        jButton24.setEnabled(b);
        jButton25.setEnabled(b);
        jButton26.setEnabled(b);
        jButton27.setEnabled(b);
        jButton28.setEnabled(b);

        jMenuItem2.setEnabled(b);
        jMenuItem21.setEnabled(b);
        jMenuItem22.setEnabled(b);
        jMenuItem23.setEnabled(b);
        jMenuItem24.setEnabled(b);

        jMenuItem32.setEnabled(b);
    }

    public void setGenerateMenuEnable(boolean b) {
        jMenuItem32.setEnabled(b);
    }

    public void setDeleteButtonEnable(boolean b) {
        jButton29.setEnabled(b);
        jMenuItem13.setEnabled(b);
        jMenuItem4.setEnabled(b);
    }

    public void setUndoButtonEnable(boolean b) {
        jButton22.setEnabled(b);
        jMenuItem16.setEnabled(b);
    }

    public void setRedoButtonEnable(boolean b) {
        jButton23.setEnabled(b);
        jMenuItem15.setEnabled(b);
    }

    /*
     * public void setStopButtonEnable( boolean b ) { jButton31.setEnabled(b); }
     */
    public void setStartButtonEnable(boolean b) {
        jButton30.setEnabled(b);
        jButton31.setEnabled(!b);

        jMenuItem28.setEnabled(b);
        jMenuItem29.setEnabled(!b);
    }

    void jButton25_actionPerformed(ActionEvent e) {
        designerControl.alignRight();
    }

    void jMenuItem16_actionPerformed(ActionEvent e) {
        designerControl.undo();
    }

    void jMenuItem15_actionPerformed(ActionEvent e) {
        designerControl.redo();
    }

    void jMenuItem12_actionPerformed(ActionEvent e) {
        designerControl.selectAll();
    }

    void jMenuItem4_actionPerformed(ActionEvent e) {
        designerControl.deslectAll();
    }

    void jMenuItem13_actionPerformed(ActionEvent e) {
        designerControl.currentDesignPane.removeDesignComponent();
    }

    void jMenuItem2_actionPerformed(ActionEvent e) {
        designerControl.autoLayout();
    }

    void jMenuItem21_actionPerformed(ActionEvent e) {
        designerControl.alignLeft();
    }

    void jMenuItem22_actionPerformed(ActionEvent e) {
        designerControl.alignRight();
    }

    void jMenuItem23_actionPerformed(ActionEvent e) {
        designerControl.alignTop();
    }

    void jMenuItem24_actionPerformed(ActionEvent e) {
        designerControl.alignBottom();
    }

    void jMenuItem28_actionPerformed(ActionEvent e) {
        designerControl.testRun();
    }

    void jMenuItem29_actionPerformed(ActionEvent e) {
        designerControl.stopRun();
    }

    void jMenuItem25_actionPerformed(ActionEvent e) {
        designerControl.generateCode();
    }

    void jMenuItem27_actionPerformed(ActionEvent e) {
        designerControl.generateUI();
    }

    void jCheckBoxMenuItem1_actionPerformed(ActionEvent e) {
        if (jCheckBoxMenuItem1.isSelected()) {
            designerControl.gridSize = 4;
        } else {
            designerControl.gridSize = 1;
        }
    }

    void jMenuItem1_actionPerformed(ActionEvent e) {
        designerControl.closeCurrentWorkSpace(reportControl);
        designerControl.newWorkSpace();
    }

    void jMenuItem7_actionPerformed(ActionEvent e) {
        designerControl.closeCurrentWorkSpace(reportControl);
    }

    void jMenuItem6_actionPerformed(ActionEvent e) {
        designerControl.saveObject();
    }

    void jMenuItem5_actionPerformed(ActionEvent e) {
        designerControl.saveAsObject();
    }

    void jMenuItem11_actionPerformed(ActionEvent e) {
        designerControl.importObject(reportControl);
    }

    void jMenuItem10_actionPerformed(ActionEvent e) {
        designerControl.exportObject();
    }

    void jMenuItem9_actionPerformed(ActionEvent e) {
        setVisible(false);

    }

    void jMenuItem3_actionPerformed(ActionEvent e) {
        designerControl.deletePane(designerControl.currentDesignPane);
    }

    void jMenuItem8_actionPerformed(ActionEvent e) {
        designerControl.loadObject(reportControl);
    }

    void jMenuItem32_actionPerformed(ActionEvent e) {
        if (designerControl.currentDesignPane.selectedCovers.size() == 1) {
            new GenerateInternalFrameControl(((CoverComponent) designerControl.currentDesignPane.selectedCovers.elementAt(0)).getCoverControl()).generate();
        }
    }

    void jMenuItem33_actionPerformed(ActionEvent e) {
        workPaneTab.removeAll();
    }

    void jMenuItem35_actionPerformed(ActionEvent e) {
        reportControl.newReport();
    }

    public void setReportEnable() {
        jMenu10.setEnabled(true);
        jMenu11.setEnabled(true);
        jMenuItem36.setEnabled(true);
        jMenuItem41.setEnabled(true);
    }

    public void setFrameEnable() {
        jMenu10.setEnabled(false);
        jMenu11.setEnabled(false);
        jMenuItem36.setEnabled(false);
        jMenuItem41.setEnabled(false);
    }

    void jMenuItem37_actionPerformed(ActionEvent e) {
        reportControl.getControl().editHeader();
    }

    void jMenuItem38_actionPerformed(ActionEvent e) {
        reportControl.getControl().removeHeader();
    }

    void jCheckBoxMenuItem2_actionPerformed(ActionEvent e) {
        reportControl.getControl().addHeaderLine(jCheckBoxMenuItem2.isSelected());
    }

    void jMenuItem39_actionPerformed(ActionEvent e) {
        reportControl.getControl().editFooter();
    }

    void jMenuItem40_actionPerformed(ActionEvent e) {
        reportControl.getControl().removeFooter();
    }

    void jCheckBoxMenuItem3_actionPerformed(ActionEvent e) {
        reportControl.getControl().addFooterLine(jCheckBoxMenuItem3.isSelected());
    }

    void jMenuItem36_actionPerformed(ActionEvent e) {
        reportControl.setFormatting();

    }

    void jMenu10_menuSelected(MenuEvent e) {
        jCheckBoxMenuItem2.setSelected(reportControl.getControl().getHeaderLine());
        jCheckBoxMenuItem2.setEnabled(reportControl.getControl().getHaveHeader());
        jMenuItem38.setEnabled(reportControl.getControl().getHaveHeader());
        jMenuItem37.setText(reportControl.getControl().getHaveHeader() ? "Edit Header" : "Add Header");

    }

    void jMenu11_menuSelected(MenuEvent e) {

        jCheckBoxMenuItem3.setSelected(reportControl.getControl().getFooterLine());
        jCheckBoxMenuItem3.setEnabled(reportControl.getControl().getHaveFooter());
        jMenuItem40.setEnabled(reportControl.getControl().getHaveFooter());
        jMenuItem39.setText(reportControl.getControl().getHaveFooter() ? "Edit Footer" : "Add Footer");

    }

    void jMenuItem41_actionPerformed(ActionEvent e) {
        reportControl.newPage();
    }

    void jButton18_actionPerformed(ActionEvent e) {
        designerControl.newWorkSpace();

    }

    void jButton19_actionPerformed(ActionEvent e) {
        designerControl.loadObject(reportControl);

    }

    void jButton20_actionPerformed(ActionEvent e) {
        designerControl.saveObject();

    }

    void jButton21_actionPerformed(ActionEvent e) {
        designerControl.saveAsObject();
    }

    void jMenuItem42_actionPerformed(ActionEvent e) {
        designerControl.exportObject();
    }

    void jMenuItem26_actionPerformed(ActionEvent e) {
        designerControl.viewCode();
    }

    /*
     * void jCheckBoxMenuItem3_actionPerformed(ActionEvent e) {
     * controlMainEditor.addHeaderLine(jCheckBoxMenuItem3.isSelected()); }
     * 
     * void jCheckBoxMenuItem4_actionPerformed(ActionEvent e) {
     * controlMainEditor.addFooterLine(jCheckBoxMenuItem4.isSelected()); }
     * 
     * void jMenuItem25_actionPerformed(ActionEvent e) {
     * controlMainEditor.editFooter(); }
     * 
     * void jMenuItem24_actionPerformed(ActionEvent e) {
     * controlMainEditor.editHeader(); }
     * 
     * void jMenuItem26_actionPerformed(ActionEvent e) {
     * controlMainEditor.removeHeader(); }
     * 
     * void jMenuItem27_actionPerformed(ActionEvent e) {
     * controlMainEditor.removeFooter(); }
     * 
     * 
     * /* jCheckBoxMenuItem3.setSelected(controlMainEditor.getHeaderLine());
     * jCheckBoxMenuItem3.setEnabled(controlMainEditor.getHaveHeader());
     * jMenuItem26.setEnabled(controlMainEditor.getHaveHeader());
     * jMenuItem24.setText
     * ((controlMainEditor.getHaveHeader()?"Edit Header":"Add Header")); }
     * 
     * void jMenu6_menuSelected(MenuEvent e) {
     * jCheckBoxMenuItem4.setSelected(controlMainEditor.getFooterLine());
     * jCheckBoxMenuItem4.setEnabled(controlMainEditor.getHaveFooter());
     * jMenuItem27.setEnabled(controlMainEditor.getHaveFooter());
     * jMenuItem25.setText
     * ((controlMainEditor.getHaveFooter()?"Edit Footer":"Add Footer"));
     */
}
