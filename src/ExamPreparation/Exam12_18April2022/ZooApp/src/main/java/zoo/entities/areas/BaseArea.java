package zoo.entities.areas;

import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static zoo.common.ExceptionMessages.*;

public abstract class BaseArea implements Area {
    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public int sumCalories() {
        return this.foods
                .stream()
                .mapToInt(Food::getCalories)
                .sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() >= this.capacity) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }

    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);

    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (%s):", name, getClass().getSimpleName())).append(System.lineSeparator());
        sb.append("Animals: ").append(this.animals.isEmpty()
                        ? "none"
                        : this.animals.stream().map(Animal::getName).collect(Collectors.joining(" ")))
                .append(System.lineSeparator());
        sb.append(String.format("Foods: %d", this.foods.size())).append(System.lineSeparator());
        sb.append(String.format("Calories: %d", sumCalories())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
