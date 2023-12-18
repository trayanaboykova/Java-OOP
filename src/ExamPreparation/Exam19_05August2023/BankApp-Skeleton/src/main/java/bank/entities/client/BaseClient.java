package bank.entities.client;

import static bank.common.ExceptionMessages.*;

public abstract class BaseClient implements Client {
    private String name;
    private String ID;
    private int interest;
    private double income;

    public BaseClient(String name, String ID, int interest, double income) {
        setName(name);
        setID(ID);
        setInterest(interest);
        setIncome(income);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        if (ID == null || ID.isBlank()) {
            throw new IllegalArgumentException(CLIENT_ID_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.ID = ID;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public void setIncome(double income) {
        if (income <= 0) {
            throw new IllegalArgumentException(CLIENT_INCOME_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.income = income;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(CLIENT_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getInterest() {
        return interest;
    }

    @Override
    public double getIncome() {
        return income;
    }

    @Override
    public void increase() {

    }
}
