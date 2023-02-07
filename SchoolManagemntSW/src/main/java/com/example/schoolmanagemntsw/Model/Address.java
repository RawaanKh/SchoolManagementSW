package com.example.schoolmanagemntsw.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    private Integer id;
    @NotEmpty(message = "Area cannot be Empty")
    @Size(min = 3,message = "area must be at least 3 letters")
    private  String area;
    @NotEmpty(message = "Street cannot be Empty")
    private String street;
    @NotNull(message = " Building Number cannot be Empty")
    @Min(value =4 ,message = "Building Number must be at least 4 numbers")
    private Integer buildingNumber;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Teacher teacher;
}
