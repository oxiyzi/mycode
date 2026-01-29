package bank;

public class PremiumClient extends Client {

    public PremiumClient(String fullName) {
        super(fullName);
    }

    @Override
    public double calculateCashback(double amount) {
        return amount >= 10_000 ? amount * 0.05 : 0;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}

