package blockbuster;

public class Rental {
    private Movie _movie;
    private int   _daysRented;
    private PriceStrategy priceStrategy;
    private FrequentRenterPointsStrategy frequentRenterPointsStrategy;

    public Rental(Movie movie, int daysRented) {
        _movie      = movie;
        _daysRented = daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public String getGenre() { return _movie.getGenre(); }

    public int getDaysRented() { return _daysRented; }

    public double getRentalPrice() {
        return priceStrategy.computeRentalPrice(_daysRented);
    }

    public double getFrequentRenterPoints() {
        return frequentRenterPointsStrategy.computeFrequentRenterPoints();
    }

    public void setFrequentRenterPointsStrategy(FrequentRenterPointsStrategy strategy) {
        this.frequentRenterPointsStrategy = strategy;
    }

    public void setPriceStrategy(PriceStrategy strategy) {
        this.priceStrategy = strategy;
    }

    //public PriceStrategy getPriceStrategy() { return priceStrategy;}

}