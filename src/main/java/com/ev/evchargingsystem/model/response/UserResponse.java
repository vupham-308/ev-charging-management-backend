package com.ev.evchargingsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    long id;
    String fullName;
    String email;
    String gender;
    String phone;
    String password;
    String token;
}
