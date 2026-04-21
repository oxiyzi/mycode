import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // 👇 имя твоей базы уже здесь
    private static final String URL =
            "jdbc:postgresql://localhost:5432/movie_catalog";

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres"; // вставь свой пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}