package blockbuster;

public class ChildrensPriceStrategy extends PriceStrategy{


    private double lateFeeFactor;
    private double rentalPrice;
    private double allowedRentalDays;
    private double baseRentalPrice;


    public ChildrensPriceStrategy() {
        lateFeeFactor = 1.5;
        rentalPrice = 0;
        allowedRentalDays = 3;
        baseRentalPrice = 1.5;
    }

    @Override
    public double computeRentalPrice(int daysRented) {
        rentalPrice = baseRentalPrice;
        if (daysRented > allowedRentalDays) {
            rentalPrice += (daysRented - allowedRentalDays) * lateFeeFactor;
        }
        return rentalPrice;
    }
}

