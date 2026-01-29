package bank;

public abstract class Client {

    protected String fullName;

    public Client(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new BankException("ФИО не может быть пустым");
        }
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public abstract double calculateCashback(double amount);
    public abstract double monthlyFee();
}