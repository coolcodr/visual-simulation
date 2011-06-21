package designer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class CodeViewer extends JFrame
{
    JPanel jPanel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel2 = new JPanel();
    JButton jButton1 = new JButton();
    JPanel jPanel3 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    BorderLayout borderLayout2 = new BorderLayout();
    JEditorPane jEditorPane1 = new JEditorPane();
    public CodeViewer()
    {
        try
        {
            jbInit();
            refreshPane("designer/deployment/MainControl.java");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void refreshPane ( String path)
    {
        Font font = new Font("Courier New", Font.PLAIN, 13);
        try
        {
            File inFile = new File(path);
            jScrollPane1.getViewport().remove(jEditorPane1);
            jEditorPane1 = new JEditorPane(inFile.toURL());
            jEditorPane1.setFont(font);
            jEditorPane1.setEditable(false);
            jScrollPane1.getViewport().add(jEditorPane1, null);

            repaint();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

    }
    private void jbInit() throws Exception
    {

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(0,0,760, 600);

        this.setLocation( (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth()) / 2,
                         (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight()) / 2);
        this.setTitle("Java Code for Simulation Model");


        jPanel1.setLayout(borderLayout1);
        jButton1.setText("CLOSE");
        jButton1.addActionListener(new CodeViewer_jButton1_actionAdapter(this));
        jPanel3.setLayout(borderLayout2);
        jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        jPanel1.add(jPanel2, BorderLayout.SOUTH);
        jPanel2.add(jButton1, null);
        jPanel1.add(jPanel3, BorderLayout.CENTER);
        jPanel3.add(jScrollPane1, BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jEditorPane1, null);
    }

    void jButton1_actionPerformed(ActionEvent e)
    {
        setVisible(false);
    }

}

class CodeViewer_jButton1_actionAdapter implements java.awt.event.ActionListener
{
    CodeViewer adaptee;

    CodeViewer_jButton1_actionAdapter(CodeViewer adaptee)
    {
        this.adaptee = adaptee;
    }
    public void actionPerformed(ActionEvent e)
    {
        adaptee.jButton1_actionPerformed(e);
    }
}