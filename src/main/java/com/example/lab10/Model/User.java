package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @NotEmpty(message = "name should not be empty")
    @Size(min=5,max = 20, message = "name should contain at least 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "name should only contain characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "please provide valid email")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8,message = "password should at least contain 8 characters")
//    @Pattern(regexp = )
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
//    @NotNull(message = "age should not be empty")
    @Min(value = 18,message = "minimum age should be 18")
    @Column(columnDefinition = "int not null check (age >= 18)")
    private int age;
    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "role can only be JOB_SEEKER or EMPLOYER")
    @Column(columnDefinition = "varchar(10) not null check(role = 'JOB_SEEKER' or role = 'EMPLOYER')")
    private String role;

}
