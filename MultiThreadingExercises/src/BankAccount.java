public class BankAccount {

    private int balance = 1000;

//    public void withdraw(int amount) {
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing...");
            balance -= amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Not enough money for " + Thread.currentThread().getName());
        }
    }

    public int getBalance() {
        return balance;
    }
}