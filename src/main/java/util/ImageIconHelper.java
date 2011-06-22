package util;
import javax.swing.ImageIcon;

public class ImageIconHelper {
    public ImageIcon getImageIcon(String path) {
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIconHelper.class.getResource(path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imageIcon;
    }
}
