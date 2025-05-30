package com.example.note_app_mobile.models;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Date createdAt;

    public User() {}

    public User(String email, String id, String name, String password, String role, Date createdAt) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}


