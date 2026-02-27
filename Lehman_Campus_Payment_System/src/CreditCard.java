public class CreditCard extends PaymentMethod {

    private double creditLimit;
    private String status;

    public CreditCard(String accountHolder, double balance, double creditLimit) {
        super(accountHolder, balance);   // MUST be first line
        this.creditLimit = creditLimit;
        this.status = "Not Processed";
    }

    @Override
    public void validateAccount() {
        if (accountHolder == null || accountHolder.isEmpty()) {
            System.out.println("Invalid Credit Card Account.");
        }
    }

    @Override
    public void processPayment(double amount) {
        validateAccount();

        if (amount > (balance + creditLimit)) {
            System.out.println("Transaction Declined.");
            status = "Declined";
        } else {
            balance -= amount;
            totalTransactions++;
            status = "Approved";
            System.out.println("Credit Card Payment Approved.");
        }
    }

    @Override
    public String getPaymentStatus() {
        return status;
    }
}