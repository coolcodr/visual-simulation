package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author: Kenny
 * @version 1.0
 */

//public class DiagramBarChartFrame extends DiagramShape {
public class DiagramChart extends DiagramShape {

    /**
     * 
     */
    private static final long serialVersionUID = -1283006087138827525L;

    public DiagramChart() {
        super();

        _properties.add(new Property("java.lang.String", "Name", new String("Chart")));
        _properties.add(new Property("chart.Presentation", "Presentation", null));
        _properties.add(new Property("chart.DataProcessor", "DataProcessor", null));
        // _properties.add(new Property("java.lang.Integer", "Analyze Time", new
        // Integer(-1)));
    }

    public void setPort() {
        try {
            addPort(this, DiagramPort.CHARTPORT);
        } catch (Exception e) {
        };
    }
}
