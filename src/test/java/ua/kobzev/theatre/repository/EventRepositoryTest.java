package ua.kobzev.theatre.repository;

import org.junit.Before;
import org.junit.Test;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.impl.inmemory.EventRepositoryImpl;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class EventRepositoryTest {

	private EventRepository eventRepository;

	private Event event;

	private static final String NAME = "Pirates of Caribbean sea";

	@Before
	public void setUp() {
		eventRepository = new EventRepositoryImpl();
		event = new Event(NAME, 80.00, EventRate.HIGH);
	}

	@Test
	public void shouldReturnTrueWhenCreateUnExistingEvent() {
		assertTrue(eventRepository.create(event));
	}

	@Test
	public void shouldReturnTrueWhenRemoveExistingEvent() {
		eventRepository.create(event);
		assertTrue(eventRepository.remove(event));
	}

	@Test
	public void shouldReturnFalseWhenRemoveUnExistingEvent() {
		assertFalse(eventRepository.remove(new Event()));
	}

	@Test
	public void shouldReturnNullWhenGivenUnxistingName() {
		assertNull(eventRepository.getByName("unExistingName"));

	}

	@Test
	public void shouldReturnTestEvevntWhenGivenTestName() {
		eventRepository.create(event);
		assertEquals(event, eventRepository.getByName(NAME));
	}
}
