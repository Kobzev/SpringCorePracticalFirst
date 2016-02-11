package ua.kobzev.theatre.repository.impl.mysql;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

/**
 * Created by kkobziev on 2/11/16.
 */


public class EventRepositoryImpl implements EventRepository {
    @Override
    public boolean create(Event event) {
        return false;
    }

    @Override
    public boolean remove(Event event) {
        return false;
    }

    @Override
    public Event getByName(String name) {
        return null;
    }
}
