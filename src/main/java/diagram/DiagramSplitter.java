package diagram;

import mcomponent.distribution.DefaultSplitterModel2;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramSplitter extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = 8899882338889056105L;

    public DiagramSplitter() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("Splitter")));
        _properties.add(new Property("mcomponent.distribution.SplitterModel", "SplitterModel", new DefaultSplitterModel2()));
        _properties.add(new Property("java.lang.Integer", "Output No", new Integer(2)));
    }

    // Testing!!!!
    public void setPort() {
        try {
            addPort(this, DiagramPort.INPORT);
            addPort(this, DiagramPort.OUTPORT).setPortIndex(0);
            addPort(this, DiagramPort.OUTPORT).setPortIndex(1);
        } catch (Exception e) {
        };
    }
}
