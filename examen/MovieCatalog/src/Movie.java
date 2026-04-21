public class Movie {

    public int id;
    public String title;
    public String genre;
    public int year;
    public double rating;

    public Movie() {}

    public Movie(int id, String title, String genre, int year, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }
}