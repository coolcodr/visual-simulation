package statistic;

import java.util.Vector;

public class DataCollector extends AnalysisTool {

    private Vector _dataList;

    public DataCollector(String id) {
        super(id);

        _dataList = new Vector();
    }

    public void processStartPoint(StatisticData data) {
        addData(data);
    }

    public void processEndPoint(StatisticData data) {
        addData(data);
    }

    public void addData(StatisticData data) {
        _dataList.add(data);
    }

    public StatisticData[] getStatisticData() {
        StatisticData[] result = new StatisticData[_dataList.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = (StatisticData) _dataList.elementAt(i);
        }

        return result;
    }

    public void display() {
        for (int i = 0; i < _dataList.size(); i++) {
            System.out.println(_dataList.elementAt(i));
        }
    }
}
