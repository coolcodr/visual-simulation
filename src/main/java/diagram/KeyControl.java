package diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public interface KeyControl {
	final public static DefaultKeyControl _defaultKeyControl = new DefaultKeyControl();
	final public static DeleteKeyControl _deleteKeyControl = new DeleteKeyControl();
	final public static MoveShapeKeyControl _moveShapeKeyControl = new MoveShapeKeyControl();
	
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);
	
	public void keyTyped(KeyEvent e);
}