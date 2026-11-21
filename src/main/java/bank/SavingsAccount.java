package bank;

/**
 * Сберегательный счёт. После снятия на счёте обязан остаться
 * минимальный остаток {@code minimumBalance}.
 */
public class SavingsAccount extends BankAccount {

    private final double minimumBalance;

    public SavingsAccount(String number, String owner, double initialBalance, double minimumBalance) {
        super(number, owner, initialBalance);

        if (minimumBalance < 0) {
            throw new IllegalArgumentException("Минимальный остаток не может быть отрицательным");
        }
        this.minimumBalance = minimumBalance;
    }

    @Override
    public boolean withdraw(double amount) {
        if (!canWithdraw(amount, minimumBalance)) {
            return false;
        }

        decreaseBalance(amount);
        return true;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }
}
