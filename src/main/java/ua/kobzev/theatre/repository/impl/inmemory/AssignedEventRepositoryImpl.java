package ua.kobzev.theatre.repository.impl.inmemory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AssignedEventRepository;

public class AssignedEventRepositoryImpl implements AssignedEventRepository {

	private List<AssignedEvent> assignedEventList;

	{
		assignedEventList = new ArrayList<>();
	}

	@Override
	public List<AssignedEvent> getAll() {
		return new ArrayList<AssignedEvent>(assignedEventList);
	}

	@Override
	public boolean assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
		AssignedEvent assignedEvent = new AssignedEvent(event, auditorium, date);

		if (assignedEventList.stream().anyMatch(assignedEvent::equals))
			return false;

		if (assignedEventList.stream().filter(p -> (auditorium.equals(p.getAuditorium()) && date.equals(p.getDate())))
				.count() > 0L)
			return false;

		assignedEventList.add(assignedEvent);
		return true;
	}

	@Override
	public List<AssignedEvent> getForDateRange(LocalDateTime from, LocalDateTime to) {
		return assignedEventList.stream().filter(p -> (from.isBefore(p.getDate()) || from.isEqual(p.getDate()))
				&& (to.isAfter(p.getDate()) || to.isEqual(p.getDate()))).collect(Collectors.toList());
	}

	@Override
	public List<AssignedEvent> getNextEvents(LocalDateTime to) {
		return getForDateRange(LocalDateTime.now(), to);
	}

	@Override
	public Auditorium findEventAuditorium(Event event, LocalDateTime dateTime) {
		AssignedEvent assignedEvent = findByEventAndDate(event, dateTime);

		if (assignedEvent == null)
			return null;

		return assignedEvent.getAuditorium();
	}

	@Override
	public AssignedEvent findByEventAndDate(Event event, LocalDateTime dateTime) {
		return assignedEventList.stream().filter(p -> event.equals(p.getEvent()) && dateTime.equals(p.getDate()))
				.findFirst().orElse(null);
	}

}
