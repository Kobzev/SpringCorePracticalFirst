package ua.kobzev.theatre.repository;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.Map;

/**
 * Created by kkobziev on 2/10/16.
 */
public interface AspectRepository {

    Map<Event, Integer> getAccessByName();
    void saveAccessByName(Event event);

    Map<Event, Integer> getPriceQueried();
    void savePriceQueried(Event event);

    Map<Event, Integer> getBookedTicket();
    void saveBookedTicket(Event event);

    Map<String, Integer> getTotalDiscounts();
    void saveTotalDiscount(DiscountStrategy strategy);

    Map<User, Integer> getInfoAboutTotalDiscountForUser();
    void saveInfoAboutTotalDiscountForUser(User user);
}
