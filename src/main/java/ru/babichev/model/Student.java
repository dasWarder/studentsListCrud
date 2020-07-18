package ru.babichev.model;

import org.springframework.stereotype.Component;

public class Student {

    private Integer id;

    private String name;

    private String surname;

    private int point;

    public Student() {}

    public Student(String name, String surname, int point) {
        this(null, name, surname, point);
    }

    public Student(Integer id, String name, String surname, int point) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
