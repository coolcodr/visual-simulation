package mcomponent.distribution;

public class CashierTransform extends Transform {
    public void start() {
        int _nextFood = ((int) (Math.random() * 3));

        while (_nextFood != 0) {
            if (_nextFood == 1) {
                ((Customer) getObject()).getFoodList().enqueue(FoodList._FoodOrder);
            } else if (_nextFood == 2) {
                ((Customer) getObject()).getFoodList().enqueue(FoodList._DrinkOrder);
            }
            _nextFood = ((int) (Math.random() * 3));
        }
    }
}
