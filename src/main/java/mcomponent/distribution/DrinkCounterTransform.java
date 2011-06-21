package mcomponent.distribution;

public class DrinkCounterTransform extends Transform {
    public void start() {
        while (((Customer) getObject()).getFoodList().search(FoodList._DrinkOrder) != -1) {
            ((Customer) getObject()).getFoodList().delete(FoodList._DrinkOrder);
            ((Customer) getObject()).getFoodList().enqueue(FoodList._Drink);
        }
    }
}
