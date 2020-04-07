package br.com.preventsenior.exam.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.com.preventsenior.exam")
@EnableJpaRepositories("br.com.preventsenior.exam.repository")
@EntityScan("br.com.preventsenior.exam.model")
/**
 * Classe inicial start
 * @author Jean
 *
 */
public class ExamPreventSeniorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamPreventSeniorApplication.class, args);
	}

}
