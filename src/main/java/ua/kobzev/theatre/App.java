package ua.kobzev.theatre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import ua.kobzev.theatre.configuration.MainConfiguration;
import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.service.AuditoriumService;
import ua.kobzev.theatre.service.BookingService;
import ua.kobzev.theatre.service.EventService;
import ua.kobzev.theatre.service.UserService;

/**
 * 
 * @author kkobziev
 *
 */

@Component
public class App {

	@Autowired
	private UserService userService;

	@Autowired
	private AuditoriumService auditoriumService;

	@Autowired
	private EventService eventService;

	@Autowired
	private BookingService bookingService;

	public static void main(String... args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.register(MainConfiguration.class);
		ctx.refresh();

		App application = (App) ctx.getBean("app");
		application.testApp();

		ctx.close();
	}

	private void testApp() {
		User user = registerUser();
		Auditorium auditoriumLondon = getAuditorium();

		Event movieLoTR = createEvent(eventService, "Movie: Lord Of The Rings", 100, EventRate.MID);
		Event movieHobbit = createEvent(eventService, "Movie: The Hobbit", 10, EventRate.HIGH);

		LocalDateTime dateTimeLoTR = LocalDateTime.of(2016, Month.FEBRUARY, 8, 13, 00);
		eventService.assignAuditorium(movieLoTR, auditoriumLondon, dateTimeLoTR);
		LocalDateTime dateTimeHobbit = LocalDateTime.of(2016, Month.FEBRUARY, 8, 15, 00);
		eventService.assignAuditorium(movieHobbit, auditoriumLondon, dateTimeHobbit);

		List<Integer> seats = new ArrayList<>();
		seats.add(1);
		seats.add(2);
		seats.add(10);
		seats.add(11);

		double ticketPrice = bookingService.getTotalPrice(movieHobbit, dateTimeHobbit, seats, user);
		System.out.println("Hobbit tickets price = " + ticketPrice);

		ticketPrice = bookingService.getTotalPrice(movieLoTR, dateTimeLoTR, seats, user);
		System.out.println("LoTR  tickets price = " + ticketPrice);

		System.out.println(
				"Purchased tickets for Hobbit: " + bookingService.getTicketsForEvent(movieHobbit, dateTimeHobbit));

		AssignedEvent assignedEvent = eventService.getAssignedEvent(movieHobbit, dateTimeHobbit);

		Ticket ticket1 = bookingService.createTicket(assignedEvent, 1);
		bookingService.bookTicket(user, ticket1);

		Ticket ticket2 = bookingService.createTicket(assignedEvent, 2);
		bookingService.bookTicket(user, ticket2);

		System.out.println(
				"Purchased tickets for Hobbit: " + bookingService.getTicketsForEvent(movieHobbit, dateTimeHobbit));
	}

	private Event createEvent(EventService eventService, String name, int basePrice, EventRate mid) {
		Event movieLoTR = new Event(name, basePrice, mid);
		eventService.create(movieLoTR);

		return movieLoTR;
	}

	private Auditorium getAuditorium() {
		Auditorium auditoriumMercury = auditoriumService.findAuditoriumByName("London");
		System.out.println(auditoriumMercury);
		return auditoriumMercury;
	}

	private User registerUser() {
		User user = new User("Tester", "test@mail.com", LocalDateTime.now());
		userService.register(user);
		System.out.println(userService.getUserByEmail("test@mail.com"));

		return user;
	}

	public void printStatistic() {

	}

	public void consoleRun() {
		String answer = "";

		while (!"0".equals(answer)) {
			answer = firstPage();

			switch (answer) {
			case "1":
				answer = logIn();
				break;
			case "2":
				printStatistic();
				answer = "0";
			default:
				break;
			}
		}

		userService.getById(1);
	}

	private String logIn() {
		String answer = "";

		System.out.println("please enter your email");

		answer = "0";

		return answer;

	}

	private String firstPage() {
		String answer = "";

		System.out.println("what you wanna do?");
		System.out.println("0. Exit");
		System.out.println("1. Log in");
		System.out.println("2. Print statistic");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			answer = br.readLine();
		} catch (IOException e) {
			answer = "0";
		}

		return answer;
	}

}
