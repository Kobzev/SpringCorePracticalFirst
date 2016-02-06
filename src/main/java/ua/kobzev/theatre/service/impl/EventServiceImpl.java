package ua.kobzev.theatre.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;
import ua.kobzev.theatre.service.EventService;

/**
 * 
 * @author kkobziev
 *
 */

public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public boolean create(Event event) {
		return eventRepository.create(event);
	}

	@Override
	public boolean remove(Event event) {
		return eventRepository.remove(event);
	}

	@Override
	public Event getByName(String name) {
		return eventRepository.getByName(name);
	}

	@Override
	public List<Event> getAll() {
		return eventRepository.getAll();
	}

	@Override
	public List<Event> getForDateRange(Date from, Date to) {
		return eventRepository.getForDateRange(from, to);
	}

	@Override
	public List<Event> getNextEvents(Date to) {
		return eventRepository.getNextEvents(to);
	}

	@Override
	public boolean assignAuditorium(Event event, Auditorium auditorium, Date date) {
		return eventRepository.assignAuditorium(event, auditorium, date);
	}

}
