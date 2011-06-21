package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import mcomponent.distribution.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramSplitter extends DiagramShape {

	public DiagramSplitter() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", new String("Splitter")));
		_properties.add(new Property("mcomponent.distribution.SplitterModel", "SplitterModel", new DefaultSplitterModel2()));
		_properties.add(new Property("java.lang.Integer", "Output No", new Integer(2)));
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.INPORT);
			addPort(this, DiagramPort.OUTPORT).setPortIndex(0);
			addPort(this, DiagramPort.OUTPORT).setPortIndex(1);
		}
		catch (Exception e){};
	}
}