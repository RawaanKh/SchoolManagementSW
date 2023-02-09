package com.example.schoolmanagemntsw.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2,message = "name must be 2 letters or longer")
    private String name;
    @NotNull(message = "Age cannot be Empty")
    @Min(value = 2,message = "age must consist of 2 digits at least")
    private Integer age;

    @NotEmpty(message = "Major cannot be Empty")
    private String major;

    @ManyToMany
    @JsonIgnore
    private Set<Course> courses;

}
