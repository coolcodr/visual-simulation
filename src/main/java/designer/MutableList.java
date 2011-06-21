package designer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MutableList extends JList {
    /**
     * 
     */
    private static final long serialVersionUID = 7838074654408306253L;

    public MutableList() {
        super(new DefaultListModel());
        addMouseListener(new ListControl());
    }

    DefaultListModel getContents() {
        return (DefaultListModel) getModel();
    }

}

class ListControl extends MouseAdapter implements MouseListener {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JList list = (JList) e.getSource();
            Object object = list.getSelectedValue();

            UIDesigner.getControl().showFrame((DesignPane) object);
            System.out.println(object);
        }
    }
}
