package diagram;

import java.util.*;
import java.lang.*;
import java.lang.reflect.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PropertiesSetting {
	
	public PropertiesSetting() {
	}
	
	public void set(Object parent, String methodName, Object value) {
		try {
			Class parentType = parent.getClass();
			Class[] para = new Class[1];
			para[0] = value.getClass();
			Method setMethod = parentType.getDeclaredMethod(methodName, para);
			Object[] validPara = new Object[1];
			validPara[0] = value;
			setMethod.invoke(parent, validPara);
		}
		catch(Exception e) {
			System.out.println("Setting s-- " + e);
		}
	}
	
	public Object get(Object parent, String methodName) {
		try {
			Class parentType = parent.getClass();
			Class[] para = new Class[1];
			Method getMethod = parentType.getDeclaredMethod(methodName, null);
			return getMethod.invoke(parent, null);
		}
		catch(Exception e) {
			System.out.println("Setting g-- " + e);
			return null;
		}
	}
}