package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.kobzev.theatre.repository.*;
import ua.kobzev.theatre.repository.impl.hibernate.*;
import ua.kobzev.theatre.repository.impl.jdbctemplate.RoleRepositoryImpl;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Configuration
@Profile("HIBERNATE")
public class BeansHibernate {

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
}
