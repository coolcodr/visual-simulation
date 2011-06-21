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

public class AnalysisConnectorRules extends ConnectorRules{
	
	public boolean matchOne(DiagramElement d) {
		
		ConnectorRule rule;
		
		for (int i = 0; i < _rules.size(); i++) {
			rule = (ConnectorRule) _rules.elementAt(i);
			if (rule.getRule(0) == d.getClass()) {
				setOne(d.getClass());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean matchTwo(DiagramElement d) {
		
		ConnectorRule rule;
		
		for (int i = 0; i < _rules.size(); i++) {
			rule = (ConnectorRule) _rules.elementAt(i);
			if ((rule.getRule(0) == _one) && (rule.getRule(1) == d.getClass())) {
				return true;
			}
		}
		
		return false;
	}
}