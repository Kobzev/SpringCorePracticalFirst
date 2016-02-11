package ua.kobzev.theatre.strategy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.UserService;
import ua.kobzev.theatre.strategy.DiscountStrategy;

@Component
public class PurchasedStrategy implements DiscountStrategy {

	private int discountForEveryTicket = 10;
	private double percent = 50.0;

	@Autowired
	private UserService userService;

	@Override
	public double getDiscount(User user, List<Ticket> ticketsList) {
		int bookedTickets = userService.getBookedTickets(user).size();

		int firstDiscount = bookedTickets / discountForEveryTicket + 1;
		int lastDiscount = (bookedTickets + ticketsList.size()) / discountForEveryTicket;

		if (firstDiscount * discountForEveryTicket > bookedTickets + ticketsList.size())
			return 0;

		double discount = 0.0;

		for (int i = firstDiscount; i <= lastDiscount; i++) {

			discount += ticketsList.get(i * discountForEveryTicket - bookedTickets - 1).getPrice() * percent / 100;
		}
		return discount;
	}

	public void setDiscountForEveryTicket(int discountForEveryTicket) {
		this.discountForEveryTicket = discountForEveryTicket;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "Purchased discount";
	}
}
