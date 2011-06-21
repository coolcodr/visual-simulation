package chart;

import statistic.AnalysisTool2;

public abstract class DataProcessor {

    public abstract void processData();

    public abstract AnalysisTool2 getAnalysisTool2();

    public abstract void setAnalysisTool2(AnalysisTool2 dataSource);

    public abstract Chart getChart();

    public abstract void setChart(Chart chart);
}
