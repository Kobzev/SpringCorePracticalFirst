package ua.kobzev.theatre.service;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

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
import ua.kobzev.theatre.repository.EventRepository;
import ua.kobzev.theatre.service.EventService;
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
		verify(eventRepository, times(1)).getAll();
	}

	@Test
	public void testGetForDateRange() {
		eventService.getForDateRange(new Date(), new Date());
		verify(eventRepository, times(1)).getForDateRange(anyObject(), anyObject());
	}

	@Test
	public void testGetNextEvents() {
		eventService.getNextEvents(new Date());
		verify(eventRepository, times(1)).getNextEvents(anyObject());
	}

	@Test
	public void testAssignAuditorium() {
		eventService.assignAuditorium(event, auditorium, new Date());
		verify(eventRepository, times(1)).assignAuditorium(anyObject(), anyObject(), anyObject());
	}

}
