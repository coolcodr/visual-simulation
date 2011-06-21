package mcomponent.distribution;

public class Customer extends Entity
{
	private String _identity;
	
	private FoodList _foodList = new FoodList();
	
	public Customer(String _id,Object object)
	{
		super(object,"animation.Man");
		_identity = _id;
	}
	
	public FoodList getFoodList()
	{
		return _foodList;
	}
	
	public String toString()
	{
		return _identity;
	}
}