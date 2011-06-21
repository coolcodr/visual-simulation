package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */
import javax.swing.text.StyledDocument;

public class PCParagraph extends PCHeaderText {
    /**
     * 
     */
    private static final long serialVersionUID = -7047213517438847822L;
    int pi;

    public PCParagraph(StyledDocument styledDocument, ReportPage reportPage, int pi) {
        this.styledDocument = styledDocument;
        this.reportPage = reportPage;
        this.pi = pi;
        x = 0;
        y = 0;
        width = 8;
        height = 8;
    }

    public int getPI() {
        return pi;
    }

    public void setXY(int x, int y) {
        if (x < 10) {
            x = 10;
        }
        if (x + width + 10 > reportPage.getPageFormat().getImageableWidth()) {
            x = (int) reportPage.getPageFormat().getImageableWidth() - width - 10;
        }
        if (y < reportPage.getHeaderHeight()) {
            y = reportPage.getHeaderHeight();
        }
        if (y + height > reportPage.getMaxImagaebleHeight()) {
            y = reportPage.getMaxImagaebleHeight() - height;
        }
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        if (width < 5) {
            width = 5;
        }
        if (height < 10) {
            height = 10;
        }
        if (width + 20 > reportPage.getPageFormat().getImageableWidth()) {
            width = (int) reportPage.getPageFormat().getImageableWidth() - 20;
        }
        if (height > reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight()) {
            height = reportPage.getMaxImagaebleHeight() - reportPage.getHeaderHeight();
        }
        this.width = width;
        this.height = height;
        setXY(x, y);
    }
}
