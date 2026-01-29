package bank;

import java.util.UUID;

public class Account {

    private Client client;
    private String accountNumber;
    private double balance;
    private boolean bonusAdded = false;

    public Account(Client client) {
        this.client = client;
        this.accountNumber = generateAccountNumber();
        this.balance = 0;
    }

    private String generateAccountNumber() {
        StringBuilder number = new StringBuilder();

        while (number.length() < 20) {
            number.append((int) (Math.random() * 10));
        }

        return number.toString();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new BankException("Сумма должна быть положительной");
        }
        balance += amount;
    }

    public void printBalance() {
        System.out.println(client.getFullName() +
                " | Баланс: " + balance);
    }

    public void payService(double amount) {
        if (amount <= 0) {
            throw new BankException("Сумма некорректна");
        }
        if (balance < amount) {
            throw new BankException("Недостаточно средств");
        }

        balance -= amount;

        double cashback = client.calculateCashback(amount);
        balance += cashback;

        if (!bonusAdded) {
            balance += 1000;
            bonusAdded = true;
            System.out.println("Начислен приветственный бонус 1000₽");
        }

        System.out.println("Оплата услуги: " + amount +
                ", кешбек: " + cashback);
    }

    public void transfer(Account target, double amount) {
        if (amount <= 0) {
            throw new BankException("Сумма некорректна");
        }
        if (balance < amount) {
            throw new BankException("Недостаточно средств");
        }

        balance -= amount;
        target.balance += amount;
    }

    public void chargeMonthlyFee() {
        double fee = client.monthlyFee();
        if (balance < fee) {
            throw new BankException("Отрицательный баланс после списания");
        }
        balance -= fee;
    }
}
