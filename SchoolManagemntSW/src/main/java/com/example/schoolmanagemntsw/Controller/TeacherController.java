package com.example.schoolmanagemntsw.Controller;

import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.DTO.AddressDTO;
import com.example.schoolmanagemntsw.Model.Teacher;
import com.example.schoolmanagemntsw.Service.AddressService;
import com.example.schoolmanagemntsw.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/Teacher")

public class TeacherController {
    private final TeacherService teacherService;
    private  final AddressService addressService;

    @GetMapping("/get")
    public ResponseEntity getTeacher(){
        List<Teacher> teachers=teacherService.getTeacher();
        return ResponseEntity.status(200).body(teachers);
    }
    @PostMapping("/add")
    public ResponseEntity addTeacher(@Valid @RequestBody Teacher teacher){

        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("Teacher Added");
    }
    @PutMapping("/Update/{ID}")
    public ResponseEntity updateTeacher(@PathVariable Integer ID,@Valid@RequestBody Teacher teacher){
        boolean isUpdate= teacherService.updateTeacher(ID,teacher);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Teacher Updated");
        }
        throw new ApiException("Invalid Teacher Id");
    }
    @DeleteMapping("/Delete/{ID}")
    public  ResponseEntity deleteTeacher(@PathVariable Integer ID){
        boolean isDelete= teacherService.deleteTeacher(ID);
        if(isDelete) {
            return ResponseEntity.status(200).body("Teacher Deleted");
        }
        throw new ApiException("Invalid Teacher Id");
    }
    @PostMapping("/AddAddress")
    public ResponseEntity addAddress(@RequestBody AddressDTO addressDTO){
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(200).body("Address Added ");
    }
    @PutMapping("/UpdateAddress")
    public ResponseEntity updateAddress(@RequestBody AddressDTO addressDTO) {
        boolean isUpdated = addressService.updateAddress(addressDTO);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Address updated ");
        }
        throw new ApiException("Invalid Address Id");
    }
    @DeleteMapping("/DeleteAddress/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id) {
        boolean isUpdated = addressService.deleteAddress(id);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Address deleted");
        }
        throw new ApiException("Invalid Address Id");
    }
    @GetMapping("/TeacherInfo/{id}")
    public ResponseEntity getTeacherInfo(@PathVariable Integer id){
        Teacher teacher=teacherService.getTeacherInfo(id);
        return ResponseEntity.status(200).body(teacher);
    }
}
