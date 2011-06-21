package engine;

import engine.eventhandler.TerminationEventHandler;
import engine.eventhandler.TimeEventHandler;

public class SimSystemData {
    private double _currentTime = 0;
    private FEL _fel = new FEL();
    private int _activeCount = 0;
    private SystemThread _systemThread;

    private boolean _terminate = false;

    public SimSystemData() {
        _systemThread = new SystemThread();
        _systemThread.start();
    }

    public synchronized void addEvent(Event _event) {
        _fel.enqueue(_event);
    }

    public synchronized void executeNextEvent() {
        if (!_fel.isEmpty()) {
            Event _event = _fel.dequeue();

            if (_event.getValidity()) {

                _currentTime = _event.getTime();
                Object _source = _event.getSource();

                if (_event.getType().equals("Hold")) {
                    ((SimThread) _source).cont();
                }

                else if (_event.getType().equals("Time")) {
                    ((TimeEventHandler) _source).processTimeEvent(_event);
                } else if (_event.getType().equals("Termination")) {
                    _terminate = true;
                    ((TerminationEventHandler) _source).processTerminationEvent(_event);
                }
            }
        }
    }

    public synchronized void incrementActiveCount() {
        _activeCount++;
    }

    public synchronized void decrementActiveCount() {
        _activeCount--;
    }

    public synchronized int getActiveCount() {
        return (_activeCount);
    }

    public synchronized double getCurrentTime() {
        return (_currentTime);
    }

    public Event searchEvent(Object _source, Object _object) {
        return _fel.searchEvent(_source, _object);
    }

    public boolean isTerminate() {
        return _terminate;
    }

}
