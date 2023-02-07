package com.example.schoolmanagemntsw.Service;

import com.example.schoolmanagemntsw.Api.ApiException;
import com.example.schoolmanagemntsw.Model.Teacher;
import com.example.schoolmanagemntsw.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    public List<Teacher> getTeacher(){
        return teacherRepository.findAll();
    }
    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
    public boolean updateTeacher(Integer id,Teacher teacher){
        Teacher oldTeacher=teacherRepository.findTeacherById(id);
        if(oldTeacher==null){
            return false;
        }
        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setSalary(teacher.getSalary());
        teacherRepository.save(oldTeacher);
        return true;
    }
    public boolean deleteTeacher(Integer id){
        Teacher oldTeacher=teacherRepository.findTeacherById(id);
        if(oldTeacher==null){
            return false;
        }
        teacherRepository.delete(oldTeacher);
        return true;
    }
    public Teacher getTeacherInfo(Integer id){
        Teacher teacher=teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw new ApiException("Required Teacher Not Found");
        }
        return teacher;
    }
}
