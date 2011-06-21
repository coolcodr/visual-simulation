package designer;

import java.io.Serializable;

import designer.deployment.DeployObject;

public class DesignerProject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -7197782350152888281L;
    private int gridSize;
    private boolean realTimeMove;
    private int count;

    private DeployObject deployObject;

    public DesignerProject() {
    }

    public void setGridSize(int i) {
        gridSize = i;
    }

    public void setRealTimeMove(boolean b) {
        realTimeMove = b;
    }

    public void setCount(int i) {
        count = i;
    }

    public int getGridSize() {
        return gridSize;
    }

    public boolean getRealTimeMove() {
        return realTimeMove;
    }

    public int getCount() {
        return count;
    }

    public void setDeployObject(DeployObject deployObject) {
        this.deployObject = deployObject;
    }

    public DeployObject getDeployObject() {
        return deployObject;
    }

}
