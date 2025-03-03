package cn.gaein.java.course_evaluation.dto;

import cn.gaein.java.course_evaluation.entity.Evaluation;
import cn.gaein.java.course_evaluation.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentDto {

    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private String email;
    private List<Long> evaluationIds;

    public StudentDto() {
    }

    public StudentDto(Student student) {
        this.id = student.getId();
        this.idNumber = student.getIdNumber();
        this.name = student.getName();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.evaluationIds = new ArrayList<>();
        Set<Evaluation> evaluations = student.getEvaluations();
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

    public String getIdNumber() {
        return this.idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getEvaluationIds() {
        return this.evaluationIds;
    }

    public void setEvaluationIds(List<Long> evaluationIds) {
        this.evaluationIds = evaluationIds;
    }
}
