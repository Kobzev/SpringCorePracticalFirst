package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.Auditorium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

@Component
@PropertySource("classpath:auditoriums.properties")
public class AuditoriumsReader {

    @Value("${london.name}")
    private String londonName;

    @Value("${london.numberofseats}")
    private String londonNumberOfSeats;

    @Value("${london.vipseats}")
    private String londonVipSeats;

    @Value("${paris.name}")
    private String parisName;

    @Value("${paris.numberofseats}")
    private String parisNumberOfSeats;

    @Value("${paris.vipseats}")
    private String parisVipSeats;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public List<Auditorium> readAuditoriumsFromProperties() {
        List<Auditorium> result = new ArrayList<>();
        Auditorium london = new Auditorium();
        london.setName(londonName);
        london.setNumberOfSeats(Integer.parseInt(londonNumberOfSeats));
        london.setVipSeats(londonVipSeats);

        Auditorium paris = new Auditorium();
        paris.setName(parisName);
        paris.setNumberOfSeats(Integer.parseInt(parisNumberOfSeats));
        paris.setVipSeats(parisVipSeats);

        result.add(london);
        result.add(paris);

        return result;
    }
}
