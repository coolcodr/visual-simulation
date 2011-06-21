package animation;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class GreenMan extends Animation {
	
	Image cover;
	
	public GreenMan() {
		java.net.URL imageurl = this.getClass().getResource("greenman.gif");
		cover = new ImageIcon(imageurl).getImage();
		this.setSize(new Dimension(30,40));
		this.setStartStatus(false);
	}
	
	public void paintComponent(Graphics g) {
		if(this.getStartStatus()) {
			g.drawImage(cover,0,0,this.getParent());
			this.setVisible(true);
		}
	}
	
}