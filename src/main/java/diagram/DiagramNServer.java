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

public class DiagramNServer extends DiagramShape {

	public DiagramNServer() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", new String("NServer")));
		_properties.add(new Property("mcomponent.distribution.Transform", "Transform", null));
		_properties.add(new Property("mcomponent.distribution.Distribution", "Distribution", null));
		_properties.add(new Property("java.lang.Integer", "Server No", new Integer(2)));
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.OUTPORT);
			addPort(this, DiagramPort.INPORT);
		}
		catch (Exception e){}
	}
}