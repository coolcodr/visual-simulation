package diagram;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramButton extends JButton {
    /**
     * 
     */
    private static final long serialVersionUID = -8578499697109950672L;
    private DiagramElementType _type;
    private Control _controller;

    public DiagramButton(String label, String image, DiagramElementType type, String controller, DiagramControl control) {
        super(new ImageIcon(image, label));
        // super(label);
        _type = type;
        try {
            _controller = (Control) Class.forName(controller).newInstance();
            _controller.setType(_type);
            _controller.setDiagramControl(control);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Control getControl() {
        return _controller;
    }

    public DiagramElementType getDiagramElementType() {
        return _type;
    }
}
