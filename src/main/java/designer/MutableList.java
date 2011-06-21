package designer;

import javax.swing.*;
import java.awt.event.*;

public class MutableList extends JList
{
    public MutableList()
    {
        super(new DefaultListModel());
        this.addMouseListener(new ListControl());
    }

    DefaultListModel getContents()
    {
        return (DefaultListModel) getModel();
    }

}

class ListControl extends MouseAdapter implements MouseListener
{
	public void mouseClicked(MouseEvent e)
    {
    	if ( e.getClickCount() == 2 )
    	{
	    	JList list = (JList)e.getSource();
	    	Object object = list.getSelectedValue();

            UIDesigner.getControl().showFrame((DesignPane)object);
	    	System.out.println(object);
    	}
    }
}
