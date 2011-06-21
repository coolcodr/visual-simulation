package mcomponent;

import mcomponent.distribution.Distribution;
import mcomponent.distribution.Transform;
import mcomponent.queue.MessageQueue;
import statistic.AnalysisTool;
import engine.SimThread;

public class Server extends SimThread {
    private Object _input;
    private Object _output;
    private Distribution _distribution;
    private Transform _transform;

    public Server(String _id) {
        super(_id);
    }

    public void setInput(Object _mq) {
        _input = _mq;
    }

    public void setOutput(Object _mq) {
        _output = _mq;
    }

    public void setDistribution(Distribution _d) {
        _distribution = _d;
    }

    public void setTransform(Transform _tf) {
        _transform = _tf;
    }

    public void run() {
        while (!getStop()) {
            Object _object = ((MessageQueue) _input).send();
            // added by matthew
            System.out.println("Server:[" + getName() + "] <<<<" + _object);
            AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", getSimSystemData().getCurrentTime());

            double _time = _distribution.getNextValue();
            if (_time < 0) {
                _time = (_time * -1);
            }
            _transform.setObject(_object);

            _transform.start();

            hold(this, _time);

            _object = _transform.getObject();

            AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Output", getSimSystemData().getCurrentTime());
            // added by matthew
            System.out.println("Server:[" + getName() + "] >>>>" + _object);
            ((MessageQueue) _output).receive(_object);

        }

//		destroy();
    }
}
