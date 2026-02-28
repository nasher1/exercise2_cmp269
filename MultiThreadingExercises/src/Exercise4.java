public class Exercise4 {

    public static void main(String[] args) throws InterruptedException {

        final long[] result = {0};

        Thread heavyCalculation = new Thread(() -> {
            for (long i = 0; i < 1_000_000_000L; i++) {
                result[0] += i;
            }
        });

        heavyCalculation.start();

        heavyCalculation.join(); // WAIT HERE

        System.out.println("Calculation Finished: " + result[0]);
    }
}