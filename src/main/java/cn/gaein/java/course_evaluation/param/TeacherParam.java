package cn.gaein.java.course_evaluation.param;

import org.springframework.validation.annotation.Validated;

@Validated
public class TeacherParam {
    private String idNumber;
    private String name;
    private String phone;

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
}
