package ua.kobzev.theatre.service;

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

}
