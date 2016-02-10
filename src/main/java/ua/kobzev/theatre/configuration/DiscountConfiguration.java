package ua.kobzev.theatre.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.kobzev.theatre.service.DiscountService;
import ua.kobzev.theatre.service.impl.DiscountServiceImpl;
import ua.kobzev.theatre.strategy.DiscountStrategy;
import ua.kobzev.theatre.strategy.impl.BirthdayStrategy;
import ua.kobzev.theatre.strategy.impl.PurchasedStrategy;

@Configuration
public class DiscountConfiguration {

	@Bean
	public DiscountService discountService() {
		DiscountServiceImpl discountService = new DiscountServiceImpl();

		List<DiscountStrategy> discountsStrategy = new ArrayList<>();
		discountsStrategy.add(new BirthdayStrategy());
		discountsStrategy.add(new PurchasedStrategy());

		discountService.setDiscountStrategy(discountsStrategy);

		return discountService;
	}
}
