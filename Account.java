public class Account {

    private String accountNumber;
    private double balance;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0;
    }

    public Account(String accountNumber, double balance) {
        this(accountNumber);
        this.balance = balance;

        if (balance < 0)
            throw new IllegalArgumentException("Balance must be non-negative!");
    }

    public String getAcctNum() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount >= 0)
            balance += amount;
        else
            throw new InvalidAmountException(amount);
    }

    public void withdrawal(double amount) {
        if (amount > 0 && amount <= balance)
            balance -= amount;
        else
            throw new InvalidAmountException(amount);

    }

    public String toString() {
        return "Account " + accountNumber + " has " + balance;
    }

    public void setBalance(double balance) {
        if (balance >= 0)
            this.balance = balance;
    }

}
