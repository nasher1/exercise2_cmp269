public class Exercise1 {

    public static void main(String[] args) {

        GreeterTask task = new GreeterTask();

        Thread t1 = new Thread(task);
        t1.setName("Lehman-Thread-1");

        Thread t2 = new Thread(task);
        t2.setName("Lehman-Thread-2");

        t1.start();
        t2.start();
    }
}