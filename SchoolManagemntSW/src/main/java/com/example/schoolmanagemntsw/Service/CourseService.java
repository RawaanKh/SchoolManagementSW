package com.example.schoolmanagemntsw.Service;


import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.Model.Course;
import com.example.schoolmanagemntsw.Model.Teacher;
import com.example.schoolmanagemntsw.Repository.CourseRepository;
import com.example.schoolmanagemntsw.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    public List<Course>getCourse(){
        return courseRepository.findAll();
    }
    public void addCourse(Course course){
        courseRepository.save(course);
    }
    public boolean updateCourse(Integer id,Course course){
        Course oldCourse =courseRepository.findCourseById(id);
        if(oldCourse==null){
            return false;
        }
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
        return true;
    }
    public boolean deleteCourse(Integer ID){
        Course oldCourse=courseRepository.findCourseById(ID);
        if(oldCourse==null){
            return false;
        }
        courseRepository.delete(oldCourse);
        return true;
    }
    public void assignCourseToTeacher(Integer teacher_id,Integer course_id){
        Teacher teacher=teacherRepository.findTeacherById(teacher_id);
        Course course=courseRepository.findCourseById(course_id);
        if(teacher==null || course==null){
            throw new ApiException("Wrong id");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }
    public String getTeacherName(Integer course_id){

        Course course = courseRepository.findCourseById(course_id);
        Teacher teacher = teacherRepository.findTeacherById(course.getTeacher().getId());
        if (teacher==null ||course==null ) {
            throw new ApiException("Id is not found");
        }
        else return teacher.getName();
    }
}
