import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    public List<Trainer> getAllTrainers() throws SQLException {
        List<Trainer> trainers = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM trainers")) {

            while (rs.next()) {
                trainers.add(new Trainer(rs.getInt("id"), rs.getString("name")));
            }
        }
        return trainers;
    }
}
