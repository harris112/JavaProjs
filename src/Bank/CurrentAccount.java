package Bank;

/**
 * Created by u1774154 on 08/02/2018.
 */
public class CurrentAccount {

    private String accountHolder;
    private String accountNumber;
    private double balance;
    private boolean hasOverdraft;

    public boolean isHasOverdraft() {
        return hasOverdraft;
    }

    public void setHasOverdraft(boolean hasOverdraft) {
        this.hasOverdraft = hasOverdraft;
    }

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

    public CurrentAccount(boolean hasOverdraft) {

        this.hasOverdraft = hasOverdraft;
    }

    public CurrentAccount(double balance) {

        this.balance = balance;
    }

    public CurrentAccount(String accountNumber) {

        this.accountNumber = accountNumber;
    }

    public CurrentAccount(String accountNumber, String accountHolder, boolean hasOverdraft) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.hasOverdraft = hasOverdraft;
    }

    public void deposit(double amount) {
        this.balance += amount;

    }

    public void withdraw(double amount) {
        this.balance -= amount;


    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                ", hasOverdraft=" + hasOverdraft +
                '}';
    }

    public static void main(String[] args) {

        CurrentAccount c = new CurrentAccount("123","harris",false);
        c.setBalance(1500);
        c.deposit(1500);
        System.out.println(c);




    }
}
