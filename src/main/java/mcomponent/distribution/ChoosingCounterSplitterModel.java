package mcomponent.distribution;

public class ChoosingCounterSplitterModel extends SplitterModel {
    public void start() {
        if (((Customer) getObject()).getFoodList().search(FoodList._FoodOrder) != -1) {
            setOutputNo(1);
        } else if (((Customer) getObject()).getFoodList().search(FoodList._DrinkOrder) != -1) {
            setOutputNo(2);
        } else {
            setOutputNo(0);
        }
    }
}
