package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramAnalysisTool extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = -7924065180585242133L;

    public DiagramAnalysisTool() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("AnalysisTool")));
        _properties.add(new Property("java.lang.Integer", "Start Time", new Integer(0)));
        _properties.add(new Property("java.lang.Integer", "Analyze Time", new Integer(-1)));
    }

    public void setPort() {
        try {
            addPort(this, DiagramPort.STARTPORT);
            addPort(this, DiagramPort.ENDPORT);
            addPort(this, DiagramPort.DATASOURCEPORT);
        } catch (Exception e) {
        };
    }
}
