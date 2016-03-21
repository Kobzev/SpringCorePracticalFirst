package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.Event;

import java.util.List;

public interface EventRepository {

	boolean create(Event event);

	boolean remove(Event event);

	Event getByName(String name);

	List<Event> findAll();

}
