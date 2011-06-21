package diagram;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.lang.reflect.*;

public interface PropertyEditorComponent {
	
	public void setObject(Object object);
	
	public Object getObject();
}