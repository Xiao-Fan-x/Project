package com.example.demo.entity.roles;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {

    private String userId;
    private String password;
    private String role;
    private List<String> roles;
}
