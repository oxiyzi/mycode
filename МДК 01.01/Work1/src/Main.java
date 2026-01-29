import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Message> messages = MessageGenerator.generate(20);
        System.out.println("Исходный список:");
        messages.forEach(System.out::println);

        Set<Message> unique = new LinkedHashSet<>(messages);
        System.out.println("\nУникальные сообщения:");
        unique.forEach(System.out::println);

        messages.removeIf(m -> m.getPriority() == Priority.HIGH);

        Map<Priority, Integer> byPriority = new HashMap<>();
        for (Message m : unique) {
            byPriority.merge(m.getPriority(), 1, Integer::sum);
        }
        System.out.println(byPriority);
    }
}
