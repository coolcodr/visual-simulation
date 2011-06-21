package print;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.*;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */

public class DialogHeaderFooter extends JDialog {
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

    private PCPageNumber pcPageNumber;
    private PCPageNumber pcDateTime;

    private JTextPane jTextPaneFormat = new JTextPane();
    private JTabbedPane jTabbedPane1 = new JTabbedPane();
    private JCheckBox jCheck2 = new JCheckBox();
    private JCheckBox jCheck1 = new JCheckBox();
    private JTextField jTextSize = new JTextField();
    private JRadioButton jRadioButtonHeader = new JRadioButton();
    private JRadioButton jRadioButtonLeft = new JRadioButton();
    private JPanel jPanel5 = new JPanel();
    private JPanel jPanel4 = new JPanel();
    private JPanel jPanel3 = new JPanel();
    private JPanel jPanel2 = new JPanel();
    private JPanel jPanel1 = new JPanel();
    private JRadioButton jRadioButtonCenter = new JRadioButton();
    private JCheckBox jCheckBoxUnderline = new JCheckBox();
    private JCheckBox jCheckBoxItalic = new JCheckBox();
    private JTextField jText2 = new JTextField();
    private JCheckBox jCheckBoxBold = new JCheckBox();
    private JTextField jText1 = new JTextField();
    private JLabel jLabel8 = new JLabel();
    private JLabel jLabel7 = new JLabel();
    private JLabel jLabel6 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel1 = new JLabel();
    private JRadioButton jRadioButtonFooter = new JRadioButton();
    private BorderLayout borderLayout1 = new BorderLayout();
    private JRadioButton jRadioButtonRight = new JRadioButton();
    private JComboBox jComboBox1 = new JComboBox();
    private JCheckBox jCheck4 = new JCheckBox();
    private JCheckBox jCheck3 = new JCheckBox();
    private JTextField jTextSize1 = new JTextField();
    private JRadioButton jRadioButtonHeader1 = new JRadioButton();
    private JRadioButton jRadioButtonLeft1 = new JRadioButton();
    private JPanel jPanel7 = new JPanel();
    private JPanel jPanel8 = new JPanel();
    private JPanel jPanel9 = new JPanel();
    private JPanel jPanel10 = new JPanel();
    private JPanel jPanel11 = new JPanel();
    private JRadioButton jRadioButtonCenter1 = new JRadioButton();
    private JCheckBox jCheckBoxUnderline1 = new JCheckBox();
    private JCheckBox jCheckBoxItalic1 = new JCheckBox();
    private JCheckBox jCheckBoxBold1 = new JCheckBox();
    private JLabel jLabel9 = new JLabel();
    private JLabel jLabel10 = new JLabel();
    private JLabel jLabel11 = new JLabel();
    private JLabel jLabel12 = new JLabel();
    private JLabel jLabel13 = new JLabel();
    private JLabel jLabel14 = new JLabel();
    private JRadioButton jRadioButtonFooter1 = new JRadioButton();
    private BorderLayout borderLayout2 = new BorderLayout();
    private JRadioButton jRadioButtonRight1 = new JRadioButton();
    private JComboBox jComboBox11 = new JComboBox();
    private JComboBox jComboBox3 = new JComboBox();
    private JComboBox jComboBox4 = new JComboBox();

    private String[][] dateTimeFormat;
    private JTextPane jTextPaneDateFormat = new JTextPane();
    private ButtonGroup buttonGroup11 = new ButtonGroup();
    private ButtonGroup buttonGroup21 = new ButtonGroup();

    public DialogHeaderFooter(JFrame owner)
    {
        this( owner, true );
    }

    public DialogHeaderFooter( JFrame owner, boolean modal )
    {
        super( owner, modal );
        jbInit();
    }
    private void jbInit()
    {
        this.setBounds(150,150,400,370);
		this.setTitle("Set Page Number");
		this.setResizable(false);

		//Container contentPane = this.getContentPane();
 		//contentPane.setLayout(null);

        border1 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        titledBorder1 = new TitledBorder(border1,"Page Number Format");
        border2 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        titledBorder2 = new TitledBorder(border2,"Font Format");
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
        jButtonOK.addActionListener(new DialogPageNumber_jButtonOK_actionAdapter(this));
        jButtonCancel.setBounds(new Rectangle(192, 0, 77, 29));
        jButtonCancel.setFont(new java.awt.Font("Dialog", 0, 12));
        jButtonCancel.setPreferredSize(new Dimension(73, 29));
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new DialogPageNumber_jButtonCancel_actionAdapter(this));
        //this.getContentPane().setLayout(null);
        panel1.setPreferredSize(new Dimension(300, 200));
        panel1.setBounds(new Rectangle(10, 10, 567, 503));
        jTextPaneFormat.setVisible(false);
        jTextPaneFormat.setBounds(new Rectangle(6, 1, 90, 28));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 0, 12));
        jTabbedPane1.setBounds(new Rectangle(9, 5, 373, 295));
        jCheck2.addActionListener(new DialogPageNumber_jCheck2_actionAdapter(this));
        jCheck2.setSelected(true);
        jCheck2.setBounds(new Rectangle(234, 6, 21, 21));
        jCheck2.addActionListener(new DialogPageNumber_jCheck2_actionAdapter(this));
        jCheck1.addActionListener(new DialogPageNumber_jCheck1_actionAdapter(this));
        jCheck1.setSelected(true);
        jCheck1.setBounds(new Rectangle(72, 6, 21, 21));
        jCheck1.addActionListener(new DialogPageNumber_jCheck1_actionAdapter(this));
        jTextSize.setBorder(BorderFactory.createEtchedBorder());
        jTextSize.setPreferredSize(new Dimension(63, 234));
        jTextSize.setBounds(new Rectangle(127, 101, 90, 24));
        jTextSize.setText("12");
        jRadioButtonHeader.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonHeader.setText("Header     ");
        jRadioButtonHeader.setBounds(new Rectangle(103, 70, 99, 27));
        jRadioButtonLeft.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonLeft.setText("Left  ");
        jRadioButtonLeft.setBounds(new Rectangle(97, 5, 53, 27));
        jPanel5.setBounds(new Rectangle(4, 18, 295, 36));
        jPanel5.setLayout(null);
        jPanel4.setForeground(new Color(102, 102, 154));
        jPanel4.setBorder(titledBorder2);
        jPanel4.setBounds(new Rectangle(19, 107, 333, 144));
        jPanel4.setLayout(null);
        jPanel3.setPreferredSize(new Dimension(80, 80));
        jPanel3.setBounds(new Rectangle(229, 52, 89, 80));
        jPanel3.setLayout(borderLayout1);
        jPanel2.setPreferredSize(new Dimension(340, 40));
        jPanel2.setBounds(new Rectangle(16, 27, 340, 40));
        jPanel2.setLayout(null);
        jPanel1.setForeground(new Color(102, 102, 154));
        jPanel1.setBorder(titledBorder1);
        jPanel1.setLayout(null);
        jRadioButtonCenter.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonCenter.setText("Center");
        jRadioButtonCenter.setBounds(new Rectangle(155, 5, 63, 27));
        jCheckBoxUnderline.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxUnderline.setText("Underline");
        jCheckBoxItalic.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxItalic.setText("Italic");
        jText2.setMinimumSize(new Dimension(4, 20));
        jText2.setPreferredSize(new Dimension(30, 24));
        jText2.setText("of");
        jText2.setBounds(new Rectangle(260, 5, 30, 24));
        jCheckBoxBold.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxBold.setText("Bold");
        jText1.setMinimumSize(new Dimension(4, 20));
        jText1.setPreferredSize(new Dimension(50, 24));
        jText1.setText("Page");
        jText1.setBounds(new Rectangle(98, 5, 50, 24));
        jLabel8.setForeground(Color.black);
        jLabel8.setText("Font size:");
        jLabel8.setBounds(new Rectangle(63, 105, 74, 16));
        jLabel7.setForeground(Color.black);
        jLabel7.setText("Font:");
        jLabel7.setBounds(new Rectangle(21, 57, 43, 26));
        jLabel6.setForeground(Color.black);
        jLabel6.setText("Alignment:");
        jLabel6.setBounds(new Rectangle(17, 9, 81, 19));
        jLabel5.setForeground(Color.black);
        jLabel5.setText("Location:     ");
        jLabel5.setBounds(new Rectangle(25, 75, 75, 19));
        jLabel4.setForeground(Color.black);
        jLabel4.setToolTipText("");
        jLabel4.setText("Format:");
        jLabel4.setBounds(new Rectangle(9, 7, 60, 19));
        jLabel3.setBounds(new Rectangle(211, 7, 18, 19));
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setForeground(Color.black);
        jLabel2.setText("(Total)");
        jLabel2.setBounds(new Rectangle(295, 7, 51, 19));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setForeground(Color.black);
        jLabel1.setText("(Number)");
        jLabel1.setBounds(new Rectangle(153, 7, 74, 19));
        jRadioButtonFooter.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonFooter.setSelected(true);
        jRadioButtonFooter.setText("Footer");
        jRadioButtonFooter.setBounds(new Rectangle(208, 70, 87, 27));
        jRadioButtonRight.setText("Right");
        jRadioButtonRight.setBounds(new Rectangle(223, 5, 54, 27));
        jRadioButtonRight.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonRight.setSelected(true);
        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox1.setMaximumSize(new Dimension(32767, 24));
        jComboBox1.setMinimumSize(new Dimension(126, 24));
        jComboBox1.setPreferredSize(new Dimension(130, 24));
        jComboBox1.setBounds(new Rectangle(63, 59, 154, 24));
        jCheck4.setBounds(new Rectangle(210, 6, 19, 21));
        jCheck4.addActionListener(new DialogPageNumber_jCheck4_actionAdapter(this));
        jCheck4.addActionListener(new DialogPageNumber_jCheck4_actionAdapter(this));
        jCheck3.setBounds(new Rectangle(72, 6, 21, 21));
        jCheck3.addActionListener(new DialogPageNumber_jCheck3_actionAdapter(this));
        jCheck3.setSelected(true);
        jCheck3.addActionListener(new DialogPageNumber_jCheck3_actionAdapter(this));
        jTextSize1.setText("12");
        jTextSize1.setBounds(new Rectangle(127, 101, 90, 24));
        jTextSize1.setPreferredSize(new Dimension(63, 234));
        jTextSize1.setBorder(BorderFactory.createEtchedBorder());
        jRadioButtonHeader1.setBounds(new Rectangle(103, 70, 99, 27));
        jRadioButtonHeader1.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonHeader1.setText("Header     ");
        jRadioButtonLeft1.setBounds(new Rectangle(97, 5, 53, 27));
        jRadioButtonLeft1.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonLeft1.setSelected(true);
        jRadioButtonLeft1.setText("Left  ");
        jPanel7.setLayout(null);
        jPanel7.setBounds(new Rectangle(4, 18, 295, 36));
        jPanel8.setForeground(new Color(102, 102, 154));
        jPanel8.setBorder(titledBorder2);
        jPanel8.setBounds(new Rectangle(19, 107, 333, 144));
        jPanel8.setLayout(null);
        jPanel9.setPreferredSize(new Dimension(80, 80));
        jPanel9.setBounds(new Rectangle(229, 52, 89, 80));
        jPanel9.setLayout(borderLayout2);
        jPanel10.setPreferredSize(new Dimension(340, 40));
        jPanel10.setBounds(new Rectangle(16, 27, 340, 40));
        jPanel10.setLayout(null);
        jPanel11.setForeground(new Color(102, 102, 154));
        jPanel11.setBorder(titledBorder1);
        jPanel11.setLayout(null);
        jRadioButtonCenter1.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonCenter1.setText("Center");
        jRadioButtonCenter1.setBounds(new Rectangle(155, 5, 63, 27));
        jCheckBoxUnderline1.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxUnderline1.setText("Underline");
        jCheckBoxItalic1.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxItalic1.setText("Italic");
        jCheckBoxBold1.setFont(new java.awt.Font("Dialog", 0, 12));
        jCheckBoxBold1.setText("Bold");
        jLabel9.setForeground(Color.black);
        jLabel9.setText("Font size:");
        jLabel9.setBounds(new Rectangle(63, 105, 74, 16));
        jLabel10.setForeground(Color.black);
        jLabel10.setText("Font:");
        jLabel10.setBounds(new Rectangle(21, 57, 43, 26));
        jLabel11.setForeground(Color.black);
        jLabel11.setText("Alignment:");
        jLabel11.setBounds(new Rectangle(17, 9, 81, 19));
        jLabel12.setForeground(Color.black);
        jLabel12.setText("Location:     ");
        jLabel12.setBounds(new Rectangle(25, 75, 75, 19));
        jLabel13.setForeground(Color.black);
        jLabel13.setToolTipText("");
        jLabel13.setText("Format:");
        jLabel13.setBounds(new Rectangle(9, 7, 60, 19));
        jLabel14.setBounds(new Rectangle(197, 6, 20, 19));
        jRadioButtonFooter1.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonFooter1.setSelected(true);
        jRadioButtonFooter1.setText("Footer");
        jRadioButtonFooter1.setBounds(new Rectangle(208, 70, 87, 27));
        jRadioButtonRight1.setText("Right");
        jRadioButtonRight1.setBounds(new Rectangle(223, 5, 54, 27));
        jRadioButtonRight1.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox11.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox11.setMaximumSize(new Dimension(32767, 24));
        jComboBox11.setMinimumSize(new Dimension(126, 24));
        jComboBox11.setPreferredSize(new Dimension(130, 24));
        jComboBox11.setBounds(new Rectangle(63, 59, 154, 24));
        jComboBox3.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox3.setBounds(new Rectangle(93, 5, 110, 24));
        jComboBox4.setFont(new java.awt.Font("Dialog", 0, 12));
        jComboBox4.setBounds(new Rectangle(230, 5, 110, 24));
        jTextPaneDateFormat.setVisible(false);
        jTextPaneDateFormat.setBounds(new Rectangle(286, 2, 68, 24));
        getContentPane().add(panel1, null);
        jPanel6.add(jButtonCancel, null);
        jPanel6.add(jButtonOK, null);
        jPanel6.add(jTextPaneFormat, null);
        jPanel6.add(jTextPaneDateFormat, null);
        panel1.add(jTabbedPane1, null);
        panel1.add(jPanel6, null);
        jTabbedPane1.add(jPanel1,  "Page Number");
        jPanel1.add(jRadioButtonFooter, null);
        jPanel1.add(jRadioButtonHeader, null);
        jPanel1.add(jLabel5, null);
        jPanel1.add(jPanel4, null);
        jPanel4.add(jLabel7, null);
        jPanel4.add(jComboBox1, null);
        jPanel4.add(jTextSize, null);
        jPanel4.add(jLabel8, null);
        jPanel4.add(jPanel5, null);
        jPanel5.add(jLabel6, null);
        jPanel5.add(jRadioButtonLeft, null);
        jPanel5.add(jRadioButtonCenter, null);
        jPanel5.add(jRadioButtonRight, null);
        jPanel4.add(jPanel3, null);
        jPanel3.add(jCheckBoxUnderline, BorderLayout.SOUTH);
        jPanel3.add(jCheckBoxItalic, BorderLayout.CENTER);
        jPanel3.add(jCheckBoxBold, BorderLayout.NORTH);
        jPanel1.add(jPanel2, null);
        jPanel2.add(jLabel4, null);
        jPanel2.add(jCheck1, null);
        jPanel2.add(jText1, null);
        jPanel2.add(jLabel1, null);
        jPanel2.add(jLabel3, null);
        jPanel2.add(jCheck2, null);
        jPanel2.add(jText2, null);
        jPanel2.add(jLabel2, null);
        jTabbedPane1.add(jPanel11,   "Date Time");
        jPanel11.add(jRadioButtonFooter1, null);
        jPanel11.add(jRadioButtonHeader1, null);
        jPanel11.add(jLabel12, null);
        jPanel11.add(jPanel8, null);
        jPanel8.add(jLabel10, null);
        jPanel8.add(jComboBox11, null);
        jPanel8.add(jTextSize1, null);
        jPanel8.add(jLabel9, null);
        jPanel8.add(jPanel7, null);
        jPanel7.add(jLabel11, null);
        jPanel7.add(jRadioButtonLeft1, null);
        jPanel7.add(jRadioButtonCenter1, null);
        jPanel7.add(jRadioButtonRight1, null);
        jPanel8.add(jPanel9, null);
        jPanel9.add(jCheckBoxUnderline1, BorderLayout.SOUTH);
        jPanel9.add(jCheckBoxItalic1, BorderLayout.CENTER);
        jPanel9.add(jCheckBoxBold1, BorderLayout.NORTH);
        jPanel11.add(jPanel10, null);
        jPanel10.add(jLabel13, null);
        jPanel10.add(jCheck3, null);
        jPanel10.add(jCheck4, null);
        jPanel10.add(jComboBox3, null);
        jPanel10.add(jComboBox4, null);
        jPanel10.add(jLabel14, null);

        pcPageNumber = new PCPageNumber();
        pcDateTime = new PCPageNumber();

        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT );
        jTextPaneFormat.setParagraphAttributes(attr, false);
        pcPageNumber.addPageNumber(jTextPaneFormat);

        MutableAttributeSet attr2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr2, StyleConstants.ALIGN_RIGHT );
        jTextPaneDateFormat.setParagraphAttributes(attr2, false);
        pcDateTime.addPageNumber(jTextPaneDateFormat);

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font fontList[] = env.getAllFonts();
        for (int i = 0; i < fontList.length; i++)
        {
            jComboBox1.addItem(fontList[i].getFontName());
            jComboBox11.addItem(fontList[i].getFontName());
        }
        /*
        for ( int i = 0 ; i < jComboBox1.getItemCount() ; i++ )
        {
            jComboBox1.setSelectedIndex(0);
            if ( (jComboBox1.getItemAt(i).toString().compareTo("dialog.plain") == 0 ))
            {
                jComboBox1.setSelectedIndex(i);
                jComboBox11.setSelectedIndex(i);
                break;
            }
        }*/
        jComboBox1.setSelectedItem("dialog.plain");//new JTextField().getFont().getFontName());
        jComboBox11.setSelectedItem("dialog.plain");//new JTextField().getFont().getFontName());

        String [][] tempString = { {
            "dd-M", "dd-MMM-yy",
            "dd/MM/yyyy", "dd/MM/yyyy (E)",
            "dd MMM, yyyy", "EEEEEE dd MMM, yyyy" },{
            "HH:mm", "HH:mm:ss",
            "hh:mm a", "hh:mm:ss a",
            "hh:mm a z", "hh:mm:ss a z"
            }};

        dateTimeFormat = tempString;

        setTimeCombo();

        jComboBox3.setSelectedIndex(2);
        jComboBox4.setSelectedIndex(0);

        updatePCPageNumber();
        updatePCDateTime();

        buttonGroupAlignment.add(jRadioButtonLeft);
        buttonGroupAlignment.add(jRadioButtonCenter);
        buttonGroupAlignment.add(jRadioButtonRight);
        buttonGroupLocation.add(jRadioButtonHeader);
        buttonGroupLocation.add(jRadioButtonFooter);
        buttonGroup11.add(jRadioButtonHeader1);
        buttonGroup11.add(jRadioButtonFooter1);
        buttonGroup21.add(jRadioButtonLeft1);
        buttonGroup21.add(jRadioButtonCenter1);
        buttonGroup21.add(jRadioButtonRight1);
    }
    public void updatePCPageNumber()
    {
        //Now incomplete
        MutableAttributeSet attr = new SimpleAttributeSet();

        if ( jRadioButtonLeft.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );
        else if ( jRadioButtonCenter.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_CENTER );
        else if ( jRadioButtonRight.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_RIGHT );

        jTextPaneFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        attr = new SimpleAttributeSet();
        StyleConstants.setFontFamily( attr, (String)jComboBox1.getSelectedItem());
        jTextPaneFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        attr = new SimpleAttributeSet();
        StyleConstants.setFontSize( attr, Integer.parseInt(jTextSize.getText()));
        jTextPaneFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        StyleConstants.setBold(attr, jCheckBoxBold.isSelected());
        StyleConstants.setItalic(attr, jCheckBoxItalic.isSelected());
        StyleConstants.setUnderline(attr, jCheckBoxUnderline.isSelected());
        jTextPaneFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        pcPageNumber.addPageNumber(jTextPaneFormat);

        pcPageNumber.setFirstString(jText1.getText());
        pcPageNumber.setSecondString(jText2.getText());
        if ( jRadioButtonHeader.isSelected() )
            pcPageNumber.setPageNumberLocation(PCPageNumber.AT_HEADER);
        else if ( jRadioButtonFooter.isSelected() )
            pcPageNumber.setPageNumberLocation(PCPageNumber.AT_FOOTER);
        pcPageNumber.setHaveTotalNumber(jCheck2.isSelected());

        if ( !(jCheck1.isSelected()) )
            pcPageNumber.removePageNumber();
    }
    public void updatePCDateTime()
    {
        //Now incomplete
        MutableAttributeSet attr = new SimpleAttributeSet();

        if ( jRadioButtonLeft1.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );
        else if ( jRadioButtonCenter1.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_CENTER );
        else if ( jRadioButtonRight1.isSelected() )
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_RIGHT );

        jTextPaneDateFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        attr = new SimpleAttributeSet();
        StyleConstants.setFontFamily( attr, (String)jComboBox11.getSelectedItem());
        jTextPaneDateFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        attr = new SimpleAttributeSet();
        StyleConstants.setFontSize( attr, Integer.parseInt(jTextSize1.getText()));
        jTextPaneDateFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        StyleConstants.setBold(attr, jCheckBoxBold1.isSelected());
        StyleConstants.setItalic(attr, jCheckBoxItalic1.isSelected());
        StyleConstants.setUnderline(attr, jCheckBoxUnderline1.isSelected());
        jTextPaneDateFormat.getStyledDocument().setParagraphAttributes(0, 1, attr, false);

        pcDateTime.addPageNumber(jTextPaneDateFormat);

        //pcPageNumber.setFirstString(jText1.getText());
        //pcPageNumber.setSecondString(jText2.getText());
        if ( jRadioButtonHeader1.isSelected() )
            pcDateTime.setPageNumberLocation(PCPageNumber.AT_HEADER);
        else if ( jRadioButtonFooter1.isSelected() )
            pcDateTime.setPageNumberLocation(PCPageNumber.AT_FOOTER);
        //pcPageNumber.setHaveTotalNumber(jCheck2.isSelected());

        if ( !(jCheck3.isSelected()) && ! (jCheck4.isSelected())  )
            pcDateTime.removePageNumber();
        else
        {
            String[] tempString = {dateTimeFormat[0][jComboBox3.getSelectedIndex()],dateTimeFormat[1][jComboBox4.getSelectedIndex()]};
            if ( !(jCheck3.isSelected()) )
                tempString[0] = "None";
            if ( !(jCheck4.isSelected()) )
                tempString[1] = "None";
            pcDateTime.setDateTimeFormat(tempString);
        }
    }
    public void setPageNumberFormat ( PCPageNumber ppn )
    {
        this.pcPageNumber = ppn;
    }
    public void setDateTimeFormat ( PCPageNumber ppn )
    {
        this.pcDateTime = ppn;
    }
    public PCPageNumber getPageNumberFormat ()
    {
        updatePCPageNumber();
        return pcPageNumber;
    }
    public PCPageNumber getDateTimeFormat ()
    {
        updatePCDateTime();
        return pcDateTime;
    }
    void jCheck1_actionPerformed(ActionEvent e)
    {
        if ( !jCheck1.isSelected() )
        {
            if ( jCheck2.isSelected() )
            {
                jCheck2.setSelected(false);
                jText2.setEnabled(jCheck2.isSelected());
                jLabel2.setEnabled(jCheck2.isSelected());
            }
        }
        jText1.setEnabled(jCheck1.isSelected());
        jLabel1.setEnabled(jCheck1.isSelected());
    }

    void jCheck2_actionPerformed(ActionEvent e)
    {
        jText2.setEnabled(jCheck2.isSelected());
        jLabel2.setEnabled(jCheck2.isSelected());
    }

    public void showInitial()
    {
        setTimeCombo();
        setVisible(true);
    }

    void jButtonCancel_actionPerformed(ActionEvent e)
    {
        setVisible(false);
    }

    void jButtonOK_actionPerformed(ActionEvent e)
    {
        updatePCDateTime();
        updatePCPageNumber();
        setVisible(false);
    }

    void jCheck3_actionPerformed(ActionEvent e) {

    }

    void jCheck4_actionPerformed(ActionEvent e) {

    }
    private void setTimeCombo()
    {
        int selected3 = jComboBox3.getSelectedIndex();
        int selected4 = jComboBox4.getSelectedIndex();
        jComboBox3.removeAllItems();
        jComboBox4.removeAllItems();
        for ( int i = 0 ; i < dateTimeFormat[0].length ; i ++ )
        {
            java.text.DateFormat formatter = new java.text.SimpleDateFormat(dateTimeFormat[0][i]);
            jComboBox3.addItem( formatter.format(java.util.Calendar.getInstance().getTime()));
        }
        for ( int i = 0 ; i < dateTimeFormat[1].length ; i ++ )
        {
            java.text.DateFormat formatter = new java.text.SimpleDateFormat(dateTimeFormat[1][i]);
            jComboBox4.addItem( formatter.format(java.util.Calendar.getInstance().getTime()));
        }
        jComboBox3.setSelectedIndex(selected3);
        jComboBox4.setSelectedIndex(selected4);
    }
}

class DialogPageNumber_jButtonCancel_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jButtonCancel_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonCancel_actionPerformed(e);
    }
}

class DialogPageNumber_jButtonOK_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jButtonOK_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jButtonOK_actionPerformed(e);
    }
}

class DialogPageNumber_jCheck1_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jCheck1_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jCheck1_actionPerformed(e);
    }
}

class DialogPageNumber_jCheck2_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jCheck2_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jCheck2_actionPerformed(e);
    }
}

class DialogPageNumber_jCheck3_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jCheck3_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jCheck3_actionPerformed(e);
    }
}

class DialogPageNumber_jCheck4_actionAdapter implements java.awt.event.ActionListener {
    private DialogHeaderFooter adaptee;

    DialogPageNumber_jCheck4_actionAdapter(DialogHeaderFooter adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jCheck4_actionPerformed(e);
    }
}