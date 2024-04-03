package com.project.FileChecker.service;

import com.project.FileChecker.entity.Student;
import com.project.FileChecker.entity.UserRole;
import com.project.FileChecker.repo.RoleRepository;
import com.project.FileChecker.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    StudentRepository sr;

    @Autowired
    RoleRepository rr;

    @Override
    public Student createStudent(Student student, Set<UserRole> userRoles) throws Exception{
        Student isExistStudent = sr.findByStudentEmail(student.getStudentEmail());

        if(isExistStudent!=null)
        {
            throw new Exception("student already present"+ student.getStudentEmail());
        }
else {
            for (UserRole ur : userRoles) {
                rr.save(ur.getRole());
            }

            student.getUserRoles().addAll(userRoles);
            isExistStudent = this.sr.save(student);
        }
    return  isExistStudent;
    }



    @Override
    public String deleteStudentByStudentId(long id) {
        sr.deleteById(id);
        return "student Deleted Successfully";
    }

    @Override
    public List<Student> getAllStudents() {
        return sr.findAll();
    }

}
