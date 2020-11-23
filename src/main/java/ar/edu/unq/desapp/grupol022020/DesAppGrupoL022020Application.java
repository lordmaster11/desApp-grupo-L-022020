package ar.edu.unq.desapp.grupol022020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import ar.edu.unq.desapp.grupol022020.services.SchedulingTasksApplication;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DesAppGrupoL022020Application {

	public static void main(String[] args) {
		SpringApplication.run(DesAppGrupoL022020Application.class, args);
		SpringApplication.run(SchedulingTasksApplication.class);
	}
}