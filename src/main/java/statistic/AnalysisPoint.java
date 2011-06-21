package statistic;

public class AnalysisPoint {
	
	private Object _source;
	private String _type;
	
	public AnalysisPoint(Object source, String type) {
		setSource(source);
		setType(type);
	}
	
	public void setSource(Object source) {
		_source = source;
	}
	
	public void setType(String type) {
		_type = type;
	}
	
	public Object getSource() {
		return _source;
	}
	
	public String getType() {
		return _type;
	}
	
	public String toString() {
		return ("Point : " + _source + ", " + _type);
	}
}