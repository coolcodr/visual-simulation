package designer;

import java.util.Vector;

import diagram.Associations;
import diagram.DiagramControl;

public class DesignerMain {
    private UIDesigner uIDesigner;

    public DesignerMain() {
        uIDesigner = new UIDesigner();
    }

    public void setDiagramSource(DiagramControl dc, Vector elements, Associations associations) {
        UIGeneratorControl uIGeneratorControl = new UIGeneratorControl();
        CodeGeneratorControl codeGeneratorControl = new CodeGeneratorControl(dc, elements, associations);
        DiagramElementSource diagramElementSource = new DiagramElementSource(dc, elements, associations);

        DesignerControl designerControl = new DesignerControl(uIGeneratorControl, codeGeneratorControl, diagramElementSource);
        uIDesigner = new UIDesigner(designerControl);

        DiagramSourceList list = diagramElementSource.getDiagramSourceList();
        DiagramSourceAnalysisList aList = diagramElementSource.getAnalysisList();
        System.out.println("CONE SUCCESS: " + list);
        uIDesigner.setDiagramSourceList(list);
        uIDesigner.setAnlaysisList(aList);
        designerControl.newPane("main", "Main");
    }

    public void showDesigner() {
        uIDesigner.setVisible(true);

        /*
         * uIDesigner.addWindowListener(new WindowAdapter() { public void
         * windowDeactivated(WindowEvent e) { //uIDesigner.setState(JFrame.);
         * uIDesigner.toFront(); //uIDesigner.setState(JFrame.NORMAL); } });
         */
        uIDesigner.toFront();
        // uIDesigner.show();
    }
}
