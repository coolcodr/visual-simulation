package diagram;

import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class ConnectorRules {
	protected Vector _rules = new Vector();
	
	protected Object _one; // Class of the Connector Source
//	private boolean _isOneMessageQueue;
	
	public void setRule(Object one, Object two) {
		_rules.add(new ConnectorRule(one, two));
	}
	
	public void setRule(int index, Object one, Object two) {
		_rules.add(index, new ConnectorRule(one, two));
	}
	
	public void setOne(Object object) {
		_one = object;
	}
	
	public boolean matchOne(DiagramElement d) {
		
		if (d.getConnectValid()) {
			
			ConnectorRule rule;
			
			for (int i = 0; i < _rules.size(); i++) {
				rule = (ConnectorRule) _rules.elementAt(i);
				if (rule.getRule(0) == d.getClass()) {
					setOne(d.getClass());
//					_isOneMessageQueue = d.isMessageQueue();
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean matchTwo(DiagramElement d) {
		
//		if ( (d.getConnectValid()) && (d.isMessageQueue() != _isOneMessageQueue) ) {
		
		if ( (d.getConnectValid()) ) {
			
			ConnectorRule rule;
			
			for (int i = 0; i < _rules.size(); i++) {
				rule = (ConnectorRule) _rules.elementAt(i);
				if ((rule.getRule(0) == _one) && (rule.getRule(1) == d.getClass())) {
					return true;
				}
			}
		}
		
		return false;
	}
}