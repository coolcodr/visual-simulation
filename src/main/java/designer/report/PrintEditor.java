package designer.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.MenuEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;

import print.DialogMultiPage;
import print.ObjectComponent;
import print.ObjectTest1;
import print.PCPageNumber;
import print.ReportDocument;
import designer.UIDesigner;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class PrintEditor extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 8886734108944868563L;
    // GUI component
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JMenuBar jMenuEditor = new JMenuBar();
    private JMenu jMenu1 = new JMenu();
    private JToolBar jToolBar1 = new JToolBar();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JButton jButton1New = new JButton(new ImageIcon("print/images/New16.gif"));
    private JButton jButton1Open = new JButton(new ImageIcon("print/images/Open16.gif"));
    private JButton jButton1Save = new JButton(new ImageIcon("print/images/Save16.gif"));
    private JButton jButton1PageSetup = new JButton(new ImageIcon("print/images/PageSetup16.gif"));
    private JToggleButton jButton1Preview = new JToggleButton(new ImageIcon("print/images/PrintPreview16.gif"));
    private JButton jButton1Print = new JButton(new ImageIcon("print/images/Print16.gif"));
    private JButton jButton1ZoomIn = new JButton(new ImageIcon("print/images/ZoomIn16.gif"));
    private JButton jButton1ZoomOut = new JButton(new ImageIcon("print/images/ZoomOut16.gif"));
    private JMenuItem jMenuItem1 = new JMenuItem(new ImageIcon("print/images/New16.gif"));
    private JMenuItem jMenuItem2 = new JMenuItem(new ImageIcon("print/images/Open16.gif"));
    private JMenuItem jMenuItem3 = new JMenuItem(new ImageIcon("print/images/Blank16.gif"));
    private JMenuItem jMenuItem5 = new JMenuItem(new ImageIcon("print/images/Save16.gif"));
    private JMenuItem jMenuItem6 = new JMenuItem(new ImageIcon("print/images/Blank16.gif"));
    private JMenuItem jMenuItem8 = new JMenuItem(new ImageIcon("print/images/PageSetup16.gif"));
    private JMenuItem jMenuItem9 = new JMenuItem(new ImageIcon("print/images/PrintPreview16.gif"));
    private JMenuItem jMenuItem10 = new JMenuItem(new ImageIcon("print/images/Print16.gif"));
    private JMenuItem jMenuItem4 = new JMenuItem(new ImageIcon("print/images/Blank16.gif"));
    private JPanel jPanel3 = new JPanel();
    private JToggleButton jButton2AlignLeft = new JToggleButton(new ImageIcon("print/images/AlignLeft16.gif"));
    private JPanel jToolBar2 = new JPanel();
    private BorderLayout borderLayout3 = new BorderLayout();
    private JSplitPane jSplitPane1 = new JSplitPane();
    private JComboBox jComboBox1 = new JComboBox();
    private JToggleButton jButton2AlignCenter = new JToggleButton(new ImageIcon("print/images/AlignCenter16.gif"));
    private JToggleButton jButton2AlignRight = new JToggleButton(new ImageIcon("print/images/AlignRight16.gif"));
    private JToggleButton jButton2AlignJustify = new JToggleButton(new ImageIcon("print/images/AlignJustify16.gif"));
    private JComboBox jComboBox2Font = new JComboBox();
    private JComboBox jComboBox2Size = new JComboBox();
    private JToggleButton jButton2Bold = new JToggleButton(new ImageIcon("print/images/Bold16.gif"));
    private JToggleButton jButton2Italic = new JToggleButton(new ImageIcon("print/images/Italic16.gif"));
    private JToggleButton jButton2Underline = new JToggleButton(new ImageIcon("print/images/Underline16.gif"));
    private JToggleButton jButton2Text = new JToggleButton(new ImageIcon("print/images/Text16.gif"));
    private JButton jButtonCenter = new JButton(new ImageIcon("print/images/GraphJusCenter16.gif"));
    private JButton jButtonFitHoz = new JButton(new ImageIcon("print/images/GraphJusHorizontal16.gif"));
    private JButton jButtonFitVer = new JButton(new ImageIcon("print/images/GraphJusVertical16.gif"));
    private JButton jButtonDelete = new JButton(new ImageIcon("print/images/Delete16.gif"));
    private JButton jButton1 = new JButton();
    private JMenu jMenu2 = new JMenu();
    private JMenuItem jMenuItem11 = new JMenuItem();
    private JMenuItem jMenuItem12 = new JMenuItem();
    private JMenuItem jMenuItem13 = new JMenuItem();
    private JMenuItem jMenuItem14 = new JMenuItem();
    private JMenuItem jMenuItem15 = new JMenuItem();
    private JMenuItem jMenuItem16 = new JMenuItem();
    private JMenuItem jMenuItem7 = new JMenuItem();
    private JMenu jMenu3 = new JMenu();
    private JMenuItem jMenuItem17 = new JMenuItem();
    private JMenuItem jMenuItem18 = new JMenuItem();
    private JMenuItem jMenuItem19 = new JMenuItem();
    private JMenuItem jMenuItem20 = new JMenuItem();
    private JMenuItem jMenuItem21 = new JMenuItem();
    private JMenu jMenu4 = new JMenu();
    private JMenuItem jMenuItem23 = new JMenuItem();
    private JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
    private JCheckBoxMenuItem jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
    private JComboBox jComboBox2Scale = new JComboBox();
    private JScrollPane jScrollPane = new JScrollPane();

    // Printing Control Object
    private DialogHeaderFooter dialogPageNumber;
    private DialogMultiPage dialogMultiPage;

    private ControlMainEditor controlMainEditor;
    private JTextField txtTemp = new JTextField();
    private JLabel jLabelStatus1 = new JLabel();
    private BorderLayout borderLayout4 = new BorderLayout();
    private JLabel jLabelStatus3 = new JLabel();
    private JLabel jLabelStatus2 = new JLabel();
    private Border border1;

    private int currentPage;
    private int totalPage;
    private JList jList1 = new JList();
    private JButton jButton2 = new JButton();
    private JList jList2 = new JList();
    private JPanel jPanel4 = new JPanel();
    private BorderLayout borderLayout5 = new BorderLayout();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JMenuItem jMenuItem22 = new JMenuItem(new ImageIcon("print/images/Blank16.gif"));
    private JPanel jPanel5 = new JPanel();
    private JMenu jMenu5 = new JMenu();
    private JMenuItem jMenuItem24 = new JMenuItem();
    private JMenuItem jMenuItem26 = new JMenuItem();
    private JCheckBoxMenuItem jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
    private JMenu jMenu6 = new JMenu();
    private JMenuItem jMenuItem25 = new JMenuItem();
    private JMenuItem jMenuItem27 = new JMenuItem();
    private JCheckBoxMenuItem jCheckBoxMenuItem4 = new JCheckBoxMenuItem();
    private JButton jButton3 = new JButton();
    BorderLayout borderLayout6 = new BorderLayout();
    JScrollPane jScrollPane2 = new JScrollPane();
    private JList pageList = new JList(new DefaultListModel());
    private JPanel jToolPane = new JPanel();
    private JButton closeButton = new JButton();

    public PrintEditor() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PrintEditor(Component component) {
        this();
        addPage(new ObjectComponent(component));
        // addPage( component) ;
    }

    public PrintEditor(ReportDocument reportDocument) {
        this();
        controlMainEditor.load(reportDocument);
    }

    public DialogHeaderFooter getFormattingDialog() {
        return dialogPageNumber;
    }

    public PrintEditor(Component[] component) {
        this();
        for (int i = 0; i < component.length; i++) {
            addPage(new ObjectComponent(component[i]));
        }
    }

    public ControlMainEditor getControl() {
        return controlMainEditor;
    }

    public void updateFormatingDialog(PCPageNumber pageNumber, PCPageNumber dateTime) {
        dialogPageNumber.setPageNumberFormat(pageNumber);
        dialogPageNumber.setDateTimeFormat(dateTime);
    }

    private void jbInit() throws Exception {
        border1 = BorderFactory.createLineBorder(Color.lightGray, 1);
        this.setBounds(0, 0, 800, 600);
        setTitle("Print Editor");

        // GUI Component
        getContentPane().setLayout(borderLayout1);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setJMenuBar(jMenuEditor);
        jMenu1.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu1.setText("File");
        jPanel1.setLayout(borderLayout2);
        jButton1New.setBounds(18, 4, 24, 24);
        jMenuItem1.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem1.setText("New...");
        jMenuItem1.addActionListener(new PrintEditor_jMenuItem1_actionAdapter(this));
        jMenuItem2.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem2.setText("Open...");
        jMenuItem2.addActionListener(new PrintEditor_jMenuItem2_actionAdapter(this));
        jMenuItem3.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem3.setText("Close");
        jMenuItem3.addActionListener(new PrintEditor_jMenuItem3_actionAdapter(this));
        jMenuItem5.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem5.setText("Save");
        jMenuItem5.addActionListener(new PrintEditor_jMenuItem5_actionAdapter(this));
        jMenuItem6.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem6.setText("Save as...");
        jMenuItem6.addActionListener(new PrintEditor_jMenuItem6_actionAdapter(this));
        jMenuItem8.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem8.setText("Page Setup...");
        jMenuItem8.addActionListener(new PrintEditor_jMenuItem8_actionAdapter(this));
        jMenuItem9.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem9.setText("Print Preview");
        jMenuItem9.addActionListener(new PrintEditor_jMenuItem9_actionAdapter(this));
        jMenuItem10.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem10.setText("Print...");

        jMenuItem10.addActionListener(new PrintEditor_jMenuItem10_actionAdapter(this));
        jMenuItem4.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new PrintEditor_jMenuItem4_actionAdapter(this));
        jPanel2.setMaximumSize(new Dimension(32767, 20));
        jPanel2.setPreferredSize(new Dimension(14, 20));
        jPanel2.setLayout(borderLayout4);
        jButton2AlignLeft.setFocusPainted(false);
        jButton2AlignLeft.setRolloverEnabled(true);
        jButton2AlignLeft.addActionListener(new PrintEditor_jButton2AlignLeft_actionAdapter(this));
        jButton2AlignLeft.setFocusable(false);
        jToolBar2.setLayout(new BorderLayout());
        jToolBar2.add(jToolPane, BorderLayout.CENTER);
        jToolBar2.setPreferredSize(new Dimension(30, 32));
        // jToolBar2.setFloatable(false);

        jPanel3.setLayout(borderLayout3);
        jToolBar1.setFloatable(false);
        jComboBox1.setEnabled(false);
        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox1.setEditable(true);
        jComboBox1.setBounds(new Rectangle(232, 4, 70, 24));
        jComboBox1.addItemListener(new PrintEditor_jComboBox1_itemAdapter(this));
        jButton2AlignCenter.setBounds(new Rectangle(36, 5, 53, 25));
        jButton2AlignRight.setBounds(new Rectangle(106, 12, 53, 25));
        jButton2AlignJustify.setBounds(new Rectangle(182, 9, 19, 15));
        jComboBox2Font.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox2Font.setBounds(new Rectangle(34, 4, 140, 24));
        jComboBox2Font.addItemListener(new PrintEditor_jComboBox2Font_itemAdapter(this));
        jComboBox2Font.setFocusable(false);
        jComboBox2Size.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox2Size.setBounds(new Rectangle(176, 4, 70, 24));
        jComboBox2Size.addItemListener(new PrintEditor_jComboBox2Size_itemAdapter(this));
        jComboBox2Size.setFocusable(false);
        jButton2Bold.setFocusPainted(false);
        jButton2Bold.setBounds(new Rectangle(256, 4, 24, 24));
        jButton2Bold.addActionListener(new PrintEditor_jButton2Bold_actionAdapter(this));
        jButton2Bold.setFocusable(false);
        jButton2Italic.setFocusPainted(false);
        jButton2Italic.setBounds(new Rectangle(282, 4, 24, 24));
        jButton2Italic.addActionListener(new PrintEditor_jButton2Italic_actionAdapter(this));
        jButton2Italic.setFocusable(false);
        jButton2Underline.setFocusPainted(false);
        jButton2Underline.setBounds(new Rectangle(308, 4, 24, 24));
        jButton2Underline.addActionListener(new PrintEditor_jButton2Underline_actionAdapter(this));
        jButton2Underline.setFocusable(false);
        jButton2Text.setFocusPainted(false);
        jButton2Text.setBounds(new Rectangle(8, 4, 24, 24));
        jButton2Text.addActionListener(new PrintEditor_jButton2Text_actionAdapter(this));
        jButtonCenter.setEnabled(false);
        jButtonCenter.setFocusPainted(false);
        jButtonCenter.addActionListener(new PrintEditor_jButtonCenter_actionAdapter(this));
        jButtonCenter.setBounds(new Rectangle(454, 4, 24, 24));
        jButtonFitHoz.setEnabled(false);
        jButtonFitHoz.setFocusPainted(false);
        jButtonFitHoz.addActionListener(new PrintEditor_jButtonFitHoz_actionAdapter(this));
        jButtonFitHoz.setBounds(new Rectangle(480, 4, 24, 24));
        jButtonFitVer.setEnabled(false);
        jButtonFitVer.setFocusPainted(false);
        jButtonFitVer.addActionListener(new PrintEditor_jButtonFitVer_actionAdapter(this));
        jButtonFitVer.setBounds(new Rectangle(506, 4, 24, 24));
        jButtonDelete.setEnabled(false);
        jButtonDelete.setFocusPainted(false);
        jButtonDelete.addActionListener(new PrintEditor_jButtonDelete_actionAdapter(this));
        jButtonDelete.setBounds(new Rectangle(610, 4, 24, 24));
        jButton1.setBounds(new Rectangle(304, 4, 50, 24));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton1.setFocusPainted(false);
        jButton1.setText("Close");
        jButton1.addActionListener(new PrintEditor_jButton1_actionAdapter(this));
        jMenu2.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu2.setText("Edit");
        jMenuItem11.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem11.setVisible(false);
        jMenuItem11.setText("Edit Header and Footer");
        jMenuItem12.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem12.setVisible(false);
        jMenuItem12.setText("Add Text");
        jMenuItem13.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem13.setVisible(false);
        jMenuItem13.setText("Add Text");
        jMenuItem14.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem14.setVisible(false);
        jMenuItem14.setText("Add Image");
        jMenuItem15.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem15.setVisible(false);
        jMenuItem15.setText("Add Simulation Diagram");
        jMenuItem16.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem16.setVisible(false);
        jMenuItem16.setText("Add Simulation Result");
        jMenuItem7.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem7.setActionCommand("age Header & Footer");
        jMenuItem7.setText("Page Number & Date");
        jMenuItem7.addActionListener(new PrintEditor_jMenuItem7_actionAdapter(this));
        jMenu3.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu3.setText("View");
        jMenuItem17.setEnabled(false);
        jMenuItem17.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem17.setText("Zoom In");
        jMenuItem17.addActionListener(new PrintEditor_jMenuItem17_actionAdapter(this));
        jMenuItem18.setEnabled(false);
        jMenuItem18.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem18.setText("Zoom out");
        jMenuItem18.addActionListener(new PrintEditor_jMenuItem18_actionAdapter(this));
        jMenuItem19.setEnabled(false);
        jMenuItem19.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem19.setText("Page Width");
        jMenuItem19.addActionListener(new PrintEditor_jMenuItem19_actionAdapter(this));
        jMenuItem20.setEnabled(false);
        jMenuItem20.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem20.setText("Whole Page");
        jMenuItem20.addActionListener(new PrintEditor_jMenuItem20_actionAdapter(this));
        jMenuItem21.setEnabled(false);
        jMenuItem21.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem21.setText("Two page");
        jMenuItem21.addActionListener(new PrintEditor_jMenuItem21_actionAdapter(this));
        jMenu4.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu4.setText("Option");
        jMenuItem23.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem23.setText("Edit Grid");
        jCheckBoxMenuItem1.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxMenuItem1.setVisible(false);
        jCheckBoxMenuItem1.setText("View Grid");
        jCheckBoxMenuItem2.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("Snap to Grid");
        jCheckBoxMenuItem2.addActionListener(new PrintEditor_jCheckBoxMenuItem2_actionAdapter(this));
        jButton2AlignJustify.setFocusPainted(false);
        jButton2AlignJustify.addActionListener(new PrintEditor_jButton2AlignJustify_actionAdapter(this));
        jButton2AlignJustify.setFocusable(false);
        jButton2AlignRight.setFocusPainted(false);
        jButton2AlignRight.addActionListener(new PrintEditor_jButton2AlignRight_actionAdapter(this));
        jButton2AlignRight.setFocusable(false);
        jButton2AlignCenter.setFocusPainted(false);
        jButton2AlignCenter.addActionListener(new PrintEditor_jButton2AlignCenter_actionAdapter(this));
        jButton2AlignCenter.setFocusable(false);
        jButton1New.setFocusPainted(false);
        jButton1New.addActionListener(new PrintEditor_jButton1New_actionAdapter(this));
        jButton1Open.setFocusPainted(false);
        jButton1Open.addActionListener(new PrintEditor_jButton1Open_actionAdapter(this));
        jButton1Save.setFocusPainted(false);
        jButton1Save.addActionListener(new PrintEditor_jButton1Save_actionAdapter(this));
        jButton1PageSetup.setFocusPainted(false);
        jButton1PageSetup.addActionListener(new PrintEditor_jButton1PageSetup_actionAdapter(this));
        jButton1Preview.setFocusPainted(false);
        jButton1Preview.addActionListener(new PrintEditor_jButton1Preview_actionAdapter(this));
        jButton1Print.setFocusPainted(false);
        jButton1Print.addActionListener(new PrintEditor_jButton1Print_actionAdapter(this));
        jButton1ZoomIn.setEnabled(false);
        jButton1ZoomIn.setFocusPainted(false);
        jButton1ZoomIn.addActionListener(new PrintEditor_jButton1ZoomIn_actionAdapter(this));
        jButton1ZoomOut.setEnabled(false);
        jButton1ZoomOut.setFocusPainted(false);
        jButton1ZoomOut.addActionListener(new PrintEditor_jButton1ZoomOut_actionAdapter(this));
        jComboBox2Scale.setEnabled(false);
        jComboBox2Scale.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox2Scale.setEditable(true);
        jComboBox2Scale.setBounds(new Rectangle(532, 4, 70, 24));
        jComboBox2Scale.addItemListener(new PrintEditor_jComboBox2Scale_itemAdapter(this));
        txtTemp.setVisible(false);
        txtTemp.setBounds(new Rectangle(406, 4, 58, 22));
        jLabelStatus1.setForeground(new Color(102, 102, 154));
        jLabelStatus1.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabelStatus1.setMinimumSize(new Dimension(120, 23));
        jLabelStatus1.setPreferredSize(new Dimension(145, 23));
        jLabelStatus3.setForeground(new Color(102, 102, 154));
        jLabelStatus3.setBorder(BorderFactory.createLoweredBevelBorder());
        jLabelStatus3.setMaximumSize(new Dimension(150, 23));
        jLabelStatus3.setMinimumSize(new Dimension(150, 23));
        jLabelStatus3.setPreferredSize(new Dimension(150, 23));
        jLabelStatus3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelStatus2.setForeground(new Color(102, 102, 154));
        jLabelStatus2.setBorder(BorderFactory.createLoweredBevelBorder());
        jButton2.setBounds(new Rectangle(373, 5, 64, 23));
        jButton2.setVisible(false);
        jButton2.setText("Test");
        jButton2.addActionListener(new PrintEditor_jButton2_actionAdapter(this));
        jPanel4.setLayout(borderLayout5);
        // jScrollPane1.setBorder(BorderFactory.createEtchedBorder());
        jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
        jPanel4.setVisible(false);
        jScrollPane.setName("");
        jScrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        jMenuItem22.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem22.setText("Multipage Setup");
        jMenuItem22.addActionListener(new PrintEditor_jMenuItem22_actionAdapter(this));
        jMenu5.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu5.setText("Header");
        jMenu5.addMenuListener(new PrintEditor_jMenu5_menuAdapter(this));
        jMenuItem24.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem24.setText("Edit Header");
        jMenuItem24.addActionListener(new PrintEditor_jMenuItem24_actionAdapter(this));
        jMenuItem26.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem26.setText("Remove Header");
        jMenuItem26.addActionListener(new PrintEditor_jMenuItem26_actionAdapter(this));
        jCheckBoxMenuItem3.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxMenuItem3.setText("Header Line");
        jCheckBoxMenuItem3.addActionListener(new PrintEditor_jCheckBoxMenuItem3_actionAdapter(this));
        jMenu6.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenu6.setText("Footer");
        jMenu6.addMenuListener(new PrintEditor_jMenu6_menuAdapter(this));
        jMenuItem25.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem25.setText("Edit Footer");
        jMenuItem25.addActionListener(new PrintEditor_jMenuItem25_actionAdapter(this));
        jMenuItem27.setFont(new java.awt.Font("Dialog", 0, 12));
        jMenuItem27.setText("Remove Footer");
        jMenuItem27.addActionListener(new PrintEditor_jMenuItem27_actionAdapter(this));
        jCheckBoxMenuItem4.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxMenuItem4.setText("Footer Line");
        jCheckBoxMenuItem4.addActionListener(new PrintEditor_jCheckBoxMenuItem4_actionAdapter(this));
        jButton3.addActionListener(new PrintEditor_jButton3_actionAdapter(this));
        jButton3.setText("Test2");
        jButton3.addActionListener(new PrintEditor_jButton3_actionAdapter(this));
        jButton3.setBounds(new Rectangle(438, 5, 64, 23));
        jButton3.setVisible(false);
        jPanel5.setBackground(Color.white);
        jPanel5.setLayout(borderLayout6);
        pageList.setVisible(false);
        pageList.setBorder(null);
        pageList.addMouseListener(new PrintEditor_pageList_mouseAdapter(this));
        jScrollPane2.setVisible(false);
        jScrollPane2.setBorder(BorderFactory.createLoweredBevelBorder());
        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerSize(0);
        getContentPane().add(jPanel1, BorderLayout.NORTH);
        getContentPane().add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(jLabelStatus1, BorderLayout.WEST);
        jPanel2.add(jLabelStatus3, BorderLayout.EAST);
        jPanel2.add(jLabelStatus2, BorderLayout.CENTER);
        jPanel3.add(jToolBar2, BorderLayout.NORTH);
        jPanel3.add(jSplitPane1, BorderLayout.CENTER);
        jMenuEditor.add(jMenu1);
        jMenuEditor.add(jMenu2);
        jMenuEditor.add(jMenu3);
        jMenuEditor.add(jMenu4);
        jPanel1.add(jToolBar1, BorderLayout.NORTH);
        jToolBar1.setLayout(null);
        jToolBar1.setPreferredSize(new Dimension(30, 30));
        jToolBar1.add(jButton1New, null);

        jButton1New.setBounds(new Rectangle(8, 4, 24, 24));
        jButton1Open.setBounds(34, 4, 24, 24);
        jButton1Save.setBounds(60, 4, 24, 24);
        jButton1PageSetup.setBounds(94, 4, 24, 24);
        jButton1Preview.setBounds(120, 4, 24, 24);
        jButton1Print.setBounds(146, 4, 24, 24);
        jButton1ZoomIn.setBounds(180, 4, 24, 24);
        jButton1ZoomOut.setBounds(206, 4, 24, 24);

        jToolBar1.add(jButton1New, null);
        jToolBar1.add(jButton1Open, null);
        jToolBar1.add(jButton1Save, null);
        jToolBar1.add(jButton1PageSetup, null);
        jToolBar1.add(jButton1Preview, null);
        jToolBar1.add(jButton1Print, null);
        jToolBar1.add(jButton1ZoomIn, null);
        jToolBar1.add(jButton1ZoomOut, null);
        jToolBar1.add(jComboBox1, null);
        jToolBar1.add(jButton1, null);
        jToolBar1.add(txtTemp, null);
        jToolBar1.add(jButton2, null);
        jToolBar1.add(jButton3, null);

        jButton2AlignLeft.setBounds(new Rectangle(342, 4, 24, 24));
        jButton2AlignCenter.setBounds(new Rectangle(368, 4, 24, 24));
        jButton2AlignRight.setBounds(new Rectangle(394, 4, 24, 24));
        jButton2AlignJustify.setBounds(new Rectangle(420, 4, 24, 24));

        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu1.add(jMenuItem3);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem5);
        jMenu1.add(jMenuItem6);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem8);
        jMenu1.add(jMenuItem22);
        jMenu1.add(jMenuItem9);
        jMenu1.add(jMenuItem10);
        jMenu1.addSeparator();
        jMenu1.add(jMenuItem4);
        jToolPane.setLayout(null);
        jToolPane.add(jButton2AlignRight, null);
        jToolPane.add(jButton2AlignJustify, null);
        jToolPane.add(jButton2AlignCenter, null);
        jToolPane.add(jButton2AlignLeft, null);
        jToolPane.add(jButton2Underline, null);
        jToolPane.add(jButtonCenter, null);
        jToolPane.add(jButtonFitHoz, null);
        jToolPane.add(jButtonFitVer, null);
        jToolPane.add(jButtonDelete, null);
        jToolPane.add(jButton2Italic, null);
        jToolPane.add(jButton2Bold, null);
        jToolPane.add(jButton2Text, null);
        jToolPane.add(jComboBox2Font, null);
        jToolPane.add(jComboBox2Size, null);
        jToolPane.add(jComboBox2Scale, null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(new Rectangle(708, 0, 28, 32));
        panel.setPreferredSize(new Dimension(28, 25));

        JButton button = new JButton(new ImageIcon("designer/images/close.gif"));

        jToolBar2.add(panel, BorderLayout.EAST);
        panel.add(button, BorderLayout.CENTER);

        button.setPreferredSize(new Dimension(15, 15));
        button.setBounds(new Rectangle(8, 8, 15, 15));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_actionPerformed(e);
            }
        });

        jMenu2.add(jMenuItem12);
        jMenu2.add(jMenuItem14);
        jMenu2.add(jMenuItem13);
        jMenu2.add(jMenuItem15);
        jMenu2.add(jMenuItem16);
        jMenu2.add(jMenuItem11);
        jMenu2.add(jMenu5);
        jMenu2.add(jMenu6);
        jMenu2.addSeparator();
        jMenu2.add(jMenuItem7);
        jMenu3.add(jMenuItem17);
        jMenu3.add(jMenuItem18);

        jMenu3.add(jMenuItem19);
        jMenu3.add(jMenuItem20);
        jMenu3.add(jMenuItem21);
        jMenu4.add(jMenuItem23);
        jMenu4.add(jCheckBoxMenuItem2);
        jMenu4.add(jCheckBoxMenuItem1);
        jSplitPane1.add(jScrollPane, JSplitPane.RIGHT);
        jSplitPane1.add(jPanel5, JSplitPane.LEFT);
        jPanel5.add(jScrollPane2, BorderLayout.CENTER);
        jScrollPane2.getViewport().add(pageList, null);
        getContentPane().add(jPanel4, BorderLayout.CENTER);
        getContentPane().add(jPanel3, BorderLayout.CENTER);
        jPanel4.add(jScrollPane1, BorderLayout.CENTER);
        jMenu5.add(jMenuItem24);
        jMenu5.add(jMenuItem26);
        jMenu5.add(jCheckBoxMenuItem3);
        jMenu6.add(jMenuItem25);
        jMenu6.add(jMenuItem27);
        jMenu6.add(jCheckBoxMenuItem4);
        jSplitPane1.setDividerLocation(0);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPane1.addComponentListener(new ComponentListener() {
            public void componentHidden(ComponentEvent event) {
            }

            public void componentShown(ComponentEvent event) {
            }

            public void componentMoved(ComponentEvent event) {
            }

            public void componentResized(ComponentEvent event) {
                // controlMainEditor.refreshPreview();
            }
        });
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font fontList[] = env.getAllFonts();
        for (int i = 0; i < fontList.length; i++) {
            jComboBox2Font.addItem(fontList[i].getFontName());
        }
        for (int i = 8; i <= 18; i += 2) {
            jComboBox2Size.addItem(String.valueOf(i));
        }
        for (int i = 24; i <= 36; i += 6) {
            jComboBox2Size.addItem(String.valueOf(i));
        }
        for (int i = 48; i <= 72; i += 12) {
            jComboBox2Size.addItem(String.valueOf(i));
        }

        for (int i = 25; i <= 200; i += 25) {
            jComboBox2Scale.addItem(String.valueOf(i) + "%");
            jComboBox1.addItem(String.valueOf(i) + "%");
        }/*
          * for (int i = 200; i <= 300; i =+ 50) {
          * jComboBox2Scale.addItem(String.valueOf(i));
          * jComboBox1.addItem(String.valueOf(i)); }
          */
        // jComboBox2Font.setEditable(true);

        jComboBox2Size.setSelectedIndex(2);
        jComboBox2Scale.setSelectedIndex(3);
        jComboBox1.setSelectedIndex(3);
        /*
         * for ( int i = 0 ; i < jComboBox2Font.getItemCount() ; i++ ) {
         * jComboBox2Font.setSelectedIndex(0); if (
         * (jComboBox2Font.getItemAt(i).toString().compareTo("dialog.plain") ==
         * 0 )) { jComboBox2Font.setSelectedIndex(i); break; } }
         */
        jComboBox2Font.setSelectedItem("dialog.plain");// new
                                                       // JTextField().getFont().getFontName());
        controlMainEditor = new ControlMainEditor(jScrollPane, jScrollPane1, this);

        dialogPageNumber = new DialogHeaderFooter(this);
        // dialogMultiPage = new DialogMultiPage(this);
        // dialogPageNumber.show();
        controlMainEditor.updatePageNumber(dialogPageNumber.getPageNumberFormat());
        controlMainEditor.updateDataTime(dialogPageNumber.getDateTimeFormat());
        // Printing Object
        setStatus3Text("Editing ");
        enableEditMenuItem(false);
    }

    public String toString() {
        return "Report - " + getTitle();
    }

    void jButton_actionPerformed(ActionEvent e) {
        UIDesigner.workPaneTab.remove(getDesignPane());
    }

    void jMenuItem1_actionPerformed(ActionEvent e) {
        ObjectTest1 test1 = new ObjectTest1();
        controlMainEditor.addNewDiagram(new ObjectComponent(test1));
        controlMainEditor.refreshView();
    }

    void jMenuItem10_actionPerformed(ActionEvent e) {
        controlMainEditor.print();
        controlMainEditor.refreshView();
    }

    void jMenuItem8_actionPerformed(ActionEvent e) {
        controlMainEditor.pageSetup();
        controlMainEditor.refreshView();
    }

    void jButton2Text_actionPerformed(ActionEvent e) {
        controlMainEditor.addParagraph();
    }

    void jButton1New_actionPerformed(ActionEvent e) {
        addPage(new ObjectComponent(new ObjectTest1()));
    }

    void jComboBox2Scale_itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            int i = 0;
            String s;
            if (controlMainEditor != null) {
                s = (String) e.getItem();
                if (s.substring(s.length() - 1, s.length()).compareTo("%") == 0) {
                    i = Integer.parseInt(s.substring(0, s.length() - 1));
                } else {
                    i = Integer.parseInt(s);
                }
                controlMainEditor.setDiagramScale(i);
            }
        }
    }

    void jComboBox2Size_itemStateChanged(ItemEvent e) {
        int i = Integer.parseInt((String) e.getItem());
        if (controlMainEditor != null) {
            controlMainEditor.setFontSize(i);
        }
    }

    void jButton2Italic_actionPerformed(ActionEvent e) {
        controlMainEditor.setItatic(jButton2Italic.isSelected());
    }

    void jButton2Bold_actionPerformed(ActionEvent e) {
        controlMainEditor.setBold(jButton2Bold.isSelected());
    }

    void jButton2Underline_actionPerformed(ActionEvent e) {
        controlMainEditor.setUnderLine(jButton2Underline.isSelected());
    }

    void jComboBox2Font_itemStateChanged(ItemEvent e) {
        String s = (String) e.getItem();
        if (controlMainEditor != null) {
            controlMainEditor.setFont(s);
        }
    }

    void jButton2AlignRight_actionPerformed(ActionEvent e) {
        controlMainEditor.setRightAlign(jButton2AlignRight.isSelected());
    }

    void jButton2AlignCenter_actionPerformed(ActionEvent e) {
        controlMainEditor.setCenterAlign(jButton2AlignCenter.isSelected());
    }

    void jButton2AlignJustify_actionPerformed(ActionEvent e) {
        controlMainEditor.setJustifyAlign(jButton2AlignJustify.isSelected());
    }

    void jButton2AlignLeft_actionPerformed(ActionEvent e) {
        controlMainEditor.setLeftAlign(jButton2AlignLeft.isSelected());
    }

    public void addPage(ObjectComponent component) {
        controlMainEditor.addNewDiagram(component);
        enableEditMenuItem(true);
        controlMainEditor.refreshView();
    }

    public void updateTextFormat(AttributeSet attr) {
        // styledDocument.getCharacterElement(1).getAttributes().getAttribute(StyleConstants.Alignment)
        // == new Integer(StyleConstants.ALIGN_LEFT) )
        if (String.valueOf(attr.getAttribute(StyleConstants.Bold)) == "true") {
            jButton2Bold.setSelected(true);
        } else {
            jButton2Bold.setSelected(false);
        }
        if (String.valueOf(attr.getAttribute(StyleConstants.Italic)) == "true") {
            jButton2Italic.setSelected(true);
        } else {
            jButton2Italic.setSelected(false);
        }
        if (String.valueOf(attr.getAttribute(StyleConstants.Underline)) == "true") {
            jButton2Underline.setSelected(true);
        } else {
            jButton2Underline.setSelected(false);
        }

        jComboBox2Size.setSelectedItem(String.valueOf(attr.getAttribute(StyleConstants.FontSize)));
        jComboBox2Font.setSelectedItem(String.valueOf(attr.getAttribute(StyleConstants.FontFamily)));
    }

    public void updateParagraphFormat(AttributeSet attr) {
        if (String.valueOf(attr.getAttribute(StyleConstants.Alignment)).compareTo(String.valueOf(StyleConstants.ALIGN_LEFT)) == 0) {
            jButton2AlignLeft.setSelected(true);
        } else {
            jButton2AlignLeft.setSelected(false);
        }
        if (String.valueOf(attr.getAttribute(StyleConstants.Alignment)).compareTo(String.valueOf(StyleConstants.ALIGN_RIGHT)) == 0) {
            jButton2AlignRight.setSelected(true);
        } else {
            jButton2AlignRight.setSelected(false);
        }
        if (String.valueOf(attr.getAttribute(StyleConstants.Alignment)).compareTo(String.valueOf(StyleConstants.ALIGN_CENTER)) == 0) {
            jButton2AlignCenter.setSelected(true);
        } else {
            jButton2AlignCenter.setSelected(false);
        }
        if (String.valueOf(attr.getAttribute(StyleConstants.Alignment)).compareTo(String.valueOf(StyleConstants.ALIGN_JUSTIFIED)) == 0) {
            jButton2AlignJustify.setSelected(true);
        } else {
            jButton2AlignJustify.setSelected(false);
            // txtTemp.setText(String.valueOf(attr.getAttribute(StyleConstants.Alignment)));
        }
    }

    public void setFontFormat() {
        int i = Integer.parseInt((String) jComboBox2Size.getSelectedItem());
        controlMainEditor.setFontSize(i);
        controlMainEditor.setItatic(jButton2Italic.isSelected());
        controlMainEditor.setBold(jButton2Bold.isSelected());
        controlMainEditor.setUnderLine(jButton2Underline.isSelected());
        String s = (String) jComboBox2Font.getSelectedItem();
        controlMainEditor.setFont(s);
        controlMainEditor.setRightAlign(jButton2AlignRight.isSelected());
        controlMainEditor.setCenterAlign(jButton2AlignCenter.isSelected());
        controlMainEditor.setJustifyAlign(jButton2AlignJustify.isSelected());
        controlMainEditor.setLeftAlign(jButton2AlignLeft.isSelected());
    }

    public void enableParagraphEditButton(boolean b) {
        jButtonFitHoz.setEnabled(b);
        jButtonFitVer.setEnabled(b);
        jButtonDelete.setEnabled(b);
    }

    public void enableDiagramEditButton(boolean b) {
        jComboBox2Scale.setEnabled(b);
        jButtonCenter.setEnabled(b);
        jButtonFitHoz.setEnabled(b);
        jButtonFitVer.setEnabled(b);
        jButtonDelete.setEnabled(false);
    }

    public void enableZoomButton(boolean b) {
        jButton1ZoomIn.setEnabled(b);
        jButton1ZoomOut.setEnabled(b);
        jComboBox1.setEnabled(b);
        jMenuItem17.setEnabled(b);
        jMenuItem18.setEnabled(b);
        jMenuItem19.setEnabled(b);
        jMenuItem20.setEnabled(b);
        jMenuItem21.setEnabled(b);
    }

    public JList getList() {
        return pageList;
    }

    public void setScaleText(int i) {
        jComboBox2Scale.setSelectedItem(String.valueOf(i) + "%");
    }

    public void setPreviewScaleText(int i) {
        jComboBox1.setSelectedItem(String.valueOf(i) + "%");
    }

    public void enableAddTextButton(boolean b) {
        jButton2Text.setSelected(b);
    }

    public void enableEditMenuItem(boolean b) {
        jMenu5.setEnabled(b);
        jMenu6.setEnabled(b);
        jMenuItem7.setEnabled(b);
    }

    public void setStatus3Text(String s) {
        jLabelStatus3.setText(s);
    }

    public void setStatus2Text(String s) {
        jLabelStatus2.setText(s);
    }

    public void setStatus1Text(int currentPage, int totalPage) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        jLabelStatus1.setText(" Page " + (currentPage + 1) + " of " + totalPage);
    }

    public JPanel getDesignPane() {
        return jPanel3;
    }

    void jMenuItem7_actionPerformed(ActionEvent e) {
        dialogPageNumber.showInitial();
        controlMainEditor.refreshView();
    }

    void jButton1PageSetup_actionPerformed(ActionEvent e) {
        controlMainEditor.pageSetup();
        // controlMainEditor.refreshView();
    }

    void jButton1Print_actionPerformed(ActionEvent e) {
        controlMainEditor.print();
        // controlMainEditor.refreshView();
    }

    void jButton2_actionPerformed(ActionEvent e) {
        controlMainEditor.editHeader();
    }

    void jButton1Preview_actionPerformed(ActionEvent e) {
        if (jButton1Preview.isSelected()) {
            jPanel3.setVisible(false);
            getContentPane().add(jPanel4, BorderLayout.CENTER);
            controlMainEditor.refreshPreview();
            jPanel4.setVisible(true);
            enableZoomButton(true);
            setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));

        } else {
            jPanel4.setVisible(false);
            getContentPane().add(jPanel3, BorderLayout.CENTER);
            controlMainEditor.refreshView();
            jPanel3.setVisible(true);
            enableZoomButton(false);
        }
    }

    void jButton1ZoomIn_actionPerformed(ActionEvent e) {
        controlMainEditor.setPreviewScale(controlMainEditor.getPreviewScale() + 0.25);
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jButton1ZoomOut_actionPerformed(ActionEvent e) {
        double scale = controlMainEditor.getPreviewScale() - 0.25;
        controlMainEditor.setPreviewScale((scale > 0.25 ? scale : controlMainEditor.getPreviewScale() * 0.5));
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jMenuItem22_actionPerformed(ActionEvent e) {
        controlMainEditor.setMultiPageFormat();
    }

    void jComboBox1_itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            int i = 0;
            String s;
            if (controlMainEditor != null) {
                s = (String) e.getItem();
                if (s.substring(s.length() - 1, s.length()).compareTo("%") == 0) {
                    i = Integer.parseInt(s.substring(0, s.length() - 1));
                } else {
                    i = Integer.parseInt(s);
                }
                controlMainEditor.setPreviewScale((float) i / 100);
            }
        }
    }

    void jMenuItem19_actionPerformed(ActionEvent e) {
        controlMainEditor.setPageWidthPreview();
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jMenuItem20_actionPerformed(ActionEvent e) {
        controlMainEditor.setWholePagePreview();
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jMenuItem21_actionPerformed(ActionEvent e) {
        controlMainEditor.setTwoPagePreview();
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jCheckBoxMenuItem2_actionPerformed(ActionEvent e) {
        if (jCheckBoxMenuItem2.isSelected()) {
            controlMainEditor.controlPaneEdit.setGridSize(20);
        } else {
            controlMainEditor.controlPaneEdit.setGridSize(1);
        }
    }

    void jButtonFitVer_actionPerformed(ActionEvent e) {
        controlMainEditor.setDiagramScaleVerticalFit();
    }

    void jButtonFitHor_actionPerformed(ActionEvent e) {
        controlMainEditor.setDiagramScaleHorizontalFit();
    }

    void jButtonCenter_actionPerformed(ActionEvent e) {
        controlMainEditor.setDiagramPositionCenter();
    }

    void jMenuItem9_actionPerformed(ActionEvent e) {
        jButton1Preview.setSelected(true);
        jButton1Preview_actionPerformed(null);
    }

    void jButton3_actionPerformed(ActionEvent e) {
        controlMainEditor.scrollToPageTop(0);
    }

    void jMenuItem25_actionPerformed(ActionEvent e) {
        controlMainEditor.editFooter();
    }

    void jMenuItem24_actionPerformed(ActionEvent e) {
        controlMainEditor.editHeader();
    }

    void jMenuItem26_actionPerformed(ActionEvent e) {
        controlMainEditor.removeHeader();
    }

    void jMenuItem27_actionPerformed(ActionEvent e) {
        controlMainEditor.removeFooter();
    }

    void jMenu5_menuSelected(MenuEvent e) {
        jCheckBoxMenuItem3.setSelected(controlMainEditor.getHeaderLine());
        jCheckBoxMenuItem3.setEnabled(controlMainEditor.getHaveHeader());
        jMenuItem26.setEnabled(controlMainEditor.getHaveHeader());
        jMenuItem24.setText((controlMainEditor.getHaveHeader() ? "Edit Header" : "Add Header"));
    }

    void jMenu6_menuSelected(MenuEvent e) {
        jCheckBoxMenuItem4.setSelected(controlMainEditor.getFooterLine());
        jCheckBoxMenuItem4.setEnabled(controlMainEditor.getHaveFooter());
        jMenuItem27.setEnabled(controlMainEditor.getHaveFooter());
        jMenuItem25.setText((controlMainEditor.getHaveFooter() ? "Edit Footer" : "Add Footer"));
    }

    void jCheckBoxMenuItem3_actionPerformed(ActionEvent e) {
        controlMainEditor.addHeaderLine(jCheckBoxMenuItem3.isSelected());
    }

    void jCheckBoxMenuItem4_actionPerformed(ActionEvent e) {
        controlMainEditor.addFooterLine(jCheckBoxMenuItem4.isSelected());
    }

    void jButtonDelete_actionPerformed(ActionEvent e) {
        controlMainEditor.removeItem();
    }

    void jMenuItem4_actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    void jMenuItem6_actionPerformed(ActionEvent e) {
        controlMainEditor.saveAs();
    }

    void jMenuItem5_actionPerformed(ActionEvent e) {
        controlMainEditor.save();
    }

    void jMenuItem2_actionPerformed(ActionEvent e) {
        controlMainEditor.load();
    }

    void jMenuItem3_actionPerformed(ActionEvent e) {

        /*
         * jScrollPane.removeAll(); this.controlMainEditor = new
         * ControlMainEditor(jScrollPane, jScrollPane1, this);
         * 
         * dialogPageNumber = new DialogHeaderFooter(this);
         * controlMainEditor.updatePageNumber
         * (dialogPageNumber.getPageNumberFormat());
         * controlMainEditor.updateDataTime
         * (dialogPageNumber.getDateTimeFormat());
         */
        controlMainEditor.close();

        controlMainEditor.updatePageNumber(dialogPageNumber.getPageNumberFormat());
        controlMainEditor.updateDataTime(dialogPageNumber.getDateTimeFormat());

    }

    void jMenuItem17_actionPerformed(ActionEvent e) {
        controlMainEditor.setPreviewScale(controlMainEditor.getPreviewScale() + 0.25);
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }

    void jButton1Open_actionPerformed(ActionEvent e) {
        controlMainEditor.load();

    }

    void jButton1Save_actionPerformed(ActionEvent e) {
        controlMainEditor.save();
    }

    void jButton1_actionPerformed(ActionEvent e) {
        setVisible(false);

    }

    void pageList_mousePressed(MouseEvent e) {
        int index = ((JList) e.getSource()).getSelectedIndex();
        if (index >= 0) {
            controlMainEditor.viewPage(index);
        }
    }

    void pageList_mouseReleased(MouseEvent e) {
        int index = ((JList) e.getSource()).getSelectedIndex();
        if (index >= 0) {
            controlMainEditor.viewPage(index);
        }
    }

    void jMenuItem18_actionPerformed(ActionEvent e) {
        double scale = controlMainEditor.getPreviewScale() - 0.25;
        controlMainEditor.setPreviewScale((scale > 0.25 ? scale : controlMainEditor.getPreviewScale() * 0.5));
        setPreviewScaleText((int) (controlMainEditor.getPreviewScale() * 100));
    }
}

class PrintEditor_jMenuItem1_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem1_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem1_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem10_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem10_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem10_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem8_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem8_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem8_actionPerformed(e);
    }
}

class PrintEditor_jButton2Text_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2Text_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2Text_actionPerformed(e);
    }
}

class PrintEditor_jButton1New_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1New_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1New_actionPerformed(e);
    }
}

class PrintEditor_jComboBox2Scale_itemAdapter implements java.awt.event.ItemListener {
    private PrintEditor adaptee;

    PrintEditor_jComboBox2Scale_itemAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox2Scale_itemStateChanged(e);
    }
}

class PrintEditor_jComboBox2Size_itemAdapter implements java.awt.event.ItemListener {
    private PrintEditor adaptee;

    PrintEditor_jComboBox2Size_itemAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox2Size_itemStateChanged(e);
    }
}

class PrintEditor_jButton2Italic_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2Italic_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2Italic_actionPerformed(e);
    }
}

class PrintEditor_jButton2Bold_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2Bold_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2Bold_actionPerformed(e);
    }
}

class PrintEditor_jButton2Underline_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2Underline_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2Underline_actionPerformed(e);
    }
}

class PrintEditor_jComboBox2Font_itemAdapter implements java.awt.event.ItemListener {
    private PrintEditor adaptee;

    PrintEditor_jComboBox2Font_itemAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox2Font_itemStateChanged(e);
    }
}

class PrintEditor_jButton2AlignRight_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2AlignRight_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2AlignRight_actionPerformed(e);
    }
}

class PrintEditor_jButton2AlignCenter_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2AlignCenter_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2AlignCenter_actionPerformed(e);
    }
}

class PrintEditor_jButton2AlignJustify_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2AlignJustify_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2AlignJustify_actionPerformed(e);
    }
}

class PrintEditor_jButton2AlignLeft_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2AlignLeft_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2AlignLeft_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem7_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem7_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem7_actionPerformed(e);
    }
}

class PrintEditor_jButton1PageSetup_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1PageSetup_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1PageSetup_actionPerformed(e);
    }
}

class PrintEditor_jButton1Print_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1Print_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1Print_actionPerformed(e);
    }
}

class PrintEditor_jButton2_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton2_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}

class PrintEditor_jButton1Preview_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1Preview_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1Preview_actionPerformed(e);
    }
}

class PrintEditor_jButton1ZoomIn_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1ZoomIn_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1ZoomIn_actionPerformed(e);
    }
}

class PrintEditor_jButton1ZoomOut_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton1ZoomOut_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1ZoomOut_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem22_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem22_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem22_actionPerformed(e);
    }
}

class PrintEditor_jComboBox1_itemAdapter implements java.awt.event.ItemListener {
    private PrintEditor adaptee;

    PrintEditor_jComboBox1_itemAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void itemStateChanged(ItemEvent e) {
        adaptee.jComboBox1_itemStateChanged(e);
    }
}

class PrintEditor_jMenuItem19_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem19_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem19_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem20_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem20_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem20_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem21_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem21_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem21_actionPerformed(e);
    }
}

class PrintEditor_jCheckBoxMenuItem2_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jCheckBoxMenuItem2_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCheckBoxMenuItem2_actionPerformed(e);
    }
}

class PrintEditor_jButtonFitHoz_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButtonFitHoz_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonFitHor_actionPerformed(e);
    }
}

class PrintEditor_jButtonFitVer_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButtonFitVer_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonFitVer_actionPerformed(e);
    }
}

class PrintEditor_jButtonCenter_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButtonCenter_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCenter_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem9_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem9_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem9_actionPerformed(e);
    }
}

class PrintEditor_jButton3_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButton3_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton3_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem25_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem25_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem25_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem24_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem24_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem24_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem26_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem26_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem26_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem27_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jMenuItem27_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem27_actionPerformed(e);
    }
}

class PrintEditor_jMenu5_menuAdapter implements javax.swing.event.MenuListener {
    private PrintEditor adaptee;

    PrintEditor_jMenu5_menuAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void menuSelected(MenuEvent e) {
        adaptee.jMenu5_menuSelected(e);
    }

    public void menuDeselected(MenuEvent e) {
    }

    public void menuCanceled(MenuEvent e) {
    }
}

class PrintEditor_jMenu6_menuAdapter implements javax.swing.event.MenuListener {
    private PrintEditor adaptee;

    PrintEditor_jMenu6_menuAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void menuSelected(MenuEvent e) {
        adaptee.jMenu6_menuSelected(e);
    }

    public void menuDeselected(MenuEvent e) {
    }

    public void menuCanceled(MenuEvent e) {
    }
}

class PrintEditor_jCheckBoxMenuItem3_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jCheckBoxMenuItem3_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCheckBoxMenuItem3_actionPerformed(e);
    }
}

class PrintEditor_jCheckBoxMenuItem4_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jCheckBoxMenuItem4_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jCheckBoxMenuItem4_actionPerformed(e);
    }
}

class PrintEditor_jButtonDelete_actionAdapter implements java.awt.event.ActionListener {
    private PrintEditor adaptee;

    PrintEditor_jButtonDelete_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonDelete_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem4_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem4_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem4_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem6_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem6_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem6_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem5_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem5_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem5_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem2_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem2_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem2_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem3_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem3_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem3_actionPerformed(e);
    }
}

class PrintEditor_jMenuItem17_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem17_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem17_actionPerformed(e);
    }
}

class PrintEditor_jButton1Open_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jButton1Open_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1Open_actionPerformed(e);
    }
}

class PrintEditor_jButton1Save_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jButton1Save_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1Save_actionPerformed(e);
    }
}

class PrintEditor_jButton1_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jButton1_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class PrintEditor_pageList_mouseAdapter extends java.awt.event.MouseAdapter {
    PrintEditor adaptee;

    PrintEditor_pageList_mouseAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void mousePressed(MouseEvent e) {
        adaptee.pageList_mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        adaptee.pageList_mouseReleased(e);
    }
}

class PrintEditor_jMenuItem18_actionAdapter implements java.awt.event.ActionListener {
    PrintEditor adaptee;

    PrintEditor_jMenuItem18_actionAdapter(PrintEditor adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jMenuItem18_actionPerformed(e);

    }
}
