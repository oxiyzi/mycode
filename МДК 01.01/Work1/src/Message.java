public class Message {
    private int code;
    private String text;
    private Priority priority;

    public Message(int code, String text, Priority priority) {
        this.code = code;
        this.text = text;
        this.priority = priority;
    }

    public int getCode() {
        return code;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message m = (Message) o;
        return code == m.code &&
                text.equals(m.text) &&
                priority == m.priority;
    }

    @Override
    public int hashCode() {
        return code + text.hashCode() + priority.hashCode();
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", priority=" + priority +
                ", text='" + text + '\'' +
                '}';
    }
}