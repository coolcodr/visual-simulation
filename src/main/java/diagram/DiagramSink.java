package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramSink extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = 6122911390937023218L;

    public DiagramSink() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("Sink")));
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.INPORT);
        } catch (Exception e) {
        };
    }
}
