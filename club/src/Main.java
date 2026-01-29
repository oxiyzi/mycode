import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainerDAO trainerDAO = new TrainerDAO();
        ClientDAO clientDAO = new ClientDAO();
        TrainingDAO trainingDAO = new TrainingDAO();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Меню администратора ===");
            System.out.println("1. Показать тренеров");
            System.out.println("2. Показать клиентов");
            System.out.println("3. Показать тренировки");
            System.out.println("4. Добавить тренировку");
            System.out.println("5. Выход");
            System.out.print("Выберите пункт: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        List<Trainer> trainers = trainerDAO.getAllTrainers();
                        trainers.forEach(t -> System.out.println(t.getId() + ": " + t.getName()));
                    }
                    case 2 -> {
                        List<Client> clients = clientDAO.getAllClients();
                        clients.forEach(c -> System.out.println(c.getId() + ": " + c.getName()));
                    }
                    case 3 -> trainingDAO.printAllTrainings();
                    case 4 -> {
                        System.out.print("Введите ID тренера: ");
                        int trainerId = sc.nextInt();
                        System.out.print("Введите ID клиента: ");
                        int clientId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Введите дату тренировки (например, 2026-01-27): ");
                        String date = sc.nextLine();
                        System.out.print("Введите время тренировки (например, 10:00): ");
                        String time = sc.nextLine();
                        System.out.print("Введите ID услуги: ");
                        int serviceId = sc.nextInt();

                        if (trainingDAO.isTrainerAvailable(trainerId, time, date)) {
                            trainingDAO.addTraining(trainerId, clientId, time, serviceId, date);
                            System.out.println("✅ Тренировка добавлена!");
                        } else {
                            System.out.println("❌ Тренер занят в это время!");
                        }
                    }
                    case 5 -> running = false;
                    default -> System.out.println("Неверный выбор!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
