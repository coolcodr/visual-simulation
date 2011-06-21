package statistic;

public class StatisticData {

    private Object _eventSource;
    private double _captureTime;
    private String _captureType;
    private Object _object;

    public StatisticData(Object _source, Object _object, String _type, double _time) {
        setSource(_source);
        setObject(_object);
        setType(_type);
        setTime(_time);
    }

    public void setTime(double _time) {
        _captureTime = _time;
    }

    public void setSource(Object _source) {
        _eventSource = _source;
    }

    public void setType(String _type) {
        _captureType = _type;
    }

    public void setObject(Object _obj) {
        _object = _obj;
    }

    public Object getSource() {
        return (_eventSource);
    }

    public double getTime() {
        return (_captureTime);
    }

    public String getType() {
        return (_captureType);
    }

    public Object getObject() {
        return (_object);
    }

    public String toString() {
        return _eventSource + "	" + _object + "	" + _captureType + "	" + _captureTime;
    }
}
