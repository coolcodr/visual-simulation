package animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Man extends Animation {

    /**
     * 
     */
    private static final long serialVersionUID = 6935125103323735540L;
    Image cover;

    public Man() {
        java.net.URL imageurl = getClass().getResource("man.gif");
        cover = new ImageIcon(imageurl).getImage();
        this.setSize(new Dimension(30, 40));
        setStartStatus(false);
    }

    public void paintComponent(Graphics g) {
        if (getStartStatus()) {
            g.drawImage(cover, 0, 0, getParent());
            setVisible(true);
        }
    }

}
