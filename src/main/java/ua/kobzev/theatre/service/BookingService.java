package ua.kobzev.theatre.service;

import java.time.LocalDateTime;
import java.util.List;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

/**
 * 
 * @author kkobziev
 *
 *         Manages tickets, prices, bookings
 * 
 *         getTicketPrice(event, date, time, seats, user) - returns price for
 *         ticket for specified event on specific date and time for specified
 *         seats. User is needed to calculate discount (see below) Event is
 *         needed to get base price, rating Vip seats should cost more than
 *         regular seats (For example, 2xBasePrice) All prices for high rated
 *         movies should be higher (For example, 1.2xBasePrice) bookTicket(user,
 *         ticket) - user could be registered or not. If user is registered,
 *         then booking information is stored for that user. Purchased tickets
 *         for particular event should be stored getTicketsForEvent(event, date)
 *         - get all purchased tickets for event for specific date
 *
 * 
 */

public interface BookingService {

	double getTotalPrice(Event event, LocalDateTime date, List<Integer> seats, User user);

	boolean bookTicket(User user, Ticket ticket);

	List<Ticket> getTicketsForEvent(Event event, LocalDateTime date);

}
