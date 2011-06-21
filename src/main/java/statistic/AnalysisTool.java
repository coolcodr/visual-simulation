package statistic;

import java.util.Vector;

public abstract class AnalysisTool {

    public static AnalysisInfo _analysisInfo = new AnalysisInfo();

    private String _id;

    private Vector _startPoints;
    private Vector _endPoints;

    private double _startTime;
    private double _analyzeTime;

    public AnalysisTool() {
        _startPoints = new Vector();
        _endPoints = new Vector();

        setStartTime(0);
        setAnalyzeTime(-1); // -1 for infinite analyze time

        _analysisInfo.addAnalysisTool(this);
    }

    public AnalysisTool(String id) {
        this();

        setID(id);
    }

    public void setID(String id) {
        _id = id;
    }

    public void setStartTime(double startTime) {
        _startTime = startTime;
    }

    public void setAnalyzeTime(double analyzeTime) {
        _analyzeTime = analyzeTime;
    }

    public void addStartPoint(Object source, String type) {
        _startPoints.add(new AnalysisPoint(source, type));
    }

    public void addEndPoint(Object source, String type) {
        _endPoints.add(new AnalysisPoint(source, type));
    }

    public String getID() {
        return _id;
    }

    public double getStartTime() {
        return _startTime;
    }

    public double getAnalyzeTime() {
        return _analyzeTime;
    }

    public boolean isAnalyzeTime(double time) {
        if (time >= getStartTime()) {
            if (getAnalyzeTime() == -1) {
                return true;
            }

            return (time - getStartTime() <= getAnalyzeTime());
        }

        return false;
    }

    public AnalysisPoint getStartPoint(int index) {
        return (AnalysisPoint) _startPoints.elementAt(index);
    }

    public AnalysisPoint getEndPoint(int index) {
        return (AnalysisPoint) _endPoints.elementAt(index);
    }

    public Object getStartPointSource(int index) {
        return ((AnalysisPoint) _startPoints.elementAt(index)).getSource();
    }

    public Object getEndPointSource(int index) {
        return ((AnalysisPoint) _endPoints.elementAt(index)).getSource();
    }

    public String getStartPointType(int index) {
        return ((AnalysisPoint) _startPoints.elementAt(index)).getType();
    }

    public String getEndPointType(int index) {
        return ((AnalysisPoint) _endPoints.elementAt(index)).getType();
    }

    public Vector getStartPoints() {
        return _startPoints;
    }

    public Vector getEndPoints() {
        return _endPoints;
    }

    public void displayStartPoints() {
        for (int i = 0; i < _startPoints.size(); i++) {
            System.out.println(_startPoints.elementAt(i));
        }
    }

    public void displayEndPoints() {
        for (int i = 0; i < _endPoints.size(); i++) {
            System.out.println(_endPoints.elementAt(i));
        }
    }

    public boolean isStartPoint(Object source, String type) {
        for (int i = 0; i < _startPoints.size(); i++) {
            if ((getStartPointSource(i) == source) && (getStartPointType(i).equals(type))) {
                return true;
            }
        }

        return false;
    }

    public boolean isEndPoint(Object source, String type) {
        for (int i = 0; i < _endPoints.size(); i++) {
            if ((getEndPointSource(i) == source) && (getEndPointType(i).equals(type))) {
                return true;
            }
        }

        return false;
    }

    public static AnalysisInfo getAnalysisInfo() {
        return _analysisInfo;
    }

    public String toString() {
        return _id;
    }

    abstract public void processStartPoint(StatisticData data);

    abstract public void processEndPoint(StatisticData data);
}
