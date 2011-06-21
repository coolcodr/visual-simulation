package print;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

public class PCHeaderText extends PComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -4036101091525361505L;
    protected StyledDocument styledDocument;
    protected JTextPane jTextPane = new JTextPane();

    public PCHeaderText() {
    }

    public PCHeaderText(StyledDocument styledDocument, ReportPage reportPage) {
        this.styledDocument = styledDocument;
        this.reportPage = reportPage;
        // jTextPane = new JTextPane ();

        x = 0;
        y = 0;
        width = 8;
        height = 8;

    }

    public void print(Graphics2D g2d) {
        // Graphics2D g2d = (Graphics2D) g;
        Shape shape = g2d.getClip();
        setSize(width, height);

        jTextPane.setBounds(0, 0, width, height);
        g2d.translate((double) x, (double) y);

        disableDoubleBuffering(jTextPane);

        g2d.clipRect(0, 0, width, height);

        jTextPane.paint(g2d);

        enableDoubleBuffering(jTextPane);

        g2d.translate(-(double) x, -(double) y);
        g2d.setClip(shape);

    }

    public void setParagraph(StyledDocument styleDocument) {
        styledDocument = styleDocument;
        jTextPane.setStyledDocument(styledDocument);
    }

    public StyledDocument getParagraph() {
        return styledDocument;
    }

}
