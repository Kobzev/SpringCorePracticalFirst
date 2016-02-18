package ua.kobzev.theatre.repository.impl.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private Mapper mapper;

    @Override
    public List<AssignedEvent> getAll() {
        return mapper.getAllAssignedEvent();
    }

    @Override
    public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return mapper.getForDateRange(from, to);
    }

    @Override
    public List<AssignedEvent> getNextEvents(LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }

    @Override
    public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        if (mapper.countAssignedEvents(event.getName(), auditorium.getName(), date)!=0) return false;
        return mapper.assignAuditorium(event.getName(), auditorium.getName(), date)>0;
    }

    @Override
    public Auditorium findEventAuditorium(Event event, LocalDateTime dateTime) {
        return mapper.findEventAuditorium(event.getName(), dateTime);
    }

    @Override
    public AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime) {
        return mapper.findAssignedEventByEventAndDate(event.getName(), dateTime);
    }
}
