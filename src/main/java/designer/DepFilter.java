package designer;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

class DesFileFilter extends FileFilter
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
            if (extension.equals(Utils.des))
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
        return "Designer Project (*.vsd)";
    }
}

class DepFileFilter extends FileFilter
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
            if (extension.equals(Utils.dep))
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
        return "Deployment file (*.vsx)";
    }
}
class BatFileFilter extends FileFilter
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
            if (extension.equals(Utils.bat))
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
        return "Deployment Model and Execute Files (*.jar, *.bat, *.vsx)";
    }
}
class BatFileFilter2 extends FileFilter
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
            if (extension.equals(Utils.bat))
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
        return "Deployment Execute Files (*.bat, *.vsx)";
    }
}

class Utils
{
    public final static String xml = "xml";
    public final static String des = "vsd";
    public final static String dep = "vsx";
    public final static String bat = "bat";

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
