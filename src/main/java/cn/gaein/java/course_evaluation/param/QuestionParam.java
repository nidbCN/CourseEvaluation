package cn.gaein.java.course_evaluation.param;

import org.springframework.validation.annotation.Validated;

@Validated
public class QuestionParam {
    private String content;
    private int score;
    private long evaluationId;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getEvaluationId() {
        return this.evaluationId;
    }

    public void setEvaluationId(long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
