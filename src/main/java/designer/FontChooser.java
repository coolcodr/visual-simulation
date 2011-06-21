package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * <p>
 * Title: Print Editor
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
 * @author HYLim,
 * @version 1.0
 */

public class FontChooser extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1371988416782310191L;
    private JPanel panel1 = new JPanel();
    private Border border1;
    private TitledBorder titledBorder1;
    private Border border2;
    private TitledBorder titledBorder2;
    private JPanel jPanel6 = new JPanel();
    private JButton jButtonOK = new JButton();
    private JButton jButtonCancel = new JButton();
    private ButtonGroup buttonGroupLocation = new ButtonGroup();
    private ButtonGroup buttonGroupAlignment = new ButtonGroup();

    private JTextPane jTextPaneFormat = new JTextPane();

    private String[][] dateTimeFormat;
    private JTextPane jTextPaneDateFormat = new JTextPane();
    private ButtonGroup buttonGroup11 = new ButtonGroup();
    private ButtonGroup buttonGroup21 = new ButtonGroup();
    JLabel jLabel8 = new JLabel();
    JCheckBox jCheckBoxUnderline = new JCheckBox();
    JPanel jPanel4 = new JPanel();
    JCheckBox jCheckBoxItalic = new JCheckBox();
    JComboBox jComboBox1 = new JComboBox();
    JLabel jLabel7 = new JLabel();
    JTextField jTextSize = new JTextField();
    JCheckBox jCheckBoxBold = new JCheckBox();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel3 = new JPanel();
    JButton jButton1 = new JButton();
    JPanel jPanel1 = new JPanel();
    JButton jButton2 = new JButton();
    Font font;

    public FontChooser(JFrame owner, Font font) {
        this(owner, true);
        this.font = font;
        updateValue();
    }

    public FontChooser(JFrame owner, boolean modal) {
        super(owner, modal);
        jbInit();
    }

    private void jbInit() {
        this.setBounds(150, 150, 305, 190);
        setTitle("Set Page Number");
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Container contentPane = this.getContentPane();
        // contentPane.setLayout(null);

        border1 = BorderFactory.createEtchedBorder(Color.white, new Color(142, 142, 142));
        titledBorder1 = new TitledBorder(border1, "Page Number Format");
        border2 = BorderFactory.createEtchedBorder(Color.white, new Color(142, 142, 142));
        titledBorder2 = new TitledBorder(border2, "Font Format");
        panel1.setLayout(null);
        jPanel6.setBounds(new Rectangle(11, 307, 372, 38));
        jPanel6.setLayout(null);
        jButtonOK.setBounds(new Rectangle(103, 0, 73, 29));
        jButtonOK.setFont(new java.awt.Font("Dialog", 0, 12));
        jButtonOK.setSize(new Dimension(73, 29));
        jButtonOK.setMaximumSize(new Dimension(73, 24));
        jButtonOK.setMinimumSize(new Dimension(73, 24));
        jButtonOK.setPreferredSize(new Dimension(51, 24));
        jButtonOK.setText("OK");
        // jButtonOK.addActionListener(new
        // DialogPageNumber_jButtonOK_actionAdapter(this));
        jButtonCancel.setBounds(new Rectangle(192, 0, 77, 29));
        jButtonCancel.setFont(new java.awt.Font("Dialog", 0, 12));
        jButtonCancel.setPreferredSize(new Dimension(73, 29));
        jButtonCancel.setText("Cancel");
        // jButtonCancel.addActionListener(new
        // DialogPageNumber_jButtonCancel_actionAdapter(this));
        // this.getContentPane().setLayout(null);
        panel1.setPreferredSize(new Dimension(300, 200));
        panel1.setBounds(new Rectangle(0, 0, 350, 160));
        jTextPaneFormat.setVisible(false);
        jTextPaneFormat.setBounds(new Rectangle(6, 1, 90, 28));
        // jCheck2.addActionListener(new
        // DialogPageNumber_jCheck2_actionAdapter(this));
        // jCheck2.addActionListener(new
        // DialogPageNumber_jCheck2_actionAdapter(this));
        // jCheck1.addActionListener(new
        // DialogPageNumber_jCheck1_actionAdapter(this));
        // jCheck1.addActionListener(new
        // DialogPageNumber_jCheck1_actionAdapter(this));
        // jCheck4.addActionListener(new
        // DialogPageNumber_jCheck4_actionAdapter(this));
        // jCheck4.addActionListener(new
        // DialogPageNumber_jCheck4_actionAdapter(this));
        // jCheck3.addActionListener(new
        // DialogPageNumber_jCheck3_actionAdapter(this));
        // jCheck3.addActionListener(new
        // DialogPageNumber_jCheck3_actionAdapter(this));
        jTextPaneDateFormat.setVisible(false);
        jTextPaneDateFormat.setBounds(new Rectangle(286, 2, 68, 24));
        jLabel8.setForeground(Color.black);
        jLabel8.setText("Font size:");
        jLabel8.setBounds(new Rectangle(59, 73, 74, 16));
        jCheckBoxUnderline.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxUnderline.setVisible(false);
        jCheckBoxUnderline.setText("Underline");
        jPanel4.setForeground(new Color(102, 102, 154));
        jPanel4.setBorder(titledBorder2);
        jPanel4.setBounds(new Rectangle(9, 9, 284, 114));
        jPanel4.setLayout(null);
        jCheckBoxItalic.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxItalic.setText("Italic");
        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox1.setMaximumSize(new Dimension(32767, 24));
        jComboBox1.setMinimumSize(new Dimension(126, 24));
        jComboBox1.setPreferredSize(new Dimension(130, 24));
        jComboBox1.setBounds(new Rectangle(59, 27, 154, 24));
        jComboBox1.setSelectedItem("dialog.plain");
        jLabel7.setForeground(Color.black);
        jLabel7.setText("Font:");
        jLabel7.setBounds(new Rectangle(17, 25, 43, 26));
        jTextSize.setBorder(BorderFactory.createEtchedBorder());
        jTextSize.setPreferredSize(new Dimension(63, 234));
        jTextSize.setBounds(new Rectangle(123, 69, 90, 24));
        jTextSize.setText("12");
        jCheckBoxBold.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxBold.setText("Bold");
        jPanel3.setPreferredSize(new Dimension(80, 80));
        jPanel3.setBounds(new Rectangle(221, 28, 58, 71));
        jPanel3.setLayout(borderLayout1);
        jButton1.setBounds(new Rectangle(31, 5, 76, 25));
        jButton1.setText("OK");
        jButton1.addActionListener(new FontChooser_jButton1_actionAdapter(this));
        jPanel1.setBounds(new Rectangle(50, 124, 242, 35));
        jPanel1.setLayout(null);
        jButton2.setBounds(new Rectangle(112, 5, 76, 25));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new FontChooser_jButton2_actionAdapter(this));
        getContentPane().add(panel1, null);
        jPanel6.add(jButtonCancel, null);
        jPanel6.add(jButtonOK, null);
        jPanel6.add(jTextPaneFormat, null);
        jPanel6.add(jTextPaneDateFormat, null);
        panel1.add(jPanel4, null);
        jPanel3.add(jCheckBoxUnderline, BorderLayout.SOUTH);
        jPanel3.add(jCheckBoxItalic, BorderLayout.CENTER);
        jPanel3.add(jCheckBoxBold, BorderLayout.NORTH);
        jPanel4.add(jComboBox1, null);
        jPanel4.add(jLabel7, null);
        jPanel4.add(jTextSize, null);
        panel1.add(jPanel1, null);
        jPanel1.add(jButton1, null);
        jPanel1.add(jButton2, null);
        jPanel4.add(jLabel8, null);
        jPanel4.add(jPanel3, null);
        panel1.add(jPanel6, null);

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
        jTextPaneFormat.setParagraphAttributes(attr, false);

        MutableAttributeSet attr2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr2, StyleConstants.ALIGN_RIGHT);
        jTextPaneDateFormat.setParagraphAttributes(attr2, false);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font fontList[] = env.getAllFonts();
        for (int i = 0; i < fontList.length; i++) {
            jComboBox1.addItem(fontList[i].getFontName());
        }

    }

    private void updateValue() {
        jCheckBoxBold.setSelected(false);
        jCheckBoxItalic.setSelected(false);
        String fontName = font.getFontName();
        boolean bold = font.isBold();
        boolean italic = font.isItalic();
        int size = font.getSize();
        jComboBox1.setSelectedItem(fontName);
        if (bold) {
            jCheckBoxBold.setSelected(true);
        }
        if (italic) {
            jCheckBoxItalic.setSelected(true);
        }
        jTextSize.setText(new Integer(size).toString());
    }

    void jButton1_actionPerformed(ActionEvent e) {
        try {
            int size = Integer.parseInt(jTextSize.getText());
            String fontName = jComboBox1.getSelectedItem().toString();
            boolean bold = jCheckBoxBold.isSelected();
            boolean italic = jCheckBoxItalic.isSelected();

            font = new Font(fontName, (bold ? Font.BOLD : 0) | (italic ? Font.ITALIC : 0), size);
            setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Text Size", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Font getCustomerFont() {
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - getWidth()) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - getHeight()) / 2);

        setVisible(true);
        return font;
    }

    void jButton2_actionPerformed(ActionEvent e) {
        setVisible(false);
    }
}

class FontChooser_jButton1_actionAdapter implements java.awt.event.ActionListener {
    FontChooser adaptee;

    FontChooser_jButton1_actionAdapter(FontChooser adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class FontChooser_jButton2_actionAdapter implements java.awt.event.ActionListener {
    FontChooser adaptee;

    FontChooser_jButton2_actionAdapter(FontChooser adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}
