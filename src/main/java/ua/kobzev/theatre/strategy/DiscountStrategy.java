package ua.kobzev.theatre.strategy;

import java.util.List;

import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;

public interface DiscountStrategy {
	double getDiscount(User user, List<Ticket> ticketsList);
}
