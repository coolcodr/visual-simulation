package diagram;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramOutPort extends DiagramPort {

    /**
     * 
     */
    private static final long serialVersionUID = 2892895050022984819L;

    public DiagramOutPort() {
        super();
    }

    public void connected(boolean isConnected) {
        setConnectValid(!isConnected);
    }
}
