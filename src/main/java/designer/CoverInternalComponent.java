package designer;

import java.awt.Component;

public class CoverInternalComponent extends CoverComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -267612398446630773L;
    private DiagramComponentSetPanel setPanel;

    public CoverInternalComponent(Component relateComponent, int coverSize, boolean haveBorder, DiagramComponentSetPanel setPanel) {
        super(relateComponent, coverSize, haveBorder);
        this.setPanel = setPanel;
    }

    public void deselectInternal() {
        setPanel.deselectInternal();
        setPanel.getCover().setVisible(false);
    }

    public int getGridSize() {
        return super.getGridSize() == 1 ? 1 : 4;
    }

    public void requestFocus() {

    }
}
