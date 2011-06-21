package statistic;

import java.util.Vector;

import engine.SimThread;

public class AnalysisTool2 extends DataCollector {

    private StatisticData[] _data;

    private AnalysisTool3Data[] _times;

    private double _startTime;
    private double _analyzeTime;

    private double[] _serviceTimes;
    private int[] _throughPut;
    private int[] _objectInComponent;
    private double[] _utilizations;

    public AnalysisTool2(String id) {
        super(id);
    }

    public void cal() {

        _data = getStatisticData();

        double startTime, analyzeTime;

        startTime = super.getStartTime();
        analyzeTime = super.getAnalyzeTime();

        if (analyzeTime == -1) {
            analyzeTime = SimThread.getSimSystemData().getCurrentTime();
        }

        Vector startData = new Vector();
        Vector times = new Vector();

        for (int i = 0; i < _data.length; i++) {
            if (_data[i].getType().equals("Input")) {
                startData.add(_data[i]);
            } else {
                for (int j = 0; j < startData.size(); j++) {
                    StatisticData startDataTemp = (StatisticData) startData.elementAt(j);

                    if (startDataTemp.getObject() == _data[i].getObject()) {
                        times.add(new AnalysisTool3Data(startDataTemp.getTime(), _data[i].getTime() - startDataTemp.getTime()));
                        startData.remove(j);
                        j = startData.size() + 1;
                    }
                }
            }
        }

        for (int i = 0; i < startData.size(); i++) {
            StatisticData startDataTemp = (StatisticData) startData.elementAt(i);

            times.add(new AnalysisTool3Data(startDataTemp.getTime(), -1));
        }

        _times = new AnalysisTool3Data[times.size()];

        for (int i = 0; i < times.size(); i++) {
            _times[i] = (AnalysisTool3Data) times.elementAt(i);
        }

        int partSize = 20;

        double[] serviceTimes = new double[partSize];
        int[] throughPut = new int[partSize];
        int[] objectInComponent = new int[partSize];
        double[] utilizations = new double[partSize];

        for (int i = 0; i < partSize; i++) {
            serviceTimes[i] = 0;
            throughPut[i] = 0;
            objectInComponent[i] = 0;
            utilizations[i] = 0;
        }

        // to calculate the TotalServiceTime, ThroughtPut, ObjectInComponent
        // during the time-lots

        for (int i = 0; i < _times.length; i++) {

            if (_times[i].getServiceTime() == -1) {
                int partIndex = 0;
                while ((_times[i].getStartTime() >= (startTime + analyzeTime) / partSize * (partIndex + 1)) && (partIndex < partSize)) {
                    partIndex++;
                }

                if (partIndex < partSize) {
                    serviceTimes[partIndex] += (startTime + analyzeTime) / partSize * (partIndex + 1) - _times[i].getStartTime();
                    throughPut[partIndex]++;
                    objectInComponent[partIndex]++;

                    partIndex++;
                    for (partIndex = partIndex; partIndex < partSize; partIndex++) {
                        serviceTimes[partIndex] += (startTime + analyzeTime) / partSize;
                        objectInComponent[partIndex]++;
                    }
                }
            } else {
                for (int partIndex = 0; partIndex < partSize; partIndex++) {
                    if ((_times[i].getStartTime() >= (startTime + analyzeTime) / partSize * partIndex) && (_times[i].getStartTime() < (startTime + analyzeTime) / partSize * (partIndex + 1))) {
                        serviceTimes[partIndex] += _times[i].getServiceTime();
                        throughPut[partIndex]++;

                        if ((_times[i].getStartTime() + _times[i].getServiceTime()) > (startTime + analyzeTime) / partSize * (partIndex + 1)) {
                            serviceTimes[partIndex] -= (_times[i].getStartTime() + _times[i].getServiceTime()) - (startTime + analyzeTime) / partSize * (partIndex + 1);

                            int temp = partIndex + 1;

                            while (((_times[i].getStartTime() + _times[i].getServiceTime()) >= (startTime + analyzeTime) / partSize * (temp)) && (temp < partSize)) {
                                if ((_times[i].getStartTime() + _times[i].getServiceTime()) < (startTime + analyzeTime) / partSize * (temp + 1)) {
                                    serviceTimes[temp] += (_times[i].getStartTime() + _times[i].getServiceTime()) - (startTime + analyzeTime) / partSize * (partIndex + 1);
                                } else {
                                    serviceTimes[temp] += (startTime + analyzeTime) / partSize;
                                }

                                objectInComponent[temp - 1]++;

                                temp++;
                            }
                        }

                        partIndex = partSize;
                    }
                }
            }
        }

        // to calculate the Utilization

        for (int partIndex = 0; partIndex < partSize; partIndex++) {
            utilizations[partIndex] = serviceTimes[partIndex] / ((startTime + analyzeTime) / partSize);
        }

        _startTime = startTime;
        _analyzeTime = analyzeTime;

        _serviceTimes = serviceTimes;
        _throughPut = throughPut;
        _objectInComponent = objectInComponent;
        _utilizations = utilizations;
    }

    public void display() {
        cal();

        AnalysisTool3Report report = new AnalysisTool3Report();

        report.setData(this, _startTime, _analyzeTime, _serviceTimes, _throughPut, _objectInComponent, _utilizations);

        report.display();
    }

    public static void displayAll() {
        try {
            AnalysisTool2[] analysisTools = _analysisInfo.getAnalysisTools();

            double serviceTimeMean[] = new double[analysisTools.length];
            double utilizations[] = new double[analysisTools.length];

            for (int i = 0; i < analysisTools.length; i++) {
                int throughPut = 0;

                analysisTools[i].cal();
                for (int j = 0; j < analysisTools[i].getServiceTimes().length; j++) {
                    serviceTimeMean[i] += analysisTools[i].getServiceTimes()[j];
                    utilizations[i] += analysisTools[i].getUtilizations()[j];

                    throughPut += analysisTools[i].getThroughPut()[j];
                }
                serviceTimeMean[i] = serviceTimeMean[i] / throughPut;
                utilizations[i] = utilizations[i] / analysisTools[i].getServiceTimes().length;
            }

            SummaryReport sReport = new SummaryReport();
            sReport.setData(analysisTools, serviceTimeMean, utilizations);
            sReport.display();
        } catch (Exception e) {
        }
    }

    public double getStartTime() {
        return _startTime;
    }

    public double getAnalyzeTime() {
        return _analyzeTime;
    }

    public double[] getServiceTimes() {
        return _serviceTimes;
    }

    public int[] getThroughPut() {
        return _throughPut;
    }

    public int[] getObjectInComponent() {
        return _objectInComponent;
    }

    public double[] getUtilizations() {
        return _utilizations;
    }
}
