package com.example.lap11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @NotNull(message = "categoryId should be not null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

    @NotEmpty(message = "title should be not empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "content should be not empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;

    @NotNull(message = "userId should be not null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "publishDate should be Not Null!")
    @JsonFormat(pattern ="yyyy-MM-dd")
    @PastOrPresent
    @Column(columnDefinition = "datetime default (CURRENT_TIMESTAMP)")
    private LocalDate publishDate;


}
