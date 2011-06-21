package designer.deployment;

/**
 * Title:
 * Description:
 * Company:
 * @author
 */
import java.io.Serializable;

public class FrameIDandName implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7229871022971675054L;
    private String ID;
    private String name;

    public FrameIDandName(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void replaceValue(FrameIDandName newObject) {
        ID = newObject.getID();
        name = newObject.getName();
        // ( (SimPropertyChoice)
        // DiagramSourceTable.selectedObject).setFrameID(ID);
    }

    public String toString() {
        return getName();
    }

    public void setID(String i) {
        ID = i;
    }

    public void setName(String s) {
        name = s;
    }
}
