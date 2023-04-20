package com.rolex.watchit;

import com.rolex.watchit.auth.AuthenticationService;
import com.rolex.watchit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WatchitApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchitApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthenticationService service,UserRepository repository) {
		return args -> {
			service.registerAdmin();
		};
	}
}
