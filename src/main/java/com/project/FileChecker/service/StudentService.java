package com.project.FileChecker.service;

import com.project.FileChecker.entity.Student;
import com.project.FileChecker.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface StudentService {

    public Student createStudent(Student stu, Set<UserRole> userRoles) throws Exception;


    public String deleteStudentByStudentId(long id);

    public List<Student> getAllStudents();

}
