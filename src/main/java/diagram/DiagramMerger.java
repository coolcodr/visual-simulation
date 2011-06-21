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

public class DiagramMerger extends DiagramShape {

	public DiagramMerger() {
		super();
		
		_properties.add(new Property("java.lang.String", "Name", new String("Merger")));
		_properties.add(new Property("mcomponent.distribution.MergerModel", "MergerModel", null));
		_properties.add(new Property("java.lang.Integer", "Input No", new Integer(2)));
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.INPORT).setPortIndex(0);
			addPort(this, DiagramPort.INPORT).setPortIndex(1);
			addPort(this, DiagramPort.OUTPORT);
		}
		catch (Exception e){};
	}
}