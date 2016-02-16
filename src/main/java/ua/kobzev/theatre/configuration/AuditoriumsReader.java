package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment environment;

    public List<Auditorium> readAuditoriumsFromProperties() {
        List<Auditorium> result = new ArrayList<>();
        Auditorium london = new Auditorium();
        london.setName(environment.getProperty("london.name"));
        london.setNumberOfSeats(Integer.parseInt(environment.getProperty("london.numberofseats")));
        london.setVipSeats(environment.getProperty("london.vipseats"));

        Auditorium paris = new Auditorium();
        paris.setName(environment.getProperty("paris.name"));
        paris.setNumberOfSeats(Integer.parseInt(environment.getProperty("paris.numberofseats")));
        paris.setVipSeats(environment.getProperty("paris.vipseats"));

        result.add(london);
        result.add(paris);

        return result;
    }
}
