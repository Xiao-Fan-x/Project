package com.example.demo.entity;


import lombok.Data;

@Data
public class Clasz {

    private String grade;
    private String department;
    private String major;
    private String className;

    @Override
    public String toString() {
        return "Clasz{" +
                "grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
