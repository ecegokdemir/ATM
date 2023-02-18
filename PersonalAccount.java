import java.util.Random;

public class PersonalAccount extends Account {

    private String name;
    private String surname;
    private String pin;

    public PersonalAccount(String accountNumber, double balance, String name, String surname) {
        super(accountNumber, balance);
        this.name = name;
        this.surname = surname;

        Random rnd = new Random();
        int tempPin = rnd.nextInt(9999);
        pin = String.valueOf(String.format("%04d", tempPin));

    }

    public PersonalAccount(String accountNumber, String name, String surname) {
        super(accountNumber);
        this.name = name;
        this.surname = surname;

        Random rnd = new Random();
        int tempPin = rnd.nextInt(9999);
        pin = String.valueOf(String.format("%04d", tempPin));

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

    public String getPIN() {
        return pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public String toString() {
        return "Account " + getAcctNum() + " belonging to " + name + " " + surname.toUpperCase() + " has "
                + getBalance();
    }

}
