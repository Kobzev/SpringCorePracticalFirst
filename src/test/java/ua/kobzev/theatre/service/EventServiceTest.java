package ua.kobzev.theatre.service;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.AssignedEventRepository;
import ua.kobzev.theatre.repository.EventRepository;
import ua.kobzev.theatre.service.impl.EventServiceImpl;

/**
 * 
 * @author kkobziev
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

	@InjectMocks
	private EventService eventService = new EventServiceImpl();

	@Mock
	private EventRepository eventRepository;

	@Mock
	private AssignedEventRepository assignedEventRepository;

	private Event event;
	private Auditorium auditorium;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		event = new Event("Pirates of Caribbean sea", 80.00, EventRate.HIGH);
		auditorium = new Auditorium();
	}

	@Test
	public void testCreate() {
		eventService.create(event);
		verify(eventRepository, times(1)).create(anyObject());
	}

	@Test
	public void testRemove() {
		eventService.remove(event);
		verify(eventRepository, times(1)).remove(anyObject());
	}

	@Test
	public void testGetByName() {
		eventService.getByName(event.getName());
		verify(eventRepository, times(1)).getByName(anyString());
	}

	@Test
	public void testGetAll() {
		eventService.getAll();
		verify(assignedEventRepository, times(1)).getAll();
	}

	@Test
	public void testGetForDateRange() {
		eventService.getForDateRange(LocalDateTime.now(), LocalDateTime.now());
		verify(assignedEventRepository, times(1)).getForDateRange(anyObject(), anyObject());
	}

	@Test
	public void testGetNextEvents() {
		eventService.getNextEvents(LocalDateTime.now());
		verify(assignedEventRepository, times(1)).getNextEvents(anyObject());
	}

	@Test
	public void testAssignAuditorium() {
		eventService.assignAuditorium(event, auditorium, LocalDateTime.now());
		verify(assignedEventRepository, times(1)).assignAuditorium(anyObject(), anyObject(), anyObject());
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullDate() {
		assertFalse(eventService.assignAuditorium(event, auditorium, null));
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullEvent() {
		assertFalse(eventService.assignAuditorium(null, auditorium, LocalDateTime.now()));
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullAuditorium() {
		assertFalse(eventService.assignAuditorium(event, null, LocalDateTime.now()));
	}

	@Test
	public void shouldReturnFalseWhenCreateExistingEvent() {
		eventService.create(event);
		assertFalse(eventService.create(event));
	}

	@Test
	public void shouldReturnFalseWhenRemoveNullEvent() {
		assertFalse(eventService.remove(null));
	}

	@Test
	public void shouldReturnFalseWhenCreateNullEvent() {
		assertFalse(eventService.create(null));
	}
}
