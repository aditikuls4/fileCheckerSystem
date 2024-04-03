package com.project.FileChecker.repo;

import com.project.FileChecker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public Student findByStudentEmail(String email);

}



