package bank;

/**
 * Кредитный счёт. Баланс может уйти в минус, но не ниже кредитного лимита.
 */
public class CreditAccount extends BankAccount {

    private final double creditLimit;

    public CreditAccount(String number, String owner, double initialBalance, double creditLimit) {
        super(number, owner, initialBalance);

        if (creditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным");
        }
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (!canWithdraw(amount, -creditLimit)) {
            return false;
        }

        decreaseBalance(amount);
        return true;
    }

    public double getCreditLimit() {
        return creditLimit;
    }
}
