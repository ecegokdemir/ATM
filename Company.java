import java.util.ArrayList;

public class Company {

    private int id;
    private String name;

    private BusinessAccount b;

    ArrayList<BusinessAccount> businessAccounts;

    public Company(int id, String name) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive!");
        else {
            this.id = id;
            this.name = name;
            businessAccounts = new ArrayList<BusinessAccount>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    public void openAccount(String acctNum, double rate) {
        BusinessAccount bAccount = new BusinessAccount(acctNum, rate);
        businessAccounts.add(bAccount);
    }

    public BusinessAccount getAccount(String acctNum) {
        b = null;

        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(acctNum))
                b = businessAccount;
        }
        if (b == null)
            throw new AccountNotFoundException(acctNum);
        else
            return b;
    }

    public void closeAccount(String accountNum) {
        b = null;

        for (BusinessAccount businessAccount : businessAccounts) {
            if (businessAccount.getAcctNum().equals(accountNum))
                b = businessAccount;
        }

        if (b != null) {
            if (b.getBalance() == 0)
                businessAccounts.remove(b);
            else
                throw new BalanceRemainingException(b.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);

    }

    public String toString() {
        return name;
    }

}
