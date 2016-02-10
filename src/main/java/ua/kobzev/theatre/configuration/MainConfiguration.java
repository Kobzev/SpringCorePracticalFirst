package ua.kobzev.theatre.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.repository.AuditoriumRepository;
import ua.kobzev.theatre.repository.impl.inmemory.AuditoriumRepositoryImpl;

@PropertySource("file:src/main/resources/auditoriums.properties")
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ua.kobzev.theatre")
@Import(DiscountConfiguration.class)
public class MainConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public AuditoriumRepository auditoriumRepository() {
		AuditoriumRepositoryImpl auditoriumRepository = new AuditoriumRepositoryImpl();
		auditoriumRepository.setAuditoriumList(readAuditoriumsFromProperties());

		return auditoriumRepository;

	}

	private List<Auditorium> readAuditoriumsFromProperties() {
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
