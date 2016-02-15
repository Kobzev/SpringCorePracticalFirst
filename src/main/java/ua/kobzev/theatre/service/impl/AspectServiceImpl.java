package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.service.AspectService;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.Map;

/**
 * Created by kkobziev on 2/10/16.
 */

@Service
public class AspectServiceImpl implements AspectService {

    @Autowired
    private AspectRepository aspectRepository;

	@Override
	public void printStatistic() {

		System.out.println("1. accessedByName");
        Map<Event, Integer> accessByName = aspectRepository.getAccessByName();
        accessByName.forEach((event, integer) -> System.out.println(event + " " + integer + " times"));

		System.out.println("2. priceQueried");
        Map<Event, Integer> priceQueried = aspectRepository.getPriceQueried();
        priceQueried.forEach((event, integer) -> System.out.println(event + " " + integer + " times"));


        System.out.println("3. bookedTicket");
        Map<Event, Integer> bookedTicket = aspectRepository.getBookedTicket();
        bookedTicket.forEach((event, integer) -> System.out.println(event + " " + integer + " times"));

        System.out.println("4. totalDiscount");
        Map<String, Integer> totalDiscount = aspectRepository.getTotalDiscounts();
        totalDiscount.forEach((strategy, integer) -> System.out.println(strategy + " " + integer + " times"));


        System.out.println("5. discountForUser");
        Map<User, Integer> discountForUser = aspectRepository.getInfoAboutTotalDiscountForUser();
        discountForUser.forEach((user, integer) -> System.out.println(user + " " + integer + " times"));

	}

    @Override
    public void saveAccessByName(Event event) {
        aspectRepository.saveAccessByName(event);
    }

    @Override
    public void savePriceQueried(Event event) {
        aspectRepository.savePriceQueried(event);
    }

    @Override
    public void saveBookedTicket(Event event) {
        aspectRepository.saveBookedTicket(event);
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        aspectRepository.saveTotalDiscount(strategy);
    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        aspectRepository.saveInfoAboutTotalDiscountForUser(user);
    }
}
