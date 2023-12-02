package Backup;

import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);
    private static Educator educator = new Educator("Default Name", "Default ID", "Default Subject");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to IQMAAS, Your favorite Console Based Quiz System");
            System.out.println("Choose an option:");
            System.out.println("1. Educator");
            System.out.println("2. Student");
            System.out.println("3. Exit");

            try {
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        educatorMenu();
                        break;
                    case 2:
                        studentMenu();
                        break;
                    case 3:
                        System.out.println("Exiting the Quiz Management System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice (1, 2, or 3).");
                scanner.nextLine();
            }
        }
    }

    private static void educatorMenu() {
        System.out.println("You have chosen to be an Educator.");

        // Ask for the Educator's name and ID
        System.out.print("Enter your name: ");
        String educatorName = scanner.nextLine();
        System.out.print("Enter your ID: ");
        String educatorID = scanner.nextLine();
        System.out.print("Enter the subject you're teaching: ");
        String educatorSubject = scanner.nextLine();

        // Update the Educator object with user input
        educator.setName(educatorName);
        educator.setID(educatorID);
        educator.setSubject(educatorSubject);

        boolean educatorMenuActive = true;

        while (educatorMenuActive) {
            System.out.println("Educator Menu");
            System.out.println("Choose an option:");
            System.out.println("1. Create Quiz");
            System.out.println("2. Edit Quiz");
            System.out.println("3. Delete Quiz");
            System.out.println("4. View Students' Scores");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int educatorChoice;

            try {
                educatorChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option (1-5).");
                scanner.nextLine(); // Consume the newline character
                continue; // Continue the loop to get a valid input
            }

            switch (educatorChoice) {
                case 1:
                    // Create Quiz
                    educator.createQuiz();
                    break;
                case 2:
                    // Edit Quiz
                    educator.editQuiz();
                    break;
                case 3:
                    // Delete Quiz
                    educator.deleteQuiz();
                    break;
                case 4:
                    // View Students' Scores
                    break;
                case 5:
                    // Back to Main Menu
                    educatorMenuActive = false;
                    System.out.println("Returning to the Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-5).");
            }
        }
    }

    private static void studentMenu() {
        System.out.println("You have chosen to be a Student.");

        // Ask for the student's name and ID
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter your ID: ");
        String studentID = scanner.nextLine();

        // Create a Student object
        Student student = new Student(studentName, studentID, 1);

        boolean studentMenuActive = true;

        while (studentMenuActive) {
            System.out.println("Student Menu");
            System.out.println("Choose an option:");
            System.out.println("1. Take Quiz");
            System.out.println("2. View Scores");
            System.out.println("3. Back to Main Menu");

            System.out.print("Enter your choice: ");
            int studentChoice;

            try {
                studentChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option (1-3).");
                scanner.nextLine(); // Consume the newline character
                continue; // Continue the loop to get a valid input
            }

            switch (studentChoice) {
                case 1:
                    // Take Quiz
                    student.takeQuiz(educator);
                    break;
                case 2:
                    // View Scores
                    student.viewScores();
                    break;
                case 3:
                    // Back to Main Menu
                    studentMenuActive = false;
                    System.out.println("Returning to the Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-3).");
            }
        }
    }
}