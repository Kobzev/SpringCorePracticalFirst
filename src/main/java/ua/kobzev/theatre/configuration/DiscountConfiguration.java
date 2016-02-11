package ua.kobzev.theatre.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountConfiguration {

	/*
	 * @Bean public DiscountService discountService(ApplicationContext context)
	 * { DiscountServiceImpl discountService = new DiscountServiceImpl();
	 * 
	 * List<DiscountStrategy> discountsStrategy = new ArrayList<>();
	 * discountsStrategy.add((DiscountStrategy)
	 * context.getBean("birthdayStrategy"));
	 * discountsStrategy.add((DiscountStrategy)
	 * context.getBean("purchasedStrategy"));
	 * 
	 * discountService.setDiscountStrategy(discountsStrategy);
	 * 
	 * return discountService; }
	 */
}
