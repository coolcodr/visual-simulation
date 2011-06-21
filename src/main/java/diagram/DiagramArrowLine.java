package diagram;


/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramArrowLine extends DiagramConnector {

//	private static ConnectorRules _rules;

    /**
     * 
     */
    private static final long serialVersionUID = 6934738975586743052L;

    public DiagramArrowLine() {
        super();

        try {
            _rules.setRule(Class.forName("diagram.DiagramOutPort"), Class.forName("diagram.DiagramInPort"));
            _rules.setRule(Class.forName("diagram.DiagramExitPort"), Class.forName("diagram.DiagramInPort"));
        } catch (Exception e) {
        }
    }

    public DiagramArrowLine(DiagramElementType t) {
        super(t);
    }
}
