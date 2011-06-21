package designer;

/**
 * Title:
 * Description:
 * Company:
 * @author
 */



import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class testvpframe extends JFrame {
  Border border1;

  Border border2;
  TitledBorder titledBorder1;
  Border border3;
  TitledBorder titledBorder2;
    JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  CardLayout cardLayout1 = new CardLayout();
  JPanel jPanel3 = new JPanel();
  JButton jButton2 = new JButton();
  JPanel jPanel4 = new JPanel();
    Border border4;
    JButton jButton1 = new JButton();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel5 = new JPanel();
    TitledBorder titledBorder3;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu jMenu1 = new JMenu();
    JMenuItem jMenuItem1 = new JMenuItem();
    JMenuItem jMenuItem2 = new JMenuItem();
    JMenu jMenu2 = new JMenu();
    JMenuItem jMenuItem3 = new JMenuItem();
    JMenuItem jMenuItem4 = new JMenuItem();

  public testvpframe() {
    try {
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }


  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.white,Color.white,new Color(99, 99, 99),new Color(142, 142, 142));
    border2 = BorderFactory.createEmptyBorder();
    titledBorder1 = new TitledBorder(border2,"TTT");
    border3 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
    titledBorder2 = new TitledBorder(border3,"AA");
        border4 = BorderFactory.createEmptyBorder();
        titledBorder3 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(142, 142, 142)),"TEST");
        jPanel1.setLayout(null);
        jPanel1.setLocale(java.util.Locale.getDefault());
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setBounds(new Rectangle(26, 8, 329, 220));
    jPanel2.setLayout(cardLayout1);
    jPanel3.setLayout(null);
    jButton2.setBounds(new Rectangle(23, 26, 134, 30));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 18));
        jButton2.setForeground(Color.red);
    jButton2.setText("jButton2");
    jPanel4.setBorder(border4);
        jPanel4.setBounds(new Rectangle(114, 59, 220, 71));
        jPanel4.setLayout(borderLayout1);
        jButton1.setBorder(BorderFactory.createLoweredBevelBorder());
        jButton1.setPreferredSize(new Dimension(49, 4));
        jButton1.setHorizontalAlignment(SwingConstants.CENTER);
        jButton1.setText("jButton1");
        jButton1.addActionListener(new testvpframe_jButton1_actionAdapter(this));
        jPanel5.setFont(new java.awt.Font("Dialog", 3, 16));
        jPanel5.setForeground(Color.red);
        jPanel5.setBorder(titledBorder3);
        jPanel5.setBounds(new Rectangle(18, 123, 222, 91));
        jMenu1.setText("Action");
        jMenuItem1.setText("Run");
        jMenuItem1.addActionListener(new testvpframe_jMenuItem1_actionAdapter(this));
        jMenuItem2.setText("Exit");
        jMenu2.setText("Data");
        jMenuItem3.setText("Save");
        jMenuItem4.setText("Load");
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel2, null);
    jPanel2.add(jPanel3, "jPanel3");
    jPanel3.add(jButton2, null);
    jPanel3.add(jPanel4, null);
        jPanel4.add(jButton1, BorderLayout.NORTH);
        jPanel3.add(jPanel5, null);
        jMenuBar1.add(jMenu1);
        jMenuBar1.add(jMenu2);
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenu2.add(jMenuItem3);
        jMenu2.add(jMenuItem4);
  }

    void jButton1_actionPerformed(ActionEvent e) {

    }

    void jMenuItem1_actionPerformed(ActionEvent e)
    {

    }

}

class testvpframe_jButton1_actionAdapter implements java.awt.event.ActionListener {
  testvpframe adaptee;

  testvpframe_jButton1_actionAdapter(testvpframe adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class testvpframe_jMenuItem1_actionAdapter implements java.awt.event.ActionListener
{
    testvpframe adaptee;

    testvpframe_jMenuItem1_actionAdapter(testvpframe adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.jMenuItem1_actionPerformed(e);
    }
}
