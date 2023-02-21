package hu.elte.joooble;

import hu.elte.joooble.service.TestDataGenerator;
import hu.elte.joooble.storage.StorageProperties;
import hu.elte.joooble.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JooobleApplication {
	@Autowired
	private TestDataGenerator testDataGenerator;


    public static void main(String[] args) {
        SpringApplication.run(JooobleApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            testDataGenerator.createTestData();
        };
    }
}