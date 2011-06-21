package animation;

import java.awt.*;
import java.awt.geom.*;

public class BlueRotatingRect extends Animation {
	
	int angle;
    Image offscreen;
    int imagewidth, imageheight;
    
	public BlueRotatingRect() {
		setSize(new Dimension(50,50));
		setStartStatus(false);
		angle = 0;
	}
	
	public void paintComponent(Graphics g) {
		if(this.getStartStatus()) {
			Graphics2D g2 = (Graphics2D)g;
			AffineTransform transForm = AffineTransform.getRotateInstance(Math.PI*angle/360,25,25);
			g2.transform(transForm);
			g2.setColor(Color.blue);
			g2.fillRect(12,12,25,25);
			angle = angle +10;
			if(angle>360)
				angle =0;
		}
	}
}