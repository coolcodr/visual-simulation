package mcomponent.queue;

public class PriorityModelRandom extends PriorityModel {
    public int getPriority(Object _object) {
        return (int) (Math.random() * 10);
    }
}
