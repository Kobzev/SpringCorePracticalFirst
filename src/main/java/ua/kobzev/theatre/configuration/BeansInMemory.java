package ua.kobzev.theatre.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.kobzev.theatre.repository.*;
import ua.kobzev.theatre.repository.impl.inmemory.*;

/**
 * Created by Kostiantyn_Kobziev on 3/29/2016.
 */
@Configuration
@Profile("INMEMORY")
public class BeansInMemory {

    @Autowired
    private AuditoriumsReader auditoriumsReader;

    @Bean
    public AuditoriumRepository auditoriumRepository() {
        AuditoriumRepositoryImpl auditoriumRepository = new AuditoriumRepositoryImpl();
        auditoriumRepository.setAuditoriumList(auditoriumsReader.readAuditoriumsFromProperties());
        return auditoriumRepository;
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
}
