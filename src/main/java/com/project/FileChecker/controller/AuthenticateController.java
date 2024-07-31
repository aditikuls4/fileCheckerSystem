package com.project.FileChecker.controller;



import java.security.Principal;

import com.project.FileChecker.config.JwtUtil;
import com.project.FileChecker.entity.JwtRequest;
import com.project.FileChecker.entity.JwtResponse;
import com.project.FileChecker.entity.User;
import com.project.FileChecker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
    {
        try
        {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch (UsernameNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new Exception("User not found");
        }
        ///authenticate
        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username,String password) throws Exception
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        }catch (DisabledException e) {
            // TODO: handle exception
            throw new Exception("USER DISABLED");
        }catch (BadCredentialsException e) {
            // TODO: handle exception
            throw new Exception("Inavlid Credentials "+e.getMessage());
        }
    }
    //return the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {
        return ( (User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()) );
    }
}
