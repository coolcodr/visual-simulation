package designer.deployment;

import java.io.Serializable;
import java.util.Vector;

public class SimPropertyChoice implements Serializable
{
    private String value;
    private String displayName;
    private boolean display;
    private String frameID;

    private Vector properties = new Vector();
    public SimPropertyChoice(String name)
    {
        value = name;
        displayName = name;
        display = true;
        frameID = "-1"; //TEMP, 0 means the top form, (no popup)
    }

    /*
       public void setFrameIDandName ( FrameIDandName id )
       {
     this.id = id;
       }*/
    public void addProperty(Object[] object)
    {
        properties.add(object);
    }

    public Vector getProperties()
    {
        return properties;
    }
    public String getValue ()
    {
        return value;
    }
    /*
       public FrameIDandName getFrameIDandName ()
       {
     return id;
       }*/
    public void setDisplayName(String name)
    {
        displayName = name;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public String toString()
    {
        return getDisplayName();
    }

    public void setDisplay(boolean b)
    {
        display = b;
    }

    public boolean getDisplay()
    {
        return display;
    }

    public void setFrameID(String i)
    {
        frameID = i;
    }

    public String getFrameID()
    {
        return frameID;
    }
}