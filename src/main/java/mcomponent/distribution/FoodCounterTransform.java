package mcomponent.distribution;

public class FoodCounterTransform extends Transform {

    public void start() {

        while (((Customer) getObject()).getFoodList().search(FoodList._FoodOrder) != -1) {
            ((Customer) getObject()).getFoodList().delete(FoodList._FoodOrder);
            ((Customer) getObject()).getFoodList().enqueue(FoodList._Food);
        }
    }
}
