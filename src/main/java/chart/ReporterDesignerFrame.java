package chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

public class ReporterDesignerFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -7749484307691256049L;
    JMenuBar jMenuBar1 = new JMenuBar();
    JMenu fileMenu = new JMenu();
    JMenuItem exitMenuItem = new JMenuItem();
    JToolBar jToolBar = new JToolBar();
    JButton jButton1 = new JButton();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JPanel jPanel6 = new JPanel();
    JSplitPane jSplitPane1 = new JSplitPane();
    JTree jTree1 = new JTree();

    public ReporterDesignerFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ReporterDesignerFrame reporterDesignerFrame = new ReporterDesignerFrame();
        reporterDesignerFrame.setVisible(true);
    }

    private void jbInit() throws Exception {
        fileMenu.setText("File");
        exitMenuItem.setText("Exit");
        jButton1.setToolTipText("Open File");
        jButton1.setText("New Chart");
        jPanel3.setBounds(new Rectangle(139, 71, 109, 104));
        jPanel2.setBounds(new Rectangle(17, 17, 102, 82));
        jPanel1.setBackground(Color.white);
        jPanel1.setLayout(null);
        getContentPane().setLayout(borderLayout1);
        jPanel4.setBounds(new Rectangle(139, 71, 109, 104));
        jPanel5.setBounds(new Rectangle(17, 17, 102, 82));
        jPanel6.setLayout(null);
        jPanel6.setBackground(Color.white);
        jMenuBar1.add(fileMenu);
        fileMenu.add(exitMenuItem);
        getContentPane().add(jToolBar, BorderLayout.NORTH);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jPanel3, null);
        getContentPane().add(jSplitPane1, BorderLayout.CENTER);
        jSplitPane1.add(jTree1, JSplitPane.LEFT);
        jSplitPane1.add(jPanel6, JSplitPane.RIGHT);
        jPanel6.add(jPanel5, null);
        jPanel6.add(jPanel4, null);
        jToolBar.add(jButton1);
    }

}
