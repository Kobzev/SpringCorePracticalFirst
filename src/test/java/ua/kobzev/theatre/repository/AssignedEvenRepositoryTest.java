package ua.kobzev.theatre.repository;

import org.junit.Before;
import org.junit.Test;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.impl.inmemory.AssignedEventRepositoryImpl;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AssignedEvenRepositoryTest {

	private AssignedEventRepository assignedEventRepository;

	private Event event;
	private Auditorium auditorium;

	private static final String NAME = "Pirates of Caribbean sea";
	private LocalDateTime testDate;

	@Before
	public void setUp() {
		assignedEventRepository = new AssignedEventRepositoryImpl();
		event = new Event(NAME, 80.00, EventRate.HIGH);
		auditorium = new Auditorium();
		testDate = LocalDateTime.of(2000, 01, 01, 9, 00);
	}

	@Test
	public void shouldReturnTrueWhenAssignNewEventAuditoriumADate() {
		assertTrue(assignedEventRepository.assignAuditorium(event, auditorium, testDate));
	}

	@Test
	public void shouldReturnFalseWhenAssignNewEventToSameAuditoriumADate() {
		assertTrue(assignedEventRepository.assignAuditorium(event, auditorium, testDate));
		assertFalse(assignedEventRepository.assignAuditorium(new Event(), auditorium, testDate));
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
		LocalDateTime now = LocalDateTime.of(2016, 02, 28, 9, 00);

		assertNotEquals(0, assignedEventRepository.getForDateRange(now, LocalDateTime.of(2016, 03, 01, 9, 00)).size());

	}

	@Test
	public void shouldReturnListWithOneElementFromNowToDate() {
		assertEquals(0, assignedEventRepository.getNextEvents(LocalDateTime.of(2016, 03, 01, 9, 00)).size());

		assignedEventRepository.assignAuditorium(event, auditorium, LocalDateTime.of(2016, 02, 28, 9, 00));

		assignedEventRepository.assignAuditorium(new Event(), auditorium, LocalDateTime.of(2015, 11, 01, 9, 00));
		LocalDateTime now = LocalDateTime.of(2016, 02, 28, 9, 00);

		assertEquals(1, assignedEventRepository.getForDateRange(now, LocalDateTime.of(2016, 03, 01, 9, 00)).size());

	}

}
