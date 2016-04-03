package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kobzev.theatre.domain.Ticket;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.service.DiscountService;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

	@Autowired
	private List<DiscountStrategy> discountStrategy;

	public List<DiscountStrategy> getDiscountStrategy() {
		return discountStrategy;
	}

	public void setDiscountStrategy(List<DiscountStrategy> discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	@Override
	public double getDiscount(User user, List<Ticket> ticketsList) {
		List<Double> discountsList = new ArrayList<>();

		discountStrategy.forEach(strategy -> discountsList.add(strategy.getDiscount(user, ticketsList)));

		return discountsList.stream().max(Double::compareTo).orElse(0.0);
	}

}
