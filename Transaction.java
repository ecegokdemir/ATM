import java.security.InvalidParameterException;

public class Transaction {

    private int type;
    private String to;
    private String from;
    private double amount;

    // 1-> deposit
    // 2-> transfer
    // 3-> withdrawal

    public Transaction(int type, String to, String from, double amount) {

        if (type == 1 || type == 2 || type == 3) {
            this.type = type;
            this.to = to;
            this.from = from;
            this.amount = amount;
        }

        else
            throw new InvalidParameterException("Invalid transaction type.");
    }

    public Transaction(int type, String account, double amount) {

        if (type == 1) {
            this.type = type;
            this.to = account;
            this.from = null;
            this.amount = amount;
        }

        else if (type == 3) {
            this.type = type;
            this.from = account;
            this.to = null;
            this.amount = amount;
        } else
            throw new InvalidParameterException("Invalid transaction type.");
    }

    public int getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public double getAmount() {
        return amount;
    }

}
