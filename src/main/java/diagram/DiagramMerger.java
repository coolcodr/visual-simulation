package diagram;


/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramMerger extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = -6357286992792022060L;

    public DiagramMerger() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("Merger")));
        _properties.add(new Property("mcomponent.distribution.MergerModel", "MergerModel", null));
        _properties.add(new Property("java.lang.Integer", "Input No", new Integer(2)));
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.INPORT).setPortIndex(0);
            addPort(this, DiagramPort.INPORT).setPortIndex(1);
            addPort(this, DiagramPort.OUTPORT);
        } catch (Exception e) {
        };
    }
}
