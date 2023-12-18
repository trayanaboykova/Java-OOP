package christmasPastryShop.core;

import christmasPastryShop.common.enums.BoothType;
import christmasPastryShop.common.enums.CocktailType;
import christmasPastryShop.common.enums.DelicacyType;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;


import java.util.Locale;

import static christmasPastryShop.common.ExceptionMessages.*;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private final DelicacyRepository<Delicacy> delicacyRepository;
    private final CocktailRepository<Cocktail> cocktailRepository;
    private final BoothRepository<Booth> boothRepository;
    private double totalMoney;

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
        this.totalMoney = 0;
    }

    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = null;
        DelicacyType delicacyType = DelicacyType.valueOf(type);
        switch (delicacyType) {
            case Gingerbread:
                delicacy = new Gingerbread(name, price);
                break;
            case Stolen:
                delicacy = new Stolen(name, price);
                break;
        }
        boolean doesExist = delicacyRepository.getAll()
                .stream()
                .anyMatch(existingDelicacy -> existingDelicacy.getName().equals(name));

        if (doesExist) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = null;
        CocktailType cocktailType = CocktailType.valueOf(type);
        switch (cocktailType) {
            case MulledWine:
                cocktail = new MulledWine(name, size, brand);
                break;
            case Hibernation:
                cocktail = new Hibernation(name, size, brand);
                break;
        }
        boolean doesExist = cocktailRepository.getAll()
                .stream()
                .anyMatch(existingCocktail -> existingCocktail.getName().equals(name));

        if (doesExist) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        cocktailRepository.add(cocktail);
        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = null;
        BoothType tableType = BoothType.valueOf(type);
        switch (tableType) {
            case OpenBooth:
                booth = new OpenBooth(boothNumber, capacity);
                break;
            case PrivateBooth:
                booth = new PrivateBooth(boothNumber, capacity);
                break;
        }
        for (Booth currentBooth : this.boothRepository.getAll()) {
            if (currentBooth.getBoothNumber() == booth.getBoothNumber()) {
                throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
            }
        }

        this.boothRepository.add(booth);
        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth booth = this.boothRepository.getAll()
                .stream()
                .filter(e -> e.getCapacity() >= numberOfPeople && !e.isReserved())
                .findFirst()
                .orElse(null);

        if (booth == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        booth.reserve(numberOfPeople);
        return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
    }


    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        this.totalMoney += bill;
        booth.clear();
        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, this.totalMoney);
    }
}
