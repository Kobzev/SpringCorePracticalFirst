package ua.kobzev.theatre.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kobzev.theatre.domain.AssignedEvent;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.TicketRepository;
import ua.kobzev.theatre.service.BookingService;
import ua.kobzev.theatre.service.DiscountService;
import ua.kobzev.theatre.service.EventService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private EventService eventService;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private DiscountService discountService;

	@Override
	public double getTotalPrice(final Event event, LocalDateTime date, List<Integer> seats, final User user) {
		double baseTicketPrice = event.getBasePrice();

		EventRate eventRate = event.getRate();
		if (EventRate.HIGH == eventRate) {
			baseTicketPrice *= 1.2;
		}

		final double vipTicketPrice = 2 * baseTicketPrice;
		final double basePrice = baseTicketPrice;

		List<Ticket> ticketsList = new ArrayList<>();

		seats.forEach(seat -> ticketsList.add(createTicket(user, event, date, basePrice, vipTicketPrice, seat)));

		double price = ticketsList.stream().map(x -> x.getPrice()).reduce((x, y) -> x + y).get();

		double discount = discountService.getDiscount(user, ticketsList);

		return price - discount;
	}

	private Ticket createTicket(User user, Event event, LocalDateTime dateTime, Double basePrice, Double vipPrice,
			Integer seat) {
		AssignedEvent assignedEvent = eventService.getAssignedEvent(event, dateTime);

		Ticket ticket = new Ticket(user, assignedEvent, seat);

		if (assignedEvent.getAuditorium().getVipSeats().contains(seat)) {
			ticket.setPrice(vipPrice);
		} else {
			ticket.setPrice(basePrice);
		}

		return ticket;

	}

	@Override
	public boolean bookTicket(User user, Ticket ticket) {
		if (ticketRepository.isPurchased(ticket.getAssignedEvent(), ticket.getSeat()))
			return false;

		ticket.setUser(user);
		ticketRepository.save(ticket);

		return true;
	}

	@Override
	public List<Ticket> getTicketsForEvent(Event event, LocalDateTime date) {
		return ticketRepository.findAllByEvent(event, date);
	}

	@Override
	public Ticket createTicket(AssignedEvent assignedEvent, int seat) {
		return new Ticket(assignedEvent, seat);
	}

}
