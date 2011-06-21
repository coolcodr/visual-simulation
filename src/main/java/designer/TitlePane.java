package designer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class TitlePane extends DesignPane implements DesignerComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -4908277661720920887L;
    private Border border;
    private Border titledBorder;
    // private String text;

    private CoverComponent cover;
    private CoverControl coverControl;

    private ElementProperties properties;

    // private Color foreground;
    // private Font font;

    public TitlePane() {
        this("E" + DesignerControl.getNewID());
    }

    public void setProperties(ElementProperties properties) {
        this.properties = properties;
    }

    public ElementProperties getProperties() {
        return properties;
    }

    public TitlePane(String id) {
        super(id, "Untitled Border");
        border = BorderFactory.createEtchedBorder();
        // text = "Untitled Border";
        setText("Untitled Border");
        setCoverControl(CoverControl.createEditTextControl());
        setProperties(PropertiesFactory.createTitlePaneProperties(this));
    }

    public CoverControl getCoverControl() {
        return coverControl;
    }

    public void setCoverControl(CoverControl coverControl) {
        this.coverControl = coverControl;
    }

    public CoverComponent getCover() {
        return cover;
    }

    public void setCover(CoverComponent cover) {
        this.cover = cover;
        coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/title16.gif");
    }

    public String getText() {
        return super.getTitle();
    }

    public void setText(String s) {
        super.setTitle(s);
        titledBorder = new TitledBorder(border, s);
        bottomPane.setBorder(titledBorder);
    }

    public void setFont(Font font) {
        // this.font = font;
        super.setFont(font);
        if (bottomPane != null) {
            bottomPane.setFont(font);
        }
    }

    public void setForeground(Color fc) {
        // this.foreground = fc;
        super.setForeground(fc);
        if (bottomPane != null) {
            bottomPane.setForeground(fc);
        }
    }

    /*
     * public Font getFont () { return bottomPane.getFont(); } public Color
     * getForeground () { return bottomPane.getForeground(); }
     */
    public String toString() {
        return getText();
    }
}
