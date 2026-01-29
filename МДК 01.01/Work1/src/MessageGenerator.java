import java.util.*;

public class MessageGenerator {

    public static List<Message> generate(int count) {
        List<Message> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            list.add(new Message(
                    random.nextInt(5),
                    "Text " + random.nextInt(5),
                    Priority.values()[random.nextInt(4)]
            ));
        }
        return list;
    }
}
