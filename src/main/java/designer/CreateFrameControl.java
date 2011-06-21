package designer;

import designer.deployment.*;

import java.awt.*;
import javax.swing.JOptionPane;

class CreateFrameControl
{
    public CreateFrameControl()
    {
    }
    public static void ForceCreateFrame2 ( DiagramComponentSetPanel[] setPanes, SimPropertyChoice choice, String name, String title, boolean button )
    {
        DesignPane old = UIDesigner.getControl().getDesignPane(choice.getFrameID());
        if ( old != null )
            UIDesigner.getControl().forceDeletePane(old);

        DesignPane pane = UIDesigner.getControl().newPane();
        String newID = DesignerControl.currentDesignPane.getID();
        choice.setFrameID(newID);

        for ( int i = 0 ; i < setPanes.length ; i ++ )
        {
            setPanes[i].setLocation(10, 10);
            pane.addDesignComponent(setPanes[i]);
        }
        CButton cButton = null;

        if ( button )
        {
            cButton = new CButton();
            cButton.setText("OK");
            cButton.setDAction((DAction)UIDesigner.getControl().getActionChooser()[1]);
            cButton.setBounds(100,100,56,20);
            pane.addDesignComponent(cButton);
        }

        pane.selectAll();
        pane.autoLayout();
        pane.alignRight();

        if ( cButton != null )
            pane.setSize(cButton.getX()+cButton.getWidth()+ 15, cButton.getY()+cButton.getHeight()+10);
        else
            pane.setSize(setPanes[setPanes.length-1].getX()+setPanes[setPanes.length-1].getWidth()+10, setPanes[setPanes.length-1].getY()+setPanes[setPanes.length-1].getHeight()+10);

        pane.setFrameName(name);//+" ("+newID+")");
        pane.setTitle(title);

        pane.commandStack.clearUndo();
        pane.commandStack.clearRedo();

        UIDesigner.mainWindow.workPaneTab.updateUI();
        UIDesigner.mainWindow.getFrameList().updateUI();
        pane.refreshTitle();

    }
    public static void ForceCreateFrame ( SimPropertyChoice choice )
    {
        UIDesigner.getControl().newPane();
        String newID = DesignerControl.currentDesignPane.getID();
        choice.setFrameID(newID);
    }
    public static void showFrame ( SimPropertyChoice choice )
    {
        UIDesigner.getControl().showFrame(choice.getFrameID());
    }
    public static DesignPane CreateFrame( DiagramComponentSetPanel setPane, SimPropertyChoice choice)
    {
    	return CreateFrame ( setPane, choice, true);
    }
    public static DesignPane CreateFrame( DiagramComponentSetPanel setPane, SimPropertyChoice choice, boolean show)
    {
    	DesignPane newPane = null;
        String id = choice.getFrameID();

        if (id.equalsIgnoreCase("-1"))
        {
            int selectedValue = JOptionPane.showConfirmDialog(UIDesigner.mainWindow, "No frame is associated, create a new frame?", "Create Frame", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (selectedValue == JOptionPane.OK_OPTION)
            {
                //System.out.println(name+"|"+setMode+"|"+comboBox.getSelectedItem());
                String newID = setPane.getName() + "|" + setPane.getSetMode() + "|" + choice.getValue();
                newPane = UIDesigner.getControl().newPane(newID);
                //String newID = DesignerControl.currentDesignPane.getID();
                choice.setFrameID(newID);
                //setPane.addPaneToCardPane(newPane);
            }
            else if (selectedValue == JOptionPane.CANCEL_OPTION)
            {
            }
        }
        else
        {
        	if ( show)
            UIDesigner.getControl().showFrame(id);
            return null;
        }
        return newPane;
    }
}