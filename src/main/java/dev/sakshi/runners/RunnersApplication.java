package dev.sakshi.runners;


// import java.time.LocalDateTime;
// import java.time.temporal.ChronoUnit;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;



// import dev.sakshi.runners.run.Run;
// import dev.sakshi.runners.run.RunRepository;

@SpringBootApplication
public class RunnersApplication {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(RunnersApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RunnersApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(RunRepository runRepository){
	// 	return args->{
	// 		Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1,ChronoUnit.HOURS), 5, dev.sakshi.runners.run.Location.OUTDOOR);
	// 		runRepository.create(run);
	// 	};
		
	// }

}
