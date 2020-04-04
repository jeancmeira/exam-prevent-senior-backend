package br.com.preventsenior.exam.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.preventsenior.exam")
public class ExamPreventSeniorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPreventSeniorApplication.class, args);
	}

}
