package blockbuster;

public class NewPriceStrategy extends PriceStrategy{

    private double dailyRentalFactor;

    public NewPriceStrategy() {
        dailyRentalFactor = 3;
    }

    @Override
    public double computeRentalPrice(int daysRented) {
        return daysRented * dailyRentalFactor;
    }
}

