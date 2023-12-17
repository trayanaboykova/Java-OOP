package zoo.entities.foods;

public abstract class BaseFood implements Food {
    private int calories;
    private double price;

    public BaseFood(int calories, double price) {
        setCalories(calories);
        setPrice(price);
    }

    @Override
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
