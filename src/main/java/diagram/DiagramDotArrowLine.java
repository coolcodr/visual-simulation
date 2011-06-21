package diagram;


/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class DiagramDotArrowLine extends DiagramConnector {

    /**
     * 
     */
    private static final long serialVersionUID = 4500493409860373938L;

    public DiagramDotArrowLine() {
        super();

        _rules = new AnalysisConnectorRules();

        try {
            _rules.setRule(Class.forName("diagram.DiagramStartPort"), Class.forName("diagram.DiagramInPort"));
            _rules.setRule(Class.forName("diagram.DiagramStartPort"), Class.forName("diagram.DiagramOutPort"));
            _rules.setRule(Class.forName("diagram.DiagramStartPort"), Class.forName("diagram.DiagramExitPort"));
            _rules.setRule(Class.forName("diagram.DiagramEndPort"), Class.forName("diagram.DiagramInPort"));
            _rules.setRule(Class.forName("diagram.DiagramEndPort"), Class.forName("diagram.DiagramOutPort"));
            _rules.setRule(Class.forName("diagram.DiagramEndPort"), Class.forName("diagram.DiagramExitPort"));
            // add by Kenny
            _rules.setRule(Class.forName("diagram.DiagramDataSourcePort"), Class.forName("diagram.DiagramChartPort"));
        } catch (Exception e) {
        }
    }

    public DiagramDotArrowLine(DiagramElementType t) {
        super(t);
    }
}
