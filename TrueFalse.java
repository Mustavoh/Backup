package Backup;

public class TrueFalse extends Question {
    private boolean isTrue;

    public TrueFalse(String questionText, int maxScore, boolean isTrue) {
        super(questionText, maxScore);
        this.isTrue = isTrue;
    }

    // Additional methods for TrueFalse
    public boolean isCorrect(boolean studentAnswer) {
        return studentAnswer == isTrue;
    }
}
