package com.example.demo.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Elective {

    private Integer id;

    private String grade;

    private String elective;

    private String studentId;

    @Override
    public String toString() {
        return "Elective{" +
                "id=" + id +
                ", grade='" + grade + '\'' +
                ", elective='" + elective + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
