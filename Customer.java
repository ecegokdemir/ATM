import java.util.ArrayList;

public class Customer {

    private int id;
    private String name;
    private String surname;

    private PersonalAccount p;

    ArrayList<PersonalAccount> personalAccounts;

    public Customer(int id, String name, String surname) {
        if (id <= 0)
            throw new IllegalArgumentException("ID must be positive!");
        else {
            this.id = id;
            this.name = name;
            this.surname = surname;
            personalAccounts = new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0)
            this.id = id;
    }

    public void openAccount(String acctNum) {
        PersonalAccount pAccount = new PersonalAccount(acctNum, name, surname);
        personalAccounts.add(pAccount);
    }

    public PersonalAccount getAccount(String accountNum) {
        p = null;

        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum))
                p = personalAccount;
        }
        if (p == null)
            throw new AccountNotFoundException(accountNum);
        else
            return p;

    }

    public void closeAccount(String accountNum) {
        p = null;

        for (PersonalAccount personalAccount : personalAccounts) {
            if (personalAccount.getAcctNum().equals(accountNum))
                p = personalAccount;
        }

        if (p != null) {
            if (p.getBalance() == 0)
                personalAccounts.remove(p);
            else
                throw new BalanceRemainingException(p.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);

    }

    public String toString() {
        return name + " " + surname.toUpperCase();
    }

}
