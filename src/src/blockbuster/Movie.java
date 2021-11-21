package blockbuster;

public class Movie {
    
    private String _title;

    private int WeeksSinceRelease;

    private String genre;

    public Movie(String title, String movieGenre, int releaseDate) {
        _title = title;
        genre = movieGenre;
        WeeksSinceRelease = releaseDate;
    }

    public String getTitle() {
        return _title;
    }

    public int getReleaseDate() {
        return WeeksSinceRelease;
    }

    public String getGenre() {
        return genre;
    }
}