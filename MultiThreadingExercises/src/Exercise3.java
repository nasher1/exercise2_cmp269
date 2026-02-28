public class Exercise3 {

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        Runnable husband = () -> {
            account.withdraw(700);
        };

        Runnable wife = () -> {
            account.withdraw(700);
        };

        Thread t1 = new Thread(husband, "Husband");
        Thread t2 = new Thread(wife, "Wife");

        t1.start();
        t2.start();
    }
}