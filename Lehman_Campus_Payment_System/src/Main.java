import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Payable> paymentQueue = new ArrayList<>();

        CreditCard cc = new CreditCard("Shahd Nasher", 200.0, 500.0);
        MealPlan mp = new MealPlan("Student Meal Account", 100.0);

        paymentQueue.add(cc);
        paymentQueue.add(mp);

        for (Payable payment : paymentQueue) {
            payment.processPayment(50.0);
            System.out.println("Status: " + payment.getPaymentStatus());
        }

        System.out.println("Total Transactions: " + PaymentMethod.totalTransactions);
    }
}