package com.example.schoolmanagemntsw.Service;

import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.Model.Course;
import com.example.schoolmanagemntsw.Model.Student;
import com.example.schoolmanagemntsw.Repository.CourseRepository;
import com.example.schoolmanagemntsw.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    public List<Student> getStudent(){
        return studentRepository.findAll();
    }
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public boolean updateStudent(Integer id,Student student){
        Student oldStudent =studentRepository.findStudentById(id);
        if(oldStudent==null){
            return false;
        }
        oldStudent.setName(student.getName());
        studentRepository.save(oldStudent);
        return true;
    }
    public boolean deleteStudent(Integer ID){
        Student oldStudent=studentRepository.findStudentById(ID);
        if(oldStudent==null){
            return false;
        }
        studentRepository.delete(oldStudent);
        return true;
    }
    public void assignStudentToCourse(Integer student_id,Integer course_id){
        Student student=studentRepository.findStudentById(student_id);
        Course course=courseRepository.findCourseById(course_id);

        if(student==null|| course==null){
            throw new ApiException("Wrong Entries");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        courseRepository.save(course);
    }
    public List<Student> getStudentsByCourse(Integer course_id){
        Course course=courseRepository.findCourseById(course_id);
        if(course==null){
            throw new ApiException("Wrong Id");
        }
        List<Student> studentList=studentRepository.findStudentByCourses(course_id);
        return studentList;
    }
    public void ChangeMajor (Integer student_id,String major){
        Student student=studentRepository.findStudentById(student_id);
        if(student==null){
            throw new ApiException("Invalid Id");
        }
       Course course =courseRepository.findCourseByStudentsId(student_id);
        student.setMajor(major);
        student.getCourses().clear();
        studentRepository.save(student);
        course.getStudents().remove(student);
        courseRepository.save(course);
    }




}
