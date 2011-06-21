package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramArrowLine extends DiagramConnector {
	
//	private static ConnectorRules _rules;
	
	public DiagramArrowLine()
	{
		super();
		
		try {
			_rules.setRule(Class.forName("diagram.DiagramOutPort"), Class.forName("diagram.DiagramInPort"));
			_rules.setRule(Class.forName("diagram.DiagramExitPort"), Class.forName("diagram.DiagramInPort"));
		}
		catch(Exception e) {}
	}
	
	public DiagramArrowLine (DiagramElementType t)
	{
		super(t);
	}
}