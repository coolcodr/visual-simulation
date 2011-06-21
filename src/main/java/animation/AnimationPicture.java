package animation;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class AnimationPicture extends Animation {
	
	Image cover;
	
	public AnimationPicture() {
		java.net.URL imageurl = this.getClass().getResource("dukeWaveRed.gif");
		cover = new ImageIcon(imageurl).getImage();
		this.setSize(new Dimension(100,100));
		this.setStartStatus(false);
	}
	
	public void paintComponent(Graphics g) {
		if(this.getStartStatus()) {
			g.drawImage(cover,-5,-5,this.getParent());
			this.setVisible(true);
		}
	}
	
}