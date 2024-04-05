package com.project.FileChecker.controller;


import com.project.FileChecker.entity.Role;
import com.project.FileChecker.entity.Student;
import com.project.FileChecker.entity.UserRole;
import com.project.FileChecker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

    @Autowired
    StudentService studentService;
    public static String rid="R1";

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) throws Exception {

        student.setProfile("default.png");
        Set<UserRole> roles=new HashSet<>();
        Role role=new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");

        UserRole userRole=new UserRole();
        userRole.setStudent(student);
        userRole.setRole(role);
        roles.add(userRole);
        return this.studentService.createStudent(student,roles);

    }





    //delete student by id
    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Integer studentId)
    {
       return studentService.deleteStudentByStudentId(studentId);


    }

    //getAllStudents
    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }


}
