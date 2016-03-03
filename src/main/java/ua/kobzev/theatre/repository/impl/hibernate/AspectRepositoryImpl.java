package ua.kobzev.theatre.repository.impl.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkobziev on 2/16/16.
 */

public class AspectRepositoryImpl implements AspectRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map<Event, Integer> getAccessByName() {
        // TODO
        return new HashMap<>();
    }

    @Override
    public void saveAccessByName(Event event) {
        // TODO
    }

    @Override
    public Map<Event, Integer> getPriceQueried() {
        // TODO
        return new HashMap<>();
    }

    @Override
    public void savePriceQueried(Event event) {
        // TODO
    }

    @Override
    public Map<Event, Integer> getBookedTicket() {
        // TODO
        return new HashMap<>();
    }

    @Override
    public void saveBookedTicket(Event event) {
        // TODO
    }

    @Override
    public Map<String, Integer> getTotalDiscounts() {
        // TODO
        return new HashMap<>();
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        // TODO
    }

    @Override
    public Map<User, Integer> getInfoAboutTotalDiscountForUser() {
        // TODO
        return new HashMap<>();
    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        // TODO
    }
}
