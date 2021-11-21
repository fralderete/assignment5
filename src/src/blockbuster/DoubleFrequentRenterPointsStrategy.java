package blockbuster;

public class DoubleFrequentRenterPointsStrategy extends FrequentRenterPointsStrategy{

    public double computeFrequentRenterPoints(){
        return currentFrequentRenterPoints * 2;
    }

}