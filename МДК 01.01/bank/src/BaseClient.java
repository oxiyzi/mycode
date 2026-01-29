package bank;

public class BaseClient extends Client {

    public BaseClient(String fullName) {
        super(fullName);
    }

    @Override
    public double calculateCashback(double amount) {
        return amount >= 10_000 ? amount * 0.01 : 0;
    }

    @Override
    public double monthlyFee() {
        return 100;
    }
}