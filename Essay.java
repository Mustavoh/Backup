package Backup;

public class Essay extends Question {
    private String sampleAnswer;

    public Essay(String questionText, int maxScore, String sampleAnswer) {
        super(questionText, maxScore);
        this.sampleAnswer = sampleAnswer;
    }

    // Method to set the sample answer
    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }

    // Method to evaluate an essay answer
    public boolean evaluateEssay(String studentAnswer) {
        System.out.println("Evaluating Essay Question:");
        System.out.println("Question: " + getQuestionText());
        System.out.println("Maximum Score: " + getMaxScore());
        System.out.println("Student's Answer: " + studentAnswer);
        System.out.println("Sample Answer: " + sampleAnswer);

        // Implement the essay evaluation logic
        if (studentAnswer.equals(sampleAnswer)) {
            return true;
        } else {
            return false;
        }

    }
}
