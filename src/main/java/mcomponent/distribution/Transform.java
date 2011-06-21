package mcomponent.distribution;

abstract public class Transform {
    private Object _object;

    public Transform() {
    }

    public void setObject(Object _obj) {
        _object = _obj;
    }

    abstract public void start();

    public Object getObject() {
        return _object;
    }
}
