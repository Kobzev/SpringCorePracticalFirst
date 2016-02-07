package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.Event;

public interface EventRepository {

	boolean create(Event event);

	boolean remove(Event event);

	Event getByName(String name);

}
