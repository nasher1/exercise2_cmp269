public abstract class PaymentMethod implements Payable {

    protected String accountHolder;
    protected double balance;

    public static int totalTransactions = 0;

    public PaymentMethod(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public abstract void validateAccount();
}