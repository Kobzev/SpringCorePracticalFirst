package ua.kobzev.theatre.service;

import java.time.LocalDateTime;
import java.util.List;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;

/**
 * 
 * @author kkobziev
 *
 *         Manages events (movie shows). Event contains only basic information,
 *         like name, base price for tickets, rating (high, mid, low). Event can
 *         be presented on several dates and several times within each day. For
 *         each dateTime an Event will be presented only in single auditorium.
 * 
 *         create, remove, getByName, getAll getForDateRange(from, to) - returns
 *         events for specified date range (OPTIONAL) getNextEvents(to) -
 *         returns events from now till the ‘to’ date (OPTIONAL)
 *         assignAuditorium(event, auditorium, date) - assign auditorium for
 *         event for specific date. Only one auditorium for Event for specific
 *         dateTime
 *
 * 
 */

public interface EventService {

	boolean create(Event event);

	boolean remove(Event event);

	Event getByName(String name);

	List<AssignedEvent> getAll();

	List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to);

	List<AssignedEvent> getNextEvents(LocalDateTime to);

	boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);

	Auditorium getAuditorium(Event event, LocalDateTime dateTime);

	AssignedEvent getAssignedEvent(Event event, LocalDateTime dateTime);

}
