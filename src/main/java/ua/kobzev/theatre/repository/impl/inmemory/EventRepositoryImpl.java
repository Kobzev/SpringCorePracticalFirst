package ua.kobzev.theatre.repository.impl.inmemory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.EventRepository;

public class EventRepositoryImpl implements EventRepository {

	private List<Event> eventsList;
	private List<AssignedEvent> assignedEventList;

	{
		eventsList = new ArrayList<>();
		assignedEventList = new ArrayList<>();
	}

	@Override
	public boolean create(Event event) {
		if (event == null)
			return false;

		if (getByName(event.getName()) != null)
			return false;

		eventsList.add(event);

		return true;
	}

	@Override
	public boolean remove(Event event) {
		return eventsList.remove(event);
	}

	@Override
	public Event getByName(String name) {
		Optional<Event> optionalEvent = eventsList.stream().filter(event -> event.getName().equals(name)).findFirst();

		if (!optionalEvent.isPresent())
			return null;

		return optionalEvent.get();
	}

	@Override
	public List<Event> getAll() {
		List<Event> result = new ArrayList<>();
		result.addAll(eventsList);
		return result;
	}

	@Override
	public boolean assignAuditorium(Event event, Auditorium auditorium, Date date) {
		if (event == null || auditorium == null || date == null)
			return false;

		AssignedEvent assignedEvent = new AssignedEvent(event, auditorium, date);

		Optional<AssignedEvent> optionalAssignedEvent = assignedEventList.stream()
				.filter(inter -> inter.equals(assignedEvent)).findFirst();

		if (optionalAssignedEvent.isPresent())
			return false;

		assignedEventList.add(assignedEvent);
		return true;
	}

	private boolean compareDates(Date eventDate, Date from, Date to) {
		if (eventDate.compareTo(from) > -1 && eventDate.compareTo(to) < 1)
			return true;
		return false;
	}

	@Override
	public List<Event> getForDateRange(Date from, Date to) {
		Set<Event> result = new HashSet<>();
		for (AssignedEvent assignedEvent : assignedEventList) {
			if (compareDates(assignedEvent.getDate(), from, to))
				result.add(assignedEvent.getEvent());
		}
		List<Event> resultArr = new ArrayList<>();
		resultArr.addAll(result);
		return resultArr;
	}

	@Override
	public List<Event> getNextEvents(Date to) {
		return getForDateRange(new Date(), to);
	}

}
