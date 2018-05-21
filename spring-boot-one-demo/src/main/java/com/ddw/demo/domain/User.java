package com.ddw.demo.domain;

import java.util.Date;

public class User {

    private Long id;

    private Name name;

    private Integer age;

    private Date birthday;

    public User() {
    }

    public User(Name name) {
        this.name = name;
        System.err.println("object user create.....");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
