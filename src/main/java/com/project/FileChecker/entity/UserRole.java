package com.project.FileChecker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRole {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private  Long userRoleId;

    //single user


    @ManyToOne(fetch = FetchType.EAGER)
   private Student student;

    @ManyToOne
private  Role role;

}
