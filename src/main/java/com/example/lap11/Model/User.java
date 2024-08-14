package com.example.lap11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "username should be not empty")
    @Size(min = 4,message = "name must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password should be not Empty!")
    @Pattern(regexp = "(^(?:(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*)[^\\s]{8,}$)",message = "password must contain 8 characters and digits")
    @Column(columnDefinition = "varchar(15) not null")
    private String password;

    @NotEmpty(message = "email should be not empty!")
    @Email(message = "Email must be valid email")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    @NotNull(message = "registration_date should be Not Null!")
    @JsonFormat(pattern ="yyyy-MM-dd")
    @PastOrPresent
    @Column(columnDefinition = "datetime default (CURRENT_TIMESTAMP)")
    private LocalDate registration_date;


}
