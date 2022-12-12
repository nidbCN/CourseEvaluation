package cn.gaein.java.course_evaluation.param;

import org.springframework.validation.annotation.Validated;

@Validated
public class EvaluationParam {
    private long studentId;
    private long courseId;

    public long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }
}
