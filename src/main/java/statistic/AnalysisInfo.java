package statistic;

import java.util.*;

public class AnalysisInfo {
	
	private Vector _analysisTools = new Vector();
	
	public void addAnalysisTool(AnalysisTool analysisTool) {
		_analysisTools.add(analysisTool);
	}
	
	public void addStatisticData(Object source, Object object, String type, double time) {
		
		StatisticData data = new StatisticData(source, object, type, time);
		
		AnalysisTool analysisTool;
		
		for (int i = 0; i < _analysisTools.size(); i++) {
			analysisTool = (AnalysisTool) _analysisTools.elementAt(i);
			
			if (analysisTool.isStartPoint(source, type)) {
				analysisTool.processStartPoint(data);
			}
			
			if (analysisTool.isEndPoint(source, type)) {
				analysisTool.processEndPoint(data);
			}
		}
		
		
	}
	
	public void displayAnalysisTools() {
		
		for (int i = 0; i < _analysisTools.size(); i++) {
			System.out.println(_analysisTools.elementAt(i));
		}
	}
	
	public AnalysisTool2[] getAnalysisTools() {
		AnalysisTool2[] result = new AnalysisTool2[_analysisTools.size()];
		
		for (int i = 0; i < _analysisTools.size(); i++) {
			result[i] = (AnalysisTool2) _analysisTools.elementAt(i);
		}
		
		return result;
	}
}