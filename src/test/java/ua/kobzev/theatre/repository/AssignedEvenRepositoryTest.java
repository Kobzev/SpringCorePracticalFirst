package ua.kobzev.theatre.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

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
public class AssignedEvenRepositoryTest {

	@Autowired
	private AssignedEventRepository assignedEventRepository;

	private Event event;
	private Auditorium auditorium;

	private static final String NAME = "Pirates of Caribbean sea";
	private LocalDateTime testDate;

	@Before
	public void setUp() {
		event = new Event(NAME, 80.00, EventRate.HIGH);
		auditorium = new Auditorium();
		testDate = LocalDateTime.of(2000, 01, 01, 9, 00);
	}

	@Test
	public void shouldReturnTrueWhenAssignNewEventAuditoriumADate() {
		assertTrue(assignedEventRepository.assignAuditorium(event, auditorium, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignedSecondTimeSameEvent() {
		assignedEventRepository.assignAuditorium(event, auditorium, testDate);
		assertFalse(assignedEventRepository.assignAuditorium(event, auditorium, testDate));
	}

	@Test
	public void shouldReturnEmptyListIfDontHaveAssignedEventsFromDateToDate() {
		assertEquals(0, assignedEventRepository
				.getForDateRange(LocalDateTime.of(2000, 01, 01, 9, 00), LocalDateTime.of(2000, 01, 02, 9, 00)).size());
	}

	@Test
	public void shouldReturnNotEmptyListIfHaveAssignedEventsFromDateToDate() {
		assignedEventRepository.assignAuditorium(event, auditorium, testDate);

		assertNotEquals(0, assignedEventRepository
				.getForDateRange(LocalDateTime.of(2000, 01, 01, 9, 00), LocalDateTime.of(2000, 01, 03, 9, 00)).size());
	}

	@Test
	public void shouldReturnListWithOneElementFromDateToDate() {
		assertEquals(0, assignedEventRepository
				.getForDateRange(LocalDateTime.of(2000, 01, 01, 9, 00), LocalDateTime.of(2000, 01, 03, 9, 00)).size());

		assignedEventRepository.assignAuditorium(event, auditorium, testDate);
		assignedEventRepository.assignAuditorium(new Event(), auditorium, LocalDateTime.of(2015, 11, 01, 9, 00));

		assertEquals(1, assignedEventRepository
				.getForDateRange(LocalDateTime.of(2000, 01, 01, 9, 00), LocalDateTime.of(2000, 01, 03, 9, 00)).size());
	}

	@Test
	public void shouldReturnEmptyListIfDontHaveAssignedEventsFromNowToDate() {
		assertEquals(0, assignedEventRepository.getNextEvents(LocalDateTime.of(2016, 03, 01, 9, 00)).size());
	}

	@Test
	public void shouldReturnNotEmptyListIfHaveAssignedEventsFromNowToDate() {
		assignedEventRepository.assignAuditorium(event, auditorium, LocalDateTime.of(2016, 02, 28, 9, 00));

		assertNotEquals(0, assignedEventRepository.getNextEvents(LocalDateTime.of(2016, 03, 01, 9, 00)).size());

	}

	@Test
	public void shouldReturnListWithOneElementFromNowToDate() {
		assertEquals(0, assignedEventRepository.getNextEvents(LocalDateTime.of(2016, 03, 01, 9, 00)).size());

		assignedEventRepository.assignAuditorium(event, auditorium, LocalDateTime.of(2016, 02, 28, 9, 00));

		assignedEventRepository.assignAuditorium(new Event(), auditorium, LocalDateTime.of(2015, 11, 01, 9, 00));

		assertEquals(1, assignedEventRepository.getNextEvents(LocalDateTime.of(2016, 03, 01, 9, 00)).size());

	}

}
