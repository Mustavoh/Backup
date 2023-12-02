package Backup;

public class MCQ extends Question {
    private String[] options;
    private int correctOption;

    public MCQ(String questionText, int maxScore, String[] options, int correctOption) {
        super(questionText, maxScore);
        this.options = options;
        this.correctOption = correctOption;
    }
    public String[] getOptions() {
        return options;
    }
    // Additional methods for MCQ
    public void displayOptions() {
        System.out.println("Options:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}
