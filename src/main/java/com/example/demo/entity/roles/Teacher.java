package com.example.demo.entity.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {

    private String user_id;
    private String username;
    private String gender;
    private String password;
    private String phone;
    private LocalDateTime registTime;
    private String role;
    private String department;
    private String major;

    @Override
    public String toString() {
        return "Teacher{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", registTime=" + registTime +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
