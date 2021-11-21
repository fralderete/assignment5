package blockbuster;

/*
a. The first new frequent rental point computation gives double regular points to any customer who is renting more than two types of movies.
b. The first new frequent rental point computation gives double regular points to any customer who is 18-22 and renting one or multiple new release movies.
 */

public class Transaction {
    private boolean _newMovie;
    private boolean _multipleGenre;
    private double _finalTotal = 0;
    private double _currentFrequentRenterPoints;

    public Transaction (boolean newMovie, boolean multipleGenre) {
        this._newMovie = newMovie;
        this._multipleGenre = multipleGenre;
    }

    public void setDoubleFRP (double currentFrequentRenterPoints) { _currentFrequentRenterPoints = currentFrequentRenterPoints * 2;
    }

    public double getDoubleFRP () { return _currentFrequentRenterPoints; }

    public void setNewMovie(boolean newMovie) { _newMovie = newMovie; }
    public void setMultipleGenre(boolean multipleGenre) { _multipleGenre = multipleGenre; }

    public boolean getNewMovie() { return _newMovie; }
    public boolean getMultipleGenre() { return _multipleGenre; }


}