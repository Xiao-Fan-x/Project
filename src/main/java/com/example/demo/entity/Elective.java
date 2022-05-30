package com.example.demo.entity;


import lombok.Data;

@Data
public class Elective {

    private Integer id;

    private String grade;

    private String elective;

    @Override
    public String toString() {
        return "Elective{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", elective='" + elective + '\'' +
                '}';
    }
}
