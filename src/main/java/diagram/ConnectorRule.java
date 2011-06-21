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

public class ConnectorRule {
	
	private Object[] _rule = new Object[2];
	
	public ConnectorRule(Object one, Object two) {
		setRule(0, one);
		setRule(1, two);
	}
	
	public void setRule(int index, Object object) {
		_rule[index] = object;
	}
	
	public Object getRule(int index) {
		return _rule[index];
	}
}