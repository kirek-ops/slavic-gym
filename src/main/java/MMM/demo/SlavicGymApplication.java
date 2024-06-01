package MMM.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:.env")
public class SlavicGymApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlavicGymApplication.class, args);
	}

}
