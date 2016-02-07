package ua.kobzev.theatre.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventRepository;

	private Event event;

	private static final String NAME = "Pirates of Caribbean sea";

	@Before
	public void setUp() {
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
