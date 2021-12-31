package com.aimerneige.course_evaluation.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.aimerneige.course_evaluation.model.Course;
import com.aimerneige.course_evaluation.model.Evaluation;
import com.aimerneige.course_evaluation.model.Student;

public class CourseDto {

    private Long id;
    private String title;
    private String description;
    private Long teacherId;
    private List<Long> studentIds;
    private List<Long> evaluationIds;

    public CourseDto() {
    }

    public CourseDto(Course course) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.description = course.getDescription();
        this.teacherId = course.getTeacher().getId();
        Set<Student> students = course.getStudents();
        if (students != null) {
            for (Student student : students) {
                this.studentIds.add(student.getId());
            }
        }
        Set<Evaluation> evaluations = course.getEvaluations();
        if (evaluations != null) {
            for (Evaluation evaluation : evaluations) {
                this.evaluationIds.add(evaluation.getId());
            }
        }
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Long> getStudentIds() {
        return this.studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Long> getEvaluationIds() {
        return this.evaluationIds;
    }

    public void setEvaluationIds(List<Long> evaluationIds) {
        this.evaluationIds = evaluationIds;
    }
}
