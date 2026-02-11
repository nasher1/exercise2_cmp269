import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LehmanGradebookProcessor {

    public static void main(String[] args) {

        try (
            Scanner fileScanner = new Scanner(new File("students.txt"));
            PrintWriter writer = new PrintWriter("grades_report.txt")
        ) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);

                try {
                    String name = lineScanner.next();

                    int score1 = Integer.parseInt(lineScanner.next());
                    int score2 = Integer.parseInt(lineScanner.next());
                    int score3 = Integer.parseInt(lineScanner.next());

                    double average = (score1 + score2 + score3) / 3.0;

                    if (average < 60) {
                        throw new LowGradeException("Low average detected");
                    }

                    writer.printf("Student: %s | Average: %.2f%n", name, average);

                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid data. Skipping line -> " + line);
                } catch (LowGradeException e) {
                    writer.printf("Student: %s | Average: %.2f | WARNING%n",
                            line.split(" ")[0],
                            calculateAverageSafely(line));
                } finally {
                    lineScanner.close();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt not found.");
        } finally {
            System.out.println("Processing Complete");
        }
    }

    private static double calculateAverageSafely(String line) {
        try {
            Scanner sc = new Scanner(line);
            sc.next(); // skip name
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            sc.close();
            return (a + b + c) / 3.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}