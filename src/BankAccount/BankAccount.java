package BankAccount;

/**
 * Created by u1774154 on 13/12/2017.
 */
public class BankAccount {

    public String accountNumber;
    public String accountHolder;
    public double balance;
    public boolean hasOverdraft;

    public BankAccount(String accountNumber, String accountHolder, boolean hasOverdraft) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.hasOverdraft = hasOverdraft;
    }

    public BankAccount(String accountNumber, String accountHolder, double balance, boolean hasOverdraft) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.hasOverdraft = hasOverdraft;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public void setHasOverdraft(boolean hasOverdraft) {
        this.hasOverdraft = hasOverdraft;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public boolean deposit (double amount) {
// what we changed here is that, we added an if condition to check whether money added is below or equal to zero or not
// if not then add the amount to the current balance and return a true value
// if not fulfilled then return a false value

        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        else {

            return false;
        }
    }


    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void addInterest(int interestRate) {
        this.balance += this.balance * (interestRate / 100.0);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                ", balance=" + balance +
                ", hasOverdraft=" + hasOverdraft +
                '}';
    }
}