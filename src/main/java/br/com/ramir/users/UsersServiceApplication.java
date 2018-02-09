package br.com.ramir.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.ramir.users.repo")//TODO TIRAR DAI
public class UsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}
}
