package ua.kobzev.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.service.AspectService;

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


        System.out.println("3. bookedTicket");
        Map<Event, Integer> bookedTicket = aspectRepository.getBookedTicket();
        bookedTicket.forEach((event, integer) -> System.out.println(event + " " + integer + " times"));

        System.out.println("4. totalDiscount");


        System.out.println("5. discountForUser");

	}

    @Override
    public void saveAccessByName(Event event) {
        aspectRepository.saveAccessByName(event);
    }

    @Override
    public void saveBookedTicket(Event event) {
        aspectRepository.saveBookedTicket(event);
    }
}
