package designer.report;

import print.*;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class BackPane extends JPanel
{
    private static final float EXPAND_FACTOR = 1.5f;

    WorkPane[] workPanel;
    WorkEditPane[] workEditPane;

    ReportDocument reportDocument;
    int numOfPage;

    ControlPaneEdit controlPaneEdit;
    JList pageList ;

    public BackPane( ControlPaneEdit controlPaneEdit, ReportDocument reportDocument, JList pageList )
    {
        //Print Object
        //this.controlPaneEdit = controlPaneEdit;
        this.reportDocument = reportDocument;
        this.controlPaneEdit = controlPaneEdit;
        this.pageList = pageList;

        //Preview Object (Panel)
        workPanel = new WorkPane[2];
        workEditPane = new WorkEditPane[2];

        numOfPage = 0;

        this.setBackground(new JDesktopPane().getBackground());

    }

    //This overrided paint method actually done nothing more than the original, can delete.
    //No! this function is update the page number in printer editor when repaint.
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.darkGray);

        int heightPerPage = (int)(this.getPreferredSize().getHeight() - 15) / numOfPage;//( reportDocument.getPageFormat().getPaper().getHeight()
        controlPaneEdit.setStatusPage((int)Math.floor(-this.getBounds().getY()/heightPerPage), numOfPage);
        int preHeight = 0;
    }

    public void addAllPanel ()
    {
        ((DefaultListModel)pageList.getModel()).removeAllElements();

        reportDocument.updateStartPage(true);

        int pageAdded = 0;
        for ( int i = 0 ; i < reportDocument.getNumOfReportPage() ; i ++ )
        {
            for ( int j = 0 ; j < reportDocument.getReportPage(i).getNumOfPage() ; j ++ )
            {
                addSinglePanel(pageAdded,i, j);
                numOfPage++;
                pageAdded++;

                ((DefaultListModel)pageList.getModel()).addElement("Page "+numOfPage);
                pageList.setSelectedIndex(0);
            }
        }
    }

    public void addSinglePanel ( int pageAdded, int i, int j )
    {
        if ( numOfPage == this.workPanel.length)
            expandWorkPanel();

        workPanel[pageAdded] = new WorkPane ( reportDocument.getReportPage(i) );
        workEditPane[pageAdded] = new WorkEditPane ( reportDocument.getReportPage(i), j, workPanel[pageAdded], controlPaneEdit );

        workPanel[pageAdded].setWorkEditPane(workEditPane[pageAdded]);
        workPanel[pageAdded].setLayout(null);

        workPanel[pageAdded].setLayer(workEditPane[pageAdded], JLayeredPane.DEFAULT_LAYER.intValue());

        workPanel[pageAdded].add(workEditPane[pageAdded]);

        this.add(workPanel[pageAdded]);
        this.setPreferredSize(new Dimension ( (int)workPanel[pageAdded].getPreferredSize().getWidth(), (int)workPanel[pageAdded].getPreferredSize().getHeight() * (numOfPage + 1) + (5 * numOfPage + 15)));

        workPanel[pageAdded].setVisible(true);
        workEditPane[pageAdded].setVisible(true);
    }
    public int count ()
    {
        return reportDocument.getNumOfPage();
    }
    public WorkPane getWorkPane ( int i )
    {
        return workPanel[i];
    }
    private void expandWorkPanel ( )
    {
      	int newSize = (int) (workPanel.length * EXPAND_FACTOR);

      	WorkPane tempWorkPanel[] = new WorkPane[newSize];
        WorkEditPane tempWorkEditPane[] = new WorkEditPane[newSize];
      	for ( int i = 0; i < workPanel.length; i++)
      	{
        	tempWorkPanel[i] = workPanel[i];
            tempWorkEditPane[i] = workEditPane[i];
      	}
      	workPanel = tempWorkPanel;
        workEditPane = tempWorkEditPane;
   	}
}