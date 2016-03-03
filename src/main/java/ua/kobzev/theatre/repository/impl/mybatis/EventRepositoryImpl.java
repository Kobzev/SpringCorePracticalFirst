package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

/**
 * Created by kkobziev on 2/16/16.
 */

public class EventRepositoryImpl implements EventRepository{

    @Autowired
    private Mapper mapper;

    @Override
    public boolean create(Event event) {
        return mapper.createEvent(event)>0;
    }

    @Override
    public boolean remove(Event event) {
        return mapper.removeEvent(event.getName())>0;
    }

    @Override
    public Event getByName(String name) {
        return mapper.getEventByName(name);
    }
}
