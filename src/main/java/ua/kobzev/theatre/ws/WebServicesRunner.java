package ua.kobzev.theatre.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.kobzev.theatre.configuration.MainConfiguration;

/**
 * Created by kkobziev on 4/8/16.
 */
@SpringBootApplication
public class WebServicesRunner {

    public static void main(String ... args){
//        SpringApplication springApplication = new SpringApplication();
//        springApplication.setAdditionalProfiles("JDBCTEMPLATE");
        SpringApplication.run(MainConfiguration.class, "--spring.profiles.active=JDBCTEMPLATE");
    }
}
