package mcomponent.distribution;

public class StringCreator extends ObjectCreator {

    private String _name;
    private int _count;

    public StringCreator() {
    }

    public StringCreator(String name) {
        setName(name);
    }

    public Object createObject() {
        return (_name + " " + _count++);
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
