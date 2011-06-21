package chart;

import java.util.*;
import java.lang.*;

import engine.*;
import statistic.*;

public abstract class DataProcessor{
	
	public abstract void processData();
	
	public abstract AnalysisTool2 getAnalysisTool2();
	
	public abstract void setAnalysisTool2(AnalysisTool2 dataSource);
	
	public abstract Chart getChart();
	
	public abstract void setChart(Chart chart);
}
