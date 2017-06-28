package com.roman.app.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Integer age = 0;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee [" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "age=" + age +
                "]";
    }
}