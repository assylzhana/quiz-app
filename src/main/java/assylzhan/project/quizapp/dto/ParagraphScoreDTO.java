package assylzhan.project.quizapp.dto;

public class ParagraphScoreDTO {
    private String paragraphName;
    private int score;

    public ParagraphScoreDTO(String paragraphName, int score) {
        this.paragraphName = paragraphName;
        this.score = score;
    }

    public String getParagraphName() {
        return paragraphName;
    }

    public void setParagraphName(String paragraphName) {
        this.paragraphName = paragraphName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
