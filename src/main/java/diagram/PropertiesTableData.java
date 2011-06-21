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

public class PropertiesTableData {
	
	public static final int PARENT_TYPE = 0; // this property's parent (the Class name)
	public static final int PARENT = 1; // this property's parent (the Class name)
	public static final int NAME = 2; // this property name
	public static final int TYPE = 3; // this property type (the Class name)
	public static final int SET_METHOD = 4; // setMethod of this property
	public static final int GET_METHOD = 5; // getMethod of this property
	public static final int INPUT_METHOD = 6; // the component for user input of this property (the Class name)
	
	String[] _data;
	
	public PropertiesTableData() {
		_data = new String[7];
	}
	
	public void setData(int index, String data) {
		_data[index] = data;
	}
	
	public String getData(int index) {
		return _data[index];
	}
	
	public void setParentType(String data) {
		_data[PARENT_TYPE] = data;
	}
	
	public void setParent(String data) {
		_data[PARENT] = data;
	}
	
	public void setName(String data) {
		_data[NAME] = data;
	}
	
	public void setType(String data) {
		_data[TYPE] = data;
	}
	
	public void setSetMethod(String data) {
		_data[SET_METHOD] = data;
	}
	
	public void setGetMethod(String data) {
		_data[GET_METHOD] = data;
	}
	
	public void setInputMethod(String data) {
		_data[INPUT_METHOD] = data;
	}
	
	public String getParentType() {
		return _data[PARENT_TYPE];
	}
	
	public String getParent() {
		return _data[PARENT];
	}
	
	public String getName() {
		return _data[NAME];
	}
	
	public String getType() {
		return _data[TYPE];
	}
	
	public String getSetMethod() {
		return _data[SET_METHOD];
	}
	
	public String getGetMethod() {
		return _data[GET_METHOD];
	}
	
	public String getInputMethod() {
		return _data[INPUT_METHOD];
	}
}