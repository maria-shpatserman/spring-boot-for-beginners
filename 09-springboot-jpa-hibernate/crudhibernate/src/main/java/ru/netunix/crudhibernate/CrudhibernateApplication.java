package ru.netunix.crudhibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;

@SpringBootApplication
public class CrudhibernateApplication {
	@Bean
	public CommandLineRunner commandLineRunner(String[] args) throws Exception {

        return (arguments) -> {
			System.out.println("Hello world");
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(CrudhibernateApplication.class, args);
	}

}
