package com.deltadc.quizletclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuizletcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizletcloneApplication.class, args);
	}

}
