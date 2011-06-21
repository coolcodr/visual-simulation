package designer.report;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

import print.PreviewPage;
import print.ReportDocument;
import print.ReportMultiPage;

public class PreviewPane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 4686485899051004237L;

    private static final float EXPAND_FACTOR = 1.5f;

    private ReportDocument reportDocument;
    private PreviewPage[] previewPage;

    private int numOfPage;

    private double scale;

    public PreviewPane(ReportDocument reportDocument) {
        this(reportDocument, 0.5);
    }

    public PreviewPane(ReportDocument reportDocument, double scale) {
        this.reportDocument = reportDocument;
        previewPage = new PreviewPage[2];
        numOfPage = 0;
        this.scale = scale;
        setBackground(Color.lightGray);
        // TEMP//
        setScale(scale);
    }

    public void addAllPanel() {
        previewPage = new PreviewPage[2];
        numOfPage = 0;
        removeAll();
        ReportMultiPage reportMultiPage;
        reportDocument.refresh();

        for (int i = 0; i < reportDocument.getNumOfPage(); i++) {
            addSinglePanel(reportDocument.getReportMultiPage(i));
        }

        double width = previewPage[0].getPreferredSize().getWidth();
        double requiredRow;
        if (Math.floor(getPreferredSize().getWidth() / width) == 0) {
            requiredRow = reportDocument.getNumOfPage();
        } else {
            requiredRow = Math.ceil(reportDocument.getNumOfPage() / Math.floor((getPreferredSize().getWidth() + 120) / width));
        }

        double height = (previewPage[0].getPreferredSize().getHeight() * requiredRow) + (5 * requiredRow + 10);

        setPreferredSize(new Dimension((int) width, (int) height));

    }

    /*
     * public void paint (Graphics g) { Graphics2D g2d = (Graphics2D)g;
     * g2d.scale(0.5,0.5); this.setBackground(Color.lightGray);
     * g2d.setBackground(Color.lightGray); super.paint (g2d); }
     */

    private void addSinglePanel(ReportMultiPage reportMultiPage) {
        if (numOfPage == previewPage.length) {
            expandPreviewPage();
        }

        previewPage[numOfPage] = new PreviewPage(reportMultiPage, numOfPage, scale, reportDocument.getMultiPageFormat());

        this.add(previewPage[numOfPage]);

        // this.setPreferredSize(new Dimension
        // ((int)previewPage[numOfPage].getPreferredSize().getWidth(),
        // (int)previewPage[numOfPage].getPreferredSize().getHeight()));
        previewPage[numOfPage].setVisible(true);

        numOfPage++;
    }

    private void expandPreviewPage() {
        int newSize = (int) (previewPage.length * EXPAND_FACTOR);

        PreviewPage tempPreviewPage[] = new PreviewPage[newSize];

        for (int i = 0; i < previewPage.length; i++) {
            tempPreviewPage[i] = previewPage[i];
        }

        previewPage = tempPreviewPage;
    }

    public void setScale(double d) {
        scale = d;
        addAllPanel();
        // this.setPreferredSize(new Dimension (
        // (int)(previewPage[numOfPage].getPreferredSize().getWidth() * scale),
        // (int)((previewPage[numOfPage].getPreferredSize().getHeight() *
        // (numOfPage + 1) + (5 * numOfPage + 15))*scale)));
    }

    public double getScale() {
        return scale;
    }

    public void setVisibleRegion(int x, int y) {
        scrollRectToVisible(new Rectangle(x, y));
    }
}
