package Backup;

public class Question {
    private String questionText;
    private int maxScore;

    public Question(String questionText, int maxScore) {
        this.questionText = questionText;
        this.maxScore = maxScore;
    }

    // Getters and setters for question text and max score
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    // Method to display the question
    public void displayQuestion() {
        System.out.println("Question: " + questionText);
    }
}
