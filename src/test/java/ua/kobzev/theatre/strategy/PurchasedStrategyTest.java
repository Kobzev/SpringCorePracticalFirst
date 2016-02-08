package ua.kobzev.theatre.strategy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.UserService;
import ua.kobzev.theatre.strategy.impl.PurchasedStrategy;

@RunWith(MockitoJUnitRunner.class)
public class PurchasedStrategyTest {

	@InjectMocks
	private PurchasedStrategy discountStrategy = new PurchasedStrategy();

	@Mock
	private UserService userService;

	@Mock
	private List<Ticket> mockList;

	// Every 5 ticket have discount 70%

	@Before
	public void setUp() {
		discountStrategy.setPercent(70);
		discountStrategy.setDiscountForEveryTicket(5);
	}

	@Test
	public void shouldReturnZeroDiscount() {
		when(userService.getBookedTickets(anyObject())).thenReturn(new ArrayList<>());

		List<Ticket> tickets = new ArrayList<>();

		Ticket ticket = new Ticket();
		ticket.setPrice(100.00);

		tickets.add(ticket);

		assertEquals(0.0, discountStrategy.getDiscount(new User(), tickets), .001);
	}

	@Test
	public void shouldReturnDiscountForOneTicket() {
		when(userService.getBookedTickets(anyObject())).thenReturn(mockList);
		when(mockList.size()).thenReturn(4);

		List<Ticket> tickets = new ArrayList<>();

		Ticket ticket = new Ticket();
		ticket.setPrice(100.00);

		tickets.add(ticket);

		assertEquals(70.0, discountStrategy.getDiscount(new User(), tickets), .001);

	}

	@Test
	public void shouldReturnDiscountForTwoTickets() {
		when(userService.getBookedTickets(anyObject())).thenReturn(mockList);
		when(mockList.size()).thenReturn(4);

		List<Ticket> tickets = new ArrayList<>();

		Ticket ticket = new Ticket();
		ticket.setPrice(100.00);

		for (int i = 0; i < 6; i++) {
			tickets.add(ticket);
		}

		assertEquals(140.0, discountStrategy.getDiscount(new User(), tickets), .001);

	}

}
