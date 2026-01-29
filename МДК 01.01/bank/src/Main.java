package bank;

public class Main {

    public static void main(String[] args) {

        Account[] accounts = new Account[15];

        // 5 базовых
        for (int i = 0; i < 5; i++) {
            accounts[i] = new Account(
                    new BaseClient("Base Client " + (i + 1)));
            accounts[i].deposit(50_000);
        }

        // 5 премиум
        for (int i = 5; i < 10; i++) {
            accounts[i] = new Account(
                    new PremiumClient("Premium Client " + (i - 4)));
            accounts[i].deposit(100_000);
        }

        // 5 VIP
        for (int i = 10; i < 15; i++) {
            accounts[i] = new Account(
                    new VipClient("VIP Client " + (i - 9)));
            accounts[i].deposit(200_000);
        }

        // Тест операций
        accounts[0].payService(12_000);
        accounts[6].payService(50_000);
        accounts[12].payService(120_000);

        accounts[0].transfer(accounts[1], 5_000);

        for (Account acc : accounts) {
            acc.printBalance();
        }
    }
}