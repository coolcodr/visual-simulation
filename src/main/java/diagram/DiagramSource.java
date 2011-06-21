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

public class DiagramSource extends DiagramShape {
	
	public DiagramSource() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", "Source"));
		_properties.add(new Property("mcomponent.distribution.ObjectCreator", "Object Creator", null));
		_properties.add(new Property("mcomponent.distribution.Distribution", "Distribution", null));
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.OUTPORT);
		}
		catch (Exception e){}
	}
}