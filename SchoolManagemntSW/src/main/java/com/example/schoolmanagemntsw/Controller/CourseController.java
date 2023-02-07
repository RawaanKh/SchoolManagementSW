package com.example.schoolmanagemntsw.Controller;

import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.Model.Course;
import com.example.schoolmanagemntsw.Service.CourseService;
import com.example.schoolmanagemntsw.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/course")
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    @GetMapping("/get")
    public ResponseEntity getCourse(){
        List<Course>course=courseService.getCourse();
        return ResponseEntity.status(200).body(course);
    }
    @PostMapping("/add")
    public ResponseEntity addCourse(@Valid @RequestBody Course course ){
        courseService.addCourse(course);
        return ResponseEntity.status(200).body("Course Added");
    }
    @PutMapping("/update/{ID}")
    public ResponseEntity updateCourse(@PathVariable Integer ID,@Valid@RequestBody Course course){
        boolean isUpdate= courseService.updateCourse(ID,course);
        if(isUpdate) {
            return ResponseEntity.status(200).body("Course Updated");
        }
        throw new ApiException("Wrong Id");
    }
    @DeleteMapping("/delete/{ID}")
    public ResponseEntity deleteCourse(@PathVariable Integer ID){
        boolean isDelete= courseService.deleteCourse(ID);
        if(isDelete) {
            return ResponseEntity.status(200).body("Course Deleted");
        }
        throw new ApiException("Wrong Id");
    }
    @PutMapping("/assign/{teacher_id}/{course_id}")
    public ResponseEntity assignCourseToTeacher(@PathVariable Integer teacher_id,@PathVariable Integer course_id){
        courseService.assignCourseToTeacher(teacher_id,course_id);
        return ResponseEntity.status(200).body("Course assigned to teacher successfully");
    }
    @GetMapping("/getTeacherName/{course_id}")
    public ResponseEntity getTeacherName(@PathVariable Integer course_id){
        String Tname = courseService.getTeacherName(course_id);
        return ResponseEntity.status(200).body(Tname);
    }
}
