package designer.deployment;

import java.awt.*;
import javax.swing.*;

public class GenerateProgress extends JDialog
{
    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JProgressBar jProgressBar1 = new JProgressBar();

    public GenerateProgress(Frame frame, String title, boolean modal)
    {
        super(frame, title, modal);
        try
        {
            jbInit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public GenerateProgress()
    {
        this(null, "Please wait...", false);
    }

    private void jbInit() throws Exception
    {
        this.setResizable(false);
    this.setSize(new Dimension(350, 100));
        this.setLocation( (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - this.getWidth()) / 2,
                         (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - this.getHeight()) / 2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panel1.setLayout(null);
        jLabel1.setRequestFocusEnabled(true);
        jLabel1.setText("Preparing Component for Simultation Model...");
        jLabel1.setBounds(new Rectangle(10, 10, 280, 20));
        jProgressBar1.setMaximum(100);
        jProgressBar1.setBounds(new Rectangle(10, 40, 320, 20));
        getContentPane().add(panel1);
        panel1.add(jLabel1, null);
        panel1.add(jProgressBar1, null);
        jProgressBar1.setValue(0);
    }
    public void setText ( String text )
    {
        jLabel1.setText(text);
    }
    public void setMaximun ( int i )
    {
        jProgressBar1.setMaximum(i);
    }
    public void setValue ( int i )
    {
        jProgressBar1.setValue(i);
        update(getGraphics());
    }
    public void setVisible ( boolean b )
    {
        super.setVisible(b);
    }
}