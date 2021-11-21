package blockbuster;

public class RegularPriceStrategy extends PriceStrategy{


    private double lateFeeFactor;
    private double rentalPrice;
    private double allowedRentalDays;
    private double baseRentalPrice;

    public RegularPriceStrategy() {
        lateFeeFactor = 1.5;
        rentalPrice = 0;
        allowedRentalDays = 2;
        baseRentalPrice = 2;
    }

    @Override
    public double computeRentalPrice(int daysRented) {
        rentalPrice = baseRentalPrice;
        if(daysRented > allowedRentalDays) {
            rentalPrice += (daysRented-allowedRentalDays) * lateFeeFactor;
        }
        return rentalPrice;
    }
}
