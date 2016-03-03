package ua.kobzev.theatre.repository.impl.inmemory;

import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.repository.AspectRepository;
import ua.kobzev.theatre.strategy.DiscountStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkobziev on 2/10/16.
 */

public class AspectRepositoryImpl implements AspectRepository{

    private Map<Event, Integer> accessByName = new HashMap<>();
    private Map<Event, Integer> priceQueried = new HashMap<>();
    private Map<Event, Integer> bookedTicket = new HashMap<>();
    private Map<String, Integer> totalDiscount = new HashMap<>();
    private Map<User, Integer> usersDiscount = new HashMap<>();


    @Override
    public Map<Event, Integer> getAccessByName() {
        return new HashMap<>(accessByName);
    }

    @Override
    public void saveAccessByName(Event event) {
        Integer times = accessByName.get(event);
        accessByName.put(event, times==null? 1 : ++times);
    }

    @Override
    public Map<Event, Integer> getPriceQueried() {
        return new HashMap<>(priceQueried);
    }

    @Override
    public void savePriceQueried(Event event) {
        Integer times = priceQueried.get(event);
        priceQueried.put(event, times==null? 1 : ++times);
    }

    @Override
    public Map<Event, Integer> getBookedTicket() {
        return new HashMap<>(bookedTicket);
    }

    @Override
    public void saveBookedTicket(Event event) {
        Integer times = bookedTicket.get(event);
        bookedTicket.put(event, times==null? 1 : ++times);
    }

    @Override
    public Map<String, Integer> getTotalDiscounts() {
        return new HashMap<>(totalDiscount);
    }

    @Override
    public void saveTotalDiscount(DiscountStrategy strategy) {
        Integer times = totalDiscount.get(strategy.toString());
        totalDiscount.put(strategy.toString(), times==null? 1 : ++times);
    }

    @Override
    public Map<User, Integer> getInfoAboutTotalDiscountForUser() {
        return new HashMap<>(usersDiscount);
    }

    @Override
    public void saveInfoAboutTotalDiscountForUser(User user) {
        Integer times = usersDiscount.get(user);
        usersDiscount.put(user, times==null? 1 : ++times);
    }
}
