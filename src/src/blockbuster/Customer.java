package blockbuster;

import java.util.List;
import java.util.ArrayList;

public class Customer {
    private String _name;
    private int _age;
    private boolean _multipleGenre;
    private boolean _newMovie;

    private List<Rental> rentals = new ArrayList<>();
    // private Transaction transaction;
    // added to include age, previous method didn't accept an additional parameter
    public void setName (String name) {
        _name = name;
    }

    public void setAge (int age) {
        _age = age;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return _name;
    }

    public int getAge() { return _age; }

    public String htmlRentalReceipt(Transaction transaction) {
        _multipleGenre = transaction.getMultipleGenre();
        _newMovie = transaction.getNewMovie();
        String result = "<h1>Rental record for <em>" + getName() + "</em></h1>\n<table>\n";
        for (Rental rental : rentals)
            result += "\t<tr><td>" + rental.getMovie().getTitle() + "</td><td>" + ": "
                    + rental.getRentalPrice() + "</td></tr>\n";
        result += "</table>\n<p>Amount owed is <em>" + getTotalCharge() + "</em></p>\n";
        result += "<p>You earned <em>" + getTotalFrequentRenterPoints()
                + "</em> frequent renter points</p>";

        if (_multipleGenre) {
            transaction.setDoubleFRP(getTotalFrequentRenterPoints());
            result += "<p>You have qualified for double bonus renter points due to multiple genre rentals. Your have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";
        }

        if (_newMovie) {
            transaction.setDoubleFRP(transaction.getDoubleFRP());
            result += "<p>You have qualified for double bonus renter points due to your age and new movie rental. Your have <em>" + transaction.getDoubleFRP() + "</em> total frequent renter points.</p>\n";

        }

        return result;
    }

    private double getTotalCharge() {
        double total = 0;
        for (Rental rental : rentals)
            total += rental.getRentalPrice();
        return total;
    }

    private double getTotalFrequentRenterPoints() {
        double total = 0;
        for (Rental rental: rentals)
            total += rental.getFrequentRenterPoints();
        return total;
    }
}