package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student ayush = new Student(
					"Ayush Yadav",
					"ayushtadav@gmail.com",
					LocalDate.of(1998, Month.MARCH, 4)
					);
			Student ajay = new Student(
					"Ajay Sengar",
					"ajaysengar2@gmail.com",
					LocalDate.of(1999, Month.APRIL, 6)
					);
			Student satyam = new Student(
					"Satyam ",
					"satyam@gmail.com",
					LocalDate.of(1999, Month.FEBRUARY, 8)
					);
			repository.saveAll(List.of(ayush , ajay, satyam));
			
		};
	}
}
