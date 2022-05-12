package com.example.demo.entity.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {

    private String userId;
    private String userName;
    private String gender;
    private String password;
    private String phone;
    private LocalDateTime registTime;
    private String role;
    private List<String> roles;
    private String grade;
    private String department;
    private String major;

    @Override
    public String toString() {
        return "Teacher{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", registTime=" + registTime +
                ", role='" + role + '\'' +
                ", roles=" + roles +
                ", grade='" + grade + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
