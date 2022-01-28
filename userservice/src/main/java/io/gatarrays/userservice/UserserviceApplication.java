package io.gatarrays.userservice;

import io.gatarrays.userservice.domain.Role;
import io.gatarrays.userservice.domain.User;
import io.gatarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "Test User", "testuser", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Test Manager", "testmanager", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Test Admin", "testadmin", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Test Super Admin", "testsuperadmin", "1234", new ArrayList<>()));

			userService.addRoleToUser("testuser", "ROLE_USER");
			userService.addRoleToUser("testmanager", "ROLE_MANAGER");
			userService.addRoleToUser("testadmin", "ROLE_ADMIN");
			userService.addRoleToUser("testsuperadmin", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("testsuperadmin", "ROLE_ADMIN");
			userService.addRoleToUser("testsuperadmin", "ROLE_USER");
		};
	}

}
