package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface AssignedEventRepository {

	List<AssignedEvent> getAll();

	List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to);

	List<AssignedEvent> getNextEvents(LocalDateTime to);

	boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);

	Auditorium findEventAuditorium(Event event, LocalDateTime dateTime);

	AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime);

	AssignedEvent findById(Integer id);

}
