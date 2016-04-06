package ua.kobzev.theatre.repository.impl.inmemory;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {

	private List<Event> eventsList;

	{
		eventsList = new ArrayList<>();
	}

	@Override
	public boolean create(Event event) {
		eventsList.add(event);
		return true;
	}

	@Override
	public boolean remove(Event event) {
		return eventsList.remove(event);
	}

	@Override
	public boolean updateEvent(Event event) {
		return false;
	}

	@Override
	public Event getByName(String name) {
		return eventsList.stream().filter(event -> event.getName().equals(name)).findFirst().orElse(null);
	}

	@Override
	public List<Event> findAll() {
		return eventsList;
	}

}
