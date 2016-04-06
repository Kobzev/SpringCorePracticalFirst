package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AssignedEventRepository;
import ua.kobzev.theatre.repository.EventRepository;
import ua.kobzev.theatre.service.EventService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author kkobziev
 *
 */

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private AssignedEventRepository assignedEventRepository;

	@Override
	public boolean create(Event event) {
		if (event == null)
			return false;

		if (getByName(event.getName()) != null)
			return false;

		return eventRepository.create(event);
	}

	@Override
	public boolean remove(Event event) {
		return eventRepository.remove(event);
	}

	@Override
	public boolean updateEvent(Event event) {
		return eventRepository.updateEvent(event);
	}

	@Override
	@Transactional(readOnly = true)
	public Event getByName(String name) {
		return eventRepository.getByName(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Event> findAllEvents() {
		return eventRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AssignedEvent> getAll() {
		return assignedEventRepository.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
		return assignedEventRepository.getForDateRange(from, to);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AssignedEvent> getNextEvents(LocalDateTime to) {
		return assignedEventRepository.getNextEvents(to);
	}

	@Override
	public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
		if (event == null || auditorium == null || date == null)
			return false;

		return assignedEventRepository.assignAuditorium(event, auditorium, date);
	}

	@Override
	@Transactional(readOnly = true)
	public Auditorium getAuditorium(Event event, LocalDateTime dateTime) {
		return assignedEventRepository.findEventAuditorium(event, dateTime);
	}

	@Override
	@Transactional(readOnly = true)
	public AssignedEvent getAssignedEvent(Event event, LocalDateTime dateTime) {
		return assignedEventRepository.findByEventAndDate(event, dateTime);
	}

	@Override
	@Transactional(readOnly = true)
	public AssignedEvent getAssignedEventById(Integer id) {
		return assignedEventRepository.findById(id);
	}
}
