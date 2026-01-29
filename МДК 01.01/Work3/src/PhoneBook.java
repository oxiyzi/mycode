import java.util.*;

public class PhoneBook {

    private static final Map<String, String> nameToPhone = new HashMap<>();
    private static final Map<String, String> phoneToName = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Телефонная книга. Вводите имя или номер. Команда LIST для списка, EXIT для выхода.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("LIST")) {
                if (nameToPhone.isEmpty()) {
                    System.out.println("Список пуст");
                } else {
                    nameToPhone.keySet().stream()
                            .sorted()
                            .forEach(name ->
                                    System.out.println(name + " : " + nameToPhone.get(name))
                            );
                }
            } else if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Выход");
                break;
            } else if (nameToPhone.containsKey(input)) {
                // Введено существующее имя
                System.out.println(input + " : " + nameToPhone.get(input));
            } else if (phoneToName.containsKey(input)) {
                // Введен существующий номер
                System.out.println(phoneToName.get(input) + " : " + input);
            } else {
                // Новый контакт
                if (input.matches("^[+\\d].*")) {
                    // Введён номер → спросить имя
                    System.out.println("Введите имя для номера " + input + ":");
                    String name = scanner.nextLine().trim();
                    nameToPhone.put(name, input);
                    phoneToName.put(input, name);
                    System.out.println("Контакт сохранён");
                } else {
                    // Введено имя → спросить номер
                    System.out.println("Введите номер для " + input + ":");
                    String phone = scanner.nextLine().trim();
                    nameToPhone.put(input, phone);
                    phoneToName.put(phone, input);
                    System.out.println("Контакт сохранён");
                }
            }
        }

        scanner.close();
    }
}

