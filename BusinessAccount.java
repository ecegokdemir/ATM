public class BusinessAccount extends Account {

    private double interestRate;

    public BusinessAccount(String accountNumber, double balance, double rate) {
        super(accountNumber, balance);

        if (rate <= 0)
            throw new IllegalArgumentException("Rate must be positive!");
        else
            this.interestRate = rate;

    }

    public BusinessAccount(String accountNumber, double rate) {
        super(accountNumber);

        if (rate <= 0)
            throw new IllegalArgumentException("Rate must be positive!");
        else
            this.interestRate = rate;
    }

    public double getRate() {
        return interestRate;
    }

    public void setRate(double rate) {
        if (rate > 0)
            this.interestRate = rate;
    }

    public double calculateInterest() {

        return getBalance() * getRate();

    }

}
