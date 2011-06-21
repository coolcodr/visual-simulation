package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author HYLim,
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PreviewPage extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -6956219400785797682L;
    private ReportMultiPage reportMultiPage;
    private int pageNumber;
    private PageFormat multiPageFormat;
    private double scale;

    public PreviewPage(ReportMultiPage reportMultiPage, int pageNumber, double scale, PageFormat pageFormat) {
        this.reportMultiPage = reportMultiPage;
        this.pageNumber = pageNumber;
        this.scale = scale;

        multiPageFormat = pageFormat;
        init();
    }

    private void init() {
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int) ((multiPageFormat.getWidth() + 3) * scale), (int) ((multiPageFormat.getHeight() + 3) * scale)));
        this.setBounds(0, 0, (int) ((multiPageFormat.getWidth() + 3) * scale), (int) ((multiPageFormat.getHeight() + 3) * scale));
    }

    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getPreferredSize();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, size.width, size.height);

        g2d.setColor(Color.darkGray);
        g2d.fillRect(size.width - 3, 3, 3, size.height - 3);
        g2d.fillRect(3, size.height - 3, size.width - 3, size.height + 3);

        g2d.setColor(Color.lightGray);
        g2d.fillRect(size.width - 3, 0, 3, 3);
        g2d.fillRect(0, size.height - 3, 3, 3);

        g2d.setColor(Color.darkGray);
        g2d.drawRect(0, 0, size.width - 4, size.height - 4);

        try {
            // Graphics2D g2d = (Graphics2D) g;
            g2d.scale(scale, scale);
            reportMultiPage.print(g2d, multiPageFormat, pageNumber);
        } catch (Exception PrintException) {
        }
    }
}
