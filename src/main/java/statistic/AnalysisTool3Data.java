package statistic;

import java.util.*;
import java.lang.*;

import engine.*;

public class AnalysisTool3Data {
	
	private double _startTime;
	private double _serviceTime;
	
	public AnalysisTool3Data(double startTime, double serviceTime) {
		setStartTime(startTime);
		setServiceTime(serviceTime);
	}
	
	public void setStartTime(double startTime) {
		_startTime = startTime;
	}
	
	public void setServiceTime(double serviceTime) {
		_serviceTime = serviceTime;
	}
	
	public double getStartTime() {
		return _startTime;
	}
	
	public double getServiceTime() {
		return _serviceTime;
	}
	
	public String toString() {
		return _startTime + "	,	" + _serviceTime;
	}
}