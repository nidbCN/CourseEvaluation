package cn.gaein.java.course_evaluation.param;

import org.springframework.validation.annotation.Validated;

@Validated
public class PraiseParam {
    private String content;
    private long evaluationId;

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getEvaluationId() {
        return this.evaluationId;
    }

    public void setEvaluationId(long evaluationId) {
        this.evaluationId = evaluationId;
    }
}
