public class Review {
    public int id;
    public int movieId;
    public String author;
    public String text;
    public int rating;

    public Review(int id, int movieId, String author, String text, int rating) {
        this.id = id;
        this.movieId = movieId;
        this.author = author;
        this.text = text;
        this.rating = rating;
    }
}