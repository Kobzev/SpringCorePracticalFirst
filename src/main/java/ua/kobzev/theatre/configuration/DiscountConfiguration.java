package ua.kobzev.theatre.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.kobzev.theatre.service.DiscountService;
import ua.kobzev.theatre.service.impl.DiscountServiceImpl;
import ua.kobzev.theatre.strategy.DiscountStrategy;
import ua.kobzev.theatre.strategy.impl.BirthdayStrategy;
import ua.kobzev.theatre.strategy.impl.PurchasedStrategy;

@Configuration
public class DiscountConfiguration {

	/*@Bean
	public DiscountService discountService(ApplicationContext context) {
		DiscountServiceImpl discountService = new DiscountServiceImpl();

		List<DiscountStrategy> discountsStrategy = new ArrayList<>();
		discountsStrategy.add((DiscountStrategy) context.getBean("birthdayStrategy"));
		discountsStrategy.add((DiscountStrategy) context.getBean("purchasedStrategy"));

		discountService.setDiscountStrategy(discountsStrategy);

		return discountService;
	}*/
}
