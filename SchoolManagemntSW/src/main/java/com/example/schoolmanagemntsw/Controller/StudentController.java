package com.example.schoolmanagemntsw.Controller;

import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.Model.Student;
import com.example.schoolmanagemntsw.Model.Teacher;
import com.example.schoolmanagemntsw.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/student")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudent(){
        List<Student>students=studentService.getStudent();
        return ResponseEntity.status(200).body(students);
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@Valid @RequestBody Student student){

        studentService.addStudent(student);
        return ResponseEntity.status(200).body("Student Added");
    }
    @PutMapping("/Update/{ID}")
    public ResponseEntity updateStudent(@PathVariable Integer ID,@Valid@RequestBody Student student){
        boolean isUpdate= studentService.updateStudent(ID,student);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Student Updated");
        }
        throw new ApiException("Invalid Student Id");
    }
    @DeleteMapping("/Delete/{ID}")
    public ResponseEntity deleteTeacher(@PathVariable Integer ID){
        boolean isDelete= studentService.deleteStudent(ID);
        if(isDelete) {
            return ResponseEntity.status(200).body("Student Deleted");
        }
        throw new ApiException("Invalid Student Id");
    }
    @PutMapping("/assign/{student_id}/{course_id}")
    public ResponseEntity assignStudentToCourse(@PathVariable Integer student_id ,@PathVariable Integer course_id){
        studentService.assignStudentToCourse(student_id,course_id);
        return ResponseEntity.status(200).body("Student Assigned to course");
    }
    //
    @GetMapping("/getStudents/{course_id}")
    public ResponseEntity getStudentsByCourse(@PathVariable Integer course_id ){
        List<Student>students=studentService.getStudentsByCourse(course_id);
        return ResponseEntity.status(200).body(students);
    }
    @PutMapping("/{student_id}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer student_id,@PathVariable String major){
        studentService.ChangeMajor(student_id,major);
        return ResponseEntity.status(200).body("Major has been  changed");
    }



}
