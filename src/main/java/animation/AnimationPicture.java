package animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class AnimationPicture extends Animation {

    /**
     * 
     */
    private static final long serialVersionUID = 6851347287438482972L;
    Image cover;

    public AnimationPicture() {
        java.net.URL imageurl = getClass().getResource("dukeWaveRed.gif");
        cover = new ImageIcon(imageurl).getImage();
        this.setSize(new Dimension(100, 100));
        setStartStatus(false);
    }

    public void paintComponent(Graphics g) {
        if (getStartStatus()) {
            g.drawImage(cover, -5, -5, getParent());
            setVisible(true);
        }
    }

}
