package designer;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import designer.deployment.SimPropertyChoice;

public class CardPane extends JPanel implements DesignerComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -3765773778390945810L;
    private Border border;
    private Border titledBorder;
    private String text;
    private CardLayout cardLayout;
    private JPanel defaultPane;
    private JComboBox comboBox;

    private CoverComponent cover;
    private CoverControl coverControl;

    private Vector designPanes = new Vector();
    private String id;

    private ElementProperties properties;

    public CardPane() {
        this("C" + DesignerControl.getNewID());
    }

    public void setProperties(ElementProperties properties) {
        this.properties = properties;
    }

    public ElementProperties getProperties() {
        return properties;
    }

    public CardPane(String id) {
        super();
        this.id = id;
        defaultPane = new JPanel();
        // defaultPane.setBackground(UIManager.getColor("Button.focus"));
        border = BorderFactory.createEtchedBorder();
        setBorder(border);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        text = "";
        setText(text);
        setCoverControl(CoverControl.createEditTextControl());
        add(defaultPane, "Default");
        setProperties(PropertiesFactory.createCardPaneProperties(this));
    }

    public String getID() {
        return id;
    }

    public void showDefault() {
        cardLayout.show(this, "Default");
    }

    public void addPane(DesignPane designPane, boolean autoResize) {
        designPanes.add(designPane);
        int height = text.equalsIgnoreCase("") ? 0 : 19;
        if (autoResize) {
            designPane.setBounds(0, 0, getWidth() - 14, getHeight() - 14 - height);
        }
        designPane.setParentCardPane(this);
        refresh();
    }

    public void setPanes(Vector designPanes) {
        for (int i = 0; i < this.designPanes.size(); i++) {
            DesignPane designPane = (DesignPane) this.designPanes.elementAt(i);
            designPane.setParentCardPane(null);
        }
        this.designPanes = designPanes;
        for (int i = 0; i < designPanes.size(); i++) {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            designPane.setParentCardPane(this);
        }

    }

    public Dimension getPanelSize() {
        int height = text.equalsIgnoreCase("") ? 0 : 19;
        Dimension d = new Dimension(getWidth() - 14, getHeight() - 14 - height);
        return d;
    }

    public void removeAllPanes() {
        removeAll();
        for (int i = 0; i < designPanes.size(); i++) {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            if (designPane.getBackPane() != null) {
                designPane.getBackPane().add(designPane);
            }
        }
        designPanes.removeAllElements();
        add(defaultPane, "Default");
    }

    public void refresh() {
        removeAll();
        add(defaultPane, "Default");

        for (int i = 0; i < designPanes.size(); i++) {
            DesignPane designPane = (DesignPane) designPanes.elementAt(i);
            JPanel panel = new JPanel();
            panel.add(designPane);
            panel.setVisible(true);
            panel.setLayout(null);
            this.add(panel, designPane.getID());
        }
        if (comboBox == null) {
            cardLayout.show(this, "Default");
        } else {
            cardLayout.show(this, ((SimPropertyChoice) comboBox.getSelectedItem()).getFrameID());
        }
    }

    public void removePane(DesignPane designPane) {
        designPanes.remove(designPane);
        designPane.setParentCardPane(null);
    }

    public Vector getDesignPanes() {
        return designPanes;
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

    public String toString() {
        if (getText().equalsIgnoreCase("")) {
            return "Untitled Pane (" + id + ")";
        }
        return getText();
    }

    public void setCover(CoverComponent cover) {
        this.cover = cover;
        coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/card16.gif");
    }

    public String getText() {
        return text;
    }

    public void setText(String s) {
        text = s;
        titledBorder = new TitledBorder(border, s);
        setBorder(titledBorder);
    }

    public CardLayout getCardLayout() {
        return (CardLayout) getLayout();
    }

    public void setComboBox(JComboBox jComboBox) {
        comboBox = jComboBox;
    }

    public JComboBox getComboBox() {
        return comboBox;
    }
}
