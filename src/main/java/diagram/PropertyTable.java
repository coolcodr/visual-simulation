package diagram;

import javax.swing.*;
import java.util.*;

public class PropertyTable extends JTable {
	
	private Vector _properties;
	
	public PropertyTable() {
		
	}
	
	public void setProperties(Vector ps) {
		_properties = ps;
	}
	
	public Vector getProperties() {
		return _properties;
	}
}