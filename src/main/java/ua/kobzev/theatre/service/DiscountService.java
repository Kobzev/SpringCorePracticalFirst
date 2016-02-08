package ua.kobzev.theatre.service;

import java.util.List;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

/**
 * 
 * @author kkobziev
 * 
 *
 * 
 */

public interface DiscountService {
	double getDiscount(User user, List<Ticket> ticketsList);

}
