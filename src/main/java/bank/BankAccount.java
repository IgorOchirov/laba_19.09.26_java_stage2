package bank;

/**
 * Базовый класс банковского счёта.
 * <p>
 * Номер счёта и владелец задаются один раз в конструкторе и больше не меняются.
 * Баланс снаружи изменить нельзя: для этого есть {@link #deposit(double)} и
 * {@link #withdraw(double)}. Наследники проверяют возможность снятия через
 * {@link #canWithdraw(double, double)} и меняют баланс через
 * {@link #decreaseBalance(double)}.
 */
public abstract class BankAccount {

    private final String number;
    private final String owner;
    private double balance;

    /**
     * @param number         номер счёта
     * @param owner          владелец счёта
     * @param initialBalance начальный баланс, не может быть отрицательным
     * @throws IllegalArgumentException если номер или владелец пустые,
     *                                  либо начальный баланс отрицательный
     */
    protected BankAccount(String number, String owner, double initialBalance) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Номер счёта не может быть пустым");
        }
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Владелец счёта не может быть пустым");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }

        this.number = number;
        this.owner = owner;
        this.balance = initialBalance;
    }

    /**
     * Пополняет счёт. Нулевая и отрицательная сумма просто игнорируются.
     *
     * @param amount сумма пополнения
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            return;
        }
        balance += amount;
    }

    /**
     * Снимает деньги со счёта по правилам конкретного типа счёта.
     *
     * @param amount сумма снятия
     * @return {@code true}, если деньги удалось снять
     */
    public abstract boolean withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    /**
     * Проверяет, можно ли снять сумму так, чтобы баланс не ушёл ниже минимума.
     * Метод защищённый: он нужен только наследникам с их правилами снятия.
     * Нулевые и отрицательные суммы снимать нельзя.
     *
     * @param amount     сумма снятия
     * @param minBalance минимально допустимый баланс после снятия
     * @return {@code true}, если снятие возможно
     */
    protected boolean canWithdraw(double amount, double minBalance) {
        return amount > 0 && getBalance() - amount >= minBalance;
    }

    /**
     * Уменьшает баланс на указанную сумму. Метод защищённый, чтобы наследники
     * могли менять баланс, но поле не было доступно всем подряд.
     */
    protected void decreaseBalance(double amount) {
        balance -= amount;
    }
}
