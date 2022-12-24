package cn.gaein.java.course_evaluation.dto;

import cn.gaein.java.course_evaluation.entity.Admin;

public class AdminDto {

    private long id;
    private String name;
    private String username;

    public AdminDto() {
    }

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.username = admin.getUsername();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
