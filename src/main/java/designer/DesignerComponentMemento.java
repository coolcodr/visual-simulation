package designer;

import java.awt.Container;

public class DesignerComponentMemento {
    private DesignerComponent designerComponent;

    private int x;
    private int y;
    private int width;
    private int height;
    private String text;

    private Container parent = null;

    public DesignerComponentMemento(DesignerComponent designerComponent) {
        this.designerComponent = designerComponent;
        text = designerComponent.getText();
        CoverComponent component = designerComponent.getCover();

        if (component != null) {
            x = component.getX();
            y = component.getY();
            width = component.getWidth();
            height = component.getHeight();

            parent = component.getParent();
        }
    }

    public void restore() {
        designerComponent.setText(text);

        CoverComponent component = designerComponent.getCover();

        if (parent != null && !parent.equals(component.getParent())) {
            if (component.getRelateComponent().getParent() != null) {
                component.getParent().remove(component.getRelateComponent());
            }
            if (component.getParent() != null) {
                component.getParent().remove(component);
            }

            DesignUpperPane upperPane = (DesignUpperPane) parent;
            upperPane.getDesignPane().addDesignComponent(designerComponent, false);

            component = designerComponent.getCover();

            component.moveXY(x, y);
            component.setXY();
            component.changeSize(width, height);
            component.changeResize();
            component.refreshResizePoint();

        } else if (parent == null) {
            component.getRelateComponent().getParent().remove(component.getRelateComponent());
            component.getParent().remove(component);
        } else if (parent != null && parent.equals(component.getParent())) {
            component.moveXY(x, y);
            component.setXY();
            component.changeSize(width, height);
            component.changeResize();
            component.refreshResizePoint();
        }
        component.getFocus(true);
    }
}
