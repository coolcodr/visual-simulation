package diagram;
import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author: Kenny
 * @version 1.0
 */

//public class DiagramBarChartFrame extends DiagramShape {
public class DiagramChart extends DiagramShape {
	
	public DiagramChart() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", new String("Chart")));
		_properties.add(new Property("chart.Presentation", "Presentation", null));
		_properties.add(new Property("chart.DataProcessor", "DataProcessor", null));
		//_properties.add(new Property("java.lang.Integer", "Analyze Time", new Integer(-1)));
	}
	
	public void setPort() {
		try {
			addPort(this, DiagramPort.CHARTPORT);
		}
		catch (Exception e){};
	}
}