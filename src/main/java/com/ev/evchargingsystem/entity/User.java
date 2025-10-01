package com.ev.evchargingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Fullname not empty!")
    private String fullName;
    @Email
    private String email;
    @Pattern(regexp = "/(0[3|5|7|8|9])+([0-9]{8})\\b/g")
    private String phone;
    @Pattern(regexp = "\\d{12}")
    //Government ID có 12 số
    private String governmentID;

    private String role;
}
