package ua.kobzev.theatre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ua.kobzev.theatre.configuration.MainConfiguration;
import ua.kobzev.theatre.domain.*;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
		application.testApp(ctx);

		ctx.close();
	}

	private void testApp(ApplicationContext context) {
		firstStart();

		User user = userService.getUserByEmail("test@mail.com");
		Auditorium auditoriumLondon = getAuditorium("London");
		System.out.println(auditoriumLondon);

		Event movieFirst = eventService.getByName("Movie: Pirates of Caribbean sea");
		Event movieSecond = eventService.getByName("Movie: Titanik");

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime dateTimeFirst = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 13, 00);
		eventService.assignAuditorium(movieFirst, auditoriumLondon, dateTimeFirst);
		LocalDateTime dateTimeSecond = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 15, 00);
		eventService.assignAuditorium(movieSecond, auditoriumLondon, dateTimeSecond);

		System.out.println("Users with name Tester :" + userService.getUsersByName("Tester").size());
		System.out.println("Assigned events :" + eventService.getAll().size());
		System.out.println("Assigned events :" + eventService.getNextEvents(LocalDateTime.now().plusDays(5)).size());
		System.out.println("Assigned events :" + eventService.getForDateRange(LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(5)).size());
		System.out.println("for "+movieFirst+" auditorium : " + eventService.getAuditorium(movieFirst, dateTimeFirst));
		System.out.println("for "+movieSecond+" assigned event : " + eventService.getAssignedEvent(movieSecond, dateTimeSecond));

		List<Integer> seats = new ArrayList<>();
		seats.add(1);
		seats.add(2);
		seats.add(10);
		seats.add(11);

		double ticketPrice = bookingService.getTotalPrice(movieSecond, dateTimeSecond, seats, user);
		System.out.println(movieSecond + " tickets price = " + ticketPrice);

		ticketPrice = bookingService.getTotalPrice(movieFirst, dateTimeFirst, seats, user);
		System.out.println(movieFirst + " tickets price = " + ticketPrice);

		System.out.println("Purchased tickets for " + movieSecond
				+ bookingService.getTicketsForEvent(movieSecond, dateTimeSecond));

		AssignedEvent assignedEvent = eventService.getAssignedEvent(movieSecond, dateTimeSecond);

		Ticket ticket1 = bookingService.createTicket(assignedEvent, 1);
		bookingService.bookTicket(user, ticket1);

		Ticket ticket2 = bookingService.createTicket(assignedEvent, 5);
		bookingService.bookTicket(user, ticket2);

		Ticket ticket3 = bookingService.createTicket(assignedEvent, 15);
		bookingService.bookTicket(user, ticket3);

		Ticket ticket4 = bookingService.createTicket(assignedEvent, 25);
		bookingService.bookTicket(user, ticket4);

		Ticket ticket5 = bookingService.createTicket(assignedEvent, 35);
		bookingService.bookTicket(user, ticket5);

		System.out.println("Purchased tickets for " + movieSecond
				+ bookingService.getTicketsForEvent(movieSecond, dateTimeSecond));

	}

	private void firstStart(){
		User user = new User();

		user.setName("Tester");
		user.setBirthDay(LocalDateTime.now());
		user.setEmail("test@mail.com");

		userService.register(user);

		User testUser = userService.getUserByEmail("test@mail.com");
		System.out.println(testUser);
		System.out.println(userService.getById(testUser.getId()));

		createEvent("Movie: Pirates of Caribbean sea", 100.0, EventRate.MID);
		createEvent("Movie: Titanik", 10.0, EventRate.HIGH);

		User badTester = new User();

		badTester.setName("Tester");
		badTester.setBirthDay(LocalDateTime.now());
		badTester.setEmail("badtest@mail.com");

		userService.register(badTester);
		System.out.println("Users with name Tester :" + userService.getUsersByName("Tester").size());
		System.out.println("Delete user :" + userService.remove(userService.getUserByEmail("badtest@mail.com")));
	}

	private void createEvent(String name, Double basePrice, EventRate rate) {
		Event movie = new Event();

		movie.setName(name);
		movie.setBasePrice(basePrice);
		movie.setRate(rate);

		eventService.create(movie);
	}

	private Auditorium getAuditorium(String name) {
		Auditorium auditorium = auditoriumService.findAuditoriumByName(name);
		System.out.println(auditorium);
		return auditorium;
	}

}
