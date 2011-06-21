package designer.report;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

import print.ReportPage;

/**
 * <p>
 * Title: Print Editor
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class WorkPane extends JLayeredPane {
    /**
     * 
     */
    private static final long serialVersionUID = 2880625917426311071L;
    private ReportPage reportPage;
    private WorkEditPane workEditPane;

    public WorkPane(ReportPage reportpage) {
        reportPage = reportpage;
        init();
    }

    public void setWorkEditPane(WorkEditPane workEditPane) {
        this.workEditPane = workEditPane;
    }

    private void init() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int) reportPage.getPageFormat().getWidth() + 3, (int) reportPage.getPageFormat().getHeight() + 3));
        this.setBounds(0, 0, (int) reportPage.getPageFormat().getWidth() + 3, (int) reportPage.getPageFormat().getHeight() + 3);

        // addMouseListener(this);
        // addMouseMotionListener(this);

    }

    public ReportPage getReportPage() {
        return reportPage;
    }

    public WorkEditPane getWorkEditPane() {
        return workEditPane;
    }
}
