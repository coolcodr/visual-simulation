package print;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.print.*;
import java.awt.event.*;
/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */

public class DialogMultiPage extends JDialog {
    public static final int BOARDER_ON = 0 ;
    public static final int BOARDER_OFF = 1;

    public static final int DIRECTION_DOWN = 0;
    public static final int DIRECTION_ACCROSS = 1;

    private JPanel panel1 = new JPanel();
    private JPanel jPanel1 = new JPanel();
    private Border border1;
    private TitledBorder titledBorder1;
    private JRadioButton jRadioButton8 = new JRadioButton();
    private JRadioButton jRadioButton4 = new JRadioButton();
    private JRadioButton jRadioButton2 = new JRadioButton();
    private JRadioButton jRadioButton1 = new JRadioButton();
    private JCheckBox jCheckBox1 = new JCheckBox();
    private JPanel jPanel2 = new JPanel();
    private Border border2;
    private TitledBorder titledBorder2;
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JPanel jPanel3 = new JPanel();
    private Border border3;
    private TitledBorder titledBorder3;
    private JRadioButton jRadioButtonOff = new JRadioButton();
    private JRadioButton jRadioButtonOn = new JRadioButton();
    private JPanel jPanel4 = new JPanel();
    private Border border4;
    private TitledBorder titledBorder4;
    private JRadioButton jRadioButtonDown = new JRadioButton();
    private JRadioButton jRadioButtonAcross = new JRadioButton();
    private JList jList1 = new JList();
    private JList jList2 = new JList();
    private JButton jButton1 = new JButton();
    private JButton jButton2 = new JButton();
    private ButtonGroup buttonGroupLayout = new ButtonGroup();
    private ButtonGroup buttonGroupBorder = new ButtonGroup();
    private ButtonGroup buttonGroupOrdering = new ButtonGroup();
    private JComboBox jComboBox1 = new JComboBox();

    private PageFormat pageFormat ;

    private int ver;
    private int hor;
    private int orientation;
    private int direction;
    private int boarder;
    private JScrollBar jScrollBar1 = new JScrollBar();
    private JTextField jTextField1 = new JTextField();
    private JScrollBar jScrollBar2 = new JScrollBar();
    private JTextField jTextField2 = new JTextField();

    public DialogMultiPage(Frame frame) {
        super(frame, true);
        try {
            jbInit();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public DialogMultiPage() {
        this(null);
    }
    private void jbInit() throws Exception {

        this.setBounds(150,150,315,272);
        this.setTitle("Print Editor");

        border1 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        titledBorder1 = new TitledBorder(border1,"Layout");
        border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(142, 142, 142));
        titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"Custom");
        border3 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142)),"Borders");
        border4 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        titledBorder4 = new TitledBorder(border4,"Ordering");
        panel1.setLayout(null);
        jPanel1.setForeground(new Color(102, 102, 153));
        jPanel1.setBorder(titledBorder1);
        jPanel1.setBounds(new Rectangle(10, 10, 290, 136));
        jPanel1.setLayout(null);
        jRadioButton8.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButton8.setFocusPainted(false);
        jRadioButton8.setText("6 up");
        jRadioButton8.setBounds(new Rectangle(22, 97, 50, 25));
        jRadioButton4.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButton4.setFocusPainted(false);
        jRadioButton4.setText("4 up");
        jRadioButton4.setBounds(new Rectangle(22, 72, 50, 25));
        jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButton2.setFocusPainted(false);
        jRadioButton2.setText("2 up");
        jRadioButton2.setBounds(new Rectangle(22, 47, 50, 25));
        jRadioButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButton1.setFocusPainted(false);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("1 up");
        jRadioButton1.setBounds(new Rectangle(22, 22, 50, 25));
        jCheckBox1.setForeground(UIManager.getColor("Label.foreground"));
        jCheckBox1.setFocusPainted(false);
        jCheckBox1.setText("Custom");
        jCheckBox1.setBounds(new Rectangle(94, 18, 67, 17));
        jCheckBox1.addActionListener(new DialogMultiPage_jCheckBox1_actionAdapter(this));
        jPanel2.setEnabled(false);
        jPanel2.setBorder(titledBorder2);
        jPanel2.setBounds(new Rectangle(86, 16, 192, 107));
        jPanel2.setLayout(null);
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setForeground(Color.black);
        jLabel1.setText("Horizontal pages:");
        jLabel1.setBounds(new Rectangle(22, 22, 120, 25));
        jLabel2.setBounds(new Rectangle(22, 47, 120, 25));
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setForeground(Color.black);
        jLabel2.setText("Vertical pages:");
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setForeground(Color.black);
        jLabel3.setText("Orientation:");
        jLabel3.setBounds(new Rectangle(22, 72, 100, 25));
        jPanel3.setForeground(new Color(102, 102, 153));
        jPanel3.setBorder(titledBorder3);
        jPanel3.setBounds(new Rectangle(10, 150, 89, 84));
        jPanel3.setLayout(null);
        jRadioButtonOff.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonOff.setFocusPainted(false);
        jRadioButtonOff.setSelected(true);
        jRadioButtonOff.setText("Off");
        jRadioButtonOff.setBounds(new Rectangle(22, 47, 50, 25));
        jRadioButtonOn.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonOn.setFocusPainted(false);
        jRadioButtonOn.setText("On");
        jRadioButtonOn.setBounds(new Rectangle(22, 22, 50, 25));
        jPanel4.setForeground(new Color(102, 102, 153));
        jPanel4.setBorder(titledBorder4);
        jPanel4.setBounds(new Rectangle(105, 150, 108, 84));
        jPanel4.setLayout(null);
        jRadioButtonDown.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonDown.setFocusPainted(false);
        jRadioButtonDown.setText("Down");
        jRadioButtonDown.setBounds(new Rectangle(22, 47, 72, 25));
        jRadioButtonAcross.setFont(new java.awt.Font("Dialog", 0, 12));
        jRadioButtonAcross.setFocusPainted(false);
        jRadioButtonAcross.setSelected(true);
        jRadioButtonAcross.setText("Across");
        jRadioButtonAcross.setBounds(new Rectangle(22, 22, 74, 25));
        //jList1.setBounds(new Rectangle(130, 25, 50, 17));
        jButton1.setBounds(new Rectangle(222, 178, 75, 25));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton1.setFocusPainted(false);
        jButton1.setText("OK");
        jButton1.addActionListener(new DialogMultiPage_jButton1_actionAdapter(this));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new DialogMultiPage_jButton2_actionAdapter(this));
        jButton2.setBounds(new Rectangle(222, 208, 75, 25));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton2.setFocusPainted(false);
        panel1.setBounds(new Rectangle(0, 0, 314, 297));
        jComboBox1.setEnabled(false);
        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 10));
        jComboBox1.setBounds(new Rectangle(103, 75, 77, 20));

        jScrollBar1.setBounds(new Rectangle(160, 25, 20, 21));
        jScrollBar1.addAdjustmentListener(new DialogMultiPage_jScrollBar1_adjustmentAdapter(this));
        jTextField1.setEditable(false);
        jTextField1.setText("1");
        jTextField1.setBounds(new Rectangle(140, 25, 20, 20));
        jScrollBar2.setBounds(new Rectangle(160, 50, 20, 21));
        jScrollBar2.addAdjustmentListener(new DialogMultiPage_jScrollBar2_adjustmentAdapter(this));
        jTextField2.setEditable(false);
        jTextField2.setText("1");
        jTextField2.setBounds(new Rectangle(140, 50, 20, 20));
        getContentPane().add(panel1, null);
        jPanel1.add(jRadioButton2, null);
        jPanel1.add(jRadioButton4, null);
        jPanel1.add(jRadioButton8, null);
        jPanel2.add(jLabel2, null);
        jPanel2.add(jLabel3, null);
        jPanel2.add(jLabel1, null);
        jPanel2.add(jList1, null);
        jPanel1.add(jCheckBox1, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jRadioButton1, null);
        panel1.add(jPanel3, null);
        jPanel3.add(jRadioButtonOn, null);
        jPanel3.add(jRadioButtonOff, null);
        panel1.add(jPanel4, null);
        jPanel4.add(jRadioButtonAcross, null);
        jPanel4.add(jRadioButtonDown, null);
        panel1.add(jButton1, null);
        panel1.add(jButton2, null);
        panel1.add(jPanel1, null);
        buttonGroupLayout.add(jRadioButton1);
        buttonGroupLayout.add(jRadioButton2);
        buttonGroupLayout.add(jRadioButton4);
        buttonGroupLayout.add(jRadioButton8);
        buttonGroupBorder.add(jRadioButtonOn);
        buttonGroupBorder.add(jRadioButtonOff);
        buttonGroupOrdering.add(jRadioButtonAcross);
        buttonGroupOrdering.add(jRadioButtonDown);

        String[] num = new String[6];
        for ( int i = 0 ; i < 6 ; i ++ )
            num[i] = Integer.toString(i + 1);

        jList1.setListData(num);
        jList1.setVisibleRowCount(1);
        jList1.setFont(new java.awt.Font("Dialog", 0, 10));

        jList2.setListData(num);
        jList2.setVisibleRowCount(1);
        jList2.setFont(new java.awt.Font("Dialog", 0, 10));

        jPanel2.add(jComboBox1, null);
        jPanel2.add(jTextField1, null);
        jPanel2.add(jScrollBar1, null);
        jPanel2.add(jScrollBar2, null);
        jPanel2.add(jTextField2, null);

        jComboBox1.addItem("Portrait");
        jComboBox1.addItem("Landscape");

        jList1.setEnabled(false);
        jList2.setEnabled(false);

        ver = 1; hor = 1;
        orientation = PageFormat.PORTRAIT;
        boarder = BOARDER_OFF;
        direction = DIRECTION_ACCROSS;
    }
    public void showDialog ( PageFormat pf1 )
    {
        pageFormat = pf1;
        this.setVisible(true);
    }
    private void updateMultiPageFormat()
    {
        if ( jRadioButton1.isSelected() ){
            ver = 1; hor = 1;
            orientation = pageFormat.getOrientation();
        }
        else if ( jRadioButton2.isSelected() ){
            if ( pageFormat.getOrientation() == PageFormat.PORTRAIT){
                orientation = PageFormat.LANDSCAPE;
                ver = 1;
                hor = 2;}
            else{
                orientation = PageFormat.PORTRAIT;
                ver = 2;
                hor = 1;}
        }
        else if ( jRadioButton4.isSelected() ){
            if ( pageFormat.getOrientation() == PageFormat.PORTRAIT){
                orientation = PageFormat.PORTRAIT;
                ver = 2;
                hor = 2;}
            else{
                orientation = PageFormat.LANDSCAPE;
                ver = 2;
                hor = 2;}
        }
        else if ( jRadioButton8.isSelected() ){
            if ( pageFormat.getOrientation() == PageFormat.PORTRAIT){
                orientation = PageFormat.PORTRAIT;
                ver = 3;
                hor = 2;}
            else{
                orientation = PageFormat.LANDSCAPE;
                ver = 2;
                hor = 3;}
        }
        if ( jCheckBox1.isSelected() )
        {
            if ( jComboBox1.getSelectedIndex() == 0 )
                orientation = PageFormat.PORTRAIT;
            else
                orientation = PageFormat.LANDSCAPE;
            ver = Integer.parseInt(jTextField2.getText());
            hor = Integer.parseInt(jTextField1.getText());
            //if ( ver * hor == 1 )
                //orientation = pageFormat.getOrientation();
        }
        if ( jRadioButtonOn.isSelected() )
            boarder = BOARDER_ON;
        else
            boarder = BOARDER_OFF;
        if ( jRadioButtonDown.isSelected() )
            direction = DIRECTION_DOWN;
        else
            direction = DIRECTION_ACCROSS;
    }
    public int[] getMultiPageFormat()
    {
        int[] i = new int[5];
        i[0] = hor;
        i[1] = ver;
        i[2] = orientation;
        i[3] = direction;
        i[4] = boarder;
        return i;
    }

    void jButton2_actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

    void jButton1_actionPerformed(ActionEvent e) {
        updateMultiPageFormat();
        this.setVisible(false);
    }

    void jCheckBox1_actionPerformed(ActionEvent e) {

            jList1.setEnabled(jCheckBox1.isSelected());
            jList2.setEnabled(jCheckBox1.isSelected());
            jComboBox1.setEnabled(jCheckBox1.isSelected());
            jLabel1.setEnabled(jCheckBox1.isSelected());
            jLabel2.setEnabled(jCheckBox1.isSelected());
            jLabel3.setEnabled(jCheckBox1.isSelected());

            jRadioButton1.setEnabled(!jCheckBox1.isSelected());
            jRadioButton2.setEnabled(!jCheckBox1.isSelected());
            jRadioButton4.setEnabled(!jCheckBox1.isSelected());
            jRadioButton8.setEnabled(!jCheckBox1.isSelected());
    }

    void jScrollBar1_adjustmentValueChanged(AdjustmentEvent e) {
        if ( e.getValue() != 0 )
        jTextField1.setText(Integer.toString(e.getValue()));
    }

    void jScrollBar2_adjustmentValueChanged(AdjustmentEvent e) {
        if ( e.getValue() != 0 )
        jTextField2.setText(Integer.toString(e.getValue()));
    }

}

class DialogMultiPage_jButton2_actionAdapter implements java.awt.event.ActionListener {
    private DialogMultiPage adaptee;

    DialogMultiPage_jButton2_actionAdapter(DialogMultiPage adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jButton2_actionPerformed(e);
    }
}

class DialogMultiPage_jButton1_actionAdapter implements java.awt.event.ActionListener {
    private DialogMultiPage adaptee;

    DialogMultiPage_jButton1_actionAdapter(DialogMultiPage adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jButton1_actionPerformed(e);
    }
}

class DialogMultiPage_jCheckBox1_actionAdapter implements java.awt.event.ActionListener {
    private DialogMultiPage adaptee;

    DialogMultiPage_jCheckBox1_actionAdapter(DialogMultiPage adaptee) {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e) {
        adaptee.jCheckBox1_actionPerformed(e);
    }
}

class DialogMultiPage_jScrollBar1_adjustmentAdapter implements java.awt.event.AdjustmentListener {
    private DialogMultiPage adaptee;

    DialogMultiPage_jScrollBar1_adjustmentAdapter(DialogMultiPage adaptee) {
        this.adaptee = adaptee;
    }
    public void adjustmentValueChanged(AdjustmentEvent e) {
        adaptee.jScrollBar1_adjustmentValueChanged(e);
    }
}

class DialogMultiPage_jScrollBar2_adjustmentAdapter implements java.awt.event.AdjustmentListener {
    private DialogMultiPage adaptee;

    DialogMultiPage_jScrollBar2_adjustmentAdapter(DialogMultiPage adaptee) {
        this.adaptee = adaptee;
    }
    public void adjustmentValueChanged(AdjustmentEvent e) {
        adaptee.jScrollBar2_adjustmentValueChanged(e);
    }
}