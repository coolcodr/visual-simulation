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

public class DiagramMessageQueue extends DiagramShape {
	
	public DiagramMessageQueue() {
		super();
	}
	
	//Testing!!!!
	public void setPort() {
		try {
			addPort(this, DiagramPort.OUTPORT);
			addPort(this, DiagramPort.INPORT);
			addPort(this, DiagramPort.EXITPORT);
		}
		catch (Exception e) {
 		}
	}
	
	public boolean isMessageQueue() {
		return true;
	}
}