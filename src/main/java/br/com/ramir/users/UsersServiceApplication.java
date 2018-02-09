package br.com.ramir.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigServer
@EnableJpaRepositories("br.com.ramir.users.repo")
public class UsersServiceApplication extends SpringBootServletInitializer {



	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UsersServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}
}
