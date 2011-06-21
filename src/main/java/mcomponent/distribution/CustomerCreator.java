package mcomponent.distribution;

public class CustomerCreator extends ObjectCreator {
    private String _identity;
    private int _customerCount = 0;

    public CustomerCreator() {
    }

    public Object createObject() {
        _customerCount++;
        return (new Customer("Customer" + _customerCount, null));
    }

}
