package diagram;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.reflect.*;

public class TableComboBox extends JComboBox implements PropertyEditorComponent {
	
	private Object _object;
	
	public TableComboBox(Object[] items) {
		super(items);
	}
	
	public void setObject(Object object) {
		_object = object;
	}
	
	public Object getObject() {
		return _object;
	}
}