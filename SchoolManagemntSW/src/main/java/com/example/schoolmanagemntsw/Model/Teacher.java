package com.example.schoolmanagemntsw.Model;

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
public class Teacher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2,message = "name must be 2 letters or longer")
    private String name;
    @NotNull(message = "Age cannot be Empty")
    @Min(value = 2,message = "age must consist of 2 digits at least")
    private Integer age;
    @Email(message = "Invalid Email")
    @NotEmpty(message = "Email cannot be Empty")
    private String email;
    @NotNull(message = "Salary cannot be Empty")
    @Min(value = 3,message = "Salary must consist of 3 digits at least")
    private Integer salary;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="teacher" )
    private Set<Course> courses;



}
