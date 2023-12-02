package Backup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User {
    private int totalScore;

    public Student(String name, String id, int totalScore) {
        super(name, id);
        this.totalScore = 0;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void takeQuiz(Educator educator) {
        List<String> quizNames = educator.getQuizNames();
        List<ArrayList<Question>> quizzes = educator.getQuizzes();
        Scanner scanner = new Scanner(System.in);

        if (quizNames.isEmpty()) {
            System.out.println("No quizzes are available.");
            return;
        }

        System.out.println("Available Quizzes:");
        for (int i = 0; i < quizNames.size(); i++) {
            System.out.println((i + 1) + ". " + quizNames.get(i));
        }

        System.out.print("Enter the number of the quiz you want to take (1-" + quizNames.size() + "): ");
        int selectedQuizIndex = scanner.nextInt();

        if (selectedQuizIndex < 1 || selectedQuizIndex > quizNames.size()) {
            System.out.println("Invalid quiz selection.");
            return;
        }

        int quizIndex = selectedQuizIndex - 1;
        ArrayList<Question> quiz = quizzes.get(quizIndex);

        System.out.println("Taking Quiz: " + quizNames.get(quizIndex));

        for (Question question : quiz) {
            question.displayQuestion();

            if (question instanceof MCQ) {
                MCQ mcq = (MCQ) question;
                mcq.displayOptions();
                System.out.print("Enter your answer (1-" + mcq.getOptions().length + "): ");
                int selectedOption = scanner.nextInt();

                if (mcq.isCorrect(selectedOption)) {
                    totalScore += mcq.getMaxScore();
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                }
            } else if (question instanceof TrueFalse) {
                TrueFalse trueFalse = (TrueFalse) question;
                System.out.print("Enter your answer (True/False): ");
                boolean answer = scanner.nextBoolean();

                if (trueFalse.isCorrect(answer)) {
                    totalScore += trueFalse.getMaxScore();
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                }
            } else if (question instanceof Essay) {
                Essay essay = (Essay) question;
                scanner.nextLine();
                System.out.print("Enter your essay response: ");
                String essayResponse = scanner.nextLine();
                boolean isCorrect = essay.evaluateEssay(essayResponse);

                if (isCorrect) {
                    totalScore += essay.getMaxScore();
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                }
            }
        }

        System.out.println(this.name + "'s Total Score for Quiz '" + quizNames.get(quizIndex) + "': " + getTotalScore());
    }

    public void viewScores() {
        System.out.println("Viewing scores for Student: " + this.name);
        System.out.println("Total Score: " + totalScore);
        // You can add more logic here to view detailed scores if needed.
    }
}