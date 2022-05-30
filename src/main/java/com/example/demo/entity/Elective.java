package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
