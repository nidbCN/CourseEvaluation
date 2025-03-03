package cn.gaein.java.course_evaluation.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {

    @OneToMany(mappedBy = "teacher")
    public Set<Course> courses; // 课程
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键
    @Column(length = 10, unique = true)
    private String idNumber; // 工号
    @Column(length = 10)
    private String name; // 姓名
    @Column(length = 11)
    private String phone; // 手机号
    @Column(length = 2)
    private String sex; // 性别
    @Column
    private Integer age; // 年龄

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

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
