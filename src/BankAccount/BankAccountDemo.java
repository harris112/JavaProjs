package BankAccount;

/**
 * Created by u1774154 on 13/12/2017.
 */
public class BankAccountDemo {
    public static void main(String[] args) {
/// checking whether amount added to the account is positive or not
        System.out.println ("Creating account ...");
        BankAccount b1 = new BankAccount ("78325123", "Richard Branson", false);
        System.out.println (b1.accountNumber);
        System.out.println (b1.accountHolder);
        System.out.println (b1.balance);
        System.out.println (b1.hasOverdraft);

        b1.setBalance(0);

        b1.deposit (350.0);
        System.out.println(b1);


    }
}
