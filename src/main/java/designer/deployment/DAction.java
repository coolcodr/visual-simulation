package designer.deployment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JDialog;
import java.io.Serializable;

abstract public class DAction implements ActionListener, Serializable
{
    protected MainControlHandler mainControl;
    protected MainFrame mainFrame;
    protected JDialog dialog;

    public DAction()
    {
    }

    public DAction createNewObject ()
    {
        try
        {
            return (DAction) getClass().newInstance();
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    public void setMainFrame ( MainFrame mainFrame )
    {
        this.mainFrame = mainFrame;
    }
    public void setMainControl ( MainControlHandler mainControl )
    {
        this.mainControl = mainControl;
    }
    public void setJDialog ( JDialog dialog )
    {
        this.dialog = dialog;
    }
    abstract public void actionPerformed(ActionEvent e) ;
}