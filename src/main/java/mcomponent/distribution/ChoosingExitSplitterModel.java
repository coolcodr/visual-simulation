package mcomponent.distribution;

public class ChoosingExitSplitterModel extends SplitterModel {
    public void start() {
        setOutputNo(((int) (Math.random() * 2)));
    }
}
