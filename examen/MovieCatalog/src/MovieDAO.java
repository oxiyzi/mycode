import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    // =========================
    // 🎬 ФИЛЬМЫ
    // =========================

    public List<Movie> getAll() {
        List<Movie> list = new ArrayList<>();

        String sql = "SELECT * FROM movies ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("rating")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void add(Movie m) {
        String sql = "INSERT INTO movies(title, genre, year, rating) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.title);
            ps.setString(2, m.genre);
            ps.setInt(3, m.year);
            ps.setDouble(4, m.rating);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Movie m) {
        String sql = "UPDATE movies SET title=?, genre=?, year=?, rating=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.title);
            ps.setString(2, m.genre);
            ps.setInt(3, m.year);
            ps.setDouble(4, m.rating);
            ps.setInt(5, m.id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM movies WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================
    // 💬 ОТЗЫВЫ
    // =========================

    public void addReview(int movieId, String author, String text, int rating) {

        String sql = "INSERT INTO reviews(movie_id, author, text, rating) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ps.setString(2, author);
            ps.setString(3, text);
            ps.setInt(4, rating);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Review> getReviewsByMovieId(int movieId) {

        List<Review> list = new ArrayList<>();

        String sql = "SELECT * FROM reviews WHERE movie_id=? ORDER BY id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getString("author"),
                        rs.getString("text"),
                        rs.getInt("rating")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ❌ УДАЛЕНИЕ ОТЗЫВА
    public void deleteReview(int id) {

        String sql = "DELETE FROM reviews WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ⭐ пересчёт рейтинга
    public void updateMovieRating(int movieId) {

        String sql = "SELECT AVG(rating) FROM reviews WHERE movie_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double avg = rs.getDouble(1);

                PreparedStatement up = conn.prepareStatement(
                        "UPDATE movies SET rating=? WHERE id=?"
                );

                up.setDouble(1, avg);
                up.setInt(2, movieId);
                up.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMoviesEmpty() {
        String sql = "SELECT COUNT(*) FROM movies";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1) == 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}