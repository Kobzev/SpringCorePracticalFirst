package ua.kobzev.theatre.service;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;

/**
 * Created by kkobziev on 2/10/16.
 */
public interface AspectService {

    void printStatistic();
    void saveAccessByName(Event event);
    void savePriceQueried(Event event);
    void saveBookedTicket(Event event);
    void saveTotalDiscount(String strategy);
    void saveInfoAboutTotalDiscountForUser(User user);
}
