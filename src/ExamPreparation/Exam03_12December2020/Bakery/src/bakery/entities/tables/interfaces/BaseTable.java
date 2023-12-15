package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseTable implements Table {
    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private Collection<BakedFood> foodOrders;
    //accessible only by the base class
    private Collection<Drink> drinkOrders;
    //accessible only by the base class
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    //the count of people who want a table.
    private double pricePerPerson;
    private boolean isReserved;
    //returns true if the table is reserved, otherwise false.
    private double price;
    //calculates the price for all people


    public BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.setFoodOrders(new ArrayList<>());
        this.setDrinkOrders(new ArrayList<>());
        this.setTableNumber(tableNumber);
        this.setCapacity(capacity);
        this.setPricePerPerson(pricePerPerson);
        this.isReserved = false;
    }

    public Collection<BakedFood> getFoodOrders() {
        return this.foodOrders;
    }

    public void setFoodOrders(Collection<BakedFood> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public Collection<Drink> getDrinkOrders() {
        return this.drinkOrders;
    }

    public void setDrinkOrders(Collection<Drink> drinkOrders) {
        this.drinkOrders = drinkOrders;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }

        this.capacity = capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.setPrice(this.pricePerPerson * this.numberOfPeople);
        this.isReserved = true;
    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {

        double foodsPrice = foodOrders.stream()
                .mapToDouble(BakedFood::getPrice)
                .sum();

        double drinksPrice = drinkOrders.stream()
                .mapToDouble(Drink::getPrice)
                .sum();

        this.price = getPrice() + foodsPrice + drinksPrice;

        return this.price;//currentBill + this.price;
    }

    @Override
    public void clear() {
        this.foodOrders.clear();
        this.drinkOrders.clear();
        this.numberOfPeople = 0;
        this.price = 0;
        this.isReserved = false;
    }

    @Override
    public String getFreeTableInfo() {
        return String.format("Table: %d\n" +
                        "Type: %s\n" +
                        "Capacity: %d\n" +
                        "Price per Person: %.2f",
                this.getTableNumber(),
                this.getClass().getSimpleName(),
                this.getCapacity(),
                this.getPricePerPerson());
    }
}
