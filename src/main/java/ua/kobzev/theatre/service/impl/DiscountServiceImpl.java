package ua.kobzev.theatre.service.impl;

import java.util.Date;
import java.util.List;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.DiscountService;
import ua.kobzev.theatre.strategy.DiscountStrategy;

public class DiscountServiceImpl implements DiscountService {

	private List<DiscountStrategy> discountStrategy;

	public List<DiscountStrategy> getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(List<DiscountStrategy> discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	@Override
	public double getDiscount(User user, Event event, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

}
