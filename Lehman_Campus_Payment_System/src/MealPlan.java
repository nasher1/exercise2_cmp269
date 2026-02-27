public class MealPlan extends PaymentMethod {

    private String status;

    public MealPlan(String accountHolder, double balance) {
        super(accountHolder, balance);
        this.status = "Not Processed";
    }

    @Override
    public void validateAccount() {
        if (balance < 0) {
            System.out.println("Meal plan balance cannot be negative.");
        }
    }

    @Override
    public void processPayment(double amount) {
        validateAccount();

        if (balance >= amount) {
            balance -= amount;
            totalTransactions++;
            status = "Approved";
            System.out.println("Meal Plan Payment Approved.");
        } else {
            System.out.println("Insufficient Meal Plan Balance.");
            status = "Declined";
        }
    }

    @Override
    public String getPaymentStatus() {
        return status;
    }
}