package diagram;


/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramNServer extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = 2259776712668268L;

    public DiagramNServer() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("NServer")));
        _properties.add(new Property("mcomponent.distribution.Transform", "Transform", null));
        _properties.add(new Property("mcomponent.distribution.Distribution", "Distribution", null));
        _properties.add(new Property("java.lang.Integer", "Server No", new Integer(2)));
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.OUTPORT);
            addPort(this, DiagramPort.INPORT);
        } catch (Exception e) {
        }
    }
}
