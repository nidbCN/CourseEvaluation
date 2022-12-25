package cn.gaein.java.course_evaluation.dto;

import cn.gaein.java.course_evaluation.entity.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaluationDto {
    private Long id;
    private PraiseDto praise;
    private List<QuestionDto> questions = new ArrayList<>();
    private StudentDto student;
    private CourseDto course;

    public EvaluationDto() {
    }

    public EvaluationDto(Evaluation evaluation) {
        this.id = evaluation.getId();
        if (evaluation.getPraise() != null) {
            this.praise = new PraiseDto(evaluation.getPraise());
        }

        var questions = evaluation.getQuestions();
        if (questions != null) {
            for (var question : questions) {
                this.questions.add(new QuestionDto(question));
            }
        }

        if (evaluation.getStudent() != null) {
            this.student = new StudentDto(evaluation.getStudent());
        }

        if (evaluation.getCourse() != null) {
            this.course = new CourseDto(evaluation.getCourse());
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PraiseDto getPraise() {
        return praise;
    }

    public void setPraise(PraiseDto praise) {
        this.praise = praise;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }
}
