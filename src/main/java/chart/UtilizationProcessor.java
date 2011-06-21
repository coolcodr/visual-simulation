package chart;

import statistic.AnalysisTool2;

public class UtilizationProcessor extends DataProcessor {
    private AnalysisTool2 _dataSource;
    private Chart _chart;

    public void processData() {
        int partitionOfTime;
        double[] ary;
        int length;
        Presentation presentation;

        // partitionOfTime=_dataSource.getPartitionOfTime();
        ary = _dataSource.getUtilizations();
        length = ary.length;

        _chart.setNumOfSampleValue(length);

        for (int i = length - 1; 0 <= i; i--) {
            _chart.addSampleValue(ary[i] * 100.0);
        }
        _chart.setTitle("Utilization of " + _dataSource.toString());
        _chart.setXAxisUnit("Time");
        _chart.setYAxisUnit("Utilization(%)");
        // presentation=_chart.getPresentation();
    }

    public AnalysisTool2 getAnalysisTool2() {
        return _dataSource;
    }

    public void setAnalysisTool2(AnalysisTool2 dataSource) {
        _dataSource = dataSource;
    }

    public Chart getChart() {
        return _chart;
    }

    public void setChart(Chart chart) {
        _chart = chart;
    }
}
