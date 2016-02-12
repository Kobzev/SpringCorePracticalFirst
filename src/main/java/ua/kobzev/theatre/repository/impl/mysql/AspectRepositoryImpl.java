package ua.kobzev.theatre.repository.impl.mysql;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.Map;

/**
 * Created by kkobziev on 2/11/16.
 */

public class AspectRepositoryImpl implements AspectRepository{

    @Override
    public Map<Event, Integer> getAccessByName() {
        // TODO
        return null;
    }

    @Override
    public void saveAccessByName(Event event) {
        // TODO
    }

    @Override
    public Map<Event, Integer> getPriceQueried() {
        // TODO
        return null;
    }

    @Override
    public void savePriceQueried(Event event) {
        // TODO
    }

    @Override
    public Map<Event, Integer> getBookedTicket() {
        // TODO
        return null;
    }

    @Override
    public void saveBookedTicket(Event event) {
        // TODO

    }

    @Override
    public Map<DiscountStrategy, Integer> getTotalDiscounts() {
        // TODO
        return null;
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        // TODO

    }

    @Override
    public Map<User, Integer> getInfoAboutTotalDiscountForUser() {
        // TODO
        return null;
    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        // TODO

    }
}
