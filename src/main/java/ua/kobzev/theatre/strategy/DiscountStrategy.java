package ua.kobzev.theatre.strategy;

import java.util.Date;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;

public interface DiscountStrategy {
	double getDiscount(User user, Event event, Date date);
}
