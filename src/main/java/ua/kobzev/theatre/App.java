package ua.kobzev.theatre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcOperations;
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

	@Autowired
	private AspectService aspectService;

	public static void main(String... args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

		ctx.register(MainConfiguration.class);
		ctx.refresh();

		App application = (App) ctx.getBean("app");
		application.testApp(ctx);

		application.printStatistic();

		ctx.close();
	}

	private void testApp(ApplicationContext context) {
		initBD(context);
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

	private void initBD(ApplicationContext context){
		JdbcOperations jdbcOperations = (JdbcOperations) context.getBean("jdbcTemplate");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `accessbyname` (" +
							"`eventname` varchar(100) NOT NULL, " +
							"`count` int(11) DEFAULT NULL, " +
							"PRIMARY KEY (`eventname`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");


		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `assignedevent` (" +
							"`eventname` varchar(100) NOT NULL, " +
							"`auditoriumname` varchar(50) NOT NULL, " +
							"`date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
							"`id` int(11) NOT NULL AUTO_INCREMENT, " +
							"		PRIMARY KEY (`id`) " +
							") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `auditoriums` (" +
							"`name` varchar(50) NOT NULL, " +
							"`numberOfSeats` int(11) DEFAULT NULL, " +
							"`vipSeats` varchar(200) DEFAULT NULL, " +
							"PRIMARY KEY (`name`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `bookedtickets` (" +
							"`eventname` varchar(100) NOT NULL, " +
							"`count` int(11) DEFAULT NULL, " +
							"PRIMARY KEY (`eventname`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `events` (" +
							"`name` varchar(100) NOT NULL, " +
							"`basePrice` double DEFAULT NULL, " +
							"`rate` varchar(45) DEFAULT NULL, " +
							"PRIMARY KEY (`name`), " +
							"UNIQUE KEY `name_UNIQUE` (`name`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `pricequeried` (" +
							"`eventname` varchar(100) NOT NULL, " +
							"`count` int(11) DEFAULT NULL, " +
							"PRIMARY KEY (`eventname`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `tickets` (" +
							"`id` int(11) NOT NULL AUTO_INCREMENT, " +
							"`userid` int(11) DEFAULT NULL, " +
							"`assignedeventid` int(11) DEFAULT NULL, " +
							"`seat` int(11) DEFAULT NULL, " +
							"`price` double DEFAULT NULL, " +
							"PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `totaldiscounts` (" +
							"`discountstrategy` varchar(100) NOT NULL, " +
							"`count` int(11) DEFAULT NULL, " +
							"PRIMARY KEY (`discountstrategy`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `totaldiscountsforuser` (" +
							"`userid` int(11) NOT NULL, " +
							"`count` int(11) DEFAULT NULL, " +
							"PRIMARY KEY (`userid`) " +
							") ENGINE=InnoDB DEFAULT CHARSET=utf8");

		jdbcOperations.update("CREATE TABLE IF NOT EXISTS `users` (" +
							"`id` int(11) NOT NULL AUTO_INCREMENT, " +
							"`name` varchar(45) DEFAULT NULL, " +
							"`email` varchar(45) DEFAULT NULL, " +
							"`birthDay` TIMESTAMP, " +
							"PRIMARY KEY (`id`), " +
							"UNIQUE KEY `id_UNIQUE` (`id`), " +
							"UNIQUE KEY `email_UNIQUE` (`email`)" +
							") ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8");

		try{
			jdbcOperations.update("INSERT INTO `auditoriums` VALUES ('London',150,'5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95'),('Paris',200,'10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190')");
		}catch (DuplicateKeyException e) {
			// TODO this is not first start
		}
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

	public void printStatistic() {
		aspectService.printStatistic();
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
