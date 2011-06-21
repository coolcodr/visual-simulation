package print;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ReportFileFilter extends FileFilter
{
    public boolean accept(File f)
    {
        if (f.isDirectory())
        {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null)
        {
            if (extension.equals(Utils.vsr))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public String getDescription()
    {
        return "VSim Report (*.vsr)";
    }
}
class Utils
{
    public final static String vsr = "vsr";

    public static String getExtension(File f)
    {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1)
        {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
