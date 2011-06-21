package designer.deployment;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XFileFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null) {
            if (extension.equals(Utils.xml)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String getDescription() {
        return "XML Data file (*.xml)";
    }
}

class Utils {
    public final static String xml = "xml";
    public final static String des = "des";
    public final static String dep = "dep";

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
