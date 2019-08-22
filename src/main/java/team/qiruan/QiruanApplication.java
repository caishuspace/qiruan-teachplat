package team.qiruan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QiruanApplication {

	public static void main(String[] args) {
		SpringApplication.run(QiruanApplication.class, args);
	}

}
