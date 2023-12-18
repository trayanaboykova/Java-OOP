package christmasPastryShop.entities.cocktails.interfaces;

public class MulledWine extends BaseCocktail {
    private static final double MULLEDWINE_PRICE = 3.50;
    public MulledWine(String name, int size, String brand) {
        super(name, size, MULLEDWINE_PRICE, brand);
    }
}
