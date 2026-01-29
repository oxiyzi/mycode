import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class EmailApp {
    private static final Set<String> emails = new TreeSet<>();
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Команды: add <email>, list, exit");

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                if (emails.isEmpty()) {
                    System.out.println("Список пуст");
                } else {
                    emails.forEach(System.out::println);
                }
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход");
                break;
            } else if (input.startsWith("add ")) {
                String email = input.substring(4).trim();
                if (EMAIL_PATTERN.matcher(email).matches()) {
                    emails.add(email);
                    System.out.println("Добавлено: " + email);
                } else {
                    System.out.println("Ошибка: некорректный email");
                }
            } else {
                System.out.println("Неизвестная команда");
            }
        }

        scanner.close();
    }

}
