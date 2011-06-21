package designer;

import designer.report.*;
import print.*;
import java.util.Vector;
import java.util.Hashtable;
import javax.swing.*;

public class ReportDesignerControl
{
    private UIDesigner mainWindow;
    private ReportDocument reportDocument;
    private designer.report.PrintEditor printEditor;
    private designer.report.PrintEditor currentReport;

    //All report created
    protected static Vector reports = new Vector();
    protected static Hashtable allReport = new Hashtable();

    private int count;

    public ReportDesignerControl( UIDesigner mainWindow )
    {
        this.mainWindow = mainWindow;
    }
    public void newReport ()
    {
        newReport("Report "+Integer.toString(reports.size()+1));
    }
    public void newReport ( String title )
    {
        printEditor = new designer.report.PrintEditor();
        printEditor.setTitle(title);
        currentReport = printEditor;
        printEditor.addPage ( new ObjectComponent(new ObjectTest1(), "") );
        mainWindow.workPaneTab.add(printEditor.getDesignPane(), title);
        reports.add(printEditor);
        allReport.put(title, printEditor);
        printEditor.getControl().getReportDocument().setTitle(title);
        UIDesigner.workPaneTab.repaint();
        UIDesigner.mainWindow.addReport(currentReport);
        UIDesigner.workPaneTab.setSelectedIndex(UIDesigner.workPaneTab.getTabCount() - 1);

    }
    public void newReport ( ReportDocument reportDocument )
    {
        printEditor = new designer.report.PrintEditor(reportDocument);
        printEditor.setTitle(reportDocument.getTitle());
        currentReport = printEditor;
        mainWindow.workPaneTab.add(printEditor.getDesignPane(), reportDocument.getTitle());
        reports.add(printEditor);
        allReport.put(reportDocument.getTitle(), printEditor);
        UIDesigner.workPaneTab.repaint();
        UIDesigner.mainWindow.addReport(currentReport);
    }
    public void deleteReport(designer.report.PrintEditor printEditor)
    {
        int selectedValue = JOptionPane.showConfirmDialog(UIDesigner.mainWindow, "Permanently delete the selected report?", "Delete Report", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selectedValue == JOptionPane.NO_OPTION)
            return;

        forceDeleteReport(printEditor);

    }
    public void close()
    {
        count = 0;
        reports.clear();
        allReport.clear();
    }
    public void forceDeleteReport(designer.report.PrintEditor printEditor)
    {
        System.out.println("REPOR COUNT: "+reports.size());
        reports.remove(printEditor);
        System.out.println("REPOR COUNT: "+reports.size());
        System.out.println("REPOR COUNT 2: "+allReport.size());
        allReport.remove(printEditor.getTitle());
        System.out.println("REPOR COUNT 2: "+allReport.size());

        JTabbedPane tabbedPane = UIDesigner.mainWindow.workPaneTab;

        for (int i = 0; i < tabbedPane.getComponentCount(); i++)
        {
            if (! (tabbedPane.getComponent(i)instanceof DesignBackPane))
                if ( ( (JPanel) tabbedPane.getComponent(i)).equals(printEditor.getDesignPane()))
                {
                    tabbedPane.remove(tabbedPane.getComponent(i));
                    break;
                }
        }

        JComboBox jComboBox = UIDesigner.mainWindow.jComboBoxFrame;

        Object[] objects = new Object[jComboBox.getItemCount()];
        for (int j = 0; j < objects.length; j++)
            objects[j] = jComboBox.getItemAt(j);
        jComboBox.removeAllItems();
        for (int j = 0; j < objects.length; j++)
            if ( !objects[j].equals(printEditor) )
                jComboBox.addItem(objects[j]);
    }
    public void showReport ( String title )
    {
        Object printEditor = allReport.get(title);
        if ( printEditor != null )
            showReport((designer.report.PrintEditor)printEditor);
    }
    public void setCurrentReport ( designer.report.PrintEditor printEditor )
    {
        currentReport = printEditor;
    }
    public void showReport ( designer.report.PrintEditor printEditor )
    {
        setCurrentReport(printEditor);

        for ( int i = 0 ; i < UIDesigner.workPaneTab.getTabCount() ; i ++ )
        {
            if ( !(UIDesigner.workPaneTab.getComponent(i) instanceof DesignBackPane) )
            {
                JPanel back = (JPanel) UIDesigner.workPaneTab.getComponent(i);

                if (back.equals(printEditor.getDesignPane()))
                {
                    UIDesigner.workPaneTab.setSelectedIndex(i);
                    back.setVisible(true);
                    setCurrentReport(printEditor);
                    return;
                }
            }
        }

        JPanel pane = printEditor.getDesignPane();

        UIDesigner.workPaneTab.add(pane, printEditor.getTitle());

        UIDesigner.workPaneTab.setSelectedIndex(UIDesigner.workPaneTab.getTabCount()-1);

        pane.setVisible(true);

        /*
        removePropertiesTable();

        refreshFrameList();
*/
        setCurrentReport(printEditor);

    }
    public void newPage ()
    {
        currentReport.addPage ( new ObjectComponent(new ObjectTest1()) );
    }
    public boolean getHaveHeader ()
    {
        return currentReport.getControl().getHaveHeader();
    }
    public boolean getHaveFooter ()
    {
        return currentReport.getControl().getHaveFooter();
    }
    public boolean getHeaderLine ()
    {
        return currentReport.getControl().getHeaderLine();
    }
    public boolean getFooterLine ()
    {
        return currentReport.getControl().getFooterLine();
    }
    public designer.report.ControlMainEditor getControl ()
    {
        return currentReport.getControl();
    }
    public void setFormatting ()
    {
        currentReport.getFormattingDialog().showInitial();
        currentReport.getControl().refreshView();

    }
}