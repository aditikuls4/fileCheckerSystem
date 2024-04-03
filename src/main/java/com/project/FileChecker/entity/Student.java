package com.project.FileChecker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

@Getter @Setter @AllArgsConstructor
@Entity
public class Student
//        implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotBlank(message = "Name field must required")
    @Size(min = 2,max = 20,message = "min 2 and max 20 characters required!!!!")
    private String username;
    @Column(unique = true)
    private String studentEmail;
    private String firstname;
    private String lasttname;

    //user has many roles
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "student")
   @JsonIgnore
    //so that it will not create circular dependency
    private Set<UserRole> userRoles=new HashSet<>();

    @Size(min = 9,message = "password size should not less than 9")
    private String password;
    @Pattern(regexp = "^\\d{10}$")
    private String phone;
    private String profile;
    private boolean enabled=true;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
}
