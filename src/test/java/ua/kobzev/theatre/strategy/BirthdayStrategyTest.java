package ua.kobzev.theatre.strategy;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({ "file:src/test/resources/test-context.xml" })
public class BirthdayStrategyTest {

	@Autowired
	@Qualifier("birthdayStrategy")
	private DiscountStrategy discountStrategy;

	@Test
	public void shouldReturnDiscountWhenUserHaveBirthDayToday() {
		// birthDay percent = 10; discount should be 10.0
		User user = new User();
		user.setBirthDay(LocalDateTime.now());

		List<Ticket> tickets = new ArrayList<>();

		Ticket ticket = new Ticket();
		ticket.setPrice(100.00);

		tickets.add(ticket);

		assertEquals(10.0, discountStrategy.getDiscount(user, tickets), .001);
	}

	@Test
	public void shouldReturnZeroDiscountWhenUserDontHaveBirthDayToday() {
		// birthDay percent = 10; discount should be 0.0
		User user = new User();
		user.setBirthDay(LocalDateTime.now().minusDays(5));

		List<Ticket> tickets = new ArrayList<>();

		Ticket ticket = new Ticket();
		ticket.setPrice(100.00);

		tickets.add(ticket);

		assertEquals(0.0, discountStrategy.getDiscount(user, tickets), .001);
	}

}
