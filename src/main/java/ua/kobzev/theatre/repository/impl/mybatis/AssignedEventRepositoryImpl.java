package ua.kobzev.theatre.repository.impl.mybatis;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AssignedEventRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AssignedEventRepositoryImpl implements AssignedEventRepository{
    @Override
    public List<AssignedEvent> getAll() {
        // TODO
        return null;
    }

    @Override
    public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
        // TODO
        return null;
    }

    @Override
    public List<AssignedEvent> getNextEvents(LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }

    @Override
    public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        // TODO
        return false;
    }

    @Override
    public Auditorium findEventAuditorium(Event event, LocalDateTime dateTime) {
        // TODO
        return null;
    }

    @Override
    public AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime) {
        // TODO
        return null;
    }
}
