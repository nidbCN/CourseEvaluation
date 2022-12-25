package cn.gaein.java.course_evaluation.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.gaein.java.course_evaluation.entity.Course;
import cn.gaein.java.course_evaluation.entity.Teacher;

public class TeacherDto {

    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private List<Long> courseIds;

    public TeacherDto() {
    }

    public TeacherDto(Teacher teacher) {
        this.id = teacher.getId();
        this.idNumber = teacher.getIdNumber();
        this.name = teacher.getName();
        this.phone = teacher.getPhone();
        this.courseIds = new ArrayList<>();
        Set<Course> courses = teacher.getCourses();
        if (courses != null) {
            for (var course : courses) {
                this.courseIds.add(course.getId());
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

    public List<Long> getCourseIds() {
        return this.courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
