package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.kobzev.theatre.repository.*;
import ua.kobzev.theatre.repository.impl.jdbctemplate.*;

import javax.sql.DataSource;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Configuration
@Profile("JDBCTEMPLATE")
public class BeansJDBCTemplate {

    @Bean
    @Autowired
    public JdbcOperations jdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        return jdbcTemplate;
    }

    @Bean
    public AuditoriumRepository auditoriumRepository() {
        return new AuditoriumRepositoryImpl();
    }

    @Bean
    public AssignedEventRepository assignedEventRepository(){
        return new AssignedEventRepositoryImpl();
    }

    @Bean
    public EventRepository eventRepository(){
        return new EventRepositoryImpl();
    }

    @Bean
    public TicketRepository ticketRepository(){
        return new TicketRepositoryImpl();
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }

    @Bean
    public RoleRepository roleRepository(){
        return new RoleRepositoryImpl();
    }

    @Bean
    public UserAccountRepository userAccountRepository() {
        return new UserAccountRepositoryImpl();
    }
}
