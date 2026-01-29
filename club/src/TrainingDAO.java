import java.sql.*;

public class TrainingDAO {

    // Добавление тренировки
    public void addTraining(int trainerId, int clientId, String time, int serviceId, String date) throws SQLException {
        String sql = """
            INSERT INTO trainings(trainer_id, client_id, training_time, service_id, training_date)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            pstmt.setInt(2, clientId);
            pstmt.setString(3, time);
            pstmt.setInt(4, serviceId);
            pstmt.setString(5, date); // дата тренировки
            pstmt.executeUpdate();
        }
    }

    // Проверка доступности тренера
    public boolean isTrainerAvailable(int trainerId, String time, String date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM trainings WHERE trainer_id = ? AND training_time = ? AND training_date = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, trainerId);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) == 0;
        }
    }

    // Печать всех тренировок
    public void printAllTrainings() throws SQLException {
        String sql = """
            SELECT t.id, tr.name AS trainer_name, c.name AS client_name, t.training_date, t.training_time
            FROM trainings t
            JOIN trainers tr ON t.trainer_id = tr.id
            JOIN clients c ON t.client_id = c.id
            ORDER BY t.training_date, t.training_time
        """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n📋 Список тренировок:");
            while (rs.next()) {
                System.out.printf("%d: Дата: %s, Время: %s, Тренер: %s, Клиент: %s%n",
                        rs.getInt("id"),
                        rs.getString("training_date"),
                        rs.getString("training_time"),
                        rs.getString("trainer_name"),
                        rs.getString("client_name"));
            }
        }
    }
}
