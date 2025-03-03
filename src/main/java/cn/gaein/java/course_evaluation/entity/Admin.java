package cn.gaein.java.course_evaluation.entity;

import javax.persistence.*;

@Entity
public class Admin {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键

    @Column(length = 10)
    private String name; // 姓名

    @Column(length = 11, unique = true)
    private String username; // 用户名

    @Column
    private String password; // 密码

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
