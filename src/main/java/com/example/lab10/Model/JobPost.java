package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Entity@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    @NotEmpty(message = "title should not be empty")
    @Size(min = 5, message = "title should be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null ")
    private String title;
    @NotEmpty(message = "description should not be empty")
    @Column(columnDefinition = "varchar(200) not null ")
    private String description;
    @NotEmpty(message = "location should not be empty")
    @Column(columnDefinition = "varchar(60) not null")
    private String location;
//    @NotNull(message = "salary should not be empty")
    @Positive(message = "salary should be a positive number")
    @Column(columnDefinition = "int not null check (salary>=0) ")
    private Double salary;
    @NotNull(message = "posting_date should not be null")
    @Column(columnDefinition = "date not null default current_time")
    private LocalDate posting_date;
}
