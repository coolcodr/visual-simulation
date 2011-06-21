package mcomponent.distribution;

public class DefaultCreator extends ObjectCreator {

    public DefaultCreator() {
    }

//modified by matthew
//to make the entity lock and unlock mechanism
//inorder to make the animation run logically	
    public Object createObject() {
        return new Entity(null);
    }
}
