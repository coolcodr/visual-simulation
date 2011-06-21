package diagram;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.reflect.*;

public class TableTextField extends JTextField implements PropertyEditorComponent {
	
	Object _object;
	
	public TableTextField() {
		super();
	}
	
	public void setObject(Object object) {
		_object = object;
	}
	
	public Object getObject() {
		return _object;
	}
}
