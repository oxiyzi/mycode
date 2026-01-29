import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clients")) {

            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"), rs.getString("name")));
            }
        }
        return clients;
    }
}
