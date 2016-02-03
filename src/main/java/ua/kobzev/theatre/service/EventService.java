package ua.kobzev.theatre.service;

import java.util.Date;
import java.util.List;

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

	List<Event> getAll();

	List<Event> getForDateRange(Date from, Date to);

	List<Event> getNextEvents(Date to);

	boolean assignAuditorium(Event event, Auditorium auditorium, Date date);

}
