package ua.kobzev.theatre.repository.impl.inmemory;

import java.util.ArrayList;
import java.util.List;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

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
	public Event getByName(String name) {
		return eventsList.stream().filter(event -> event.getName().equals(name)).findFirst().orElse(null);
	}

}
