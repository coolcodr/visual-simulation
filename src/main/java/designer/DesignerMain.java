package designer;

import diagram.*;

import javax.swing.*;
import java.lang.reflect.*;
import java.util.*;
import java.awt.event.*;

public class DesignerMain
{
    private UIDesigner uIDesigner;

    public DesignerMain ()
    {
        uIDesigner = new UIDesigner();
    }
    public void setDiagramSource ( DiagramControl dc, Vector elements, Associations associations )
    {
        UIGeneratorControl uIGeneratorControl = new UIGeneratorControl();
        CodeGeneratorControl codeGeneratorControl = new CodeGeneratorControl( dc, elements, associations);
        DiagramElementSource diagramElementSource = new DiagramElementSource( dc, elements, associations);

        DesignerControl designerControl = new DesignerControl( uIGeneratorControl, codeGeneratorControl, diagramElementSource);
        uIDesigner = new UIDesigner( designerControl );

        DiagramSourceList list = (DiagramSourceList)diagramElementSource.getDiagramSourceList();
        DiagramSourceAnalysisList aList = (DiagramSourceAnalysisList)diagramElementSource.getAnalysisList();
        System.out.println("CONE SUCCESS: "+list);
        uIDesigner.setDiagramSourceList((DiagramSourceList)list);
        uIDesigner.setAnlaysisList(aList);
        designerControl.newPane("main", "Main");
    }
    public void showDesigner ()
    {
        uIDesigner.setVisible(true);

        /*
        uIDesigner.addWindowListener(new WindowAdapter()
        {
            public void windowDeactivated(WindowEvent e)
            {
                //uIDesigner.setState(JFrame.);
                uIDesigner.toFront();
                //uIDesigner.setState(JFrame.NORMAL);
            }
        });
*/
        uIDesigner.toFront();
        //uIDesigner.show();
    }
}