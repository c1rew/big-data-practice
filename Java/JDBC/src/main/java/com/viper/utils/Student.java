package com.viper.utils;


import java.util.Date;

/**
 * @author c1rew
 * @date 2020-12-27 15:10
 */

public class Student {
    private String id;
    private String name;
    private Date birth;
    private String sex;

    public Student() {
    }

    public Student(String id, String name, Date age, String sex) {
        this.id = id;
        this.name = name;
        this.birth = age;
        this.sex = sex;
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

    public Date getAge() {
        return birth;
    }

    public void setAge(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", sex='" + sex + '\'' +
                '}';
    }
}
