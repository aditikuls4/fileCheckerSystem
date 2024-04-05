package com.project.FileChecker.service;

import com.project.FileChecker.entity.Student;
import com.project.FileChecker.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService  {


    @Autowired
    StudentRepository sr;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=this.sr.findByStudentEmail(username);
        if(student==null)
        {
            System.out.println("user not found");
            throw new UsernameNotFoundException("User not Found!!");
        }
        return student;

    }
}
