package mcomponent.distribution;

import mcomponent.queue.Queue;

public class EnterQueueMergerModel extends MergerModel {
    public void start() {
        int _max = ((Queue) (getMerger().getInput()[0])).getLength();
        setInputNo(0);

        for (int _i = 1; _i < getMerger().getInput().length; _i++) {
            if (((Queue) (getMerger().getInput()[_i])).getLength() > _max) {
                _max = ((Queue) (getMerger().getInput()[_i])).getLength();
                setInputNo(_i);
            }
        }
    }
}
