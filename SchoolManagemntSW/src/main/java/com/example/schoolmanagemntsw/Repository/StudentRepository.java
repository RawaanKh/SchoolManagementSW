package com.example.schoolmanagemntsw.Repository;

import com.example.schoolmanagemntsw.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentById(Integer id);
    List<Student>findStudentByCourses(Integer id);


}
