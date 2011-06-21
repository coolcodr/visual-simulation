package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramMessageQueue extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = -7293188929474666313L;

    public DiagramMessageQueue() {
        super();
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.OUTPORT);
            addPort(this, DiagramPort.INPORT);
            addPort(this, DiagramPort.EXITPORT);
        } catch (Exception e) {
        }
    }

    public boolean isMessageQueue() {
        return true;
    }
}
