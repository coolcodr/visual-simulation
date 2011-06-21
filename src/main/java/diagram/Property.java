package diagram;

public class Property {
    private String _type;
    private String _name;
    private Object _value;
    private String _inputMethod;

    public Property(String type, String name, Object value) {
        setType(type);
        setName(name);
        setValue(value);
    }

    public Property(String type, String name, Object value, String inputMethod) {
        this(type, name, value);

        setInputMethod(inputMethod);
    }

    public void setType(String type) {
        _type = type;
    }

    public void setName(String name) {
        _name = name;
    }

    public void setValue(Object value) {
        _value = value;
    }

    public void setInputMethod(String inputMethod) {
        _inputMethod = inputMethod;
    }

    public String getType() {
        return _type;
    }

    public String getName() {
        return _name;
    }

    public Object getValue() {
        return _value;
    }

    public String getInputMethod() {
        return _inputMethod;
    }
}
