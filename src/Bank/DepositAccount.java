package Bank;

/**
 * Created by u1774154 on 08/02/2018.
 */
public class DepositAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;


    public double getBalance() {

        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {

        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public DepositAccount (double balance) {

        this.balance = balance;
    }

    public DepositAccount (String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public DepositAccount (String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;

    }
    public void withdraw (double amount) {
        this.balance -= amount;


    }

    public void addInterest (int interestRate) {
        this.balance += this.balance * (interestRate / 100.0);
    }
}
