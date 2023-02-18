import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Bank {

    private String name;
    private String address;

    ArrayList<Customer> customers;
    ArrayList<Company> companies;
    ArrayList<Account> accounts;

    Customer c;
    Company comp;
    Account a;

    public Bank(String name, String address) {
        this.name = name;
        this.address = address;

        customers = new ArrayList<>();
        companies = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addCustomer(int id, String name, String surname) {
        Customer c = new Customer(id, name, surname);
        customers.add(c);

    }

    public void addCompany(int id, String name) {
        Company comp = new Company(id, name);
        companies.add(comp);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Customer getCustomer(int id) {
        c = null;

        for (Customer customer : customers) {
            if (customer.getId() == id)
                c = customer;
        }
        if (c == null)
            throw new CustomerNotFoundException(id);
        else
            return c;
    }

    public Customer getCustomer(String name, String surname) {
        c = null;

        for (Customer customer : customers) {
            if (customer.getName().equals(name) && customer.getSurname().equals(surname))
                c = customer;
        }
        if (c == null)
            throw new CustomerNotFoundException(name, surname);
        else
            return c;
    }

    public Company getCompany(int id) {
        comp = null;

        for (Company company : companies) {
            if (company.getId() == id)
                comp = company;
        }
        if (comp == null)
            throw new CompanyNotFoundException(id);
        else
            return comp;
    }

    public Company getCompany(String name) {
        comp = null;

        for (Company company : companies) {
            if (company.getName().equals(name))
                comp = company;
        }
        if (comp == null)
            throw new CompanyNotFoundException(name);
        else
            return comp;
    }

    public Account getAccount(String accountNum) {
        a = null;

        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum))
                a = account;
        }
        if (a == null)
            throw new AccountNotFoundException(accountNum);
        else
            return a;
    }

    public void transferFunds(String accountFrom, String accountTo, double amount) {
        getAccount(accountFrom);
        getAccount(accountTo);

        if (amount <= 0)
            throw new InvalidAmountException(amount);

        if (getAccount(accountFrom).getBalance() < amount)
            throw new InvalidAmountException(amount);

        else {
            getAccount(accountFrom).withdrawal(amount);
            getAccount(accountTo).deposit(amount);
        }

    }

    public void closeAccount(String accountNum) {
        for (Account account : accounts) {
            if (account.getAcctNum().equals(accountNum))
                a = account;
        }

        if (a != null) {

            if (a.getBalance() == 0)
                accounts.remove(a);
            else
                throw new BalanceRemainingException(a.getBalance());
        } else
            throw new AccountNotFoundException(accountNum);
    }

    public void processTransactions(Collection<Transaction> transactions , String outFile) {

        ArrayList<Transaction> list = new ArrayList<>();

        for (Transaction transaction : transactions) {
            list.add(transaction);
        }

        list.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction transaction1, Transaction transaction2) {
                if (transaction1.getType() > transaction2.getType()) {
                    return 1;
                } else if (transaction1.getType() < transaction2.getType()) {
                    return -1;
                } else {
                    if (transaction1.getType() == 3) {
                        if ( Integer.parseInt(transaction1.getFrom()) > Integer.parseInt(transaction2.getFrom())) {
                            return 1;
                        } else return -1;
                    } else {
                        if (Integer.parseInt(transaction1.getTo()) > Integer.parseInt(transaction2.getTo())) {
                            return 1;
                        } else return -1;
                    }
                }
            }
        });

        for (Transaction transaction : list) {

            if (transaction.getType() == 1) {

                try {
                    getAccount(transaction.getTo()).deposit(transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (transaction.getType() == 2) {

                try {
                    transferFunds(transaction.getFrom(),transaction.getTo(),transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else if (transaction.getType() == 3){

                try {
                    getAccount(transaction.getFrom()).withdrawal(transaction.getAmount());
                } catch (AccountNotFoundException accountNotFoundException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: AccountNotFoundException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (InvalidAmountException invalidAmountException) {
                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter(outFile,true));
                        writer.write("ERROR: InvalidAmountException: "+ transaction.getType()+"    "+transaction.getFrom()+"    "+transaction.getTo()+"    "+transaction.getAmount()+"\n");
                        writer.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }


    }

    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(name).append("\t").append(address).append("\n");

        for (Company compCounter : companies) {
            str.append("\t").append(compCounter.toString()).append("\n");

            for (int i = 0; i < compCounter.businessAccounts.size(); i++) {
                str.append("\t\t").append(compCounter.businessAccounts.get(i).getAcctNum());
                str.append("\t").append(compCounter.businessAccounts.get(i).getRate());
                str.append("\t").append(compCounter.businessAccounts.get(i).getBalance()).append("\n");
            }
        }

        for (Customer customer : customers) {
            str.append("\t").append(customer.toString()).append("\n");

            for (int i = 0; i < customer.personalAccounts.size(); i++) {
                int counter = 1;
                if (counter == customer.personalAccounts.size()) {
                    str.append("\t\t").append(customer.personalAccounts.get(i).getAcctNum());
                    str.append("\t").append(customer.personalAccounts.get(i).getBalance());
                } else {
                    str.append("\t\t").append(customer.personalAccounts.get(i).getAcctNum());
                    str.append("\t").append(customer.personalAccounts.get(i).getBalance()).append("\n");
                }
                counter++;
            }
        }

        return str.toString();
    }

}
