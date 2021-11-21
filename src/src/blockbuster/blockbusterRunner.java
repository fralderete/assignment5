package blockbuster;

import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class blockbusterRunner {

    public static void main(String[] args) {
        Transaction transaction = new Transaction(false, false);
        Customer customer = new Customer();
        customer.setName("Eugene McDermott");
        customer.setAge(21);

        List<Rental> Rentals = new ArrayList<>();
        populateMovieSelection();

        Rentals.add(new Rental(RunnerProperties.selection.get(0), RunnerProperties.TWO_DAYS_RENTED));
        Rentals.add(new Rental(RunnerProperties.selection.get(1), RunnerProperties.FOUR_DAYS_RENTED));
        Rentals.add(new Rental(RunnerProperties.selection.get(2), RunnerProperties.THREE_DAYS_RENTED));
        Rentals.add(new Rental(RunnerProperties.selection.get(3), RunnerProperties.ONE_DAY_RENTED));

        setRentalStrategies(Rentals);

        addCustomerRentals(customer, Rentals);

        List<String> uniqueGenre = Rentals.stream().map(Rental::getGenre).distinct().collect(Collectors.toCollection(ArrayList::new));
        List<Integer> daysRented = Rentals.stream().map(Rental::getDaysRented).collect(Collectors.toList());

        setTransaction(customer, uniqueGenre, daysRented, transaction);

        setFRPStrategies(Rentals);

        System.out.println(customer.htmlRentalReceipt(transaction));
    }

    public static void populateMovieSelection() {
        RunnerProperties.selection.add(new Movie("Jurassic Park", RunnerProperties.THRILLER_GENRE, RunnerProperties.TWO_YEARS));
        RunnerProperties.selection.add(new Movie("Empire Strikes Back", RunnerProperties.CHILDRENS_GENRE, RunnerProperties.THREE_WEEKS));
        RunnerProperties.selection.add(new Movie("Parasite", RunnerProperties.THRILLER_GENRE, RunnerProperties.ONE_WEEK));
        RunnerProperties.selection.add(new Movie("Cory in the House", RunnerProperties.CHILDRENS_GENRE, RunnerProperties.TWO_WEEKS));
    }

    public static void setRentalStrategies(List<Rental> rentals) {
        for(Rental r : rentals)
            if (r.getMovie().getReleaseDate() < RunnerProperties.TWO_WEEKS) r.setPriceStrategy(new NewPriceStrategy());
            else if (r.getMovie().getGenre().equalsIgnoreCase(RunnerProperties.CHILDRENS_GENRE))
                r.setPriceStrategy(new ChildrensPriceStrategy());
            else r.setPriceStrategy(new RegularPriceStrategy());
    }

    public static void setFRPStrategies(List<Rental> rentals) {
        for(Rental r : rentals) {
            if (r.getMovie().getReleaseDate() < RunnerProperties.TWO_WEEKS) {
                r.setFrequentRenterPointsStrategy(new BonusFrequentRenterPointsStrategy());
            } else {
                r.setFrequentRenterPointsStrategy(new FrequentRenterPointsStrategy());
            }
        }
    }

    public static void setTransaction(Customer customer, List<String> genres, List<Integer> daysRented, Transaction transaction) {
        int numberOfGenres = 0;
        int customerAge = customer.getAge();
        boolean doubleForAgeNew = false;
        boolean doubleForMultipleGenre = false;

        for(String g : genres) numberOfGenres++;

        // if customer rents any new movies while being 18-22 double points
        if(customerAge <= 22 && customerAge >= 18) for (Integer d : daysRented)
            if (d < RunnerProperties.TWO_WEEKS) {
                // give double renter points
                doubleForAgeNew = true;
                break;
            }

        // indicate double renter points
        if (numberOfGenres > 1) doubleForMultipleGenre = true;

        transaction.setNewMovie(doubleForAgeNew);
        transaction.setMultipleGenre(doubleForMultipleGenre);

    }

    public static void addCustomerRentals(Customer customer, List<Rental> rentals) {
        for(Rental r : rentals) customer.addRental(r);
    }
}
