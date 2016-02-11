package ua.kobzev.theatre.strategy.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.strategy.DiscountStrategy;

@Component
public class BirthdayStrategy implements DiscountStrategy {

	private double percent = 5.0;

	public boolean checkBirthDay(LocalDateTime birthDay) {
		LocalDateTime today = LocalDateTime.now();

		if (today.getMonth() == birthDay.getMonth() && today.getDayOfMonth() == birthDay.getDayOfMonth())
			return true;

		return false;
	}

	@Override
	public double getDiscount(User user, List<Ticket> ticketsList) {

		if (user == null || ticketsList == null || ticketsList.size() == 0)
			return 0;

		if (checkBirthDay(user.getBirthDay())) {
			double price = ticketsList.stream()
									.map(x -> x.getPrice())
									.reduce((x, y) -> x + y)
									.get();

			return price * percent / 100;
		}

		return 0;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "Birthday discount";
	}
}
