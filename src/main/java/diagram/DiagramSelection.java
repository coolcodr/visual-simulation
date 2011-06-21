package diagram;
import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramSelection extends JComponent {

	public DiagramSelection() {
		setSize(0,0);
	}

	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
	}
}