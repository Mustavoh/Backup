package Backup;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Educator extends User {
    private String subject;
    private List<ArrayList<Question>> quizzes;
    private List<String> quizNames;

    public Educator(String name, String id, String subject) {
        super(name, id);
        this.subject = subject;
        this.quizzes = new ArrayList<>();
        this.quizNames = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getQuizNames() {
        return quizNames;
    }

    public List<ArrayList<Question>> getQuizzes() {
        return quizzes;
    }

    public void createQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating a new quiz by Teacher: " + this.name + " of subject: " + this.subject);

        System.out.print("Enter the quiz name: ");
        String quizName = scanner.nextLine();
        quizNames.add(quizName);

        ArrayList<Question> quiz = new ArrayList<>();

        boolean addMoreQuestions = true;

        while (addMoreQuestions) {
            System.out.print("Enter the question text: ");
            String questionText = scanner.nextLine();

            int questionType = 0;

            while (questionType < 1 || questionType > 3) {
                System.out.println("Select the question type:");
                System.out.println("1. Multiple Choice Question (MCQ)");
                System.out.println("2. Essay Question");
                System.out.println("3. True/False Question");
                System.out.print("Enter the number (1/2/3): ");

                try {
                    questionType = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (questionType < 1 || questionType > 3) {
                        System.out.println("Invalid question type. Please choose 1, 2, or 3.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                    scanner.nextLine(); // Consume the newline character
                }
            }

            if (questionType == 1) {
                System.out.print("Enter the maximum score for this question: ");

                int maxScore = 0;
                boolean validMaxScore = false;

                while (!validMaxScore) {
                    try {
                        maxScore = scanner.nextInt();
                        scanner.nextLine();
                        validMaxScore = true;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the maximum score.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                System.out.print("Enter the number of options: ");

                int numOptions = 0;
                boolean validNumOptions = false;

                while (!validNumOptions) {
                    try {
                        numOptions = scanner.nextInt();
                        scanner.nextLine();
                        validNumOptions = true;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the number of options.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                String[] options = new String[numOptions];
                for (int i = 0; i < numOptions; i++) {
                    System.out.print("Enter option " + (i + 1) + ": ");
                    options[i] = scanner.nextLine();
                }

                int correctOption = 0;
                boolean validCorrectOption = false;

                while (!validCorrectOption || correctOption < 1 || correctOption > numOptions) {
                    System.out.print("Enter the correct option (1-" + numOptions + "): ");

                    try {
                        correctOption = scanner.nextInt();
                        scanner.nextLine();
                        validCorrectOption = true;

                        if (correctOption < 1 || correctOption > numOptions) {
                            System.out.println("Invalid option. Please enter a valid option number.");
                        }
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the correct option.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                MCQ mcq = new MCQ(questionText, maxScore, options, correctOption);
                quiz.add(mcq);
            }
            else if (questionType == 2) {
                System.out.print("Enter the maximum score for this question: ");

                int maxScore = 0;
                boolean validMaxScore = false;

                while (!validMaxScore) {
                    try {
                        maxScore = scanner.nextInt();
                        scanner.nextLine();
                        validMaxScore = true;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the maximum score.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }
                System.out.print("Enter the sample answer for the essay: ");
                String sampleAnswer = scanner.nextLine();

                Essay essay = new Essay(questionText, maxScore, sampleAnswer);
                quiz.add(essay);
            } else if (questionType == 3) {
                System.out.print("Enter the maximum score for this question: ");

                int maxScore = 0;
                boolean validMaxScore = false;

                while (!validMaxScore) {
                    try {
                        maxScore = scanner.nextInt();
                        scanner.nextLine();
                        validMaxScore = true;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number for the maximum score.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                System.out.print("Is the correct answer 'True' or 'False'? ");
                String answer = scanner.nextLine();
                boolean isTrue = answer.equalsIgnoreCase("True");

                TrueFalse trueFalse = new TrueFalse(questionText, maxScore, isTrue);
                quiz.add(trueFalse);
            } else {
                System.out.println("Invalid question type. Please choose 1, 2, or 3.");
            }

            System.out.print("Add one more question? (yes/no): ");
            String addMore = scanner.nextLine();
            addMoreQuestions = addMore.equalsIgnoreCase("yes");
        }

        quizzes.add(quiz);
        System.out.println("Quiz '" + quizName + "' has been successfully created with " + quiz.size() + " questions.");
    }


    public void editQuiz() {
        Scanner scanner = new Scanner(System.in);

        if (quizNames.isEmpty()) {
            System.out.println("There are no quizzes available to edit.");
            return; // Return to the educator menu
        }

        System.out.println("Select a quiz to edit:");

        for (int i = 0; i < quizNames.size(); i++) {
            System.out.println((i + 1) + ". " + quizNames.get(i));
        }

        System.out.print("Enter the number of the quiz you want to edit (1-" + quizNames.size() + "): ");
        int quizNumber = scanner.nextInt();

        if (quizNumber < 1 || quizNumber > quizNames.size()) {
            System.out.println("Invalid quiz number. Please enter a valid number.");
            return; // Return to the educator menu
        }

        int quizIndex = quizNumber - 1;
        ArrayList<Question> quiz = quizzes.get(quizIndex);
        boolean editMoreQuestions = true;

        while (editMoreQuestions) {
            System.out.println("Select an option to edit a question in the quiz '" + quizNames.get(quizIndex) + "':");
            System.out.println("1. Add a new question");
            System.out.println("2. Remove a question");
            System.out.print("Enter the number (1/2): ");
            int editOption = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (editOption == 1) {
                System.out.print("Enter the question text: ");
                String questionText = scanner.nextLine();

                System.out.println("Select the question type:");
                System.out.println("1. Multiple Choice Question (MCQ)");
                System.out.println("2. Essay Question");
                System.out.println("3. True/False Question");
                System.out.print("Enter the number (1/2/3): ");
                int questionType = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (questionType == 1) {
                    System.out.print("Enter the maximum score for this question: ");
                    int maxScore = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the number of options: ");
                    int numOptions = scanner.nextInt();
                    scanner.nextLine();

                    String[] options = new String[numOptions];
                    for (int i = 0; i < numOptions; i++) {
                        System.out.print("Enter option " + (i + 1) + ": ");
                        options[i] = scanner.nextLine();
                    }

                    System.out.print("Enter the correct option (1-" + numOptions + "): ");
                    int correctOption = scanner.nextInt();
                    scanner.nextLine();

                    MCQ mcq = new MCQ(questionText, maxScore, options, correctOption);
                    quiz.add(mcq);
                } else if (questionType == 2) {
                    System.out.print("Enter the maximum score for this question: ");
                    int maxScore = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the sample answer for the essay: ");
                    String sampleAnswer = scanner.nextLine();

                    Essay essay = new Essay(questionText, maxScore, sampleAnswer);
                    quiz.add(essay);
                } else if (questionType == 3) {
                    System.out.print("Enter the maximum score for this question: ");
                    int maxScore = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Is the correct answer 'True' or 'False'? ");
                    String answer = scanner.nextLine();
                    boolean isTrue = answer.equalsIgnoreCase("True");

                    TrueFalse trueFalse = new TrueFalse(questionText, maxScore, isTrue);
                    quiz.add(trueFalse);
                } else {
                    System.out.println("Invalid question type. Please choose 1, 2, or 3.");
                }
            } else if (editOption == 2) {
                if (quiz.isEmpty()) {
                    System.out.println("There are no questions to remove in the quiz.");
                } else {
                    System.out.println("Select a question to remove in the quiz '" + quizNames.get(quizIndex) + "':");

                    for (int i = 0; i < quiz.size(); i++) {
                        System.out.println((i + 1) + ". Question " + (i + 1));
                    }

                    System.out.print("Enter the number of the question you want to remove (1-" + quiz.size() + "): ");
                    int questionNumber = scanner.nextInt();

                    if (questionNumber < 1 || questionNumber > quiz.size()) {
                        System.out.println("Invalid question number. Please enter a valid number.");
                    } else {
                        quiz.remove(questionNumber - 1);
                        System.out.println("Question " + questionNumber + " has been removed.");
                    }
                }
            } else {
                System.out.println("Invalid option. Please choose 1 or 2.");
            }

            System.out.print("Edit more questions for this quiz? (yes/no): ");
            String editMore = scanner.nextLine();
            editMoreQuestions = editMore.equalsIgnoreCase("yes");
        }
    }

    public void deleteQuiz() {
        Scanner scanner = new Scanner(System.in);

        if (quizNames.isEmpty()) {
            System.out.println("There are no quizzes to delete.");
            return;
        }

        System.out.println("Select a quiz to delete:");

        for (int i = 0; i < quizNames.size(); i++) {
            System.out.println((i + 1) + ". " + quizNames.get(i));
        }

        System.out.print("Enter the number of the quiz you want to delete (1-" + quizNames.size() + "): ");
        int quizNumber = scanner.nextInt();

        if (quizNumber < 1 || quizNumber > quizNames.size()) {
            System.out.println("Invalid quiz number. Please enter a valid number.");
        } else {
            quizNames.remove(quizNumber - 1);
            quizzes.remove(quizNumber - 1);
            System.out.println("Quiz " + quizNumber + " has been deleted.");
        }
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@override
    //public String toString() {
    //        return super.toString();
    //    }
}
