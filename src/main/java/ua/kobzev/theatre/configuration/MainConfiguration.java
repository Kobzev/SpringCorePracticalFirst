package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import ua.kobzev.theatre.enums.Repositories;
import ua.kobzev.theatre.repository.*;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("ua.kobzev.theatre")
@PropertySource("classpath:jdbc.properties")
@Import({DiscountConfiguration.class, Jdbc.class})
public class MainConfiguration {

	@Autowired
	private AuditoriumsReader auditoriumsReader;

	@Autowired
	private Environment environment;

	@Bean
	public AuditoriumRepository auditoriumRepository() {
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				ua.kobzev.theatre.repository.impl.inmemory.AuditoriumRepositoryImpl auditoriumRepository =
						new ua.kobzev.theatre.repository.impl.inmemory.AuditoriumRepositoryImpl();
				auditoriumRepository.setAuditoriumList(auditoriumsReader.readAuditoriumsFromProperties());

				return auditoriumRepository;
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.mybatis.AuditoriumRepositoryImpl();
			case HIBERNATE:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.hibernate.AuditoriumRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.AuditoriumRepositoryImpl();
			default:
				return null;
		}


	}

	@Bean
	public AspectRepository aspectRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.AspectRepositoryImpl();
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.mybatis.AspectRepositoryImpl();
			case HIBERNATE:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.hibernate.AspectRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.AspectRepositoryImpl();
			default:
				return null;
		}

	}

	@Bean
	public AssignedEventRepository assignedEventRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.AssignedEventRepositoryImpl();
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.mybatis.AssignedEventRepositoryImpl();
			case HIBERNATE:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.hibernate.AssignedEventRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.AssignedEventRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public EventRepository eventRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.EventRepositoryImpl();
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.mybatis.EventRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.EventRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.EventRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public TicketRepository ticketRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.TicketRepositoryImpl();
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.mybatis.TicketRepositoryImpl();
			case HIBERNATE:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.hibernate.TicketRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.TicketRepositoryImpl();
			default:
				return null;
		}
	}

	@Bean
	public UserRepository userRepository(){
		Repositories repositories = Repositories.valueOf(environment.getProperty("version.repositories"));

		switch (repositories) {
			case INMEMORY:
				return new ua.kobzev.theatre.repository.impl.inmemory.UserRepositoryImpl();
			case MYBATIS:
				// TODO
				//return new ua.kobzev.theatre.repository.impl.hibernate.UserRepositoryImpl();
			case HIBERNATE:
				return new ua.kobzev.theatre.repository.impl.hibernate.UserRepositoryImpl();
			case JDBCTEMPLATE:
				return new ua.kobzev.theatre.repository.impl.jdbctemplate.UserRepositoryImpl();
			default:
				return null;
		}
	}
}
