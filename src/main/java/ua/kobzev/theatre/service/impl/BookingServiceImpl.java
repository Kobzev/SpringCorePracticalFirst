package ua.kobzev.theatre.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.kobzev.theatre.domain.Auditorium;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;
import ua.kobzev.theatre.repository.TicketRepository;
import ua.kobzev.theatre.service.BookingService;
import ua.kobzev.theatre.service.EventService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private EventService eventService;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public double getTicketPrice(Event event, LocalDateTime date, List<Integer> seats, User user) {
		Auditorium auditorium = eventService.getAuditorium(event, date);
		double baseTicketPrice = event.getBasePrice();

		EventRate eventRate = event.getRate();
		if (EventRate.HIGH == eventRate) {
			baseTicketPrice *= 1.2;
		}

		double vipTicketPrice = 2 * baseTicketPrice;

		long vipSeatsCount = seats.stream().filter(auditorium.getVipSeats()::contains).count();

		long regularSeatsCount = seats.size() - vipSeatsCount;

		return regularSeatsCount * baseTicketPrice + vipSeatsCount * vipTicketPrice;
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

}
