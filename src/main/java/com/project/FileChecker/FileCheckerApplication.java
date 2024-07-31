package com.project.FileChecker;

import com.project.FileChecker.entity.Role;
import com.project.FileChecker.entity.User;
import com.project.FileChecker.entity.UserRole;
import com.project.FileChecker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FileCheckerApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FileCheckerApplication.class, args);
	}

	@Autowired
	private UserService userService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

		User user=new User();
		user.setFirstName("Admin");
		user.setLastName("cts");
		user.setEmail("admin@gmail.com");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setPhoneNo("9123456780");
		user.setProfile("admin.img");

		Role role1=new Role();
		role1.setRoleId(44L);
		role1.setRoleName("ADMIN");

		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);
		userRoleSet.add(userRole);

		User user1=this.userService.createUser(user,userRoleSet);
		System.out.println(user1.getUsername());

	}
}
