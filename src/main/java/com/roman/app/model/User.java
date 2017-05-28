package com.roman.app.model;

import javax.persistence.*;

@Entity
//@Table(name = "users", catalog = "app")
public class User {

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String name;
    //private String  education;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}