package bank;

public class VipClient extends Client {

    public VipClient(String fullName) {
        super(fullName);
    }

    @Override
    public double calculateCashback(double amount) {
        if (amount >= 100_000) return amount * 0.10;
        if (amount >= 10_000) return amount * 0.05;
        return amount * 0.01;
    }

    @Override
    public double monthlyFee() {
        return 0;
    }
}