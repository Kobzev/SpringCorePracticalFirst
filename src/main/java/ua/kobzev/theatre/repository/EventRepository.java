package ua.kobzev.theatre.repository;

import java.util.Date;
import java.util.List;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;

public interface EventRepository {

	boolean create(Event event);

	boolean remove(Event event);

	Event getByName(String name);

	List<Event> getAll();

	List<Event> getForDateRange(Date from, Date to);

	List<Event> getNextEvents(Date to);

	boolean assignAuditorium(Event event, Auditorium auditorium, Date date);

}
