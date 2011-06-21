package mcomponent;

import mcomponent.distribution.Distribution;
import mcomponent.queue.MessageQueue;
import statistic.AnalysisTool;
import engine.SimThread;

public class Sink extends SimThread {
    private Object _input;
    private Distribution _distribution;

    public Sink(String _id) {
        super(_id);
    }

    public void setInput(Object _mq) {
        _input = _mq;
    }

    public synchronized void run() {

        int counter = 0;

        while (!getStop()) {
            Object _object = ((MessageQueue) _input).send();
            System.out.println("Sink:[" + getName() + "] <<<<" + _object);
            AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", getSimSystemData().getCurrentTime());
        }

//		destroy();
    }
}
