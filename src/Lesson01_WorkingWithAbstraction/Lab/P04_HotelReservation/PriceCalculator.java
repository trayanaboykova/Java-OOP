package Lesson01_WorkingWithAbstraction.Lab.P04_HotelReservation;

public class PriceCalculator {

    public static double calculateHolidayPrice(double pricePerDay, int days, Season season, DiscountType discountType) {
        double priceForAllDays = pricePerDay * days;
        priceForAllDays = priceForAllDays * season.getMultiplyCoefficient();
        priceForAllDays = priceForAllDays - priceForAllDays * (discountType.getPercent() / 100);

        return priceForAllDays;
    }
}
