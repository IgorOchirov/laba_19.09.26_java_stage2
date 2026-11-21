package bank;

/**
 * Дебетовый счёт. Снять можно только те деньги, которые уже лежат на счёте,
 * поэтому уйти в минус такой счёт не может.
 */
public class DebitAccount extends BankAccount {

    public DebitAccount(String number, String owner, double initialBalance) {
        super(number, owner, initialBalance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            return false;
        }

        decreaseBalance(amount);
        return true;
    }
}
