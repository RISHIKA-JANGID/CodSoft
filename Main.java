import java.util.Scanner;

public class StudentGradeCalculator {

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Grade Calculator!");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        double totalMarks = 0;

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
            double marks = scanner.nextDouble();
            totalMarks += marks;
        }

        double averagePercentage = totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        System.out.println("\n----- Results -----");
        System.out.println("Total Marks: " + totalMarks + " out of " + (numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
