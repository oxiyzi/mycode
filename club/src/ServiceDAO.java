import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    public List<Service> getAllServices() throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                services.add(new Service(rs.getInt("id"), rs.getString("name")));
            }
        }
        return services;
    }
}
