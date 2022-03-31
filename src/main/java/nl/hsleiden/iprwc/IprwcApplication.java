package nl.hsleiden.iprwc;

import nl.hsleiden.iprwc.model.Role;
import nl.hsleiden.iprwc.model.User;
import nl.hsleiden.iprwc.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class IprwcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IprwcApplication.class, args);
	}





	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.createRole(new Role("ROLE_USER"));
			userService.createRole(new Role("ROLE_MANAGER"));
			userService.createRole(new Role("ROLE_ADMIN"));
			userService.createRole(new Role("ROLE_SUPER_ADMIN"));

			userService.create(new User("hichoow", "hichoow@gmail.com", "1234"));
			userService.create(new User("praque", "praque@gmail.com", "1234"));
			userService.create(new User("khalid1999", "khalid1999@gmail.com", "1234"));
			userService.create(new User("azdin070", "azdin070@gmail.com", "1234"));

			userService.addRoleToUser("hichoow", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("hichoow", "ROLE_USER");
			userService.addRoleToUser("praque", "ROLE_USER");
			userService.addRoleToUser("khalid1999", "ROLE_ADMIN");
			userService.addRoleToUser("azdin070", "ROLE_USER");

		};
	}

}
