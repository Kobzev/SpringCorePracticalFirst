package ua.kobzev.theatre.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventRepository;

	private Event event;
	private Auditorium auditorium;

	private static final String NAME = "Pirates of Caribbean sea";
	private Date testDate;

	@Before
	public void setUp() {
		event = new Event(NAME, 80.00, EventRate.HIGH);
		auditorium = new Auditorium();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 01, 02);
		testDate = calendar.getTime();
	}

	@Test
	public void shouldReturnTrueWhenCreateUnExistingEvent() {
		assertTrue(eventRepository.create(event));
	}

	@Test
	public void shouldReturnFalseWhenCreateExistingEvent() {
		eventRepository.create(event);
		assertFalse(eventRepository.create(event));
	}

	@Test
	public void shouldReturnFalseWhenCreateNullEvent() {
		assertFalse(eventRepository.create(null));
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
	public void shouldReturnFalseWhenRemoveNullEvent() {
		assertFalse(eventRepository.create(null));
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

	@Test
	public void shouldReturnNonEmptyListWhenCallGetAll() {
		eventRepository.create(event);
		assertNotEquals(0, eventRepository.getAll().size());
	}

	@Test
	public void shouldReturnTrueWhenAssignNewEventAuditoriumADate() {
		assertTrue(eventRepository.assignAuditorium(event, auditorium, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignedSecondTimeSameEvent() {
		eventRepository.assignAuditorium(event, auditorium, testDate);
		assertFalse(eventRepository.assignAuditorium(event, auditorium, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullEvent() {
		assertFalse(eventRepository.assignAuditorium(null, auditorium, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullAuditorium() {
		assertFalse(eventRepository.assignAuditorium(event, null, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignedNullDate() {
		assertFalse(eventRepository.assignAuditorium(event, auditorium, null));
	}

	@Test
	public void shouldReturnEmptyListIfDontHaveAssignedEventsFromDateToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 01, 01);
		Date fromDate = calendar.getTime();
		calendar.set(2000, 01, 02);
		Date toDate = calendar.getTime();
		assertEquals(0, eventRepository.getForDateRange(fromDate, toDate).size());
	}

	@Test
	public void shouldReturnNotEmptyListIfHaveAssignedEventsFromDateToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 01, 01);
		Date fromDate = calendar.getTime();
		calendar.set(2000, 01, 03);
		Date toDate = calendar.getTime();

		eventRepository.assignAuditorium(event, auditorium, testDate);

		assertNotEquals(0, eventRepository.getForDateRange(fromDate, toDate).size());
	}

	@Test
	public void shouldReturnListWithOneElementFromDateToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 01, 01);
		Date fromDate = calendar.getTime();
		calendar.set(2000, 01, 03);
		Date toDate = calendar.getTime();

		assertEquals(0, eventRepository.getForDateRange(fromDate, toDate).size());

		eventRepository.assignAuditorium(event, auditorium, testDate);
		calendar.set(2015, 11, 01);
		eventRepository.assignAuditorium(new Event(), auditorium, calendar.getTime());

		assertEquals(1, eventRepository.getForDateRange(fromDate, toDate).size());
	}

	@Test
	public void shouldReturnEmptyListIfDontHaveAssignedEventsFromNowToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 03, 01);
		Date findDate = calendar.getTime();

		assertEquals(0, eventRepository.getNextEvents(findDate).size());
	}

	@Test
	public void shouldReturnNotEmptyListIfHaveAssignedEventsFromNowToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 03, 01);
		Date findDate = calendar.getTime();
		calendar.set(2016, 02, 28);

		eventRepository.assignAuditorium(event, auditorium, calendar.getTime());

		assertNotEquals(0, eventRepository.getNextEvents(findDate).size());

	}

	@Test
	public void shouldReturnListWithOneElementFromNowToDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 03, 01);
		Date findDate = calendar.getTime();
		calendar.set(2016, 02, 28);
		assertEquals(0, eventRepository.getNextEvents(findDate).size());

		eventRepository.assignAuditorium(event, auditorium, calendar.getTime());

		calendar.set(2015, 11, 01);
		eventRepository.assignAuditorium(new Event(), auditorium, calendar.getTime());

		assertEquals(1, eventRepository.getNextEvents(findDate).size());

	}
}
