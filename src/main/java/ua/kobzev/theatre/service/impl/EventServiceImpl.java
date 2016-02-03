package ua.kobzev.theatre.service.impl;

import java.util.Date;
import java.util.List;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.service.EventService;

/**
 * 
 * @author kkobziev
 *
 */

public class EventServiceImpl implements EventService {

	@Override
	public boolean create(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Event event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Event getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getForDateRange(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getNextEvents(Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean assignAuditorium(Event event, Auditorium auditorium, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

}
