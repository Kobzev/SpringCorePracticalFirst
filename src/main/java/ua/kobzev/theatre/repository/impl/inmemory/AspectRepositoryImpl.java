package ua.kobzev.theatre.repository.impl.inmemory;

import org.springframework.stereotype.Repository;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AspectRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkobziev on 2/10/16.
 */
@Repository
public class AspectRepositoryImpl implements AspectRepository{

    private Map<Event, Integer> accessByName = new HashMap<>();
    private Map<Event, Integer> bookedTicket = new HashMap<>();

    @Override
    public Map<Event, Integer> getAccessByName() {
        return new HashMap<>(accessByName);
    }

    @Override
    public void saveAccessByName(Event event) {
        Integer times = accessByName.get(event);
        accessByName.put(event, times==null? 1 : times+1);
    }

    @Override
    public Map<Event, Integer> getBookedTicket() {
        return new HashMap<>(bookedTicket);
    }

    @Override
    public void saveBookedTicket(Event event) {
        Integer times = bookedTicket.get(event);
        bookedTicket.put(event, times==null? 1 : times+1);
    }
}
