public class BalanceRemainingException extends RuntimeException {
    private double balance;

    public BalanceRemainingException(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceRemainingException: " + balance;
    }

    public double getBalance() {
        return balance;
    }
}
