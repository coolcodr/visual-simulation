package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramSource extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = 3083963261659733234L;

    public DiagramSource() {
        super();

        _properties.add(new Property("java.lang.String", "Name", "Source"));
        _properties.add(new Property("mcomponent.distribution.ObjectCreator", "Object Creator", null));
        _properties.add(new Property("mcomponent.distribution.Distribution", "Distribution", null));
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.OUTPORT);
        } catch (Exception e) {
        }
    }
}
