package diagram;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XFileFilter extends FileFilter {

    // Accept all directories and all gif, jpg, or tiff files.
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
        return "XML document(*.xml)";
    }
}
