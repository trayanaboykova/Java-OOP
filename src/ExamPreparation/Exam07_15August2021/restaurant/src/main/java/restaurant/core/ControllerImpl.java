package restaurant.core;

import restaurant.common.enums.BeveragesType;
import restaurant.common.enums.HealthyFoodType;
import restaurant.common.enums.TableType;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Food;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.ExceptionMessages.*;
import static restaurant.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private double totalMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository, BeverageRepository<Beverages> beverageRepository, TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.totalMoney = 0;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        Food food = null;
        HealthyFoodType healthyFoodType = HealthyFoodType.valueOf(type);
        switch (healthyFoodType) {
            case Salad:
                food = new Salad(name, price);
                break;
            case VeganBiscuits:
                food = new VeganBiscuits(name, price);
                break;
        }
        if (this.healthFoodRepository.getAllEntities().contains(food)) {
            throw new IllegalArgumentException(String.format(FOOD_EXIST, name));
        }
        this.healthFoodRepository.add(food);
        return String.format(FOOD_ADDED, name);
    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage = null;
        BeveragesType beverageType = BeveragesType.valueOf(type);
        switch (beverageType){
            case Smoothie:
                beverage = new Smoothie(name, counter, brand);
                break;
            case Fresh:
                beverage = new Fresh(name, counter, brand);
                break;
        }
        if (this.beverageRepository.getAllEntities().contains(beverage)){
            throw new IllegalArgumentException(String.format(BEVERAGE_EXIST,name));
        }
        this.beverageRepository.add(beverage);
        return String.format(BEVERAGE_ADDED,type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        TableType tableType = TableType.valueOf(type);
        switch (tableType){
            case Indoors:
                table = new Indoors(tableNumber,capacity);
                break;
            case InGarden:
                table = new InGarden(tableNumber,capacity);
                break;
        }
        for (Table currentTable : this.tableRepository.getAllEntities()){
            if (currentTable.getTableNumber() == table.getTableNumber()){
                throw new IllegalArgumentException(String.format(TABLE_IS_ALREADY_ADDED, tableNumber));
            }
        }

        this.tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserve(int numberOfPeople) {
        Table table = this.tableRepository.getAllEntities()
                .stream()
                .filter(e -> e.getSize() >= numberOfPeople && !e.isReservedTable())
                .findFirst()
                .orElse(null);

        if (table == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        table.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        HealthyFood food = this.healthFoodRepository.foodByName(healthyFoodName);

        if (food == null) {
            return String.format(NONE_EXISTENT_FOOD, healthyFoodName);
        }

        table.orderHealthy(food);

        return String.format(FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        Table table = this.tableRepository.byNumber(tableNumber);

        if (table == null) {
            return String.format(WRONG_TABLE_NUMBER, tableNumber);
        }

        Beverages beverage = this.beverageRepository.beverageByName(name, brand);

        if (beverage == null) {
            return String.format(NON_EXISTENT_DRINK, name, brand);
        }

        table.orderBeverages(beverage);

        return String.format(BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        Table table = this.tableRepository.byNumber(tableNumber);

        double tableBill = table.bill();

        this.totalMoney += tableBill;

        return String.format(BILL, tableNumber, tableBill);
    }


    @Override
    public String totalMoney() {
        return String.format(TOTAL_MONEY, this.totalMoney);
    }
}
