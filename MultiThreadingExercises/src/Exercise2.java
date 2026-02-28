public class Exercise2 {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // After creation
        System.out.println("After creation: " + t.getState());

        t.start();

        // Immediately after start
        System.out.println("After start: " + t.getState());

        Thread.sleep(500); // Wait so child thread is sleeping

        // While sleeping
        System.out.println("While sleeping: " + t.getState());

        t.join(); // Wait for thread to finish

        // After finishing
        System.out.println("After finishing: " + t.getState());
    }
}