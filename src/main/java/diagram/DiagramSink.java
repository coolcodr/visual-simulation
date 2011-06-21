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
 * @author
 * @version 1.0
 */

public class DiagramSink extends DiagramShape {
	
	public DiagramSink() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", new String("Sink")));
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.INPORT);
		}
		catch (Exception e){};
	}
}